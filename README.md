# Card Game 
This is a card game application created for the __ECM2414 pair programming assignment__. This assignment recieved an 83/100.

## Contents

 - [Compilation](#Compiling) 
 - [Functionality](#Usage)
 - [Output](#Output)

## Compilation

### Production 

In order to compile the game from the source code:

```bash
$ cd src
$ javac {Card.java,CardDeck.java,MainDeck.java,Player.java,CardGame.java} -d ../out
```

This will put all the bytecode files in the __out__ directory.

### Tests 

In order to compile the tests from the source code:

```bash
$ cd src
$ javac -cp ".:../lib/*" {CardTest.java,CardDeckTest.java,MainDeckTest.java,PlayerTest.java,CardGameTest.java} -d ../out
```

If compiling in Windows Command Line, replace the `:` with `;`.

## Functionality

### Production

In order to run the game:

```bash
$ cd out
$ java CardGame
```

For running the game, I have included a test pack called __10.txt__.

When prompted, type in __10__ for the amount of players, and __../data/10.txt__ as the pack of cards.

### Tests

In order to run the tests:

```bash
$ cd out
$ java -cp ".:../lib/*" org.junit.runner.JUnitCore TestSuite
```

Similarly with the production code, to run in windows, replace `:` with `;`.

## Output 

After running the game, the game will create a directory called __output__.

The output directory will contain all the moves made by all players, and the contents of all of the decks throughout the game.
