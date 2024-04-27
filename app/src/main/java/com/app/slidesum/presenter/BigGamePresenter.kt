package com.app.slidesum.presenter

import com.app.slidesum.repository.BigGameRepository
import com.app.slidesum.utils.Constants.Companion.GAME_OVER
import com.app.slidesum.view.fragment.BigGameFragment

class BigGamePresenter(
    private val view: BigGameFragment,
    private val repository: BigGameRepository
) {
    fun getScore(): Int {
        return repository.getScore()
    }

    fun startGame() {
        view.changeState(repository.getMatrix(), repository.animatedMatrix)
    }

    fun moveLeft() {
        repository.moveLeft()
        view.changeState(repository.getMatrix(), repository.animatedMatrix)
        checkFinish()
        repository.reInitializeAnimation()
    }

    fun moveRight() {
        repository.moveRight()
        view.changeState(repository.getMatrix(), repository.animatedMatrix)
        checkFinish()
        repository.reInitializeAnimation()
    }

    fun moveUp() {
        repository.moveUp()
        view.changeState(repository.getMatrix(), repository.animatedMatrix)
        checkFinish()
        repository.reInitializeAnimation()
    }

    fun moveDown() {
        repository.moveDown()
        view.changeState(repository.getMatrix(), repository.animatedMatrix)
        checkFinish()
        repository.reInitializeAnimation()
    }

    fun restart() {
        repository.saveData()
        repository.restart()
        view.changeState(repository.getMatrix(), repository.animatedMatrix)
        repository.reInitializeAnimation()
    }

    fun undoBoard() {
        repository.undoBoard()
        view.changeState(repository.getMatrix(), repository.animatedMatrix)
        repository.reInitializeAnimation()
    }

    fun saveData() {
        repository.saveData()
    }

    fun getRecord(): Int {
        return repository.getRecord()
    }

    private fun checkFinish() {
        val matrix = repository.getMatrix()
        for (i in 0..4) {
            var temp = ""
            for (j in 0..4) {
                temp += matrix[i][j].toString() + " "
            }

        }

        for (i in matrix) {
            for (j in i) {
                if (j == 0)
                    return
            }
        }
        for (i in 0..4) {
            for (j in 1..4) {
                if (matrix[i][j] == matrix[i][j - 1]) {
                    return
                }
            }
        }
        for (j in 0..4) {
            for (i in 1..4) {
                if (matrix[i][j] == matrix[i - 1][j])
                    return
            }
        }
        view.showDialog(GAME_OVER)
    }

    fun getTheme(): Int {
        return repository.getTheme()
    }
}