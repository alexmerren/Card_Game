import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CardDeck extends Thread {

	private ArrayList<Card> deck;
	private int deckNumber;
	private String pathToFile;

	CardDeck(int deckNumber) {
		this.deckNumber = deckNumber;
		this.deck = new ArrayList<Card>();
		this.pathToFile = String.format(".%1$soutput%1$sdeck%2$d.txt", File.separator, deckNumber+1);
		createOutputFile(pathToFile);
	}

	public ArrayList<Card> getDeck() { return deck; }

	public int getDeckNumber() { return deckNumber; }

	/**
	 * This adds a card to the last value of the deck attribute.
	 *
	 * @param card  The Card that is going to be added to the deck
	 */
	public void addCard(Card card) {
		int sizeOfHand = deck.size();
		deck.add(sizeOfHand, card);
	}

	/**
	 * This removes to top card of the deck attribute,
	 * and returns it so that it can be moved to a players' hand.
	 *
	 * @return The Card that is at the first value of the deck
	 */
	public Card moveTopCard() {
		Card topCard = deck.get(0);
		deck.remove(topCard);
		return topCard;
	}

	/**
	 * This creates an output file for the instance of the CardDeck.
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
			e.printStackTrace();
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
	 * This returns the contents of the deck attribute in a string form.
	 *
	 * @return  The contents of the deck attribute.
	 */
	public String toString() {
		return (Arrays.toString(deck.toArray()));
	}
}
