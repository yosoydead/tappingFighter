package com.example.teamfighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {


    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal lateinit var tapMeButton: Button

    //score variable
    internal var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        //variables initialized
        gameScoreTextView = findViewById(R.id.game_score_text_view)
        timeLeftTextView = findViewById(R.id.time_left_text_view)
        tapMeButton = findViewById(R.id.tap_me_button)

        //button event listener
        tapMeButton.setOnClickListener { incrementScore() }

    }

    private fun incrementScore(){
        //increment the score each time the button is tapped
        score++

        //update the text of the textView
        val newScore = "Your Score: $score"
        gameScoreTextView.text = newScore
    }

    private fun resetGame(){

    }

    private fun startGame(){

    }

    private fun endGame(){

    }
}
