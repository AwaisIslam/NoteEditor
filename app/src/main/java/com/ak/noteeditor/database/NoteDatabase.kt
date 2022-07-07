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

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: newInstance(context).also {
                INSTANCE = it
            }
        }

        private fun newInstance(context: Context): NoteDatabase{

            return Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DB)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun notesDAO(): NotesDAO
}