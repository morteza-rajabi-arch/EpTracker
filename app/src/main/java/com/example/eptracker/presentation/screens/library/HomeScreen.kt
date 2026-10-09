
package com.example.eptracker.presentation.screens.library
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Switch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eptracker.ui.components.MediaCard
import com.example.eptracker.viewmodel.MediaViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.height

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MediaViewModel,
    onAddMedia: () -> Unit,
    onMediaClick: (Long) -> Unit,
    onSettingsClick: () -> Unit,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val allMedia by viewModel.allMedia.collectAsState(initial = emptyList())

    var selectedStatus by remember { mutableStateOf("ALL") }

    val mediaList = when (selectedStatus) {
        "PLANNED" -> allMedia.filter { it.status == "PLANNED" }
        "IN_PROGRESS" -> allMedia.filter { it.status == "IN_PROGRESS" }
        "PAUSED" -> allMedia.filter { it.status == "PAUSED" }
        "COMPLETED" -> allMedia.filter { it.status == "COMPLETED" }
        else -> allMedia
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()

                    .height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "EpTracker",
                    style = MaterialTheme.typography.titleLarge
                )

                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = onThemeChange,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                IconButton(
                    onClick = onSettingsClick,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "تنظیمات",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        floatingActionButton = {
            Button(onClick = onAddMedia) {
                Text("＋ افزودن محتوا")
            }
        }
    ) { innerPadding ->
        if (allMedia.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "کتابخانه‌ات هنوز خالیه",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "اولین سریال، انیمه، فیلم یا کتابت رو اضافه کن."
                )

                Button(
                    onClick = onAddMedia,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("افزودن اولین محتوا")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    val categories = listOf(
                        "ALL" to "همه",
                        "PLANNED" to "شروع نشده",
                        "IN_PROGRESS" to "در حال تماشا",
                        "PAUSED" to "متوقف شده",
                        "COMPLETED" to "تکمیل شده"
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(categories) { category ->
                            FilterChip(
                                selected = selectedStatus == category.first,
                                onClick = {
                                    selectedStatus = category.first
                                },
                                label = {
                                    Text(category.second)
                                }
                            )
                        }
                    }
                }

                if (mediaList.isEmpty()) {
                    item {
                        Text(
                            text = "محتوایی در این دسته وجود ندارد.",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    items(
                        items = mediaList,
                        key = { it.id }
                    ) { media ->
                        MediaCard(
                            media = media,
                            onClick = { onMediaClick(media.id) },
                            onIncrementEpisode = {
                                viewModel.incrementEpisode(media.id)
                            },
                            onDecrementEpisode = {
                                viewModel.decrementEpisode(media.id)
                            }
                        )
                    }
                }
            }
        }
    }
}