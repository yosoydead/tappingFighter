* Appname: teamFighter from the book called **Android Apprentice** by *Bayliss D.*
* [x] First step:
	* 1. [x] Use a Constraint Layout
	* 2. [x] Add two text boxes. The first needs to sit in the top left corner. The second one needs to sit in the top right corner
	* 3. [x] Add a button that sits in the middle of the screen
* [x] Second step:
	* 1. [x] Add an ID to the Button
	* 2. [x] Change the text inside the Button
	* 3. [x] Add an ID for each TextView
	* 4. [x] Change the text inside each TextView. Inside both TextViews there will be a placeholder for some text that will be added later
	* 5. [x] In the main activity add the following empty functions: *incrementScore(), resetGame(), startGame(), endGame()*
	* 6. [x] Create variables for each of the components from the layout
	* 7. [x] Attach an onClick event listener to the button
	* 8. [x] Create a variable that keep track of the score
	* 9. [x] Implement the logic for the *incrementScore()* method
* [x] Third step:
	* 1. [x] Add string resources for every view so you dont use hardcoded values
	* 2. [x] Replace the contents of the *incrementScore()* method because of the string resources now
* [x] Fourth step:
	* 1. [x] Add a flag which tells if the game has been started or not
	* 2. [x] Add a CountDown object
	* 3. [x] Add a countdown interval variable (the rate at which the countdown will decrement)
	* 4. [x] Add a variable that holds the integer representation of the countdown
	* 5. [x] Implement the logic for the *resetGame()* method
	* 6. [x] Implement the *startGame()* method
	* 7. [x] Update the *incrementScore()* method to start the game when the button is tapped
	* 8. [x] Implement the *endGame()* method because the game doesn't know what to do once the timer reaches 0
* [ ] Fifth step (make the app save its state when changing the orientation):
	* 1. [i] Create a companion object (static class) that holds two labels: one for the score and one for the time left
	* 2. [x] Override the *onSaveInstanceState()* method so when it is called, it stores the current score and the current time left and cancels the timer
	* 3. [x] Create a method that restores the state of the app after rotating of changing the state	
