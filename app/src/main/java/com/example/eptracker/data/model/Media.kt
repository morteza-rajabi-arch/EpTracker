
package com.example.eptracker.data.model

data class Media(
    val id: Long = 0,
    val title: String,
    val type: MediaType,
    val status: MediaStatus = MediaStatus.PLANNED,
    val description: String = "",
    val posterUri: String? = null,
    val currentSeason: Int = 1,
    val lastWatchedEpisode: Int = 0,
    val currentPage: Int = 0,
    val isMovieWatched: Boolean = false,
    val folderId: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)