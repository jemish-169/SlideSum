package com.app.slidesum.localdata

import android.content.Context
import android.content.SharedPreferences
import com.app.slidesum.utils.Constants
import com.app.slidesum.utils.Constants.Companion.BIG_RECORD
import com.app.slidesum.utils.Constants.Companion.BIG_SCORE
import com.app.slidesum.utils.Constants.Companion.BIG_UNDO_RECORD
import com.app.slidesum.utils.Constants.Companion.BIG_UNDO_SCORE
import com.app.slidesum.utils.Constants.Companion.RECORD
import com.app.slidesum.utils.Constants.Companion.SCORE
import com.app.slidesum.utils.Constants.Companion.SMALL_RECORD
import com.app.slidesum.utils.Constants.Companion.SMALL_SCORE
import com.app.slidesum.utils.Constants.Companion.SMALL_UNDO_RECORD
import com.app.slidesum.utils.Constants.Companion.SMALL_UNDO_SCORE
import com.app.slidesum.utils.Constants.Companion.THEME
import com.app.slidesum.utils.Constants.Companion.UNDO_RECORD
import com.app.slidesum.utils.Constants.Companion.UNDO_SCORE
import com.app.slidesum.utils.Constants.Companion.bigMatrixLocation
import com.app.slidesum.utils.Constants.Companion.bigUndoMatrixLocation
import com.app.slidesum.utils.Constants.Companion.matrixLocation
import com.app.slidesum.utils.Constants.Companion.smallMatrixLocation
import com.app.slidesum.utils.Constants.Companion.smallUndoMatrixLocation
import com.app.slidesum.utils.Constants.Companion.undoMatrixLocation

class LocalData {
    companion object {

        private lateinit var sharedPref: SharedPreferences

        //      Theme       //

        fun getTheme(): Int {
            return sharedPref.getInt(THEME, 1)
        }

        fun setTheme(theme: Int) {
            sharedPref.edit().putInt(THEME, theme).apply()
        }

        //------------------//


        //      Classic Game        //

        fun getInstance(context: Context): SharedPreferences {
            if (!Companion::sharedPref.isInitialized) sharedPref =
                context.getSharedPreferences(Constants.DATA, Context.MODE_PRIVATE)
            return sharedPref
        }

        fun setMatrix(matrix: Array<Array<Int>>) {
            for (i in matrix.indices) {
                for (j in 0 until matrix[i].size) {
                    sharedPref.edit().putInt(matrixLocation(i, j), matrix[i][j]).apply()
                }
            }
        }

        fun getMatrix(): Array<Array<Int>> {
            val matrix: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0)
            )

