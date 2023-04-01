package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void isValidHand() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('6', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('4', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        //Act
        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Assert
        assertNotNull(hand);
    }

    @Test
    void isInValidFourCardHand() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('6', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('4', 'S');

        //Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Hand hand = new Hand(card1, card2, card3, card4);
        });

        //Assert
        Assertions.assertEquals("A hand must contain 5 cards", exception.getMessage());
    }

    @Test
    void isInValidSixCardHand() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('6', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('4', 'S');
        Card card5 = Card.CreateCard('4', 'C');
        Card card6 = Card.CreateCard('4', 'D');

        //Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Hand hand = new Hand(card1, card2, card3, card4, card5, card6);
        });

        //Assert
        Assertions.assertEquals("A hand must contain 5 cards", exception.getMessage());
    }

    @Test
    void isHighCard() {
        //Arrange

        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('6', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('4', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isOnePair();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotHighCard() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('6', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('4', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isHighCard();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isOnePair() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('6', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('4', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isOnePair();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotOnePair() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('4', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isOnePair();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isTwoPair() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('4', 'C');
        Card card4 = Card.CreateCard('4', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isTwoPair();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotTwoPair() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('5', 'C');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isTwoPair();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isThreeOfAKind() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('A', 'D');
        Card card3 = Card.CreateCard('A', 'C');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isThreeOfAKind();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotThreeOfAKind() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('7', 'C');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isThreeOfAKind();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isStraight() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('K', 'D');
        Card card3 = Card.CreateCard('J', 'C');
        Card card4 = Card.CreateCard('Q', 'S');
        Card card5 = Card.CreateCard('T', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isStraight();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isStraightNoAce() {
        //Arrange
        Card card1 = Card.CreateCard('9', 'S');
        Card card2 = Card.CreateCard('K', 'D');
        Card card3 = Card.CreateCard('J', 'C');
        Card card4 = Card.CreateCard('Q', 'S');
        Card card5 = Card.CreateCard('T', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isStraight();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotStraight() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('K', 'D');
        Card card3 = Card.CreateCard('Q', 'C');
        Card card4 = Card.CreateCard('J', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isThreeOfAKind();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isFlush() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('A', 'S');
        Card card3 = Card.CreateCard('6', 'S');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('3', 'S');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isFlush();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotFlush() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('7', 'C');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isFlush();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isFullHouse() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('A', 'S');
        Card card3 = Card.CreateCard('A', 'S');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('7', 'S');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isFullHouse();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotFullHouse() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('7', 'C');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isFullHouse();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isFourOfAKind() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('A', 'S');
        Card card3 = Card.CreateCard('A', 'S');
        Card card4 = Card.CreateCard('A', 'S');
        Card card5 = Card.CreateCard('7', 'S');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isFourOfAKind();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotFourOfAKind() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'S');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('7', 'C');
        Card card4 = Card.CreateCard('7', 'S');
        Card card5 = Card.CreateCard('3', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isFourOfAKind();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isStraightFlush() {
        //Arrange
        Card card1 = Card.CreateCard('K', 'C');
        Card card2 = Card.CreateCard('A', 'C');
        Card card3 = Card.CreateCard('J', 'C');
        Card card4 = Card.CreateCard('Q', 'C');
        Card card5 = Card.CreateCard('T', 'C');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isStraightFlush();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotStraightFlush() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('A', 'S');
        Card card3 = Card.CreateCard('A', 'S');
        Card card4 = Card.CreateCard('A', 'S');
        Card card5 = Card.CreateCard('7', 'S');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isStraightFlush();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isRoyalFlush() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'C');
        Card card2 = Card.CreateCard('K', 'C');
        Card card3 = Card.CreateCard('Q', 'C');
        Card card4 = Card.CreateCard('J', 'C');
        Card card5 = Card.CreateCard('T', 'C');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isRoyalFlush();

        //Assert
        assertTrue(actual);
    }

    @Test
    void isNotRoyalFlushUnmatchedSuits() {
        //Arrange
        Card card1 = Card.CreateCard('A', 'S');
        Card card2 = Card.CreateCard('K', 'D');
        Card card3 = Card.CreateCard('Q', 'C');
        Card card4 = Card.CreateCard('J', 'S');
        Card card5 = Card.CreateCard('T', 'H');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isRoyalFlush();

        //Assert
        assertFalse(actual);
    }

    @Test
    void isNotRoyalFlushMatchedSuits() {
        //Arrange
        Card card1 = Card.CreateCard('5', 'D');
        Card card2 = Card.CreateCard('5', 'D');
        Card card3 = Card.CreateCard('7', 'D');
        Card card4 = Card.CreateCard('7', 'D');
        Card card5 = Card.CreateCard('3', 'D');

        Hand hand = new Hand(card1, card2, card3, card4, card5);

        //Act
        boolean actual = hand.isRoyalFlush();

        //Assert
        assertFalse(actual);
    }
}