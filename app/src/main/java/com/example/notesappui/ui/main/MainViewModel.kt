package com.example.notesappui.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappui.data.model.NoteModel
import com.example.notesappui.data.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    fun getAllNotes(): Flow<List<NoteModel>> = notesRepository.getAllNotes()

    fun searchNotes(search: String): Flow<List<NoteModel>> = notesRepository.searchNotes(search)

    fun deleteNote(note: NoteModel) {
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }
}