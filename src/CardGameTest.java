import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardGameTest {

    static Player testPlayer;
    static CardDeck drawDeck;
    static CardDeck discardDeck;

    @Before
    public void setUp() {
        testPlayer = new Player(1);
        drawDeck = new CardDeck(1);
        discardDeck = new CardDeck(2);
    }

    // TODO
    @Test
    public void testTakeTurn() {
        // CODE:    Give the testPlayer a random hand
        // CODE:    Give the drawDeck and discardDeck some random cards
        // CODE:    
    }

}