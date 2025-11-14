package com.example.notesappui.ui.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappui.R
import com.example.notesappui.data.local.AppDatabase
import com.example.notesappui.data.repository.NotesRepository
import com.example.notesappui.databinding.FragmentNotesBinding
import com.example.notesappui.ui.factory.ViewModelFactory
import kotlinx.coroutines.launch

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotesViewModel by viewModels {
        val database = AppDatabase.getInstance(requireContext())
        val notesRepository = NotesRepository(database.noteDao())
        ViewModelFactory(notesRepository)
    }

    private lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNotesBinding.bind(view)

        setupRecyclerView()
        setupClickListeners()
        observeNotes()
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter(
            onNoteClick = { note ->
                // Временно отключаем клик по заметке
                // navigateToEditNote(note)
            },
            onNoteLongClick = { note ->
                showDeleteDialog(note)
            }
        )

        binding.recyclerViewNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notesAdapter
        }
    }

    private fun setupClickListeners() {
        binding.fabAddNote.setOnClickListener {
            navigateToCreateNote()
        }
    }

    private fun observeNotes() {
        lifecycleScope.launch {
            viewModel.getAllNotes().collect { notes ->
                notesAdapter.submitList(notes)
                updateEmptyState(notes.isEmpty())
            }
        }
    }

    private fun navigateToCreateNote() {
        try {
            findNavController().navigate(R.id.action_notesFragment_to_createNoteFragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showDeleteDialog(note: com.example.notesappui.data.model.NoteModel) {
        try {
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Удалить заметку")
                .setMessage("Вы уверены, что хотите удалить \"${note.title}\"?")
                .setPositiveButton("Удалить") { dialog, _ ->
                    viewModel.deleteNote(note)
                    dialog.dismiss()
                }
                .setNegativeButton("Отмена") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        if (isEmpty) {
            binding.emptyStateView.visibility = View.VISIBLE
            binding.recyclerViewNotes.visibility = View.GONE
        } else {
            binding.emptyStateView.visibility = View.GONE
            binding.recyclerViewNotes.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}