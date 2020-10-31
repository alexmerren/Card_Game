import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainDeck {

    // Attributes
    private ArrayList<Card> deck;
    private String pathToFile;

    // Constructors
    MainDeck(String locationOfDeck) throws FileNotFoundException, IOException{
        importDeck(locationOfDeck);
    }

    // Getters
    public int getSize() {
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Auxiliary Methods
    public void importDeck(String locationOfDeck) throws FileNotFoundException, IOException{
        this.deck = new ArrayList<Card>();
        String line;
        File deckFile = new File(locationOfDeck);
        BufferedReader bRead = new BufferedReader(new FileReader(deckFile));
        while ((line = bRead.readLine()) != null) {
            Card newCard = new Card(Integer.parseInt(line));
            this.deck.add(newCard);
        }
        bRead.close();
    }

    public String toString() {
        return (Arrays.toString(this.deck.toArray()));
    }
}
