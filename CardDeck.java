import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CardDeck extends Thread {

	// Attributes
	private ArrayList<Card> deck;
	private int deckNumber;
	private String pathToFile;

	// Constructors
	CardDeck(int deckNumber) {
		// Constructor for all the subsequent decks in the game.
		this.deckNumber = deckNumber;
		this.deck = new ArrayList<Card>();
		this.pathToFile = String.format("./output/player%d.txt", deckNumber);
	}

	// Getters
	public ArrayList<Card> getDeck() { return deck; }

	public int getDeckNumber() { return deckNumber; }

	public Card getTopCard() { return deck.get(0); }

	// Setters
	public void setCard(int index, Card card) {
		deck.set(index, card);
	}

	// Auxiliary Methods
	public void writeToFile(String stringToWrite) {
		try {
			BufferedWriter bWrite = new BufferedWriter(new FileWriter(pathToFile, true));
			bWrite.write(stringToWrite);
			bWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return String.format("%d, %d, %d, %d",
					deck.get(0).getValue(),
					deck.get(1).getValue(),
					deck.get(2).getValue(),
					deck.get(3).getValue());
	}
}
