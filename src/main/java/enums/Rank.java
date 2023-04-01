package enums;

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

    private String displayName;

    Rank(String displayName) { this.displayName = displayName; }

    @Override
    public String toString() { return displayName; }

    //get values of enum and increment the value by one, ensuring always within the bounds of enum
    public Rank getNextRank() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
