# Requirements analysis

* The project will be an implementation of the classic *Master Mind Game*. The game is for two players and in this project the *codemaker* part will be played by the computer.

## About the game
* A Decoding board, 12 rows of 4 holes on each row. 4 additional small holes on each row for key pegs.
  * Code pegs : 6 different colours, will be places on the bigger holes on the board.
  * Black and white *key pegs* for the feedback
* The *codemaker* will give feedback after each guess: a black key peg is for a right colour in a right position and a white key peg is for a right colour in a wrong position.
* The game will end if the *codebreaker* breaks the code or if there are no more guesses left.

## System requirements
* The software can be run on Linux- and OSX- systems. 
* Java 11 is needed to run the game. 
* Some player and game statistics will be saved on local disk.

## User Interface
* There will be a graphic UI.
* The player can start a new game and exit the current game. 
* The player won't see the code, that the *codemaker* has chosen. 
* The player can make a guess and the computer will give it's response.
* The game will count how many guesses the player does before getting the right solution.
* The game will time the duration of the game
* The program will save the players name and game records. 
* In the *Score board* window the user can see the played games in a descending order by score. 
* Settings: 
  * how many guesses are allowed

## Improvement ideas:
* It would be nice that the player could have more options to customize the game. For example allowing blanks or setting a time limit for each guess. Also a game difficulty setting would be nice. 

* In the  **No guesses left** situation it would be nice to have an opportunity to see the right answer.

## References
https://en.wikipedia.org/wiki/Mastermind_(board_game)
