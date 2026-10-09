
package com.example.eptracker.presentation.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eptracker.data.local.entity.MediaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMediaScreen(
    onSave: (MediaEntity) -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("SERIES") }
    var season by remember { mutableStateOf("1") }
    var episode by remember { mutableStateOf("0") }
    var expanded by remember { mutableStateOf(false) }

    val types = listOf(
        "SERIES",
        "K_DRAMA",
        "ANIME"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("افزودن محتوا")

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("عنوان محتوا") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = type,
                onValueChange = {},
                readOnly = true,
                label = { Text("نوع محتوا") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                types.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            type = option
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = season,
            onValueChange = { season = it.filter(Char::isDigit) },
            label = { Text("شماره فصل") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = episode,
            onValueChange = { episode = it.filter(Char::isDigit) },
            label = { Text("آخرین قسمت تماشا شده") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    onSave(
                        MediaEntity(
                            title = title.trim(),
                            type = type,
                            currentSeason = season.toIntOrNull() ?: 1,
                            lastWatchedEpisode = episode.toIntOrNull() ?: 0
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ذخیره محتوا")
        }

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("بازگشت")
        }
    }
}

