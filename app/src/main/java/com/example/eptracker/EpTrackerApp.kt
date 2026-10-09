
package com.example.eptracker

import android.app.Application
import com.example.eptracker.data.local.AppDatabase
import com.example.eptracker.data.repository.FolderRepository
import com.example.eptracker.data.repository.MediaRepository

class EpTrackerApp : Application() {

    val database by lazy {
        AppDatabase.getDatabase(this)
    }

    val mediaRepository by lazy {
        MediaRepository(database.mediaDao())
    }

    val folderRepository by lazy {
        FolderRepository(database.folderDao())
    }
}