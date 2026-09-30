package uy.eliasworks.miecosystem.persistence

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.io.File

class PersistenceProbeDiskTest {

    private lateinit var context: Context
    private lateinit var dbPath: File

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        dbPath = context.getDatabasePath("mi-ecosystem.db")
        if (dbPath.exists()) {
            dbPath.delete()
        }
    }

    @After
    fun teardown() {
        if (dbPath.exists()) {
            dbPath.delete()
        }
    }

    @Test
    fun diskPersistenceAfterReopen_Android() = runBlocking {
        var db = getAppDatabase(context)
        var dao = db.persistenceProbeDao()

        dao.upsert(uy.eliasworks.miecosystem.persistence.probe.PersistenceProbeEntity(id = 1L, value = "Test Persistence"))
        assertEquals("Test Persistence", dao.getById(1L)?.value)
        
        var all = dao.getAll()
        assertEquals(1, all.size)

        db.close()

        // Reopen same database file
        db = getAppDatabase(context)
        dao = db.persistenceProbeDao()

        // Read again and prove it persisted on disk
        val recovered = dao.getById(1L)
        assertEquals("Test Persistence", recovered?.value)
        
        dao.deleteById(1L)
        assertNull(dao.getById(1L))
        
        db.close()
    }
}
