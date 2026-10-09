
package com.example.eptracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,
    val type: String,
    val status: String = "PLANNED",
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