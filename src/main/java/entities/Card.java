package entities;

import enums.Rank;
import enums.Suit;

import java.util.HashMap;
import java.util.Map;

// A card is immutable
public record Card(Rank rank, Suit suit) {

    //build map of character code to suit
    private static Map<Character, Suit> getSuitMap() {
        return Map.of(
                'C', Suit.CLUB,
                'D', Suit.DIAMOND,
                'H', Suit.HEART,
                'S', Suit.SPADE
        );
    }

    //build map of characger code to rank
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
    public static Card CreateCard(char rank, char suit) {
        Rank cardRank = getRankMap().get(rank);
        Suit cardSuit = getSuitMap().get(suit);

        if(cardRank == null || cardSuit == null) {
            throw new IllegalArgumentException("Invalid Card");
        }
        return new Card(cardRank, cardSuit);
    }
}
