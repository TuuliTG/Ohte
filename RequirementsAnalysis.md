# Requirements analysis

* The project will be a Master Mind Game. The game is for two players and in this project the *codemaker* part will be played by the computer. It is not necessary to have many user roles in the software. Maybe there will be a possibility to save player statistics. 

## About the game
* A Decoding board, 12 rows of 4 holes on each row. 4 additional small holes on each row for key pegs. DONE
  * Code pegs : 6 different colours, will be places on the bigger holes on the board. DONE
  * Black and white *key pegs* for the feedback DONE
* The *codemaker* will give feedback after each guess: a black key peg is for a right colour in a right position and a white key peg is for a right colour in a wrong position. DONE
* The game will end if the *codebreaker* breaks the code or if there are no more guesses left. DONE

## System requirements
* The software can be run on Linux- and OSX- systems. 
* An unfinished game as well as player information and statistics will be saved on local disk. (EDIT: PARTLY DONE: Player and game information is saved on local disk.)

## User Interface
* There will be a graphic UI. DONE
* The player can start a new game (DONE), save current game and exit the current game (DONE). 
* The player won't see the code, that the *codemaker* has chosen. 
* The player can make a guess and then the computer will give it's response. DONE
* The game will count how many guesses the player does before getting the right solution. DONE
* The game will time the duration of the game DONE and possibly also the duration of each guess
* Settings:
  * Are blanks and/or duplicates allowed on the *code*. 
  * how many guesses are allowed (DONE)

## References
https://en.wikipedia.org/wiki/Mastermind_(board_game)
