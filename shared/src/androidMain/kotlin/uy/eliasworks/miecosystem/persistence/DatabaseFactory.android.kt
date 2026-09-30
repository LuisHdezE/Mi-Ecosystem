package uy.eliasworks.miecosystem.persistence

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase

fun getAppDatabase(context: Context): AppDatabase {
    val dbFile = context.getDatabasePath("mi-ecosystem.db")
    return getRoomDatabase(
        Room.databaseBuilder<AppDatabase>(
            context = context.applicationContext,
            name = dbFile.absolutePath
        )
    )
}
