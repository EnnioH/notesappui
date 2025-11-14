package com.example.notesappui.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.notesappui.databinding.FragmentOnBoardBinding
import com.example.notesappui.ui.onboard.adapter.OnBoardAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {

    private var _binding: FragmentOnBoardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OnBoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupListeners()
    }

    private fun setupViewPager() {
        adapter = OnBoardAdapter(
            getOnBoardList(),
            onStartClick = {
                findNavController().navigate(com.example.notesappui.R.id.action_onBoardFragment_to_mainFragment)
            },
            onSkipClick = { currentPosition ->
                binding.viewPager.currentItem = currentPosition + 1
            }
        )

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Пустые табы для индикации
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButtonVisibility(position)
            }
        })
    }

    private fun setupListeners() {
        binding.btnSkip.setOnClickListener {
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
        }
    }

    private fun updateButtonVisibility(position: Int) {
        val isLastPage = position == adapter.itemCount - 1
        binding.btnSkip.visibility = if (isLastPage) View.INVISIBLE else View.VISIBLE
    }

    private fun getOnBoardList(): List<com.example.notesappui.data.model.OnBoardModel> {
        return listOf(
            com.example.notesappui.data.model.OnBoardModel(
                com.example.notesappui.R.raw.notes_animation,
                "Создавайте заметки в два клика",
                "Записывайте мысли, идеи и важные задачи быстро и удобно"
            ),
            com.example.notesappui.data.model.OnBoardModel(
                com.example.notesappui.R.raw.organize_animation,
                "Организация и синхронизация",
                "Сортируйте заметки по категориям и получайте доступ с любого устройства"
            ),
            com.example.notesappui.data.model.OnBoardModel(
                com.example.notesappui.R.raw.access_animation,
                "Доступ в любое время",
                "Ваши записи всегда под рукой - дома, на работе или в путешествии"
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}