
package com.example.eptracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eptracker.data.local.dao.FolderDao
import com.example.eptracker.data.local.dao.MediaDao
import com.example.eptracker.data.local.entity.FolderEntity
import com.example.eptracker.data.local.entity.MediaEntity

@Database(
    entities = [
        MediaEntity::class,
        FolderEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mediaDao(): MediaDao

    abstract fun folderDao(): FolderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "eptracker_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}