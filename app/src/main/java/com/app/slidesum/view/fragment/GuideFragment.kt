package com.app.slidesum.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.slidesum.databinding.FragmentGuideBinding

class GuideFragment : Fragment() {

    private lateinit var binding: FragmentGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuideBinding.inflate(layoutInflater)
        loadViews()
        return binding.root
    }

    private fun loadViews() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}