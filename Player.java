import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;

public class Player extends Thread {

	private String pathToFile;
	private ArrayList<Card> hand;
	private int playerNumber;
	private int preferredNumber;

	Player(int playerNumber) {
		this.hand = new ArrayList<Card>();
		this.playerNumber = playerNumber+1;
		this.preferredNumber = playerNumber;
		this.pathToFile = String.format(".%1$soutput%1$splayer%2$d.txt", File.separator, playerNumber+1);
		createOutputFile(pathToFile);
	}

	public ArrayList<Card> getHand() { return hand; }

	public int getPlayerNumber() { return playerNumber; }

	public void updatePreferredNumber() {
		this.preferredNumber = mostCommonValueInHand();
	}

	/**
	 * This adds a card to the last value of the player's hand attribute.
	 *
	 * @param card  The card to be added to the hand attribute.
	 */
	public void addCard(Card card) {
		int sizeOfHand = hand.size();
		hand.add(sizeOfHand, card);
	}

	/**
	 * This goes through the hand attribute to find the most common value of
	 * Card, and setting the player's preferred number to the most common value.
	 *
	 * @return The most common value in the player's hand attribute.
	 */
	public int mostCommonValueInHand() {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		// Populate the map with the Cards in the player's hand as the keys,
		// and the amount of those Cards as the values.
		for (Card currentCard : hand) {
			int currentCardValue = currentCard.getValue();
			if (!map.containsKey(currentCardValue)) map.put(currentCardValue, 0);
			int previousValue = map.get(currentCardValue);
			map.replace(currentCardValue, previousValue, ++previousValue);
			// If the new amount of cards is above 50%, then it holds majority
			// of the hand, making it the most preferred card.
			if (previousValue >= (hand.size() / 2)) preferredNumber = currentCardValue;
		}
		return preferredNumber;
	}

	/**
	 * This method takes a card from the top of the drawing deck, and adds it
	 * to the hand attribute of the player.
	 *
	 * @param drawDeck  The deck that the player is drawing from
	 * @return The Card that has been drawn from the drawing deck
	 */
	public Card drawCard(CardDeck drawDeck) {
		Card drawnCard = drawDeck.moveTopCard();
		hand.add(hand.size(), drawnCard);
		return drawnCard;
	}

	/**
	 * This method takes a card that is not the preferred value,
	 * and discards it from the player's hand.
	 *
	 * @param discardDeck  The deck that the player discards to
	 * @return The Card that has been discarded by the player
	 */
	public Card discardCard(CardDeck discardDeck) {
		Card discardedCard = null;
		for (int i = 0; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if (currentCard.getValue() != preferredNumber) {
				discardDeck.addCard(currentCard);
				hand.remove(currentCard);
				return currentCard;
			}
		}
		return discardedCard;
	}

	/**
	 * This method creates an output file for a player in a specified location
	 * This is the same as the 'createOutputFile' in 'CardDeck'
	 * {@link CardDeck#createOutputFile(String)}
	 *
	 * @param pathToFile  The desired path for the output file of the deck
	 */
	public void createOutputFile(String pathToFile) {
		try {
			File outputFile = new File(pathToFile);
			outputFile.getParentFile().mkdirs();
			outputFile.createNewFile();
			BufferedWriter bWriteClearer = new BufferedWriter(new FileWriter(outputFile));
			bWriteClearer.write("");
			bWriteClearer.close();
		} catch (IOException e) {
			System.out.println("There was an error creating an output file.");
		}
	}

	/**
	 * This writes a specified string to the output file created in
	 * 'createOutputFile': {@link #createOutputFile(String)}.
	 *
	 * @param stringToWrite  The string to be written to the output file
	 */
	public void writeToFile(String stringToWrite) {
		try {
			BufferedWriter bWrite = new BufferedWriter(new FileWriter(pathToFile, true));
			bWrite.write(stringToWrite + "\n");
			bWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method checks if all the cards in a player's hand are the same,
	 * meaning that they have won the game.
	 *
	 * @return if the Cards are all the same, true. False if not.
	 */
	public boolean hasWon() {
		Card leadCard = hand.get(0);
		for (int i = 1; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if (!currentCard.equals(leadCard)) return false;
		}
		return true;
	}

	/**
	 * This returns the contents of the hand attribute in a string form.
	 *
	 * @return  The contents of the hand attribute.
	 */
	public String toString() {
		return (Arrays.toString(hand.toArray()));
	}
}
