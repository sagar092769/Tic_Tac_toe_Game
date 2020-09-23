package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var PLAYER = true
    var TURN_COUNT = 0
    var board_Status = Array(3) { IntArray(3) }

    //creating aaray for buttons and for their actions
    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )
        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
        initializeBoard()

        reset.setOnClickListener {
            initializeBoard()
            PLAYER = true
            TURN_COUNT = 0
            updateDisplay_2("Player X Turn")

        }


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> {
                updateDisplay(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateDisplay(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 -> {
                updateDisplay(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4 -> {
                updateDisplay(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5 -> {
                updateDisplay(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6 -> {
                updateDisplay(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7 -> {
                updateDisplay(row = 2, col = 0, player = PLAYER)
            }
            R.id.button8 -> {
                updateDisplay(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9 -> {
                updateDisplay(row = 2, col = 2, player = PLAYER)
            }
        }
        PLAYER = !PLAYER
        TURN_COUNT++
        if (PLAYER) {
            updateDisplay_2("Player X Turn")
        } else {
            updateDisplay_2("Player 0 Turn")
        }
        if (TURN_COUNT == 9) {
            updateDisplay_2("Game Draw!")
        }
        checkWinner()
    }

    private fun checkWinner() {

        //howrizontal rows
        for (i in 0..2) {
            if (board_Status[i][0] == board_Status[i][1] && board_Status[i][0] == board_Status[i][2]) {
                if (board_Status[i][0] == 1) {
                    updateDisplay_2("Player X is Winner")
                    break
                }
                if (board_Status[i][0] == 0) {
                    updateDisplay_2("Player 0 is Winner")
                    break
                }
            }
        }

        //vertical logic
        for (i in 0..2) {
            if (board_Status[0][i] == board_Status[1][i] && board_Status[0][i] == board_Status[2][i]) {
                if (board_Status[0][i] == 1) {
                    updateDisplay_2("Player X is Winner")
                    break
                }
                if (board_Status[0][i] == 0) {
                    updateDisplay_2("Player 0 is Winner")
                    break
                }
            }
        }
        //Diagonal one logic
        if (board_Status[0][0] == board_Status[1][1] && board_Status[0][0] == board_Status[2][2]) {
            if (board_Status[0][0] == 1) {
                updateDisplay_2("Player X is Winner")
            }
            if (board_Status[0][0] == 0) {
                updateDisplay_2("Player 0 is Winner")
            }
        }

        //Diagonal 2 logic
        if (board_Status[0][2] == board_Status[1][1] && board_Status[0][2] == board_Status[2][0]) {
            if (board_Status[0][2] == 1) {
                updateDisplay_2("Player X is Winner")
            }
            if (board_Status[0][2] == 0) {
                updateDisplay_2("Player 0 is Winner")
            }
        }


    }

    private fun updateDisplay_2(s: String) {
        textView.text = s
        if (s.contains("Winner")) {
            for (i in board) {
                for (button in i) {
                    button.isEnabled = false;
                }
            }
        }
    }

    private fun updateDisplay(row: Int, col: Int, player: Boolean) {
        var text_to_display = if (player) "X" else "0"
        var value = if (player) 1 else 0
        board_Status[row][col] = value
        board[row][col].text = text_to_display
        board[row][col].isEnabled = false

    }

    private fun initializeBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                board_Status[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }

    }

    private fun resetFunction() {
        PLAYER = true
        TURN_COUNT = 0
        initializeBoard()
    }
}
