
package com.example.eptracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eptracker.data.local.entity.FolderEntity
import com.example.eptracker.data.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FolderViewModel(
    private val repository: FolderRepository
) : ViewModel() {

    val allFolders: Flow<List<FolderEntity>> = repository.getAllFolders()

    fun addFolder(name: String) {
        if (name.isBlank()) return

        viewModelScope.launch {
            repository.addFolder(name)
        }
    }

    fun updateFolder(folder: FolderEntity) {
        viewModelScope.launch {
            repository.updateFolder(folder)
        }
    }

    fun deleteFolder(folder: FolderEntity) {
        viewModelScope.launch {
            repository.deleteFolder(folder)
        }
    }
}