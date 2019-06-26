package com.example.teamfighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {


    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal lateinit var tapMeButton: Button

    //score variable
    internal var score = 0

    //game started flag
    internal var gameStarted = false

    //countdown object
    internal lateinit var countDownTimer: CountDownTimer
    //the initial countdown: 60 seconds
    internal var initialCountDown: Long = 60000
    //countdown interval: 1 second
    internal var countDownInterval: Long = 1000

    //integer representation of the timer
    internal var timeLeft = 60


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        //variables initialized
        gameScoreTextView = findViewById(R.id.game_score_text_view)
        timeLeftTextView = findViewById(R.id.time_left_text_view)
        tapMeButton = findViewById(R.id.tap_me_button)

        //button event listener
        tapMeButton.setOnClickListener { incrementScore() }

        resetGame()

    }

    //override the saveInstanceState method so it stores the state of the app when the app changes
    //orientation
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        //store the current score
        outState?.putInt(SCORE_KEY, score)

        //store the current time left
        outState?.putInt(TIME_LEFT_KEY, timeLeft)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun incrementScore(){
        //if the game is not started when tapping the button, start it
        if(gameStarted == false){
            startGame()
        }

        //increment the score each time the button is tapped
        score++

        //update the text of the textView
        val newScore = getString(R.string.your_score, score.toString())
        gameScoreTextView.text = newScore
    }

    private fun resetGame(){
        /*when reseting the game i want to:
            - set the gameStarted flag to false because the game has not started
            - reset the score
            - reset the textView where the score is displayed
            - reset the timer textView
            - recreating the countdown object so it knows what to do
        */

        //reset the score
        score = 0

        //reset the value of the score textview
        val initialScore = getString(R.string.your_score, score.toString())
        gameScoreTextView.text = initialScore

        //reset the value of the timer textView. it should display 60 seconds
        val initialTimeLeft = getString(R.string.time_left, Integer.toString(60))
        timeLeftTextView.text = initialTimeLeft

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval){
            override fun onFinish() {
                //in this method, specify what action should take place one the countdown finishes
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                //every second, update the value of the time left textView
                timeLeft = millisUntilFinished.toInt() / 1000

                val timeLeftString = getString(R.string.time_left, timeLeft.toString())
                timeLeftTextView.text = timeLeftString
            }
        }

        //set the game flag to false
        gameStarted = false
    }

    private fun startGame(){
        //set the game flag to true because the game has started
        gameStarted = true

        //start the countdown because it doesnt start on itself
        //it needs to be told when to start
        countDownTimer.start()
    }

    private fun endGame(){
        //display a toast message to notify the user the game has ended
        Toast.makeText(this, getString(R.string.game_over_message, score.toString()), Toast.LENGTH_LONG).show()

        //reset the state of the game once the countdown has finished
        resetGame()
    }

    //define the companion object that stores two labels
    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }
}
