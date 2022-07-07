package com.ak.noteeditor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.repository.NoteEditorRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class NoteListViewState(
    val items: List<NoteModel> = listOf()
)

class NoteListViewModel(private val repository: NoteEditorRepository): ViewModel() {

    val states = repository.notesList()
        .map { NoteListViewState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, NoteListViewState())

    fun save(model: NoteModel) {
        repository.save(model)
    }
}