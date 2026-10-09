
package com.example.eptracker.presentation.model

data class MediaUiModel(
    val id: Long,
    val title: String,
    val type: String,
    val status: String,
    val posterUri: String?,
    val progressText: String,
    val folderName: String?
)