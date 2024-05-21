package com.app.slidesum.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.app.slidesum.data.Movement
import com.app.slidesum.utils.Constants.Companion.MOVE_REQUIREMENT
import kotlin.math.abs

class MovementDetector(context: Context) : View.OnTouchListener {
    private var listener: ((Movement) -> Unit)? = null
    private val gesture =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (e1 != null) {
                    if (abs(e1.x - e2.x) > abs(e1.y - e2.y)) {
                        if (abs(e1.x - e2.x) < MOVE_REQUIREMENT) return false
                        listener?.invoke(if (e2.x > e1.x) Movement.RIGHT else Movement.LEFT)
                    } else if (abs(e1.x - e2.x) < abs(e1.y - e2.y)) {
                        if (abs(e1.y - e2.y) < MOVE_REQUIREMENT) return false
                        listener?.invoke(if (e2.y > e1.y) Movement.DOWN else Movement.UP)
                    }
                }
                return true
            }
        })

    override fun onTouch(v: View?, v2: MotionEvent?): Boolean {
        if (v2 != null) {
            gesture.onTouchEvent(v2)
        }
        return true
    }

    fun setOnMovementListener(block: (Movement) -> Unit) {
        listener = block
    }
}