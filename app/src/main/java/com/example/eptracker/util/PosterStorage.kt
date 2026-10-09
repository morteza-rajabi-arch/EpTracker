package com.example.eptracker.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

object PosterStorage {

    fun savePoster(
        context: Context,
        sourceUri: Uri
    ): String? {
        return try {
            val postersDirectory = File(
                context.filesDir,
                "posters"
            )

            if (!postersDirectory.exists()) {
                postersDirectory.mkdirs()
            }

            val destinationFile = File(
                postersDirectory,
                "${UUID.randomUUID()}.jpg"
            )

            val inputStream = context.contentResolver
                .openInputStream(sourceUri)
                ?: return null

            inputStream.use { input ->
                FileOutputStream(destinationFile).use { output ->
                    input.copyTo(output)
                }
            }

            destinationFile.absolutePath
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }
}
