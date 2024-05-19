package com.app.slidesum.repository

import com.app.slidesum.localdata.LocalData

class SmallGameRepository {
    private var matrix = LocalData.getSmallMatrix()
    private var score = LocalData.getSmallScore()
    private var record = LocalData.getSmallRecord()
    private var moves = LocalData.getSmallMoves()
    private var initValue = 2
    private var oldMatrix = arrayOf(
        arrayOf(0, 0, 0),
        arrayOf(0, 0, 0),
        arrayOf(0, 0, 0)
    )
    var animatedMatrix = Array(3) { BooleanArray(3) { false } }

    private var undoMatrix = LocalData.getSmallMatrixUndo()
    private var oldScore = LocalData.getSmallScoreUndo()
    private var oldRecord = LocalData.getSmallRecordUndo()
    private var oldMoves = LocalData.getSmallMovesUndo()

    init {
        reInitializeAnimation()
        addFirst()
    }

    private fun addFirst() {
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] != 0) return
            }
        }
        addElement()
        addElement()
        copyFromOldMatrix()
    }

    private fun addElement() {
        val elements = ArrayList<Pair<Int, Int>>()
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) elements.add(Pair(i, j))
            }
        }
        elements.shuffle()
        if (elements.size == 0) return

        val element = elements[0]
        matrix[element.first][element.second] = initValue
    }

    fun getMatrix(): Array<Array<Int>> {
        return matrix
    }

    fun moveLeft() {
        oldScore = score
        oldRecord = record
        oldMoves = moves
        moves++
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                oldMatrix[i][j] = matrix[i][j]
            }
        }
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) continue

                if (amounts.isEmpty()) {
                    amounts.add(matrix[i][j])
                } else {
                    if (amounts.last() == matrix[i][j] && state) {
                        state = false
                        animatedMatrix[i][amounts.lastIndex] = true
                        amounts[amounts.lastIndex] = matrix[i][j] * 2
                        score += amounts[amounts.lastIndex]
                    } else {
                        state = true
                        amounts.add(matrix[i][j])
                    }
                }
                matrix[i][j] = 0
            }
            for (j in 0 until amounts.size) {
                matrix[i][j] = amounts[j]
            }
        }

        copyFromOldMatrix()

        if (isChanged()) {
            addElement()
        }
    }

    fun moveRight() {
        oldScore = score
        oldRecord = record
        moves++
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                oldMatrix[i][j] = matrix[i][j]
            }
        }
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {
                if (matrix[2 - i][2 - j] == 0) continue

                if (amounts.isEmpty()) {
                    amounts.add(matrix[2 - i][2 - j])
                } else {
                    if (amounts.last() == matrix[2 - i][2 - j] && state) {
                        animatedMatrix[2 - i][2 - amounts.lastIndex] = true
                        state = false
                        amounts[amounts.lastIndex] = matrix[2 - i][2 - j] * 2
                        score += amounts[amounts.lastIndex]
                    } else {
                        state = true
                        amounts.add(matrix[2 - i][2 - j])
                    }
                }
                matrix[2 - i][2 - j] = 0
            }
            for (j in 0 until amounts.size) {
                matrix[2 - i][2 - j] = amounts[j]
            }
        }

        copyFromOldMatrix()

        if (isChanged()) {
            addElement()
        }
    }

    fun moveUp() {
        oldScore = score
        oldRecord = record
        oldMoves = moves
        moves++
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                oldMatrix[i][j] = matrix[i][j]
            }
        }
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {
                if (matrix[j][i] == 0) continue

                if (amounts.isEmpty()) {
                    amounts.add(matrix[j][i])
                } else {
                    if (amounts.last() == matrix[j][i] && state) {
                        animatedMatrix[amounts.lastIndex][i] = true
                        state = false
                        amounts[amounts.lastIndex] = matrix[j][i] * 2
                        score += amounts[amounts.lastIndex]
                    } else {
                        state = true
                        amounts.add(matrix[j][i])
                    }
                }
                matrix[j][i] = 0
            }
            for (j in 0 until amounts.size) {
                matrix[j][i] = amounts[j]
            }
        }

        copyFromOldMatrix()

        if (isChanged()) {
            addElement()
        }
    }

    fun moveDown() {
        oldScore = score
        oldRecord = record
        oldMoves = moves
        moves++
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                oldMatrix[i][j] = matrix[i][j]
            }
        }
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {
                if (matrix[2 - j][i] == 0) continue

                if (amounts.isEmpty()) {
                    amounts.add(matrix[2 - j][i])
                } else {
                    if (amounts.last() == matrix[2 - j][i] && state) {
                        animatedMatrix[2 - amounts.lastIndex][i] = true
                        state = false
                        amounts[amounts.lastIndex] = matrix[2 - j][i] * 2
                        score += amounts[amounts.lastIndex]
                    } else {
                        state = true
                        amounts.add(matrix[2 - j][i])
                    }
                }
                matrix[2 - j][i] = 0
            }
            for (j in 0 until amounts.size) {
                matrix[2 - j][i] = amounts[j]
            }
        }

        copyFromOldMatrix()

        if (isChanged()) {
            addElement()
        }
    }

    private fun isChanged(): Boolean {
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] != oldMatrix[i][j]) {
                    return true
                }
            }
        }
        return false
    }

    fun getRecord(): Int {
        if (record < score)
            record = score
        return record
    }

    fun getScore(): Int {
        return score
    }

    fun saveData() {
        if (record <= score)
            LocalData.setSmallRecord(record)
        LocalData.setSmallScore(score)
        LocalData.setSmallMatrix(matrix)
        LocalData.setSmallMoves(moves)

        LocalData.setSmallMatrixUndo(undoMatrix)
        LocalData.setSmallScoreUndo(oldScore)
        LocalData.setSmallRecordUndo(oldRecord)
        LocalData.setSmallMovesUndo(oldMoves)
    }

    fun restart() {
        matrix = arrayOf(
            arrayOf(0, 0, 0),
            arrayOf(0, 0, 0),
            arrayOf(0, 0, 0)
        )
        score = 0
        addElement()
        addElement()
        copyToUndoMatrix()
        oldScore = 0
        oldRecord = record
        moves = 0
        oldMoves = 0
    }

    fun undoBoard() {
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                matrix[i][j] = undoMatrix[i][j]
            }
        }
        score = oldScore
        record = oldRecord
        moves = oldMoves
    }

    private fun copyFromOldMatrix() {
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                undoMatrix[i][j] = oldMatrix[i][j]
            }
        }
    }

    private fun copyToUndoMatrix() {
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                undoMatrix[i][j] = matrix[i][j]
            }
        }
    }

    fun reInitializeAnimation() {
        animatedMatrix = Array(3) { BooleanArray(3) { false } }

    }

    fun getTheme(): Int {
        return LocalData.getTheme()
    }

    fun getMoves(): Int {
        return moves
    }

    fun useHammer(): Boolean {
        val elements = ArrayList<Pair<Int, Int>>()
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 2 ||
                    matrix[i][j] == 4 ||
                    matrix[i][j] == 8 ||
                    matrix[i][j] == 16 ||
                    matrix[i][j] == 32 ||
                    matrix[i][j] == 64
                ) elements.add(
                    Pair(i, j)
                )
            }
        }
        elements.shuffle()

        if (elements.size < 3) return false

        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                oldMatrix[i][j] = matrix[i][j]
            }
        }

        val element1 = elements[0]
        matrix[element1.first][element1.second] = 0

        copyFromOldMatrix()
        return true
    }
}
