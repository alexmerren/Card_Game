import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CardGame {

	public static void main(String[] args) {

		CardGame gameInstance = new CardGame();

		BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));
		int amountOfPlayers = 0;
		MainDeck masterDeck = null;

		try {
			// Get the initial information needed to play the game.
			System.out.println("Please enter the amount of players in the game: ");
			amountOfPlayers = Integer.parseInt(bRead.readLine());
			System.out.println("Please enter the location of the card pack that will be used in the game: ");
			masterDeck = new MainDeck(bRead.readLine(), amountOfPlayers);
		} catch (NumberFormatException nfe) {
			System.out.println("You did not enter a valid number of players, please try again.");
		} catch (IOException ioe) {
			System.out.println("There was an error reading the inputs, try again.");
		}

		// Initialise an ArrayList of all the Players and CardDecks in the game.
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<CardDeck> decks = new ArrayList<CardDeck>();

		// Go through the amount of Players, and create that amount of
		// decks and players.
		for (int i = 0; i < amountOfPlayers; i++) {
			players.add(new Player(i+1));
			decks.add(new CardDeck(i+1));
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
			currentPlayer.start();
			currentPlayer.updatePreferredNumber();
			currentPlayer.writeToFile(String.format("Player %d starting hand: %s", i + 1, currentPlayer.toString()));

			CardDeck currentDeck = decks.get(i);
			currentDeck.start();
			currentPlayer.updatePreferredNumber();
			currentDeck.writeToFile(String.format("Deck %d starts with: %s", i + 1, currentDeck.toString()));
		}

		boolean hasWon = false;
		int winningPlayer = 0;

		// Keep going through the players until someone wins.
		while (!hasWon) {
			for (int i = 0; i < players.size(); i++) {
				// Get the current player, and designate the drawing and
				// discarding decks.
				Player currentPlayer = players.get(i);
				CardDeck drawDeck = decks.get(i);
				CardDeck discardDeck = decks.get((i + 1) % amountOfPlayers);

				gameInstance.takeTurn(currentPlayer, drawDeck, discardDeck);

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
			CardDeck currentDeck = decks.get(i);

			if (currentPlayer.getPlayerNumber() == winningPlayer) {
				currentPlayer.writeToFile(String.format("Player %d wins", currentPlayer.getPlayerNumber()));
			} else {
				currentPlayer.writeToFile(String.format("Played %1$d has informed player %2$d that played %1$d has won", winningPlayer, currentPlayer.getPlayerNumber()));
				currentPlayer.writeToFile(String.format("Played %d exits", currentPlayer.getPlayerNumber()));
			}
			currentPlayer.writeToFile(String.format("Player %d final hand: %s", i + 1, currentPlayer.getHand().toString()));
			currentDeck.writeToFile(String.format("Deck %d contents: %s", i + 1, currentDeck.getDeck().toString()));
		}
	}


	/**
	 * This method allows players to take turns in the CardGame main method.
	 *
	 * @param player  The Player who's turn it is to draw and discard Cards
	 * @param drawDeck  The CardDeck that the player will draw the Card from
	 * @param discardDeck  The CardDeck that the player will discard a Card to
	 */
	public synchronized void takeTurn(Player player, CardDeck drawDeck, CardDeck discardDeck) {
		// Output the current player's beginning hand.
		System.out.printf("Player %d has the hand %s\n", player.getPlayerNumber(), player.getHand());

		// Get the current player to draw a card.
		Card drawnCard = player.drawCard(drawDeck);
		String drawString = String.format("Player %d has drawn a %d from deck %d", player.getPlayerNumber(), drawnCard.getValue(), drawDeck.getDeckNumber());
		System.out.println(drawString);
		player.writeToFile(drawString);

		// Get the current player to discard a card.
		Card discardedCard = player.discardCard(discardDeck);
		String discardString = String.format("Player %d has discarded a %d to deck %d", player.getPlayerNumber(), discardedCard.getValue(), discardDeck.getDeckNumber());
		System.out.println(discardString);
		player.writeToFile(discardString);

		// Output the current player's ending hand.
		System.out.printf("Player %d has the hand %s\n", player.getPlayerNumber(), player.getHand());
	}
}