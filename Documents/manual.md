# Manual

### Downloading and running the app:

1) You can run the game by downloding the jar from [here](https://github.com/TuuliTG/Ohte/releases/tag/v1.1)

Go to the directory where you downloaded the jar and open the game with command `java -jar Mastermind-1.0-SNAPSHOT.jar`

2) You can also downlad the project zip file from [here]
Go to the downloaded folder and from there to folder "Mastermind". 
Run the game from command line with command `compile exec:java -Dexec.mainClass=mastermind.gui.Main` 


### New Game
Type your name here and press **Start a New Game**. If the name exists in the database, the app will log you in with your existing stats. 

### Playing the game
Your task is to solve the *code* of four colors. One color can appear several times. In default mode you have 12 guesses to use before the game ends. 
The game doesn't allow blanks so all four pieces have to have color. 

Choosing the color happens by clicking on the piece. The colors change in this order: grey (blank) -> black -> white -> blue -> red -> green -> yellow.
When you are happy with the guess press the **Accept Guess** button. The game will give feedback by setting black and white pegs on the rightmost tile. Black peg means right color in right place. White means right color in wrong place and grey means wrong color. The placing of the feedback pieces doesn't tell anything to the player as the app places them randomly. 

When you have solved the code, a game over window opens. From there you can start a new game or quit the game. When you solve the code, the app evaluates your score and time and saves the data in the database. If you beat your previous best score / time the app will update your stats. Also the amount of games won will increase by one.

If you run out of guesses the game over window pops up. You can start a new game or quit the game from there. The app doesn't update any stats. 

### Score board
In the options scene you can view the game stats. All played games will be shown in descending order by score. 

### Options

You can change the amount of guesses allowed here. The default amount of guesses is 12. If you change the settings and click **start a new game** button a new game will start and the size of the game board will change accordingly. 