# Project architecture

## Structure
The program is build in four layers and packages implement those layers:

mastermind.gui -> mastermind.gamelogic -> mastermind.domain -> mastermind.dao

The gui package includes the graphic user interface which is programmed using JavaFx. Mastermind.gamelogic acts as a bridge between the user interface and the program logic (domain and dao). Domain-package has all the basic program logic and dao-package is responsible for saving data to databases and to local disk.

## The User Interface
### Views
* New Game Window
	* Creating a new player / log in
* Game Scene
	* Main game window
* Score Board
	* Shows the game statistics and scores
* Options
	* The player can choose how many guesses are allowed
* Game Over Window
	* Shows the final time
	* New Game, Close and Quit buttons
* Help
	* Game Instructions

The score board, options and game instructions are scenes that can be added to the primary stage. The new game and game over windows have their own stage. 

The user interface is interacting with the GameLogic-class which is then sending tasks to other classes in the domain package. Besides that the gui is also interacting with UserService class for database functions and options class for setting new options. 

### The Program Logic

![class diagram](https://github.com/TuuliTG/Ohte/blob/main/Documents/MastermindLuokkakaavio.jpg)

### Saving data to database and local disk

### Files
The program saves player and game data to separate files on the local disk. The file names are spesified in a config.properties file which is located in Mastermind/src/main/resources. Currently the file names are set to players.txt and games.txt.

Player data is saved in this format:
`name;best time;best score;amount of games won;`

Game data is saved in this format:
`player name;score;time;number of guesses`

## The main functions in the program

### Creating a new player / log in

The program validates the player name by length. If the length is under three or longer than 15, an error message will show  up. 

![Creating a new player](https://github.com/TuuliTG/Ohte/blob/main/Documents/misc/newGameWindow.png)
![Error](https://github.com/TuuliTG/Ohte/blob/main/Documents/misc/newGameError.png)

### Playing

The player can click on the code tiles to change to color (left or right click depending on the direction the player wants).
When the player is satisfied with the guess, he/she can press the accept button to get feedback. If there were blanks left in the guess, and error message will show up and no feedback will be given.

![Game Window](https://github.com/TuuliTG/Ohte/blob/main/Documents/misc/gameWindow.png)

The following sequence diagram shows what happens when the player clicks the accept guess -button. 

![Arvauksen hyväksyminen](https://github.com/TuuliTG/Ohte/blob/main/Documents/MastermindSekvenssikaavio.png)

 

### Game Over

The game ends either when the player runs out of guesses or if he/she solves the code. 

![Game Over Window](https://github.com/TuuliTG/Ohte/blob/main/Documents/misc/gameOverWindow.png)

## Weaknesses in the program 

The PlayerDao and the GameDao classes have repetitive code and it could be refactored. 
The GameScene-class is quite big and it could be still split into smaller classes.
 

**Options:** 

It would be nice that the player could have more options to customize the game. For example allowing blanks or setting a time limit for each guess. 