package uy.eliasworks.miecosystem.persistence

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import uy.eliasworks.miecosystem.persistence.probe.room.PersistenceProbeDao
import uy.eliasworks.miecosystem.persistence.probe.room.PersistenceProbeEntity

@Database(entities = [PersistenceProbeEntity::class], version = 1, exportSchema = true)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun persistenceProbeDao(): PersistenceProbeDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>
