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
    private final Card[] cards;

    private final int HAND_SIZE = 5;

    /**
     * Method accepts a variable collection of cards (varargs)
     *
     * @param cards array of Card
     */
    public Hand(Card... cards) {
        this.cards = cards;

        //check for invalid number of cards
        if (cards.length != HAND_SIZE) {
            throw new IllegalArgumentException("A hand must contain " + HAND_SIZE + " cards");
        }
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
        Map<String, Integer> rankCounts = getRankCounts();

        return getTwoOfAKindCount(rankCounts) == 1 && getOneOfAKindCount(rankCounts) == 3;
    }


    /**
     * A two pair contains two, two of a kind
     *
     * @return boolean
     */
    public boolean isTwoPair() {
        Map<String, Integer> rankCounts = getRankCounts();

        return getTwoOfAKindCount(rankCounts) == 2;
    }

    /**
     * A three of a kind contains one three of a kind and 2 one of a kind
     *
     * @return boolean
     */
    public boolean isThreeOfAKind() {
        Map<String, Integer> rankCounts = getRankCounts();

        return getThreeOfAKindCount(rankCounts) == 1 && getOneOfAKindCount(rankCounts) == 2;
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
     * A straight check that does not check for flush
     *
     * @return boolean
     */
    public boolean isLooseStraight() {
        //to test for a straight we first sort the cards
        Card[] sortedCards = getSortedCards();

        //ace high check
        if (sortedCards[0].rank().equals(Rank.ACE) &&
                sortedCards[1].rank().equals(Rank.TEN) &&
                sortedCards[2].rank().equals(Rank.JACK) &&
                sortedCards[3].rank().equals(Rank.QUEEN) &&
                sortedCards[4].rank().equals(Rank.KING)) {
            return true;
        }

        //all others incl Ace low check
        for (int i = 0; i < sortedCards.length - 1; i++) {
            Rank current = sortedCards[i].rank();
            Rank next = sortedCards[i + 1].rank();

            //if current cards next rank does not equal the next rank, cannot be a straight
            if (current.getNextRank() != next) {
                return false;
            }
        }

        return true;
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
        Map<String, Integer> rankCounts = getRankCounts();

        return getThreeOfAKindCount(rankCounts) == 1 && getTwoOfAKindCount(rankCounts) == 1;
    }

    /**
     * A four of a kind should return one four of a kind
     *
     * @return boolean
     */
    public boolean isFourOfAKind() {
        Map<String, Integer> rankCounts = getRankCounts();

        return getFourOfAKindCount(rankCounts) == 1;
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
        return isLooseStraight() && isFlush() && Arrays.stream(cards).anyMatch(c -> c.rank().equals(Rank.KING));
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
        return Arrays.stream(cards)
                .map(c -> c.rank().toString() + c.suit().toString() )
                .collect(Collectors.joining(" "));
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
     * @param rankCounts a map containing the count of each rank
     * @return long count of one of a kind
     */
    private long getOneOfAKindCount(Map<String, Integer> rankCounts) {
        return getSameRankCount(rankCounts, 1);
    }

    /**
     * Utility method
     * Get all rankds with a card count of 2
     *
     * @param rankCounts a map containing the count of each rank
     * @return long count of two of a kind
     */
    private long getTwoOfAKindCount(Map<String, Integer> rankCounts) {
        return getSameRankCount(rankCounts, 2);
    }

    /**
     * Utility method
     * Get all ranks with a card count of 3
     *
     * @param rankCounts a map containing the count of each rank
     * @return long count of three of a kind
     */
    private long getThreeOfAKindCount(Map<String, Integer> rankCounts) {
        return getSameRankCount(rankCounts, 3);
    }

    /**
     * Utility method
     * Get all ranks with a card count of 4
     *
     * @param rankCounts a map containing the count of each rank
     * @return long count of four of a kind
     */
    private long getFourOfAKindCount(Map<String, Integer> rankCounts) {
        return getSameRankCount(rankCounts, 4);
    }

    /**
     * Utility method
     * A generic method that will get all ranks with a card count of sameCount
     *
     * @param rankCounts a map containing the count of each rank
     * @param sameCount the count to check for
     * @return long number of cards with given count
     */
    private long getSameRankCount(Map<String, Integer> rankCounts, int sameCount) {
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
        return Arrays.stream(cards).sorted(Comparator.comparing(Card::rank)).toArray(Card[]::new);
    }
}
