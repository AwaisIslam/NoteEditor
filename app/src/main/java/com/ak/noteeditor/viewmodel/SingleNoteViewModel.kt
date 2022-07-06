package com.ak.noteeditor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.repository.NoteEditorRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SingleNoteViewState(
    val item: NoteModel? = null
)

class SingleNoteViewModel(
    private val repository: NoteEditorRepository,
    private val noteId: String?
) : ViewModel() {

    val states = repository.find(noteId)
        .map { SingleNoteViewState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SingleNoteViewState())

    fun getNoteModel() = repository.find(noteId)

    fun saveNote(model: NoteModel) {
        viewModelScope.launch {

            repository.save(model)
        }
    }

    fun deleteNote(model: NoteModel) {
        viewModelScope.launch {
            repository.deleteNote(model)
        }
    }
}