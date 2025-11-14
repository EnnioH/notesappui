package com.example.notesappui.ui.onboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappui.databinding.ItemOnBoardBinding

data class OnBoardModel(
    val animation: Int,
    val title: String,
    val description: String
)

class OnBoardAdapter(
    private val list: List<OnBoardModel>
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
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class OnBoardViewHolder(private val binding: ItemOnBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoard: OnBoardModel) {
            binding.animationView.setAnimation(onBoard.animation)
            binding.titleText.text = onBoard.title
            binding.descriptionText.text = onBoard.description
        }
    }
}