package com.app.slidesum.utils

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.widget.TextView
import kotlin.math.abs
import kotlin.math.min

object Animation {

    fun animateTextView(textView: TextView) {
        val animationDuration: Long = 300

        val scaleXAnimator = ObjectAnimator.ofFloat(textView, "scaleX", 1.0f, 1.10f, 1.0f)
        scaleXAnimator.duration = animationDuration

        val scaleYAnimator = ObjectAnimator.ofFloat(textView, "scaleY", 1.0f, 1.10f, 1.0f)
        scaleYAnimator.duration = animationDuration

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator)
        animatorSet.start()
    }
    fun animateValues(finalValue: Int, view: TextView, time: Long) {
        val initialValue = if (view.text.toString() == "") 0 else view.text.toString().toInt()
        val diff: Long = abs(finalValue - initialValue).toLong()
        val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
        valueAnimator.setDuration(min(diff * 10, time))

        valueAnimator.addUpdateListener {
            view.text = it.getAnimatedValue().toString()
        }
        valueAnimator.start()
    }
}
