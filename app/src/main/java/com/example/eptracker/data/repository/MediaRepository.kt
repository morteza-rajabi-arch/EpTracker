
package com.example.eptracker.data.repository

import com.example.eptracker.data.local.dao.MediaDao
import com.example.eptracker.data.local.entity.MediaEntity
import kotlinx.coroutines.flow.Flow

class MediaRepository(
    private val mediaDao: MediaDao
) {

    fun getAllMedia(): Flow<List<MediaEntity>> {
        return mediaDao.getAllMedia()
    }

    fun getMediaById(mediaId: Long): Flow<MediaEntity?> {
        return mediaDao.getMediaById(mediaId)
    }

    fun searchMedia(query: String): Flow<List<MediaEntity>> {
        return mediaDao.searchMedia(query)
    }

    fun getMediaByType(type: String): Flow<List<MediaEntity>> {
        return mediaDao.getMediaByType(type)
    }

    fun getMediaByStatus(status: String): Flow<List<MediaEntity>> {
        return mediaDao.getMediaByStatus(status)
    }

    fun getMediaByFolder(folderId: Long): Flow<List<MediaEntity>> {
        return mediaDao.getMediaByFolder(folderId)
    }

    suspend fun addMedia(media: MediaEntity): Long {
        return mediaDao.insert(media)
    }

    suspend fun updateMedia(media: MediaEntity) {
        mediaDao.update(media)
    }

    suspend fun deleteMedia(media: MediaEntity) {
        mediaDao.delete(media)
    }

    suspend fun incrementEpisode(mediaId: Long) {
        mediaDao.incrementEpisode(mediaId)
    }

    suspend fun decrementEpisode(mediaId: Long) {
        mediaDao.decrementEpisode(mediaId)
    }

    fun getMediaCount(): Flow<Int> {
        return mediaDao.getMediaCount()
    }
}