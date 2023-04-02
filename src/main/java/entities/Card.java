package entities;

import enums.Rank;
import enums.Suit;

import java.util.HashMap;
import java.util.Map;

/**
 * This record represents a card, and contains functionality to create a card and definitions of what makes up a card (suit and rank)
 *
 * @author aine mccaughey
 * @version 1.0
 */
public record Card(Rank rank, Suit suit) {

    /**
     * Build map of character code to suit
     *
     * @return Map<Character, Suit> suitMap
     */
    private static Map<Character, Suit> getSuitMap() {
        return Map.of(
                'C', Suit.CLUB,
                'D', Suit.DIAMOND,
                'H', Suit.HEART,
                'S', Suit.SPADE
        );
    }

    /**
     * Build map of character code to rank
     *
     * @return Map<Character, Rank> rankMap
     */
    private static Map<Character, Rank> getRankMap() {
        Map<Character, Rank> rankMap = new HashMap<>();
        rankMap.put('A', Rank.ACE);
        rankMap.put('2', Rank.TWO);
        rankMap.put('3', Rank.THREE);
        rankMap.put('4', Rank.FOUR);
        rankMap.put('5', Rank.FIVE);
        rankMap.put('6', Rank.SIX);
        rankMap.put('7', Rank.SEVEN);
        rankMap.put('8', Rank.EIGHT);
        rankMap.put('9', Rank.NINE);
        rankMap.put('T', Rank.TEN);
        rankMap.put('J', Rank.JACK);
        rankMap.put('Q', Rank.QUEEN);
        rankMap.put('K', Rank.KING);

        return rankMap;
    }

    /**
     * Helper method to allow for easy creation of a card
     *
     * @param rank char
     * @param suit char
     * @return Card
     */
    public static Card CreateCard(char rank, char suit) {
        Rank cardRank = getRankMap().get(rank);
        Suit cardSuit = getSuitMap().get(suit);

        if(cardRank == null || cardSuit == null) {
            throw new IllegalArgumentException("Invalid Card");
        }
        return new Card(cardRank, cardSuit);
    }
}
