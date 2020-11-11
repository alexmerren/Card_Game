import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardGameTest {

    Player testPlayer, actualPlayer;
    CardDeck testDrawDeck, actualDrawDeck;
    CardDeck testDiscardDeck, actualDiscardDeck;
    CardGame gameInstance;

    @Before
    public void setUp() {
        testPlayer = new Player(10);
        testDrawDeck = new CardDeck(10);
        testDiscardDeck = new CardDeck(11);
        actualPlayer = new Player(11);
        actualDrawDeck = new CardDeck(11);
        actualDiscardDeck = new CardDeck(12);
        gameInstance = new CardGame();
    }

    @Test
    public void testTakeTurn() {
        for (int i = 1; i <= 4; i++) {
            // Populate the test objects and actual objects with Cards
            testPlayer.addCard(new Card(i));
            actualPlayer.addCard(new Card(i));
            testDrawDeck.addCard(new Card(i + 4));
            actualDrawDeck.addCard(new Card(i + 4));
            testDiscardDeck.addCard(new Card(i + 8));
            actualDiscardDeck.addCard(new Card(i + 8));
        }

        // Run the actual takeTurn() method on the actual players and decks
        gameInstance.takeTurn(actualPlayer, actualDrawDeck, actualDiscardDeck);

        // Get the actual value of what happened during the takeTurn method.
        Card actualDrawCard = actualPlayer.getHand().get(actualPlayer.getHand().size()-1);
        // Use the drawCard method using the test objects
        Card expectedDrawCard = testPlayer.drawCard(testDrawDeck);
        // Make sure these two are equal.
        assertEquals(expectedDrawCard.getValue(), actualDrawCard.getValue());

        // Get the actual value of what happened during the takeTurn method.
        Card actualDiscardCard = actualDiscardDeck.getDeck().get(actualDiscardDeck.getDeck().size()-1);
        // Use the discardCard method using the test objects
        Card expectedDiscardCard = testPlayer.discardCard(testDiscardDeck);
        // Make sure these two are equal.
        assertEquals(expectedDiscardCard.getValue(), actualDiscardCard.getValue());
    }

}