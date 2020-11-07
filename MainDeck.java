import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainDeck {

    // Attributes
    private int amountOfPlayers;
    private ArrayList<Card> deck;
    private String pathToFile;

    // Constructors
    MainDeck(String locationOfDeck, int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
        this.pathToFile = locationOfDeck;
        importDeck();
    }

    // Getters
    public int getSize() {
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Auxiliary Methods
    public void importDeck() {
        deck = new ArrayList<Card>();
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
        } catch (IOException e) {
            System.out.printf("There are %d cards when there should be %d\n",
                                deck.size(), (8 * amountOfPlayers));
        }
    }

    public String toString() {
        return (Arrays.toString(deck.toArray()));
    }
}
