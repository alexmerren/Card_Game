import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;

public class Player extends Thread {

	// Attributes
	private ArrayList<Card> hand;
	private int playerNumber;
	private int preferredNumber;
	private String pathToFile;

	// Constructors
	Player(int playerNumber) {
		this.hand = new ArrayList<Card>();
		this.playerNumber = playerNumber;
		this.preferredNumber = playerNumber;
		this.pathToFile = String.format(".%1$soutput%1$splayer%2$d.txt", File.separator, playerNumber+1);
		createOutputFile(pathToFile);
	}

	// Getters
	public ArrayList<Card> getHand() { return hand; }

	public int getPlayerNumber() { return playerNumber; }

	public int getPreferredNumber() { return preferredNumber; }

	// Setters
	public void setCard(int handIndex, Card newCard) { hand.set(handIndex, newCard); }

	public ArrayList<Card> setHand(int[] desiredHand) {
		ArrayList<Card> hand = new ArrayList<Card>();
		for (int value : desiredHand) {
			Card currentCard = new Card(value);
			hand.add(currentCard);
		}
		return hand;
	}

	public void updatePreferredNumber() {
		this.preferredNumber = mostCommonValueInHand();
	}

	// Auxiliary Methods
	public void addCard(Card card) {
		int sizeOfHand = hand.size();
		hand.add(sizeOfHand, card);
	}

	public boolean isPreferredCard(Card card) {
		return (card.getValue() == preferredNumber);
	}

	// TODO
	public ArrayList<Card> takeTurn(int drawDeck, int discardDeck) {
		writeToFile(hand.toString());
		// Basically need to add the appropriate methods for doing this,
		// See if I can make this work in it's current form.
		return hand;
	}

	public int mostCommonValueInHand() {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int preferredNumber = playerNumber;
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

	public Card discardCard() {
		// A Card with a value of 0 is returned only if the method
		// cannot find another card to discard, meaning the player has won.
		Card defaultCard = new Card(0);
		Card preferredCard = (new Card(mostCommonValueInHand()));
		// Go through all the cards in the player's hand to find
		// any card that is not the preferred card to replace.
		for (int i = 0; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if (!Objects.equals(currentCard, preferredCard)) {
				hand.set(i, defaultCard);
				return currentCard;
			}
		}
		return defaultCard;
	}

	public ArrayList<Card> drawCard(CardDeck cardDeck) {
		Card currentCard = new Card(0);
		Card newCard = cardDeck.getTopCard();
		for (int i = 0; i < hand.size(); i++) {
			if (currentCard.getValue() == hand.get(i).getValue()) {
				hand.set(i, newCard);
				return hand;
			}
		}
		return hand;
	}

	public void createOutputFile(String pathToFile) {
		try {
			File outputFile = new File(pathToFile);
			outputFile.getParentFile().mkdirs();
			outputFile.createNewFile();
			BufferedWriter bWriteClearer = new BufferedWriter(new FileWriter(outputFile));
			bWriteClearer.write("");
			bWriteClearer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(String stringToWrite) {
		try {
			BufferedWriter bWrite = new BufferedWriter(new FileWriter(pathToFile, true));
			bWrite.write(stringToWrite + "\n");
			bWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean hasWon() {
		Card leadCard = hand.get(0);
		for (int i = 1; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if (!currentCard.equals(leadCard)) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return (Arrays.toString(hand.toArray()));
	}
}
