package uy.eliasworks.miecosystem.persistence

import androidx.room3.Room
import androidx.room3.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getAppDatabase(): AppDatabase {
    val dbFilePath = NSHomeDirectory() + "/mi-ecosystem.db"
    return getRoomDatabase(
        Room.databaseBuilder<AppDatabase>(
            name = dbFilePath
        )
    )
}
