package com.ak.noteeditor.viewmodel

import androidx.lifecycle.ViewModel
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.repository.NoteRepository

class NoteListViewModel(private val repository: NoteRepository): ViewModel() {
    val items = repository.items

    fun getNoteItems() = repository.items

    fun save(model: NoteModel) {
        repository.save(model)
    }
}