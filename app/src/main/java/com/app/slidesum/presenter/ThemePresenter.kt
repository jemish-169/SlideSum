package com.app.slidesum.presenter

import com.app.slidesum.repository.GameRepository
import com.app.slidesum.utils.Constants.Companion.GAME_OVER
import com.app.slidesum.view.fragment.GameFragment
import com.app.slidesum.view.fragment.ThemeFragment

class ThemePresenter(
    private val view: ThemeFragment,
    private val repository: GameRepository
) {
    fun getTheme(): Int {
        return repository.getTheme()
    }

    fun setTheme(theme : Int) {
        repository.setTheme(theme)
    }
}