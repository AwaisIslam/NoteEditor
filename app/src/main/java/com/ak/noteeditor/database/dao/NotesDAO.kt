package com.ak.noteeditor.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ak.noteeditor.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract interface NotesDAO {

    @Query("SELECT * FROM notes ORDER BY description")
    fun showAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun searchNote(noteId: String?) : Flow<NoteEntity?>

    @Insert(onConflict = REPLACE)
    suspend fun addNote(vararg noteEntities: NoteEntity)

    @Delete
    suspend fun deleteNote(vararg noteEntities: NoteEntity)
}