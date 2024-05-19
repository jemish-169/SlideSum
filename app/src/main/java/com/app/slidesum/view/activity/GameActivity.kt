package com.app.slidesum.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.slidesum.R
import com.app.slidesum.databinding.ActivityGameBinding
import com.app.slidesum.localdata.LocalData

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        LocalData.getInstance(this)
        setContentView(binding.root)
    }
}

/*
TODO
     random game start from any point.
     2 tile break by hammer
     undo more than one
     multi player game
     guide of the game after over
     simple game guide
     leader bord
 */