package enums;

/**
 * This enum represents a rank, a defined set that is part of what makes up a card
 *
 * @author aine mccaughey
 * @version 1.0
 */
public enum Rank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String displayName;

    /**
     * Method accepts a display name for the rank
     *
     * @param displayName String
     */
    Rank(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the coded display name of the rank
     *
     * @return String displayName
     */
    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Gets the ordinal value of enum and increment by 1, ensuring always within the bounds of enum
     *
     * @return Rank
     */
    //get values of enum and increment the value by one, ensuring always within the bounds of enum
    public Rank getNextRank() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
