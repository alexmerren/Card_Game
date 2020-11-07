import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CardGame {

	CardGame() {}

	public static void main(String[] args) {

		CardGame c = new CardGame();

		BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));
		int amountOfPlayers = 0;
		MainDeck masterDeck = null;

		try {
			// Get the initial information needed to play the game.
			//amountOfPlayers = Integer.parseInt(bRead.readLine());
			//masterDeck = new MainDeck(bRead.readLine(), amountOfPlayers);
			amountOfPlayers = 2;
			masterDeck = new MainDeck("./data/two.txt", amountOfPlayers);
		} catch (NumberFormatException nfe) {
			System.out.println("You did not enter a valid number of players, please try again.");
		}
//		catch (IOException ioe) {
//			System.out.println("There was an error reading the inputs, try again.");
//		}

		// Initialise an ArrayList of all the Players and CardDecks in the game.
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<CardDeck> decks = new ArrayList<CardDeck>();

		// Go through the amount of Players, and create that amount of
		// decks and players.
		for (int i = 0; i < amountOfPlayers; i++) {
			players.add(new Player(i));
			//players.get(i).start();
			decks.add(new CardDeck(i));
			//decks.get(i).start();
		}

		// Distribute the cards to the players.
		for (int i = 0; i < (masterDeck.getSize() / 2); i++) {
			Card currentCard = masterDeck.getDeck().get(i);
			players.get(i % amountOfPlayers).addCard(currentCard);
		}

		// Distribute the cards to the decks.
		for (int i = (masterDeck.getSize() / 2); i < masterDeck.getSize(); i++) {
			Card currentCard = masterDeck.getDeck().get(i);
			decks.get(i % amountOfPlayers).addCard(currentCard);
		}

		// Write all the starting hands for the players.
		for (int i = 0; i < players.size(); i++) {
			Player currentPlayer = players.get(i);
			currentPlayer.writeToFile(String.format("Player %d starting hand %s", i + 1, currentPlayer.toString()));
			CardDeck currentDeck = decks.get(i);
			currentDeck.writeToFile(String.format("Deck %d starts with %s", i + 1, currentDeck.toString()));
		}

		boolean hasWon;
		int winningPlayer;

		// Update all the players' preferred numbers,
		// and check if any of them have won.
		for (Player currentPlayer : players) {
			currentPlayer.updatePreferredNumber();
			if (currentPlayer.hasWon()) {
				winningPlayer = currentPlayer.getPlayerNumber()+1;
				hasWon = true;
			}
		}

		// Code for taking turns goes here.




		/* TODO actually write out this pseudo-code
		# INPUT: 	Get the amount of players in the game "n".
		# INPUT: 	Get the location of the deck of cards "locationOfMasterDeck".
		# CODE: 	Write this location into the importDeck() method in CardDeck.
		# CODE: 	Create "n" amount of decks that are used in playing the game.
		# CODE: 	Create "n" amount of players that are used in playing the game.
		# CODE: 	Distribute the cards to all of the players and all of the decks and players.
		# WRITE: 	Write all of the starting hands and decks to their respective files.
		# CODE:	Update all the preferredNumbers for all the players, using mostCommonValueInHand() method.
		CODE: 	TakeTurn() for each of the players in the game:
			WRITE: 	Write the beginning hand of the turn to the terminal.
			CODE: 	Discard a card using the discardCard() method in the player class.
			OUTPUT:	Output the Card that the player discarded and to which deck.
			WRITE: 	Write the Card that the player discarded and to which deck.
			CODE:  	Pick up a card using the drawCard() method in the player class.
			OUTPUT: Output the Card that the player drew from which the deck.
			WRITE:	Write the Card that the player drew from which deck.
		CODE: 	Repeat TakeTurn() until a player has hasWon() is true.
		OUTPUT:	Output to the terminal showing that a player has won the game and has notified the other players that they have won.
		OUTPUT:	Output to the terminal that the other players have exited and their hands.
		OUTPUT:	Output to the terminal which player has won, and that they exit the game.
		OUTPUT: Output to the terminal the winning hand of the player.
		WRITE:	The finishing hand of all the players to their respective files.
		WRITE: 	The contents of the all the decks to their respective files.
		 */
	}
}