package com.app.slidesum.view.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.slidesum.R
import com.app.slidesum.data.Movement
import com.app.slidesum.data.Theme
import com.app.slidesum.databinding.FinishDialogBinding
import com.app.slidesum.databinding.FragmentGameBinding
import com.app.slidesum.presenter.GamePresenter
import com.app.slidesum.repository.GameRepository
import com.app.slidesum.utils.Animation
import com.app.slidesum.utils.Animation.animateValues
import com.app.slidesum.utils.Constants.Companion.EXIT
import com.app.slidesum.utils.Constants.Companion.GAME_OVER
import com.app.slidesum.utils.Constants.Companion.INITIAL_CAPACITY
import com.app.slidesum.utils.Constants.Companion.RESTART
import com.app.slidesum.utils.Constants.Companion.getGameTheme
import com.app.slidesum.utils.MovementDetector
import com.app.slidesum.view.animationView.PopUpAnimation
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val buttons: ArrayList<TextView> = ArrayList(INITIAL_CAPACITY)
    private val presenter = GamePresenter(this, GameRepository())
    private lateinit var gameTheme: Theme
    private lateinit var popUpAnimation: PopUpAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (presenter.getScore() > 0)
                    showDialog(EXIT)
                else
                    findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)

        loadViews()
        setUITheme(presenter.getTheme())

        presenter.startGame()

        return binding.root
    }

    override fun onPause() {
        presenter.saveData()
        super.onPause()
    }

    private fun setUITheme(theme: Int) {

        gameTheme = getGameTheme(theme)
        popUpAnimation = PopUpAnimation(binding.popUpAnimationView)

        binding.bestLl.setBackgroundResource(gameTheme.scoreBg)
        binding.scoreLl.setBackgroundResource(gameTheme.scoreBg)
        binding.shareImg.setBackgroundResource(gameTheme.scoreBg)
        binding.hammerGame.setBackgroundResource(gameTheme.scoreBg)
        binding.undoBtn.setBackgroundResource(gameTheme.scoreBg)
        binding.restartGame.setBackgroundResource(gameTheme.scoreBg)
        binding.mainGameField.setBackgroundResource(gameTheme.boardDrawable)
    }

    fun changeState(matrix: Array<Array<Int>>, animatedMatrix: Array<BooleanArray>) {

        animateValues(presenter.getScore(), binding.scoreGame, 500)
        animateValues(presenter.getRecord(), binding.recordGame, 500)
        animateValues(presenter.getMoves(), binding.movesGame, 500)

        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                val tile = buttons[4 * i + j]
                val value = matrix[i][j]

                if (value == 1024 || value == 2048 || value == 4096 || value == 8192) {
                    tile.textSize = 24f
                } else {
                    tile.textSize = 33f
                }

                tile.setTextColor(
                    resources.getColor(
                        gameTheme.textColorElse,
                        requireActivity().theme
                    )
                )

                tile.background = getBgDrawable(value)

                if (value >= 2048) {
                    if (animatedMatrix[i][j]) {
                        popUpAnimation.openPopUp(R.raw.winner_anim)
                    }
                }
                if (value == 0) tile.text = ""
                else {
                    if (animatedMatrix[i][j]) Animation.animateTextView(tile)
                    tile.text = matrix[i][j].toString()
                }
            }
        }
    }

    private fun getBgDrawable(value: Int): Drawable {
        val color = when (value) {
            0 -> gameTheme.color0
            2 -> gameTheme.color2
            4 -> gameTheme.color4
            8 -> gameTheme.color8
            16 -> gameTheme.color16
            32 -> gameTheme.color32
            64 -> gameTheme.color64
            128 -> gameTheme.color128
            256 -> gameTheme.color256
            512 -> gameTheme.color512
            1024 -> gameTheme.color1024
            2048 -> gameTheme.color2048
            else -> gameTheme.colorElse
        }
        val layerDrawable = ContextCompat.getDrawable(
            requireContext(),
            gameTheme.tileDrawable
        ) as LayerDrawable
        val backgroundShape =
            layerDrawable.findDrawableByLayerId(R.id.background) as GradientDrawable
        backgroundShape.setColor(ContextCompat.getColor(requireContext(), color))

        return layerDrawable
    }

    fun showDialog(message: String) {
        val dialog = Dialog(requireContext())
        val dialogBinding = FinishDialogBinding.inflate(layoutInflater)
        when (message) {
            EXIT -> {
                dialog.setContentView(dialogBinding.root)
                dialogBinding.dialogTitle.text = resources.getString(R.string.exit_dialog_title)
                dialogBinding.dialogDescription.text =
                    resources.getString(R.string.exit_dialog_desc)
                dialogBinding.positiveButton.text = resources.getString(R.string.yes)
                dialogBinding.negativeButton.text = resources.getString(R.string.no)
                dialogBinding.positiveButton.setOnClickListener {
                    dialog.dismiss()
                    findNavController().navigateUp()
                }
                dialogBinding.negativeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }

            GAME_OVER -> {
                dialog.setContentView(dialogBinding.root)
                dialogBinding.dialogDescription.text =
                    resources.getString(R.string.tv_win_dialog, presenter.getMoves().toString())
                dialogBinding.negativeButton.setOnClickListener {
                    dialog.dismiss()
                }
                dialogBinding.positiveButton.setOnClickListener {
                    presenter.saveData()
                    presenter.restart()
                    dialog.dismiss()
                }
            }

            RESTART -> {
                dialog.setContentView(dialogBinding.root)
                dialogBinding.dialogTitle.text = resources.getString(R.string.restart_dialog_title)
                dialogBinding.dialogDescription.text =
                    resources.getString(R.string.restart_dialog_desc)
                dialogBinding.positiveButton.text = resources.getString(R.string.yes)
                dialogBinding.negativeButton.text = resources.getString(R.string.no)
                dialogBinding.positiveButton.setOnClickListener {
                    dialog.dismiss()
                    presenter.restart()
                }
                dialogBinding.negativeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
        dialog.show()

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun loadViews() {
        binding.restartGame.setOnClickListener {
            if (presenter.getScore() != 0)
                showDialog(RESTART)
            else
                presenter.restart()
        }

        binding.undoBtn.setOnClickListener { presenter.undoBoard() }
        binding.shareImg.setOnClickListener { shareGameBoardImage() }
        binding.hammerGame.setOnClickListener {
            if (presenter.useHammer())
                popUpAnimation.openPopUp(R.raw.hammer_anim)
        }
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        val mainGameField = binding.mainGameField
        for (i in 0 until mainGameField.childCount) {
            val childContainer = mainGameField.getChildAt(i) as LinearLayout
            for (j in 0 until childContainer.childCount) {
                buttons.add(childContainer.getChildAt(j) as TextView)
            }
        }

        val movementDetector = MovementDetector(requireContext())
        movementDetector.setOnMovementListener {
            when (it) {
                Movement.LEFT -> presenter.moveLeft()
                Movement.RIGHT -> presenter.moveRight()
                Movement.DOWN -> presenter.moveDown()
                Movement.UP -> presenter.moveUp()
            }
        }

        binding.gameBoard.setOnTouchListener(movementDetector)
    }

    private fun shareGameBoardImage() {
        val image = binding.mainGameField
        val bitmap = Bitmap.createBitmap(
            image.width, image.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        image.draw(canvas)

        try {
            val cachePath = File(requireActivity().applicationContext.cacheDir, "images")
            cachePath.mkdirs()
            val stream =
                FileOutputStream("$cachePath/image.png")
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

            val newFile = File(cachePath, "image.png")
            val contentUri =
                FileProvider.getUriForFile(
                    requireActivity().applicationContext,
                    "com.app.slidesum.fileProvider",
                    newFile
                )

            if (contentUri != null) {
                val shareIntent = Intent()
                shareIntent.setAction(Intent.ACTION_SEND)
                shareIntent.setType("image/png")
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                startActivity(Intent.createChooser(shareIntent, "Choose an app"))
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

