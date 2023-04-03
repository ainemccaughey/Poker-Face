package entities;

import enums.Rank;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents a hand of cards, and contains functionality to determine the name of the best hand
 *
 * @author aine mccaughey
 * @version 1.0
 */
public class Hand {
    private final Set<Card> cards;

    private final int HAND_SIZE = 5;

    private final Map<String, Integer> rankCounts;
    /**
     * Method accepts a variable collection of cards (varargs)
     *
     * @param cards array of Card
     */
    public Hand(Card... cards) {
        //check for invalid number of cards
        if (cards.length != HAND_SIZE) {
            throw new IllegalArgumentException("A hand must contain " + HAND_SIZE + " cards");
        }

        // check for duplicate cards
        this.cards = new HashSet<Card>(Arrays.asList(cards));
        if (this.cards.size() != HAND_SIZE)
        {
            throw new IllegalArgumentException("Duplicate Card In Hand");
        }

        this.rankCounts = getRankCounts();
    }

    /**
     * High card is a hand that fits in no other category
     *
     * @return boolean
     */
    public boolean isHighCard() {
        return
                !isOnePair() &&
                        !isTwoPair() &&
                        !isThreeOfAKind() &&
                        !isStraight() &&
                        !isFlush() &&
                        !isFullHouse() &&
                        !isFourOfAKind() &&
                        !isStraightFlush() &&
                        !isRoyalFlush();
    }

    /**
     * A pair contains one two of a kind, and three one of a kind
     *
     * @return boolean
     */
    public boolean isOnePair() {
        return getTwoOfAKindCount() == 1 &&
                getOneOfAKindCount() == 3;
    }


    /**
     * A two pair contains two, two of a kind
     *
     * @return boolean
     */
    public boolean isTwoPair() {
        return getTwoOfAKindCount() == 2;
    }

    /**
     * A three of a kind contains one three of a kind and 2 one of a kind
     *
     * @return boolean
     */
    public boolean isThreeOfAKind() {
        return getThreeOfAKindCount() == 1 && getOneOfAKindCount() == 2;
    }

    /**
     * A default straight check always verifies that it is not also a flush
     *
     * @return boolean
     */
    public boolean isStraight() {
        return !isFlush() && isLooseStraight();
    }

    /**
     * A flush contains cards all the same suit
     *
     * @return boolean
     */
    public boolean isFlush() {
        //using set to count suits in hand
        Set<String> suits = new HashSet<>();
        for (Card card : cards) {
            suits.add(card.suit().name());
        }

        //set should be 1 if all suits the same
        return suits.size() == 1;
    }

    /**
     * A full house contains one three of a kind and one two of a kind
     *
     * @return boolean
     */
    public boolean isFullHouse() {
        return getThreeOfAKindCount() == 1 && getTwoOfAKindCount() == 1;
    }

    /**
     * A four of a kind should return one four of a kind
     *
     * @return boolean
     */
    public boolean isFourOfAKind() {
        return getFourOfAKindCount() == 1;
    }

    /**
     * Checks the hand is both a flush and a loose straight (without the flush check)
     *
     * @return boolean
     */
    public boolean isStraightFlush() {
        return isLooseStraight() && isFlush();
    }

    /**
     * Checks the hand is both a loose straight (without the flush check), a flush and that a King exists
     * rank is Ace low, so King will be last card
     *
     * @return boolean
     */
    public boolean isRoyalFlush() {
        return isLooseStraight() && isFlush() && cards.stream().anyMatch(c -> c.rank().equals(Rank.KING));
    }

    /**
     * Will determine the hand type and return a string representation of that hand
     *
     * @return String hand name
     */
    public String handName() {
        return isRoyalFlush() ? "Royal Flush" :
                isStraightFlush() ? "Straight Flush" :
                        isFourOfAKind() ? "Four of a Kind" :
                                isFullHouse() ? "Full House" :
                                        isFlush() ? "Flush" :
                                                isStraight() ? "Straight" :
                                                        isThreeOfAKind() ? "Three of a Kind" :
                                                                isTwoPair() ? "Two Pair" :
                                                                        isOnePair() ? "One Pair" :
                                                                                "High Card";
    }

    /**
     * Will stream the cards and join back into the coded representation of the hand supplied
     *
     * @return String coded representation of hand
     */
    public String toString() {
        return cards.stream()
                .map(c -> c.rank().toString() + c.suit().toString() )
                .collect(Collectors.joining(" "));
    }

    /**
     * A straight check that does not check for flush
     *
     * @return boolean
     */
    private boolean isLooseStraight() {
        //to test for a straight we first sort the cards
        Card[] sortedCards = getSortedCards();

        //check cards in sequence
        for (int i = 0; i < sortedCards.length - 1; i++) {
            Rank current = sortedCards[i].rank();
            Rank next = sortedCards[i + 1].rank();

            //check that current and next are not ace/two or ace/ten
            if( !(current.equals(Rank.ACE) && (next.equals(Rank.TWO) || next.equals(Rank.TEN)) )) {

                //check if current cards next rank does not equal the next rank, in which case cannot be a straight
                if (current.getNextRank() != next) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Utility method
     * Gets the count of each rank in the hand
     *
     * @return Map<String, Integer> rankCounts where String is the rank and Integer is the number of that rank
     */
    private Map<String, Integer> getRankCounts() {
        Map<String, Integer> rankCounts = new HashMap<>();

        //create a record of the number of each rank
        for (Card card : cards) {
            rankCounts.merge(card.rank().name(), 1, Integer::sum);
        }

        return rankCounts;
    }

    /**
     * Utility method
     * Get all ranks with a card count of 1
     *
     * @return long count of one of a kind
     */
    private long getOneOfAKindCount() {
        return getSameRankCount( 1);
    }

    /**
     * Utility method
     * Get all ranks with a card count of 2
     *
     * @return long count of two of a kind
     */
    private long getTwoOfAKindCount() {
        return getSameRankCount(2);
    }

    /**
     * Utility method
     * Get all ranks with a card count of 3
     *
     * @return long count of three of a kind
     */
    private long getThreeOfAKindCount() {
        return getSameRankCount( 3);
    }

    /**
     * Utility method
     * Get all ranks with a card count of 4
     *
     * @return long count of four of a kind
     */
    private long getFourOfAKindCount() {
        return getSameRankCount(4);
    }

    /**
     * Utility method
     * A generic method that will get all ranks with a card count of sameCount
     *
     * @param sameCount the count to check for
     * @return long number of cards with given count
     */
    private long getSameRankCount( int sameCount) {
        return rankCounts.values().stream()
                .filter(count -> count == sameCount)
                .count();
    }

    /**
     * Utility method
     * Will return the card array sorted by rank
     *
     * @return Card[] cards sorted
     */
    private Card[] getSortedCards() {
        return cards.stream().sorted(Comparator.comparing(Card::rank)).toArray(Card[]::new);
    }
}