package com.ak.noteeditor.data

import java.time.Instant
import java.util.*

data class NoteModel(
    val description: String,
    val id: String = UUID.randomUUID().toString(),
    val isCompleted: Boolean = false,
    val notes: String = "",
    val createdOn: Instant = Instant.now()
)
