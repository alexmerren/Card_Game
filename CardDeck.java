import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CardDeck extends Thread {

	// Attributes
	private ArrayList<Card> deck;
	private int deckNumber;
	private String pathToFile;

	// Constructors
	CardDeck(int deckNumber) {
		// Constructor for all the subsequent decks in the game.
		this.deckNumber = deckNumber+1;
		this.deck = new ArrayList<Card>();
		this.pathToFile = String.format(".%1$soutput%1$sdeck%2$d.txt", File.separator, deckNumber+1);
		createOutputFile(pathToFile);
	}

	// Getters
	public ArrayList<Card> getDeck() { return deck; }

	public int getDeckNumber() { return deckNumber; }

	// Setters
	public void setCard(int index, Card card) {
		deck.set(index, card);
	}

	// Auxiliary Methods
	public void addCard(Card card) {
		int sizeOfHand = deck.size();
		deck.add(sizeOfHand, card);
	}

	public Card moveTopCard() {
		Card topCard = deck.get(0);
		deck.remove(topCard);
		return topCard;
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

	public String toString() {
		return (Arrays.toString(deck.toArray()));
	}
}
