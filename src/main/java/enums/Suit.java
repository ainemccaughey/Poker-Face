package enums;

/**
 * This enum represents a suit, a defined set that is part of what makes up a card
 *
 * @author aine mccaughey
 * @version 1.0
 */
public enum Suit {
    HEART("H"),
    DIAMOND("D"),
    SPADE("S"),
    CLUB("C");

    private final String displayName;

    /**
     * Method accepts a display name for the suit
     *
     * @param displayName String
     */
    Suit(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the coded display name of the suit
     *
     * @return String displayName
     */
    @Override
    public String toString() {
        return displayName;
    }
}
