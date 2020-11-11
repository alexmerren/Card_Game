import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainDeckTest {

    static int amountOfPlayers;
    static String pathToFile;
    static MainDeck deck;

    @Before
    public void setUp() {
        amountOfPlayers = 2;
        pathToFile = "./data/2.txt";
        deck = new MainDeck(pathToFile, amountOfPlayers);
    }

    @Test
    public void testImportDeck() {
        ArrayList<Card> testDeck = new ArrayList<Card>();
        String line;
        try {
            BufferedReader bRead = new BufferedReader(new FileReader(pathToFile));
            while ((line = bRead.readLine()) != null) {
                Card newCard = new Card(Integer.parseInt(line));
                testDeck.add(newCard);
            }
            bRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(testDeck, deck.importDeck());
    }

    @Test
    public void testGetSize() {
        assertEquals(8 * amountOfPlayers, deck.getSize());
    }

    @Test
    public void testGetDeck() {
        ArrayList<Card> importedDeck = deck.importDeck();
        assertEquals(importedDeck, deck.getDeck());
    }

    @Test
    public void testToString() {
        String deckString = deck.getDeck().toString();
        assertEquals(deckString, deck.toString());
    }

}
