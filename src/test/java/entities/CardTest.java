package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {

    @Test
    void makeCard_validCard() {
        //Arrange
        char rank = 'T';
        char suit = 'C';

        //Act
        Card card = Card.CreateCard(rank, suit);

        //Assert
        assertEquals(String.valueOf(rank), card.rank().toString());
        assertEquals(String.valueOf(suit), card.suit().toString());
    }

    @Test
    void makeCard_invalidCard() {
        //Arrange
         char rank = 'T';
         char suit = 'T';

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Card card = Card.CreateCard(rank, suit);
        });

        //Assert
        assertEquals("Invalid Card", exception.getMessage());
    }
}
