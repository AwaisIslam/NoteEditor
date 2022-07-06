package com.ak.noteeditor.viewmodel

import androidx.lifecycle.ViewModel
import com.ak.noteeditor.data.NoteEditorModel
import com.ak.noteeditor.repository.NoteEditorRepository

class NoteListViewModel(private val repository: NoteEditorRepository): ViewModel() {
    val items = repository.items

    fun getNoteItems() = repository.items

    fun save(model: NoteEditorModel) {
        repository.save(model)
    }
}