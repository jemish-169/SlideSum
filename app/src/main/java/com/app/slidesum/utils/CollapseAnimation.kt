package com.app.slidesum.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.widget.TextView

object CollapseAnimation {

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
}
