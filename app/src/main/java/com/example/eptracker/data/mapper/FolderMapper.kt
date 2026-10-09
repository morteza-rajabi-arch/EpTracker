
package com.example.eptracker.data.mapper

import com.example.eptracker.data.local.entity.FolderEntity
import com.example.eptracker.data.model.Folder

fun FolderEntity.toFolder(): Folder {
    return Folder(
        id = id,
        name = name,
        createdAt = createdAt
    )
}

fun Folder.toEntity(): FolderEntity {
    return FolderEntity(
        id = id,
        name = name,
        createdAt = createdAt
    )
}