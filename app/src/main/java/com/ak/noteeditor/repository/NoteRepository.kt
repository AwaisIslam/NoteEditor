package com.ak.noteeditor.repository

import com.ak.noteeditor.data.NoteModel

class NoteRepository {

    var items = emptyList<NoteModel>()

    fun save(model: NoteModel) {
        items = if (items.any { it.id == model.id }) {
            items.map { if (it.id == model.id) model else it }
        } else {
            items + model
        }
    }

    fun deleteNote(model: NoteModel) {
        items = items.filter { it.id != model.id }
    }

    fun find(noteId: String?) = items.find{ it.id == noteId }

}