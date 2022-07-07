package com.ak.noteeditor.viewmodel

import androidx.lifecycle.ViewModel
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.repository.NoteEditorRepository

class SingleNoteViewModel(
    private val repository: NoteEditorRepository,
    private val noteId: String?
): ViewModel() {

    fun saveNote(model: NoteModel) {
        repository.save(model)
    }

    fun deleteNote(model: NoteModel) {
        repository.deleteNote(model)
    }
}