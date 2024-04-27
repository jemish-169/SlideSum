package com.app.slidesum.utils

import com.app.slidesum.R
import com.app.slidesum.data.Theme

class Constants {
    companion object {
        const val RESTART = "restart"
        const val EXIT = "exit"
        const val GAME_OVER = "game_over"
        const val DATA = "DATA"
        const val THEME = "theme"

        const val INITIAL_CAPACITY = 16
        const val SCORE = "score"
        const val RECORD = "record"
        const val MOVE_REQUIREMENT = 150
        const val UNDO_SCORE = "scoreUndo"
        const val UNDO_RECORD = "recordUndo"
        fun matrixLocation(row: Int, col: Int): String = "matrix|$row|$col"
        fun undoMatrixLocation(row: Int, col: Int): String = "undo_matrix|$row|$col"


        const val SMALL_INITIAL_CAPACITY = 9
        const val SMALL_SCORE = "small_score"
        const val SMALL_RECORD = "small_record"
        const val SMALL_UNDO_SCORE = "small_scoreUndo"
        const val SMALL_UNDO_RECORD = "small_recordUndo"
        fun smallMatrixLocation(row: Int, col: Int): String = "small_matrix|$row|$col"
        fun smallUndoMatrixLocation(row: Int, col: Int): String = "small_undo_matrix|$row|$col"


        const val BIG_INITIAL_CAPACITY = 25
        const val BIG_SCORE = "big_score"
        const val BIG_RECORD = "big_record"
        const val BIG_UNDO_SCORE = "big_scoreUndo"
        const val BIG_UNDO_RECORD = "big_recordUndo"
        fun bigMatrixLocation(row: Int, col: Int): String = "big_matrix|$row|$col"
        fun bigUndoMatrixLocation(row: Int, col: Int): String = "big_undo_matrix|$row|$col"


        fun getGameTheme(theme:Int): Theme {
           return when (theme) {

                2 -> {
                    Theme(
                        textColor24 = R.color.glass_text_color_2_4_64,
                        textColorElse = R.color.glass_text_color_else,
                        scoreBg = R.drawable.glass_score_bg,
                        boardDrawable = R.drawable.glass_color_board_bg,
                        tileDrawable = R.drawable.glass_tile_color,
                        color0 = R.color.glass_bg_color_0,
                        color2 = R.color.glass_bg_color_2,
                        color4 = R.color.glass_bg_color_4,
                        color8 = R.color.glass_bg_color_8,
                        color16 = R.color.glass_bg_color_16,
                        color32 = R.color.glass_bg_color_32,
                        color64 = R.color.glass_bg_color_64,
                        color128 = R.color.glass_bg_color_128,
                        color256 = R.color.glass_bg_color_256,
                        color512 = R.color.glass_bg_color_512,
                        color1024 = R.color.glass_bg_color_1024,
                        color2048 = R.color.glass_bg_color_2048,
                        colorElse = R.color.glass_bg_color_else
                    )
                }

                3 -> {
                    Theme(
                        textColor24 = R.color.pastel_text_color_2_4_64,
                        textColorElse = R.color.pastel_text_color_else,
                        scoreBg = R.drawable.pastel_score_bg,
                        boardDrawable = R.drawable.pastel_color_board_bg,
                        tileDrawable = R.drawable.pastel_tile_color,
                        color0 = R.color.pastel_bg_color_0,
                        color2 = R.color.pastel_bg_color_2,
                        color4 = R.color.pastel_bg_color_4,
                        color8 = R.color.pastel_bg_color_8,
                        color16 = R.color.pastel_bg_color_16,
                        color32 = R.color.pastel_bg_color_32,
                        color64 = R.color.pastel_bg_color_64,
                        color128 = R.color.pastel_bg_color_128,
                        color256 = R.color.pastel_bg_color_256,
                        color512 = R.color.pastel_bg_color_512,
                        color1024 = R.color.pastel_bg_color_1024,
                        color2048 = R.color.pastel_bg_color_2048,
                        colorElse = R.color.pastel_bg_color_else
                    )
                }

                4 -> {
                    Theme(
                        textColor24 = R.color.pink_text_color_2_4_64,
                        textColorElse = R.color.pink_text_color_else,
                        scoreBg = R.drawable.pink_score_bg,
                        boardDrawable = R.drawable.pink_color_board_bg,
                        tileDrawable = R.drawable.pink_tile_color,
                        color0 = R.color.pink_bg_color,
                        color2 = R.color.pink_bg_color,
                        color4 = R.color.pink_bg_color,
                        color8 = R.color.pink_bg_color,
                        color16 = R.color.pink_bg_color,
                        color32 = R.color.pink_bg_color,
                        color64 = R.color.pink_bg_color,
                        color128 = R.color.pink_bg_color,
                        color256 = R.color.pink_bg_color,
                        color512 = R.color.pink_bg_color,
                        color1024 = R.color.pink_bg_color,
                        color2048 = R.color.pink_bg_color,
                        colorElse = R.color.pink_bg_color
                    )
                }

                5 -> {
                    Theme(
                        textColor24 = R.color.border_text_color_2_4_64,
                        textColorElse = R.color.border_text_color_else,
                        scoreBg = R.drawable.border_score_bg,
                        boardDrawable = R.drawable.border_color_board_bg,
                        tileDrawable = R.drawable.border_tile_color,
                        color0 = R.color.border_bg_color_0,
                        color2 = R.color.border_bg_color_2,
                        color4 = R.color.border_bg_color_4,
                        color8 = R.color.border_bg_color_8,
                        color16 = R.color.border_bg_color_16,
                        color32 = R.color.border_bg_color_32,
                        color64 = R.color.border_bg_color_64,
                        color128 = R.color.border_bg_color_128,
                        color256 = R.color.border_bg_color_256,
                        color512 = R.color.border_bg_color_512,
                        color1024 = R.color.border_bg_color_1024,
                        color2048 = R.color.border_bg_color_2048,
                        colorElse = R.color.border_bg_color_else
                    )
                }

                else -> {
                    Theme(
                        textColor24 = R.color.def_text_color_2_4_64,
                        textColorElse = R.color.def_text_color_else,
                        boardDrawable = R.drawable.def_color_board_bg,
                        tileDrawable = R.drawable.def_tile_color,
                        scoreBg = R.drawable.def_score_bg,
                        color0 = R.color.def_bg_color_0,
                        color2 = R.color.def_bg_color_2,
                        color4 = R.color.def_bg_color_4,
                        color8 = R.color.def_bg_color_8,
                        color16 = R.color.def_bg_color_16,
                        color32 = R.color.def_bg_color_32,
                        color64 = R.color.def_bg_color_64,
                        color128 = R.color.def_bg_color_128,
                        color256 = R.color.def_bg_color_256,
                        color512 = R.color.def_bg_color_512,
                        color1024 = R.color.def_bg_color_1024,
                        color2048 = R.color.def_bg_color_2048,
                        colorElse = R.color.def_bg_color_else
                    )
                }
            }
        }
    }
}