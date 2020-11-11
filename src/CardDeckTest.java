import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CardDeckTest {

    static CardDeck deck;
    static int cardDeckNumber;
    static ArrayList<Card> alternateDeck;
    static String pathToFile;

    @Before
    public  void setUp() {
        cardDeckNumber = 12;
        deck = new CardDeck(cardDeckNumber);
        alternateDeck = new ArrayList<Card>();
    }

    @Test
    public void testGetDeck() {
        int testValue = 1;
        deck.addCard(new Card(testValue));
        alternateDeck.add( alternateDeck.size(), new Card(testValue));
        assertEquals(alternateDeck, deck.getDeck());
    }

    @Test
    public void testGetDeckNumber() {
        assertEquals(cardDeckNumber, deck.getDeckNumber());
    }

    @Test
    public void testAddCard() {
        CardDeck testDeck = new CardDeck(1);
        testDeck.addCard(new Card(5));
        int testDeckSize = testDeck.getDeck().size();
        Card lastCard = testDeck.getDeck().get(testDeckSize-1);
        assertEquals(5, lastCard.getValue());
    }

    @Test
    public void testMoveTopCard() {
        CardDeck testDeck = new CardDeck(1);
        for (int i = 1; i <= 4; i++) {
            testDeck.addCard(new Card(i));
        }
        Card topCard = testDeck.moveTopCard();
        assertEquals(1, topCard.getValue());
    }

    @Test
    public void testCreateOutputFile() {
        CardDeck testDeck = new CardDeck(1);
        pathToFile = String.format(".%1$soutput%1$sdeck%2$s.txt", File.separator, testDeck.getDeckNumber());
        testDeck.createOutputFile(pathToFile);
        assertTrue(new File(pathToFile).exists());
    }

    @Test
    public void testWriteToFile() {
        String stringToWrite = "This is the written string";
        assertTrue(deck.writeToFile(stringToWrite));
    }

    @Test
    public void testToString() {
        String toString = Arrays.toString(deck.getDeck().toArray());
        assertEquals(toString, deck.toString());
    }

}
