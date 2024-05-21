package com.app.slidesum.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.slidesum.R
import com.app.slidesum.adapters.ViewPagerAdapter
import com.app.slidesum.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        loadViews()
        return binding.root
    }

    private fun loadViews() {
        binding.startGame.setOnClickListener {
            when (binding.gameViewPager.currentItem) {
                0 -> findNavController().navigate(R.id.action_homeFragment_to_smallGameFragment)
                1 -> findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
                else -> findNavController().navigate(R.id.action_homeFragment_to_big_Game_Fragment)
            }
        }
        binding.themeGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_themeFragment)
        }
        binding.settingsGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }
        val adapter = ViewPagerAdapter(requireContext(), 0)
        binding.gameViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.gameViewPager)
        binding.gameViewPager.currentItem = 1
    }
}