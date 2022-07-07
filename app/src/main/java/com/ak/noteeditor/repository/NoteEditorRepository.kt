package com.ak.noteeditor.repository

import com.ak.noteeditor.data.NoteEditorModel

class NoteEditorRepository {

    var items = emptyList<NoteEditorModel>()

    fun save(model: NoteEditorModel) {
        items = if (items.any { it.id == model.id }) {
            items.map { if (it.id == model.id) model else it }
        } else {
            items + model
        }
    }

    fun deleteNote(model: NoteEditorModel) {
        items = items.filter { it.id != model.id }
    }

    fun find(noteId: String?) = items.find{ it.id == noteId }

}