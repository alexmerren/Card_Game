import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    static int desiredValue;
    static Card card;

    @BeforeAll
    public static void setUp() {
        desiredValue = 12;
        card = new Card(desiredValue);
    }

    @Test
    public void testGetValue() {
        assertEquals(desiredValue, card.getValue());
    }

    @Test
    public void testToString() {
        String cardStr = card.toString();
        assertEquals("12", cardStr);
    }

    @Test
    public void testEquals() {
        Card testCard = new Card(desiredValue);
        assertEquals(card, testCard);
    }
}
