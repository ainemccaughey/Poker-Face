package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HandFactoryTest {
    @Test
    void makeHandFromString_validHand() {
        //Arrange
        String hand = "QC JC KC AC TC";

        Card card1 = Card.CreateCard('Q', 'C');
        Card card2 = Card.CreateCard('J', 'C');
        Card card3 = Card.CreateCard('K', 'C');
        Card card4 = Card.CreateCard('A', 'C');
        Card card5 = Card.CreateCard('T', 'C');

        Hand expected = new Hand(card1, card2, card3, card4, card5);

        //Act
        Hand actual = HandFactory.MakeHandFromString(hand);

        //Assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void makeHandFromArrayOfCodes_validHand() {
        //Arrange
        String[] hand = {"QC", "JC", "KC", "AC", "TC"};

        Card card1 = Card.CreateCard('Q', 'C');
        Card card2 = Card.CreateCard('J', 'C');
        Card card3 = Card.CreateCard('K', 'C');
        Card card4 = Card.CreateCard('A', 'C');
        Card card5 = Card.CreateCard('T', 'C');

        Hand expected = new Hand(card1, card2, card3, card4, card5);

        //Act
        Hand actual = HandFactory.MakeHandFromArrayOfCodes(hand);

        //Assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void makeHandFromArrayOfCodes_invalidCardCode() {
        //Arrange
        String[] hand = {"QC", "JC", "KC", "AC", "T"};

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Hand actual = HandFactory.MakeHandFromArrayOfCodes(hand);
        });

        //Assert
        assertEquals("Invalid Card Code", exception.getMessage());
    }
}