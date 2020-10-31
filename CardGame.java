import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardGame {

	CardGame() { }

	public static void main(String[] args) {

		CardGame c = new CardGame();
		//int amountOfPlayers = c.inputAmountOfPlayers();
		int amountOfPlayers = 2;
		MainDeck masterDeck = c.inputMasterDeck(amountOfPlayers);
		System.out.println(masterDeck.toString());

		int[] hand = new int[] {1,3,3,4};
		Player player1 = new Player(1, hand);
		System.out.println(player1.getHand());
		System.out.println(player1.getPreferredNumber());
		Player player2 = new Player(2);
		System.out.println(player2.getHand());

		/* TODO actually write out this pseudo-code
		INPUT: 	Get the amount of players in the game "n".
		INPUT: 	Get the location of the deck of cards "locationOfMasterDeck".
		CODE: 	Write this location into the importDeck() method in CardDeck.
		CODE: 	Create "n" amount of decks that are used in playing the game.
		CODE: 	Create "n" amount of players that are used in playing the game.
		CODE: 	Distribute the cards to all of the players and all of the decks and players.
		WRITE: 	Write all of the starting hands and decks to their respective files.
		CODE:	Update all the preferredNumbers for all the players, using mostCommonValueInHand() method.
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

	// TODO comment on this
	public int inputAmountOfPlayers() {
		// Create a BufferedReader that takes in an input from the terminal.
		BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(bRead.readLine());
		} catch (IOException e) {
			System.out.println("Please input a valid amount of players.");
			return inputAmountOfPlayers();
		} catch (NumberFormatException e) {
			System.out.println("Please input a number.");
			return inputAmountOfPlayers();
		}
	}

	// TODO comment on this
	public MainDeck inputMasterDeck(int numberOfPlayers) {
		// Create a BufferedReader that takes in an input from the terminal.
		BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));
		int actualNumberOfCards = 0;
		int expectedNumberOfCards = 8 * numberOfPlayers;
		try {
			//MainDeck masterDeck = new MainDeck(bRead.readLine());
			MainDeck masterDeck = new MainDeck("./src/data/two.txt");
			actualNumberOfCards = masterDeck.getSize();
			// Check that the number of cards that are in the MainDeck is
			// the amount that you expect to be in there.
			// E.g. For 2 players, you have 24 cards (8 * 3).
			if (expectedNumberOfCards != actualNumberOfCards) {
				throw new Exception();
			}
			return masterDeck;
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file given.");
			return inputMasterDeck(numberOfPlayers);
		} catch (IOException e) {
			System.out.println(	"There was an error in reading the file," +
								" please give another file");
			return inputMasterDeck(numberOfPlayers);
		} catch (Exception e) {
			System.out.printf("There are %d cards when there should be %d\n",
								actualNumberOfCards, expectedNumberOfCards);
			return inputMasterDeck(numberOfPlayers);
		}
	}
}
