package com.ak.noteeditor.repository

import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.database.dao.NotesDAO
import com.ak.noteeditor.database.entity.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NoteEditorRepository(
    private val notesDAO: NotesDAO,
    private val appScope: CoroutineScope
) {
    fun notesList(): Flow<List<NoteModel>> =
        notesDAO.showAllNotes().map { all ->
            all.map { it.toModel() }
        }

    suspend fun save(model: NoteModel) {
        withContext(appScope.coroutineContext) {
            notesDAO.addNote(NoteEntity(model))
        }
    }

    suspend fun deleteNote(model: NoteModel) {
        withContext(appScope.coroutineContext) {
            notesDAO.deleteNote(NoteEntity(model))
        }
    }

    fun find(noteId: String?): Flow<NoteModel?> = notesDAO.searchNote(noteId).map { it?.toModel() }

}