
package com.example.eptracker.data.mapper

import com.example.eptracker.data.local.entity.MediaEntity
import com.example.eptracker.data.model.Media
import com.example.eptracker.data.model.MediaStatus
import com.example.eptracker.data.model.MediaType

fun MediaEntity.toMedia(): Media {
    return Media(
        id = id,
        title = title,
        type = runCatching { MediaType.valueOf(type) }
            .getOrDefault(MediaType.SERIES),
        status = runCatching { MediaStatus.valueOf(status) }
            .getOrDefault(MediaStatus.PLANNED),
        description = description,
        posterUri = posterUri,
        currentSeason = currentSeason,
        lastWatchedEpisode = lastWatchedEpisode,
        currentPage = currentPage,
        isMovieWatched = isMovieWatched,
        folderId = folderId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Media.toEntity(): MediaEntity {
    return MediaEntity(
        id = id,
        title = title,
        type = type.name,
        status = status.name,
        description = description,
        posterUri = posterUri,
        currentSeason = currentSeason,
        lastWatchedEpisode = lastWatchedEpisode,
        currentPage = currentPage,
        isMovieWatched = isMovieWatched,
        folderId = folderId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}