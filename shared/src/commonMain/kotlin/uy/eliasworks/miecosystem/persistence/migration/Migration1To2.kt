package uy.eliasworks.miecosystem.persistence.migration

import androidx.room3.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

val MIGRATION_1_2 = object : Migration(1, 2) {
    override suspend fun migrate(connection: SQLiteConnection) {
        connection.execSQL("ALTER TABLE persistence_probe ADD COLUMN created_at_epoch_ms INTEGER NOT NULL DEFAULT 0")
    }
}
