package enums;

public enum Suit {
    HEART("H"),
    DIAMOND("D"),
    SPADE("S"),
    CLUB("C");

    private String displayName;

    Suit(String displayName) { this.displayName = displayName; }


    @Override
    public String toString() { return displayName; }
}
