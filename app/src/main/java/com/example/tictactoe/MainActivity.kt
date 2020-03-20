package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var num_turns = 0
    var display=""
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
        initializeboard()
        chooseplayer()
        resetboard()
        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
    }

    private fun chooseplayer() {
        id_left_player.setOnClickListener(){
            num_turns = 1
            display="X"
            id_left_player.isEnabled = false
            id_right_player.isEnabled = false
            id_display.text="Player $display turn"
        }
        id_right_player.setOnClickListener{
            num_turns = 0
            display="O"
            id_left_player.isEnabled = false
            id_right_player.isEnabled = false
            id_display.text="Player $display turn"
        }

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
        id_display.text="Lets Start!! X or O which one first"
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

            }
            id_right_player->{

            }
        }
        if(num_turns>=5){
            var flag=0
            var diag=0
            var seconddiag=0
            var ddisplay="X"
            if(display=="X")
                ddisplay="O"
            for(i in 0..2){
                var count=0
                for(j in 0..2){
                    if(board[i][j].text==ddisplay){
                        count++
                    }
                    if(i==j && board[i][j].text==ddisplay){
                        diag++
                    }
                    if(i+j==2 && board[i][j].text==ddisplay){
                        seconddiag++
                    }
                }
                if(count==3 || diag==3 ||seconddiag==3){
                    display=ddisplay
                    win()
                    flag
                }
            }
            if(flag==0) {
                for (i in 0..2) {
                    var count = 0
                    for (j in 0..2) {
                        if (board[j][i].text == ddisplay) {
                            count++
                        }
                    }
                    if (count == 3) {
                        display=ddisplay
                        win()
                    }
                }
            }
        }
        if(num_turns==9){
            id_display.text="DRAW!!"
        }
    }
    private fun win(){
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled=false
            }
        }
        id_display.text="Player $display won!!"
    }
    private fun boardApply(row:Int,col:Int){
        board[row][col].text=display
        if(display=="X"){
            display="O"
            boad_status[row][col]=0
        }
        else {
            display="X"
            boad_status[row][col]=1
        }
        id_display.text="Player $display turn"
        board[row][col].isEnabled=false
        num_turns++

    }
}

