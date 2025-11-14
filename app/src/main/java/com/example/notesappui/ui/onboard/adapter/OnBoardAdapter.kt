package com.example.notesappui.ui.onboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappui.databinding.ItemOnBoardBinding
import com.example.notesappui.ui.onboard.model.OnBoardItem

class OnBoardAdapter(
    private val items: List<OnBoardItem>
) : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        val binding = ItemOnBoardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class OnBoardViewHolder(
        private val binding: ItemOnBoardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnBoardItem) {
            binding.ivOnBoardImage.setImageResource(item.imageRes)
            binding.tvOnBoardTitle.text = item.title
            binding.tvOnBoardDescription.text = item.description
        }
    }
}