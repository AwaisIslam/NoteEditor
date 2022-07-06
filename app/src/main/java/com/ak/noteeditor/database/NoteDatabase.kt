package com.ak.noteeditor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ak.noteeditor.database.dao.NotesDAO
import com.ak.noteeditor.database.entity.NoteEntity
import com.ak.utils.DataTimeConverter

private const val NOTE_DB = "note.db"

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(DataTimeConverter::class)

abstract class NoteDatabase : RoomDatabase() {

    abstract fun notesDAO(): NotesDAO

    companion object {
        fun newInstance(context: Context) =
            Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DB)
                .build()
    }
}