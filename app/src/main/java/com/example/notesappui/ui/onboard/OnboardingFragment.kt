package com.example.notesappui.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.notesappui.R
import com.example.notesappui.data.local.AppPreferences
import com.example.notesappui.databinding.FragmentOnboardingBinding
import com.example.notesappui.ui.onboard.adapter.OnBoardAdapter
import com.example.notesappui.ui.onboard.model.OnBoardItem

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private lateinit var onBoardAdapter: OnBoardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingBinding.bind(view)

        setupViewPager()
        setupClickListeners()
    }

    private fun setupViewPager() {
        val onBoardItems = listOf(
            OnBoardItem(
                imageRes = android.R.drawable.ic_menu_edit,
                title = "Create Notes",
                description = "Easily create and organize your notes with our intuitive interface"
            ),
            OnBoardItem(
                imageRes = android.R.drawable.ic_menu_search,
                title = "Search Notes",
                description = "Quickly find your notes with powerful search functionality"
            ),
            OnBoardItem(
                imageRes = android.R.drawable.ic_menu_save,
                title = "Save Securely",
                description = "Your notes are stored securely and available offline"
            )
        )

        onBoardAdapter = OnBoardAdapter(onBoardItems)
        binding.viewPagerOnBoard.adapter = onBoardAdapter

        setupIndicator()
    }

    private fun setupIndicator() {
    }

    private fun setupClickListeners() {
        binding.btnStart.setOnClickListener {
            val appPreferences = AppPreferences(requireContext())
            appPreferences.setIsOnboardingShown(true)
            findNavController().navigate(R.id.action_onboardingFragment_to_notesFragment)
        }

        binding.btnSkip.setOnClickListener {
            val appPreferences = AppPreferences(requireContext())
            appPreferences.setIsOnboardingShown(true)
            findNavController().navigate(R.id.action_onboardingFragment_to_notesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}