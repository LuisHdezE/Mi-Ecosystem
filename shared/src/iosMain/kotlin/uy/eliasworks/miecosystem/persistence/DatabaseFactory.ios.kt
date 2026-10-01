package uy.eliasworks.miecosystem.persistence

import androidx.room3.Room
import androidx.room3.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun getAppDatabase(): AppDatabase {
    val paths = NSSearchPathForDirectoriesInDomains(
        NSApplicationSupportDirectory,
        NSUserDomainMask,
        true
    )
    val appSupportDir = paths.firstOrNull() as? String
        ?: error("Unable to resolve Application Support directory on iOS.")

    val fileManager = NSFileManager.defaultManager
    if (!fileManager.fileExistsAtPath(appSupportDir)) {
        val created = fileManager.createDirectoryAtPath(
            appSupportDir,
            withIntermediateDirectories = true,
            attributes = null,
            error = null
        )
        if (!created) {
            error("Failed to create Application Support directory: $appSupportDir")
        }
    }

    val dbFilePath = "$appSupportDir/mi-ecosystem.db"
    return getRoomDatabase(
        Room.databaseBuilder<AppDatabase>(
            name = dbFilePath
        )
    )
}
