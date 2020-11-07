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
			amountOfPlayers = 3;
			masterDeck = new MainDeck("./data/three.txt", amountOfPlayers);
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
			players.get(i).start();
			decks.add(new CardDeck(i));
			decks.get(i).start();
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
			currentPlayer.writeToFile(String.format("Player %d starting hand: %s", i + 1, currentPlayer.toString()));
			CardDeck currentDeck = decks.get(i);
			currentDeck.writeToFile(String.format("Deck %d starts with: %s", i + 1, currentDeck.toString()));
		}

		boolean hasWon = false;
		int winningPlayer = 0;

		// Keep going through the players until someone wins.
		while (!hasWon) {
			for (int i = 0; i < players.size(); i++) {
				Player currentPlayer = players.get(i);
				currentPlayer.updatePreferredNumber();
				CardDeck drawDeck = decks.get(i);
				CardDeck discardDeck = decks.get((i + 1) % amountOfPlayers);

				synchronized (players.get(i)) {
					currentPlayer.takeTurn(drawDeck, discardDeck);
				}

				if (currentPlayer.hasWon()) {
					hasWon = true;
					winningPlayer = currentPlayer.getPlayerNumber();
					System.out.printf("Player %d wins\n", winningPlayer);
					break;
				}
			}
		}

		// Notify all the players that someone has won, and
		// write all the contents of the player's hands, and the decks to
		// their respective files.
		for (int i = 0; i < players.size(); i++) {
			Player currentPlayer = players.get(i);
			if (currentPlayer.getPlayerNumber() == winningPlayer) {
				currentPlayer.writeToFile(String.format("Player %d wins", currentPlayer.getPlayerNumber()));
			} else {
				currentPlayer.writeToFile(String.format("Played %1$d has informed player %2$d that played %1$d has won", winningPlayer, currentPlayer.getPlayerNumber()));
				currentPlayer.writeToFile(String.format("Played %d exits", currentPlayer.getPlayerNumber()));
			}
			currentPlayer.writeToFile(String.format("Player %d final hand: %s", i + 1, currentPlayer.getHand().toString()));

			CardDeck currentDeck = decks.get(i);
			currentDeck.writeToFile(String.format("Deck %d contents: %s", i + 1, currentDeck.getDeck().toString()));
		}
	}
}