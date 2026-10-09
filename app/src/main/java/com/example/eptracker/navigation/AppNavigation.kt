
package com.example.eptracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eptracker.data.local.entity.MediaEntity
import com.example.eptracker.presentation.screens.add.AddMediaScreen
import com.example.eptracker.presentation.screens.details.MediaDetailScreen
import com.example.eptracker.presentation.screens.library.HomeScreen
import com.example.eptracker.presentation.screens.search.SearchScreen
import com.example.eptracker.presentation.screens.folders.FoldersScreen
import com.example.eptracker.presentation.screens.stats.StatsScreen
import com.example.eptracker.presentation.screens.settings.SettingsScreen
import com.example.eptracker.viewmodel.MediaViewModel
import com.example.eptracker.viewmodel.FolderViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
@Composable
fun AppNavigation(
    mediaViewModel: MediaViewModel,
    folderViewModel: FolderViewModel,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
)  {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Library.route
    ) {
        composable(Screen.Library.route) {
            HomeScreen(
                viewModel = mediaViewModel,
                onAddMedia = {
                    navController.navigate(Screen.AddMedia.route)
                },
                onMediaClick = { mediaId ->
                    navController.navigate(
                        Screen.MediaDetails.createRoute(mediaId)
                    )
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                },
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }

        composable(Screen.AddMedia.route) {
            AddMediaScreen(
                onSave = { media ->
                    mediaViewModel.addMedia(media)
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.MediaDetails.route,
            arguments = listOf(
                navArgument("mediaId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getLong("mediaId") ?: 0L


            val media by mediaViewModel
                .getMediaById(mediaId)
                .collectAsState(initial = null)

            media?.let { currentMedia ->

                MediaDetailScreen(
                    media = currentMedia,
                    onIncrementEpisode = {
                        mediaViewModel.incrementEpisode(mediaId)
                    },
                    onDecrementEpisode = {
                        mediaViewModel.decrementEpisode(mediaId)
                    },
                    onUpdateMedia = { updatedMedia ->
                        mediaViewModel.updateMedia(updatedMedia)
                    },
                    onDelete = {
                        mediaViewModel.deleteMedia(currentMedia)
                        navController.popBackStack()
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
                

            }
        }

        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = mediaViewModel,
                onMediaClick = { mediaId ->
                    navController.navigate(
                        Screen.MediaDetails.createRoute(mediaId)
                    )
                }
            )
        }

        composable(Screen.Folders.route) {
            FoldersScreen(
                viewModel = folderViewModel,
                onFolderClick = { folderId ->
                    navController.navigate(
                        Screen.FolderDetails.createRoute(folderId)
                    )
                }
            )
        }

        composable(Screen.Stats.route) {
            StatsScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
    }
}