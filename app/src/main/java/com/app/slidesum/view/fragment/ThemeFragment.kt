package com.app.slidesum.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.slidesum.adapters.ViewPagerAdapter
import com.app.slidesum.databinding.FragmentThemeBinding
import com.app.slidesum.localdata.LocalData

class ThemeFragment : Fragment() {

    private lateinit var binding: FragmentThemeBinding
    private val localData = LocalData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentThemeBinding.inflate(layoutInflater)
        loadViews()
        return binding.root
    }

    private fun loadViews() {
        binding.startGame.setOnClickListener {
            localData.setTheme(binding.gameViewPager.currentItem + 1)
            findNavController().navigateUp()
        }
        val adapter = ViewPagerAdapter(requireContext(), 1)
        binding.gameViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.gameViewPager)
        binding.gameViewPager.currentItem = localData.getTheme() - 1
    }
}