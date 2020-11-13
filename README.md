# Card Game Assignment
This is a Card Game application created for the ECM2414 Pair Programming Assignment

## How to compile the game
In order to compile the game from the source code:
```
    cd src
    javac {Card.java,CardDeck.java,MainDeck.java,Player.java,CardGame.java} -d ../out
```
This will put all the bytecode (.class) files in a folder called _out_.

## How to compile the tests
### Windows
In order to compile the tests from the source code:
```
    cd src
    javac -cp ".;../lib/*" {CardTest.java,CardDeckTest.java,MainDeckTest.java,PlayerTest.java,CardGameTest.java} -d ../out
```
### MacOS
In order to compile the tests from the source code:
```
    cd src
    javac -cp ".:../lib/*" {CardTest.java,CardDeckTest.java,MainDeckTest.java,PlayerTest.java,CardGameTest.java} -d ../out
```

## How to run the game
Download the bytecode (.class files) and type:
```
    cd out
    java CardGame
```
You will then be prompted to enter:
* The amount of players
* The location of the Pack to use for the game.

For testing, I have included a file called "10.txt".
In the same vein, you must input 10 for the amount of players in the game.

If you enter the correct information, the game will run!

## How to run the tests
### Windows
To run the tests, you must type:
```
    cd out
    java -cp ".;../lib/*" org.junit.runner.JUnitCore TestSuite
```

### MacOS
To run the tests, you must type:
```
    cd out
    java -cp ".:../lib/*" org.junit.runner.JUnitCore TestSuite
```

## How to view the output files
After running the game, the _CardGame_ class will create a directory called
_output_.

This will contain all the moves made by all the players,
and the starting contents and ending contents of the decks.
