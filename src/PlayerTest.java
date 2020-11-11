import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    static Player player;
    static int playerNumber;

    @BeforeAll
    public static void setUp() {
        playerNumber = 12;
        player = new Player(playerNumber);
    }

    @Test
    public void testGetPlayerNumber() {
        assertEquals(playerNumber, player.getPlayerNumber());
    }

    @Test
    public void testAddCard() {
        player.addCard(new Card(5));
        int sizeOfHand = player.getHand().size();
        Card lastCard = player.getHand().get(sizeOfHand-1);
        assertEquals(5, lastCard.getValue());
    }

    @Test
    public void testMostCommonValueInHandCardNumber() {
        Player testPlayer = new Player(1);
        testPlayer.addCard(new Card(8));
        testPlayer.addCard(new Card(8));
        testPlayer.addCard(new Card(2));
        testPlayer.addCard(new Card(1));
        assertEquals(8, testPlayer.mostCommonValueInHand());
    }

    @Test
    public void testMostCommonValueInHandPlayerNumber() {
        for (int i = 1; i <= 4; i++) {
            player.addCard(new Card(i));
        }
        assertEquals(playerNumber, player.mostCommonValueInHand());
    }

    @Test
    public void testDrawCard() {
        int expectedValue = 10;
        CardDeck testDeck = new CardDeck(1);
        testDeck.addCard(new Card(expectedValue));
        assertEquals(expectedValue, player.drawCard(testDeck).getValue());
    }

    @Test
    public void testDiscardCard() {
        int expectedValue = 10;
        CardDeck testDeck = new CardDeck(1);
        Player testPlayer = new Player(1);
        testPlayer.addCard(new Card(expectedValue));
        Card frontCard = testPlayer.getHand().get(0);
        assertEquals(expectedValue, testPlayer.discardCard(testDeck).getValue());
    }

    @Test
    public void testCreateOutputFile() {
        String path = String.format(".%1$soutput%1$splayer%2$d.txt", File.separator, player.getPlayerNumber());
        player.createOutputFile(path);
        assertTrue(new File(path).exists());
    }

    @Test
    public void testWriteToFile() {
        String stringToWrite = "This is the written string";
        assertTrue(player.writeToFile(stringToWrite));
    }

    @Test
    public void testHasWon() {
        Player testPlayer = new Player(1);
        for (int i = 1; i <= 4; i++) {
            testPlayer.addCard(new Card(1));
        }
        assertTrue(testPlayer.hasWon());
    }

    @Test
    public void testToString() {
        String toString = Arrays.toString(player.getHand().toArray());
        assertEquals(toString, player.toString());
    }

}
