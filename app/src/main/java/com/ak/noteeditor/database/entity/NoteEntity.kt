package com.ak.noteeditor.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ak.noteeditor.data.NoteModel
import java.time.Instant
import java.util.*

@Entity(tableName = "notes", indices = [Index(value = ["id"])])
data class NoteEntity(
    val description: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val notes: String = "",
    val createdOn: Instant = Instant.now(),
    val isCompleted: Boolean = false
){
    constructor(noteModel: NoteModel) : this(
        id = noteModel.id,
        description = noteModel.description,
        isCompleted = noteModel.isCompleted,
        notes = noteModel.notes,
        createdOn = noteModel.createdOn
    )

    fun toModel(): NoteModel{
        return NoteModel(
            id = id,
            description = description,
            isCompleted = isCompleted,
            notes = notes,
            createdOn = createdOn
        )
    }
}