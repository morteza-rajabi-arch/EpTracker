
package com.example.eptracker.data.repository

import com.example.eptracker.data.local.dao.FolderDao
import com.example.eptracker.data.local.entity.FolderEntity
import kotlinx.coroutines.flow.Flow

class FolderRepository(
    private val folderDao: FolderDao
) {

    fun getAllFolders(): Flow<List<FolderEntity>> {
        return folderDao.getAllFolders()
    }

    suspend fun getFolderById(folderId: Long): FolderEntity? {
        return folderDao.getFolderById(folderId)
    }

    suspend fun addFolder(name: String): Long {
        val folder = FolderEntity(name = name.trim())
        return folderDao.insert(folder)
    }

    suspend fun updateFolder(folder: FolderEntity) {
        folderDao.update(folder)
    }

    suspend fun deleteFolder(folder: FolderEntity) {
        folderDao.delete(folder)
    }

    fun getFolderCount(): Flow<Int> {
        return folderDao.getFolderCount()
    }
}