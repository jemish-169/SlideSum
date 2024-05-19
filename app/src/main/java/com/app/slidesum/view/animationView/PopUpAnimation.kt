package com.app.slidesum.view.animationView

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.View
import com.airbnb.lottie.LottieAnimationView


class PopUpAnimation(
    private val imageView: LottieAnimationView,
) {

    fun openPopUp(anim: Int) {

        imageView.setAnimation(anim)

        val moveFabAnimation = AnimatorSet()

        val scaleUpX = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 0f, 1f)
        val scaleUpY = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 0f, 1f)
        val growCalAnimation = AnimatorSet()
        growCalAnimation.playTogether(scaleUpX, scaleUpY)
        growCalAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                imageView.visibility = View.VISIBLE
            }
        })

        val openAnimation = AnimatorSet()
        openAnimation.playSequentially(moveFabAnimation, growCalAnimation)
        openAnimation.start()
        Handler(Looper.getMainLooper()).postDelayed({
            closePopUp()
        }, 1500)
    }

    private fun closePopUp() {
        val scaleDownX = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 0f)
        val scaleDownY = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 0f)
        val shrinkCalAnimation = AnimatorSet()
        shrinkCalAnimation.playTogether(scaleDownX, scaleDownY)
        shrinkCalAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                imageView.visibility = View.INVISIBLE
            }
        })

        val moveFabAnimation = AnimatorSet()

        val closeAnimation = AnimatorSet()
        closeAnimation.playSequentially(shrinkCalAnimation, moveFabAnimation)
        closeAnimation.start()
    }

}