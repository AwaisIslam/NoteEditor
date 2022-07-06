package com.ak.noteeditor.viewmodel

import androidx.lifecycle.ViewModel
import com.ak.noteeditor.repository.NoteEditorRepository

class SingleNoteViewModel(
    private val repository: NoteEditorRepository,
    private val noteId: String
): ViewModel() {

    fun getNoteModel() = repository.find(noteId)
}