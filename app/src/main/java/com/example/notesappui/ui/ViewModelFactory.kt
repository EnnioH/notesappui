package com.example.notesappui.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesappui.data.repository.NotesRepository
import com.example.notesappui.ui.createnote.CreateNoteViewModel
import com.example.notesappui.ui.main.MainViewModel
import com.example.notesappui.ui.notes.NotesViewModel

class ViewModelFactory(
    private val notesRepository: NotesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CreateNoteViewModel::class.java) -> {
                CreateNoteViewModel(notesRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(notesRepository) as T
            }
            modelClass.isAssignableFrom(NotesViewModel::class.java) -> {
                NotesViewModel(notesRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}