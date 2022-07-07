package com.ak.noteeditor.data

import java.time.Instant
import java.util.*

data class NoteEditorModel(
    val description: String,
    val id: String = UUID.randomUUID().toString(),
    val isCompleted: Boolean = false,
    val notes: String = "",
    val createdOn: Instant = Instant.now()
)