            for (i in matrix.indices) {
                for (j in 0 until matrix[i].size) {
                    matrix[i][j] = sharedPref.getInt(matrixLocation(i, j), 0)
                }
            }
            return matrix
        }

        fun setScore(score: Int) {
            sharedPref.edit().putInt(SCORE, score).apply()
        }

        fun getScore(): Int {
            return sharedPref.getInt(SCORE, 0)
        }

        fun setRecord(record: Int) {
            sharedPref.edit().putInt(RECORD, record).apply()
        }

        fun getRecord(): Int {
            return sharedPref.getInt(RECORD, 0)
        }

        fun getMatrixUndo(): Array<Array<Int>> {
            val matrixUndo: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0)
            )
            for (i in matrixUndo.indices) {
                for (j in 0 until matrixUndo[i].size) {
                    matrixUndo[i][j] = sharedPref.getInt(undoMatrixLocation(i, j), 0)
                }
            }
            return matrixUndo
        }

        fun setMatrixUndo(matrixUndo: Array<Array<Int>>) {
            for (i in matrixUndo.indices) {
                for (j in 0 until matrixUndo[i].size) {
                    sharedPref.edit().putInt(undoMatrixLocation(i, j), matrixUndo[i][j]).apply()
                }
            }
        }

        fun getScoreUndo(): Int {
            return sharedPref.getInt(UNDO_SCORE, 0)
        }

        fun getRecordUndo(): Int {
            return sharedPref.getInt(UNDO_RECORD, 0)
        }

        fun setScoreUndo(scoreUndo: Int) {
            sharedPref.edit().putInt(UNDO_SCORE, scoreUndo).apply()
        }

        fun setRecordUndo(recordUndo: Int) {
            sharedPref.edit().putInt(UNDO_RECORD, recordUndo).apply()
        }

        //----------------------------//


        //        Small Game    //

        fun setSmallMatrix(matrix: Array<Array<Int>>) {
            for (i in matrix.indices) {
                for (j in 0 until matrix[i].size) {
                    sharedPref.edit().putInt(smallMatrixLocation(i, j), matrix[i][j]).apply()
                }
            }
        }

        fun getSmallMatrix(): Array<Array<Int>> {
            val smallMatrix: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0),
                arrayOf(0, 0, 0),
                arrayOf(0, 0, 0)
            )

            for (i in smallMatrix.indices) {
                for (j in 0 until smallMatrix[i].size) {
                    smallMatrix[i][j] = sharedPref.getInt(smallMatrixLocation(i, j), 0)
                }
            }
            return smallMatrix
        }

        fun setSmallScore(score: Int) {
            sharedPref.edit().putInt(SMALL_SCORE, score).apply()
        }

        fun getSmallScore(): Int {
            return sharedPref.getInt(SMALL_SCORE, 0)
        }

        fun setSmallRecord(record: Int) {
            sharedPref.edit().putInt(SMALL_RECORD, record).apply()
        }

        fun getSmallRecord(): Int {
            return sharedPref.getInt(SMALL_RECORD, 0)
        }

        fun getSmallMatrixUndo(): Array<Array<Int>> {
            val smallMatrixUndo: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0),
                arrayOf(0, 0, 0),
                arrayOf(0, 0, 0)
            )
            for (i in smallMatrixUndo.indices) {
                for (j in 0 until smallMatrixUndo[i].size) {
                    smallMatrixUndo[i][j] = sharedPref.getInt(smallUndoMatrixLocation(i, j), 0)
                }
            }
            return smallMatrixUndo
        }

        fun setSmallMatrixUndo(smallMatrixUndo: Array<Array<Int>>) {
            for (i in smallMatrixUndo.indices) {
                for (j in 0 until smallMatrixUndo[i].size) {
                    sharedPref.edit().putInt(smallUndoMatrixLocation(i, j), smallMatrixUndo[i][j])
                        .apply()
                }
            }
        }

        fun getSmallScoreUndo(): Int {
            return sharedPref.getInt(SMALL_UNDO_SCORE, 0)
        }

        fun getSmallRecordUndo(): Int {
            return sharedPref.getInt(SMALL_UNDO_RECORD, 0)
        }

        fun setSmallScoreUndo(scoreUndo: Int) {
            sharedPref.edit().putInt(SMALL_UNDO_SCORE, scoreUndo).apply()
        }

        fun setSmallRecordUndo(recordUndo: Int) {
            sharedPref.edit().putInt(SMALL_UNDO_RECORD, recordUndo).apply()
        }

        //-------------------------------------//


        //        Big Game    //

        fun setBigMatrix(matrix: Array<Array<Int>>) {
            for (i in matrix.indices) {
                for (j in 0 until matrix[i].size) {
                    sharedPref.edit().putInt(bigMatrixLocation(i, j), matrix[i][j]).apply()
                }
            }
        }

        fun getBigMatrix(): Array<Array<Int>> {
            val bigMatrix: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0)
            )

            for (i in bigMatrix.indices) {
                for (j in 0 until bigMatrix[i].size) {
                    bigMatrix[i][j] = sharedPref.getInt(bigMatrixLocation(i, j), 0)
                }
            }
            return bigMatrix
        }

        fun setBigScore(score: Int) {
            sharedPref.edit().putInt(BIG_SCORE, score).apply()
        }

        fun getBigScore(): Int {
            return sharedPref.getInt(BIG_SCORE, 0)
        }

        fun setBigRecord(record: Int) {
            sharedPref.edit().putInt(BIG_RECORD, record).apply()
        }

        fun getBigRecord(): Int {
            return sharedPref.getInt(BIG_RECORD, 0)
        }

        fun getBigMatrixUndo(): Array<Array<Int>> {
            val bigMatrixUndo: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0)
            )
            for (i in bigMatrixUndo.indices) {
                for (j in 0 until bigMatrixUndo[i].size) {
                    bigMatrixUndo[i][j] = sharedPref.getInt(bigUndoMatrixLocation(i, j), 0)
                }
            }
            return bigMatrixUndo
        }

        fun setBigMatrixUndo(bigMatrixUndo: Array<Array<Int>>) {
            for (i in bigMatrixUndo.indices) {
                for (j in 0 until bigMatrixUndo[i].size) {
                    sharedPref.edit().putInt(bigUndoMatrixLocation(i, j), bigMatrixUndo[i][j])
                        .apply()
                }
            }
        }

        fun getBigScoreUndo(): Int {
            return sharedPref.getInt(BIG_UNDO_SCORE, 0)
        }

        fun getBigRecordUndo(): Int {
            return sharedPref.getInt(BIG_UNDO_RECORD, 0)
        }

        fun setBigScoreUndo(scoreUndo: Int) {
            sharedPref.edit().putInt(BIG_UNDO_SCORE, scoreUndo).apply()
        }

        fun setBigRecordUndo(recordUndo: Int) {
            sharedPref.edit().putInt(BIG_UNDO_RECORD, recordUndo).apply()
        }

        //-------------------------------------//
    }
}