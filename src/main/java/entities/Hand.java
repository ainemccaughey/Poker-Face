package entities;

import enums.Rank;

import java.util.*;

public class Hand {
    private Card[] cards;

    public Hand(Card... cards) {
        this.cards = cards;
        initialiseHand(cards);
    }

    private void initialiseHand(Card... cards) {
        if (cards.length != 5) {
            throw new IllegalArgumentException("A hand must contain 5 cards");
        }
    }

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

    public boolean isOnePair() {
        Set<String> ranks = new HashSet<>();
        for (Card card : cards) {
            ranks.add(card.rank().name());
        }

        return ranks.size() == 4;
    }

    public boolean isTwoPair() {
        Map<String, Integer> rankCounts = getRankCounts();

        long twoPairCount = rankCounts.values().stream()
                .filter(count -> count == 2).count();

        return twoPairCount == 2;
    }

    public boolean isThreeOfAKind() {
        Map<String, Integer> rankCounts = getRankCounts();

        long threeOfAKindCount = rankCounts.values().stream()
                .filter(count -> count == 3)
                .count();

        long oneOfAKindCount = rankCounts.values().stream()
                .filter(count -> count == 1)
                .count();

        return threeOfAKindCount == 1 && oneOfAKindCount == 2;
    }

    public boolean isStraight() {
        Card[] sortedCards = Arrays.stream(cards).sorted(Comparator.comparing(Card::rank)).toArray(Card[]::new);

        // Ace high check
        if (sortedCards[0].rank().equals(Rank.ACE) &&
                sortedCards[1].rank().equals(Rank.TEN) &&
                sortedCards[2].rank().equals(Rank.JACK) &&
                sortedCards[3].rank().equals(Rank.QUEEN) &&
                sortedCards[4].rank().equals(Rank.KING)) {
            return true;
        }

        //All others incl Ace low check
        for (int i = 0; i < sortedCards.length - 1; i++) {
            Rank current = sortedCards[i].rank();
            Rank next = sortedCards[i + 1].rank();

            if (current.getNextRank() != next) {
                return false;
            }
        }

        return true;
    }

    public boolean isFlush() {
        Set<String> suits = new HashSet<>();
        for (Card card : cards) {
            suits.add(card.suit().name());
        }

        if (suits.size() != 1) {
            return false;
        }

        return true;
    }

    public boolean isFullHouse() {
        Map<String, Integer> rankCounts = getRankCounts();

        long threeOfAKindCount = rankCounts.values().stream()
                .filter(count -> count == 3)
                .count();

        long pairCount = rankCounts.values().stream()
                .filter(count -> count == 2)
                .count();

        return threeOfAKindCount == 1 && pairCount == 1;
    }

    public boolean isFourOfAKind() {
        Map<String, Integer> rankCounts = getRankCounts();

        long fourOfAKindCount = rankCounts.values().stream()
                .filter(count -> count == 4)
                .count();

        return fourOfAKindCount == 1;
    }

    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    public boolean isRoyalFlush() {
        // As isStraight() checks for card order, just need to check for existence of King
        // Rank is Ace low, so King will be last card
        return isStraight() && isFlush() && Arrays.stream(cards).anyMatch(c -> c.rank().equals(Rank.KING));
    }

    private Map<String, Integer> getRankCounts() {
        Map<String, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.merge(card.rank().name(), 1, Integer::sum);
        }

        return rankCounts;
    }

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

    public String toString() {
        StringBuffer output = new StringBuffer();
        for(Card card: cards) {
            output.append(" " + card.rank() + card.suit());
        }
        return output.toString();
    }
}
