package com.app.slidesum.view.fragment

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.slidesum.R
import com.app.slidesum.adapters.ViewPagerAdapter
import com.app.slidesum.databinding.FragmentHomeBinding
import com.app.slidesum.localdata.LocalData
import com.app.slidesum.utils.Animation
import com.app.slidesum.utils.Animation.animateValues


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        loadViews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.smallHighScoreLl.background = getDrawable(R.color.light_red)
        binding.mediumHighScoreLl.background = getDrawable(R.color.light_yellow)
        binding.bigHighScoreLl.background = getDrawable(R.color.light_green)

        animateValues(LocalData.getSmallRecord(), binding.smallHighScore,1000)
        animateValues(LocalData.getRecord(), binding.mediumHighScore,1000)
        animateValues(LocalData.getBigRecord(), binding.bigHighScore,1000)

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

    private fun getDrawable(color: Int): Drawable {
        val layerDrawable = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.high_score_bg
        ) as LayerDrawable
        val backgroundShape =
            layerDrawable.findDrawableByLayerId(R.id.background) as GradientDrawable
        backgroundShape.setColor(ContextCompat.getColor(requireContext(), color))
        return layerDrawable
    }
}