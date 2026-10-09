
package com.example.eptracker.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eptracker.ui.components.MediaCard
import com.example.eptracker.viewmodel.MediaViewModel

@Composable
fun SearchScreen(
    viewModel: MediaViewModel,
    onMediaClick: (Long) -> Unit
) {
    var query by remember { mutableStateOf("") }

    val results by viewModel
        .searchMedia(query)
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("جست‌وجو")

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("نام سریال، فیلم یا کتاب") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = results,
                key = { it.id }
            ) { media ->
                MediaCard(
                    media = media,
                    onClick = { onMediaClick(media.id) },
                    onIncrementEpisode = {},
                    onDecrementEpisode = {}
                )
            }
        }
    }
}