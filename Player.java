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
		this.playerNumber = playerNumber+1;
		this.preferredNumber = playerNumber+1;
		this.pathToFile = String.format(".%1$soutput%1$splayer%2$d.txt", File.separator, playerNumber+1);
		createOutputFile(pathToFile);
	}


	// Getters
	public ArrayList<Card> getHand() { return hand; }

	public int getPlayerNumber() { return playerNumber; }

	public int getPreferredNumber() { return preferredNumber; }

	// Setters
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

	public Card drawCard(CardDeck drawDeck) {
		Card drawnCard = drawDeck.moveTopCard();
		hand.add(hand.size(), drawnCard);
		return drawnCard;
	}

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

	public void takeTurn(CardDeck drawDeck, CardDeck discardDeck) {
		// Output the current player's beginning hand.
		System.out.printf("Player %d has the hand %s\n", playerNumber, hand);

		// Get the current player to draw a card.
		Card drawnCard = drawCard(drawDeck);
		String drawString = String.format("Player %d has drawn a %d from deck %d", playerNumber, drawnCard.getValue(), drawDeck.getDeckNumber());
		System.out.println(drawString);
		writeToFile(drawString);

		// Get the current player to discard a card.
		Card discardedCard = discardCard(discardDeck);
		String discardString = String.format("Player %d has discarded a %d to deck %d", playerNumber, discardedCard.getValue(), discardDeck.getDeckNumber());
		System.out.println(discardString);
		writeToFile(discardString);

		// Output the current player's ending hand.
		System.out.printf("Player %d has the hand %s\n", playerNumber, hand);
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
