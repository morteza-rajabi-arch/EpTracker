
package com.example.eptracker.presentation.screens.folders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eptracker.data.local.entity.FolderEntity
import com.example.eptracker.viewmodel.FolderViewModel

@Composable
fun FoldersScreen(
    viewModel: FolderViewModel,
    onFolderClick: (Long) -> Unit
) {
    val folders by viewModel.allFolders.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var folderName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("پوشه‌های من")

        Button(
            onClick = {
                folderName = ""
                showDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("＋ ساخت پوشه")
        }

        if (folders.isEmpty()) {
            Text("هنوز پوشه‌ای نساخته‌ای.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = folders,
                    key = { it.id }
                ) { folder ->
                    FolderRow(
                        folder = folder,
                        onClick = { onFolderClick(folder.id) },
                        onDelete = { viewModel.deleteFolder(folder) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("ساخت پوشه جدید") },
            text = {
                OutlinedTextField(
                    value = folderName,
                    onValueChange = { folderName = it },
                    label = { Text("نام پوشه") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (folderName.isNotBlank()) {
                            viewModel.addFolder(folderName)
                            showDialog = false
                        }
                    }
                ) {
                    Text("ساختن")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("انصراف")
                }
            }
        )
    }
}

@Composable
private fun FolderRow(
    folder: FolderEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(onClick = onClick) {
            Text(folder.name)
        }

        TextButton(onClick = onDelete) {
            Text("حذف")
        }
    }
}