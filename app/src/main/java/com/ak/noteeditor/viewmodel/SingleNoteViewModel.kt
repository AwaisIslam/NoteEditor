package com.ak.noteeditor.viewmodel

import androidx.lifecycle.ViewModel
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.repository.NoteRepository

class SingleNoteViewModel(
    private val repository: NoteRepository,
    private val noteId: String?
): ViewModel() {

    fun getNoteModel() = repository.find(noteId)

    fun saveNote(model: NoteModel) {
        repository.save(model)
    }

    fun deleteNote(model: NoteModel) {
        repository.deleteNote(model)
    }
}