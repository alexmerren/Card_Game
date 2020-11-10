import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainDeck {

    private int amountOfPlayers;
    private ArrayList<Card> deck;
    private String pathToFile;

    MainDeck(String locationOfDeck, int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
        this.pathToFile = locationOfDeck;
        this.deck = importDeck();
    }

    public int getSize() {
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * This sets the deck attribute to the values found in a specified file.
     */
    public ArrayList<Card> importDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        String line;
        try {
            File deckFile = new File(pathToFile);
            BufferedReader bRead = new BufferedReader(new FileReader(deckFile));
            while ((line = bRead.readLine()) != null) {
                Card newCard = new Card(Integer.parseInt(line));
                deck.add(newCard);
            }
            bRead.close();
            if (deck.size() != (8 * amountOfPlayers)) throw new IOException();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the main deck file, please try again.");
            System.exit(1);
        } catch (IOException e) {
            System.out.printf("There are %d cards when there should be %d\n",
                                deck.size(), (8 * amountOfPlayers));
            System.exit(2);
        }
        return deck;
    }

    /**
     * This returns the contents of the deck attribute.
     *
     * @return The content of the deck attribute.
     */
    public String toString() {
        return (Arrays.toString(deck.toArray()));
    }
}
