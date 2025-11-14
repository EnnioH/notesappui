package com.example.notesappui.data.repository

import com.example.notesappui.data.local.NoteDao
import com.example.notesappui.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val noteDao: NoteDao) {

    fun getAllNotes(): Flow<List<NoteModel>> = noteDao.getAllNotes()

    suspend fun getNoteById(id: Long): NoteModel? = noteDao.getNoteById(id)

    suspend fun insertNote(note: NoteModel) = noteDao.insertNote(note)

    suspend fun updateNote(note: NoteModel) = noteDao.updateNote(note)

    suspend fun deleteNote(note: NoteModel) = noteDao.deleteNote(note)

    fun searchNotes(search: String): Flow<List<NoteModel>> = noteDao.searchNotes(search)
}