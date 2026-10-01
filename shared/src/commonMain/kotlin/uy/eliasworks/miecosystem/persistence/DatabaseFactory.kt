package uy.eliasworks.miecosystem.persistence

import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

import uy.eliasworks.miecosystem.persistence.migration.MIGRATION_1_2

fun getRoomDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
        .addMigrations(MIGRATION_1_2)
        .setDriver(BundledSQLiteDriver())
        .build()
}
