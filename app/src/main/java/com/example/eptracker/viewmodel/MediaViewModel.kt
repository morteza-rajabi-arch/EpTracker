
package com.example.eptracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eptracker.data.local.entity.MediaEntity
import com.example.eptracker.data.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MediaViewModel(
    private val repository: MediaRepository
) : ViewModel() {

    val allMedia: Flow<List<MediaEntity>> = repository.getAllMedia()

    fun searchMedia(query: String): Flow<List<MediaEntity>> {
        return repository.searchMedia(query)
    }
    fun getMediaById(mediaId: Long): Flow<MediaEntity?> =
        repository.getMediaById(mediaId)

    fun getMediaByType(type: String): Flow<List<MediaEntity>> {
        return repository.getMediaByType(type)
    }

    fun getMediaByStatus(status: String): Flow<List<MediaEntity>> {
        return repository.getMediaByStatus(status)
    }

    fun getMediaByFolder(folderId: Long): Flow<List<MediaEntity>> {
        return repository.getMediaByFolder(folderId)
    }

    fun addMedia(media: MediaEntity) {
        viewModelScope.launch {
            repository.addMedia(media)
        }
    }

    fun updateMedia(media: MediaEntity) {
        viewModelScope.launch {
            repository.updateMedia(media)
        }
    }

    fun deleteMedia(media: MediaEntity) {
        viewModelScope.launch {
            repository.deleteMedia(media)
        }
    }

    fun incrementEpisode(mediaId: Long) {
        viewModelScope.launch {
            repository.incrementEpisode(mediaId)
        }
    }

    fun decrementEpisode(mediaId: Long) {
        viewModelScope.launch {
            repository.decrementEpisode(mediaId)
        }
    }
}