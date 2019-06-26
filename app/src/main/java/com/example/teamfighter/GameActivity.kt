package com.example.teamfighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
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
        tapMeButton.setOnClickListener {
            //add the bouncing animation to the button
            val bouncingAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            it.startAnimation(bouncingAnimation)

            incrementScore()
        }

        //use the saveInstanceState bundle to restore the app state if needed
        if(savedInstanceState != null){
            //grab the values of the current variables from the bundle
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeft = savedInstanceState.getInt(TIME_LEFT_KEY)
            restoreGame()
        }else{
            //if the bundle is empty, just reset the game
            resetGame()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        //inflate the menu item
        menuInflater.inflate(R.menu.menu, menu)

        //returnt true to let the activity know that the menu has been setup
        return true
    }


    //this method is called whenever an item in the menu is selected
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //if the item selected from the menu is the one defined by me, do something
        if(item?.itemId == R.id.action_settings){
            showInfo()
        }

        //return true to let the activity know that the event has been handled
        return true
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

    //this method restores the state of the app
    private fun restoreGame(){

        //here i grab the restored/modified value of the score
        val restoredScore = getString(R.string.your_score, score.toString())
        gameScoreTextView.text = restoredScore

        //here i grab the value of the restored/modified value of the time left var
        val restoredTime = getString(R.string.time_left, Integer.toString(timeLeft))
        timeLeftTextView.text = restoredTime

        //here, i create another timer that starts from the restored time left value
        countDownTimer = object : CountDownTimer(timeLeft * 1000.toLong(), countDownInterval){
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

        //i start the timer because the state has been restored and the timer needs to
        //go on
        countDownTimer.start()

        //the game remains started even after a configuration change
        gameStarted = true
    }

    private fun showInfo(){
        val dialogTitle = getString(R.string.about_title, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.about_message)

        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.create().show()

    }

    //define the companion object that stores two labels
    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }
}
