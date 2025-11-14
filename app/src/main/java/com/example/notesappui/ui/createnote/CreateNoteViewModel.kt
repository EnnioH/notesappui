package com.example.notesappui.ui.createnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappui.data.model.NoteModel
import com.example.notesappui.data.repository.NotesRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    fun saveNote(title: String, description: String) {
        if (title.isNotEmpty() && description.isNotEmpty()) {
            val currentDate = getCurrentDate()
            val note = NoteModel(
                title = title,
                description = description,
                date = currentDate,
                timestamp = System.currentTimeMillis()
            )

            viewModelScope.launch {
                notesRepository.insertNote(note)
            }
        }
    }

    fun updateNote(noteId: Long, title: String, description: String) {
        if (title.isNotEmpty() && description.isNotEmpty()) {
            val currentDate = getCurrentDate()
            val note = NoteModel(
                id = noteId,
                title = title,
                description = description,
                date = currentDate,
                timestamp = System.currentTimeMillis()
            )

            viewModelScope.launch {
                notesRepository.updateNote(note)
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(Date())
    }
}