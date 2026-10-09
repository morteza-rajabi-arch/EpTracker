
package com.example.eptracker.navigation

sealed class Screen(val route: String) {

    data object Library : Screen("library")

    data object AddMedia : Screen("add_media")

    data object MediaDetails : Screen("media_details/{mediaId}") {
        fun createRoute(mediaId: Long): String {
            return "media_details/$mediaId"
        }
    }

    data object Search : Screen("search")

    data object Folders : Screen("folders")

    data object FolderDetails : Screen("folder_details/{folderId}") {
        fun createRoute(folderId: Long): String {
            return "folder_details/$folderId"
        }
    }

    data object Stats : Screen("stats")

    data object Settings : Screen("settings")
}