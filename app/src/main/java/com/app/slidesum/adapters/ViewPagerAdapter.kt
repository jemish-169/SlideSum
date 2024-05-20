package com.app.slidesum.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.slidesum.R
import com.app.slidesum.databinding.ItemViewPagerBinding


class ViewPagerAdapter(private val context: Context, forWhat: Int) : PagerAdapter() {
    private var themeImg = arrayOf(
        R.drawable.glass, // glass_theme
        R.drawable.bubbles,  // bubble_theme
        R.drawable.pink, // pink_theme
        R.drawable.ocean,  // ocean_border_theme
        R.drawable.pastel, // pastel_theme
    )
    private var themeNames = arrayOf(
        context.getString(R.string.glass_theme),
        context.getString(R.string.bubble_theme),
        context.getString(R.string.pink_monochrome),
        context.getString(R.string.border_blue_ocean),
        context.getString(R.string.pastel_color),
    )
    private var gameModeNames = arrayOf(
        context.getString(R.string.small_3x3), // small
        context.getString(R.string.classic_4x4), // classic
        context.getString(R.string.big_5x5) //big
    )
    private var gameModeImg = arrayOf(
        R.drawable.board_3,
        R.drawable.board_4,
        R.drawable.board_5
    )
    private val imgList = if (forWhat == 0) gameModeImg else themeImg
    private val textList = if (forWhat == 0) gameModeNames else themeNames

    override fun getCount(): Int {
        return imgList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(context))
        binding.imageGameType.setImageResource(imgList[position])
        binding.gameTypeName.text = textList[position]
        val viewPager = container as ViewPager
        viewPager.addView(binding.root, 0)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}