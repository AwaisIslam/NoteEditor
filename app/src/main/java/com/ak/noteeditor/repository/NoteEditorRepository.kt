package com.ak.noteeditor.repository

import com.ak.noteeditor.data.NoteEditorModel

class NoteEditorRepository {

    var items = listOf(
        NoteEditorModel(
            description = "note 1",
            isCompleted = true,
            notes = "https://www.google.com"
        ),
        NoteEditorModel(
            description = "note 2"
        ),
        NoteEditorModel(
            description = "note 3",
            notes = "https://www.google.com"
        )
    )

    fun save(model: NoteEditorModel) {
        items = if (items.any { it.id == model.id }) {
            items.map { if (it.id == model.id) model else it }
        } else {
            items + model
        }
    }

    fun find(noteId: String) = items.find{ it.id == noteId }

}