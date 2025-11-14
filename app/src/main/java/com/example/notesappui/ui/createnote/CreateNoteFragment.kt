package com.example.notesappui.ui.createnote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesappui.R
import com.example.notesappui.data.local.AppDatabase
import com.example.notesappui.data.repository.NotesRepository
import com.example.notesappui.databinding.FragmentCreateNoteBinding
import com.example.notesappui.ui.factory.ViewModelFactory

class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateNoteViewModel by viewModels {
        val database = AppDatabase.getInstance(requireContext())
        val notesRepository = NotesRepository(database.noteDao())
        ViewModelFactory(notesRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateNoteBinding.bind(view)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSaveNote.setOnClickListener {
            val title = binding.etNoteTitle.text.toString()
            val description = binding.etNoteDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                viewModel.saveNote(title, description)
                findNavController().popBackStack()
            } else {
                if (title.isEmpty()) {
                    binding.etNoteTitle.error = "Введите заголовок"
                }
                if (description.isEmpty()) {
                    binding.etNoteDescription.error = "Введите описание"
                }
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}