
package com.example.eptracker.presentation.screens.details
import androidx.compose.material3.OutlinedTextField
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.eptracker.data.local.entity.MediaEntity
import com.example.eptracker.util.PosterStorage

@Composable
fun MediaDetailScreen(
    media: MediaEntity,
    onIncrementEpisode: () -> Unit,
    onDecrementEpisode: () -> Unit,
    onDelete: () -> Unit,
    onBack: () -> Unit,
    onUpdateMedia: (MediaEntity) -> Unit
) {
    val context = LocalContext.current
    var editedTitle by remember(media.id) {
        mutableStateOf(media.title)
    }
    var menuExpanded by remember { mutableStateOf(false) }
    var posterError by remember { mutableStateOf(false) }

    val statuses = listOf(
        "PLANNED" to "در انتظار تماشا",
        "IN_PROGRESS" to "در حال تماشا",
        "COMPLETED" to "تکمیل‌شده",
        "PAUSED" to "متوقف‌شده"
    )

    val currentStatusLabel =
        statuses.find { it.first == media.status }?.second ?: media.status

    val posterPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { selectedUri ->
        if (selectedUri != null) {
            val savedPath = PosterStorage.savePoster(
                context = context,
                sourceUri = selectedUri
            )

            if (savedPath != null) {
                posterError = false
                onUpdateMedia(
                    media.copy(
                        posterUri = savedPath,
                        updatedAt = System.currentTimeMillis()
                    )
                )
            } else {
                posterError = true
            }
        }
    }

    val posterBitmap = remember(media.posterUri) {
        media.posterUri?.let { path ->
            BitmapFactory.decodeFile(path)?.asImageBitmap()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = editedTitle,
            onValueChange = { editedTitle = it },
            label = { Text("نام محتوا") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                val newTitle = editedTitle.trim()

                if (newTitle.isNotBlank()) {
                    onUpdateMedia(
                        media.copy(
                            title = newTitle,
                            updatedAt = System.currentTimeMillis()
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ذخیره نام")
        }

        if (posterBitmap != null) {
            Image(
                bitmap = posterBitmap,
                contentDescription = "پوستر ${media.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Fit
            )
        } else {
            Text("هنوز پوستری انتخاب نشده است.")
        }

        Button(
            onClick = { posterPicker.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (media.posterUri == null) {
                    "انتخاب پوستر از گالری"
                } else {
                    "تغییر پوستر"
                }
            )
        }

        if (posterError) {
            Text(
                text = "ذخیره پوستر ناموفق بود. دوباره تلاش کن.",
                color = MaterialTheme.colorScheme.error
            )
        }

        Text("نوع: ${media.type}")
        Text("وضعیت فعلی: $currentStatusLabel")

        TextButton(onClick = { menuExpanded = true }) {
            Text("تغییر وضعیت")
        }

        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false }
        ) {
            statuses.forEach { (statusValue, statusLabel) ->
                DropdownMenuItem(
                    text = { Text(statusLabel) },
                    onClick = {
                        onUpdateMedia(
                            media.copy(
                                status = statusValue,
                                updatedAt = System.currentTimeMillis()
                            )
                        )
                        menuExpanded = false
                    }
                )
            }
        }

        when (media.type) {
            "SERIES", "K_DRAMA", "ANIME" -> {
                Text("فصل فعلی: ${media.currentSeason}")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            if (media.currentSeason > 1) {
                                onUpdateMedia(
                                    media.copy(
                                        currentSeason = media.currentSeason - 1,
                                        updatedAt = System.currentTimeMillis()
                                    )
                                )
                            }
                        },
                        enabled = media.currentSeason > 1
                    ) {
                        Text("− فصل")
                    }

                    Button(
                        onClick = {
                            onUpdateMedia(
                                media.copy(
                                    currentSeason = media.currentSeason + 1,
                                    updatedAt = System.currentTimeMillis()
                                )
                            )
                        }
                    ) {
                        Text("+ فصل")
                    }
                }

                Text("آخرین قسمت دیده‌شده: ${media.lastWatchedEpisode}")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDecrementEpisode,
                        enabled = media.lastWatchedEpisode > 0
                    ) {
                        Text("− قسمت")
                    }

                    Button(onClick = onIncrementEpisode) {
                        Text("+ قسمت")
                    }
                }
            }

            "BOOK" -> {
                Text("صفحه فعلی: ${media.currentPage}")
            }

            "MOVIE" -> {
                Text(
                    if (media.isMovieWatched) {
                        "فیلم دیده شده"
                    } else {
                        "فیلم هنوز دیده نشده"
                    }
                )
            }
        }

        if (media.description.isNotBlank()) {
            Text("توضیحات: ${media.description}")
        }

        OutlinedButton(
            onClick = onDelete,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("حذف محتوا")
        }

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("بازگشت")
        }
    }
}
