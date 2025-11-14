package com.example.notesappui.ui.onboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappui.databinding.ItemOnBoardBinding

class OnBoardAdapter(
    private val list: List<com.example.notesappui.data.model.OnBoardModel>,
    private val onStartClick: () -> Unit,
    private val onSkipClick: (Int) -> Unit
) : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    inner class OnBoardViewHolder(
        private val binding: ItemOnBoardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoardModel: com.example.notesappui.data.model.OnBoardModel, position: Int) {
            with(binding) {
                animationView.setAnimation(onBoardModel.animation)
                tvTitle.text = onBoardModel.title
                tvDescription.text = onBoardModel.description

                val isLastPage = position == list.size - 1
                btnStart.visibility = if (isLastPage) View.VISIBLE else View.INVISIBLE
                btnStart.setOnClickListener { onStartClick() }

                root.setOnClickListener {
                    if (!isLastPage) {
                        onSkipClick(position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        val binding = ItemOnBoardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}