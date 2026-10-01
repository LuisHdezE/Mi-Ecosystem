package uy.eliasworks.miecosystem.persistence

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import uy.eliasworks.miecosystem.persistence.probe.room.RoomPersistenceProbeRepository

class PersistenceProbeMigrationTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        context.deleteDatabase("migration-test.db")
    }

    @After
    fun teardown() {
        context.deleteDatabase("migration-test.db")
    }

    @Test
    fun migrationV1ToV2PreservesData() = runBlocking {
        // STEP A: Create/open a database with schema v1.
        val dbPath = context.getDatabasePath("migration-test.db")
        val driver = BundledSQLiteDriver()
        val connection = driver.open(dbPath.absolutePath)
        
        // V1 Schema
        connection.execSQL("CREATE TABLE IF NOT EXISTS `persistence_probe` (`id` INTEGER NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'cf98123aef96204867717a3128ab0083')")
        
        // Insert legacy rows
        connection.execSQL("INSERT INTO persistence_probe (id, value) VALUES (1, 'legacy-alpha')")
        connection.execSQL("INSERT INTO persistence_probe (id, value) VALUES (2, 'legacy-beta')")
        
        connection.close()

        // STEP B: Open the SAME database file with AppDatabase v2 and MIGRATION_1_2.
        val builder = Room.databaseBuilder<AppDatabase>(
            context = context.applicationContext,
            name = dbPath.absolutePath
        )
        val db = getRoomDatabase(builder)
        val repository = RoomPersistenceProbeRepository(db.persistenceProbeDao())

        // STEP C: Verify legacy rows survive and have default 0L
        val row1 = repository.get(1L)
        assertEquals("legacy-alpha", row1?.value)
        assertEquals(0L, row1?.createdAtEpochMs)

        val row2 = repository.get(2L)
        assertEquals("legacy-beta", row2?.value)
        assertEquals(0L, row2?.createdAtEpochMs)

        // STEP D: Insert NEW row with non-zero createdAtEpochMs
        repository.save(id = 3L, value = "v2-gamma", createdAtEpochMs = 999L)
        val row3 = repository.get(3L)
        assertEquals("v2-gamma", row3?.value)
        assertEquals(999L, row3?.createdAtEpochMs)
        
        // Also legacy survive
        assertEquals("legacy-alpha", repository.get(1L)?.value)

        db.close()
        
        // Final reopen to prove persistence
        val dbReopen = getRoomDatabase(Room.databaseBuilder<AppDatabase>(
            context = context.applicationContext,
            name = dbPath.absolutePath
        ))
        val repoReopen = RoomPersistenceProbeRepository(dbReopen.persistenceProbeDao())
        assertEquals(999L, repoReopen.get(3L)?.createdAtEpochMs)
        assertEquals("legacy-beta", repoReopen.get(2L)?.value)
        dbReopen.close()
    }
}
