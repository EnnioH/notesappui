package com.example.notesappui.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappui.data.model.NoteModel
import com.example.notesappui.databinding.ItemNoteBinding

class NotesAdapter(
    private val onNoteClick: (NoteModel) -> Unit,
    private val onNoteLongClick: (NoteModel) -> Unit
) : ListAdapter<NoteModel, NotesAdapter.NotesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotesViewHolder(binding, onNoteClick, onNoteLongClick)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    class NotesViewHolder(
        private val binding: ItemNoteBinding,
        private val onNoteClick: (NoteModel) -> Unit,
        private val onNoteLongClick: (NoteModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentNote: NoteModel? = null

        init {
            binding.root.setOnClickListener {
                currentNote?.let { note ->
                    onNoteClick(note)
                }
            }

            binding.root.setOnLongClickListener {
                currentNote?.let { note ->
                    onNoteLongClick(note)
                    true
                } ?: false
            }
        }

        fun bind(note: NoteModel) {
            currentNote = note
            binding.tvNoteTitle.text = note.title
            binding.tvNoteDescription.text = note.description
            binding.tvNoteDate.text = note.date
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NoteModel>() {
            override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}