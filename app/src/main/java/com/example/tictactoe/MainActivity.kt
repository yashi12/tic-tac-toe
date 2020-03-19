package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var num_turns = 0
    var boad_status = Array(3) { IntArray(3) }
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(id_button1, id_button2, id_button3),
            arrayOf(id_button4, id_button5, id_button6),
            arrayOf(id_button7, id_button8, id_button9)
        )
        resetboard()
        Log.d("choose","1")
        initializeboard()
        Log.d("choose","2")
        chooseplayer()
        Log.d("choose","3")
        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
    }

    private fun chooseplayer() {
        id_left_player.setOnClickListener(this)
        id_right_player.setOnClickListener(this)
    }

    private fun initializeboard() {
        for (i in 0..2) {
            for (j in 0..2) {
                boad_status[i][j] = -1;
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
        id_left_player.isEnabled = true
        id_right_player.isEnabled = true
    }
    private fun resetboard(){
        id_reset.setOnClickListener {
            num_turns = 0
            initializeboard()
        }
    }
    override fun onClick(view: View) {
        when (view) {
            id_button1 -> {
                boardApply(0, 0)
            }
            id_button2 -> {
                boardApply(0, 1)
            }
            id_button3 -> {
                boardApply(0, 2)
            }
            id_button4 -> {
                boardApply(1, 0)
            }
            id_button5 -> {
                boardApply(1, 1)
            }
            id_button6 -> {
                boardApply(1, 2)
            }
            id_button7 -> {
                boardApply(2, 0)
            }
            id_button8 -> {
                boardApply(2, 1)
            }
            id_button9 -> {
                boardApply(2, 2)
            }
            id_left_player -> {
                num_turns = 1
                id_left_player.isEnabled = false
                id_right_player.isEnabled = false
            }
            id_right_player->{
                num_turns = 0
                id_left_player.isEnabled = false
                id_right_player.isEnabled = false
            }
        }
    }
    private fun boardApply(row:Int,col:Int){
        if(num_turns%2!=0){
            boad_status[row][col]=0
            board[row][col].text="0"
        }else{
            boad_status[row][col]=1
            board[row][col].text="+"
        }
        board[row][col].isEnabled=false
        num_turns++
    }
}

