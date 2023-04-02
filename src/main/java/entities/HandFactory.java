package entities;

/**
 * This class represents a hand factory, and contains functionality to create a hand
 *
 * @author aine mccaughey
 * @version 1.0
 */
public class HandFactory {

    /**
     * A factory method to construct a hand from a coded string
     *
     * @param hand is a string of char pairs separated by spaces ("3D 4S...")
     * @return a new hand
     */
    public static Hand MakeHandFromString(String hand) {
        return MakeHandFromArrayOfCodes(hand.split(" "));
    }

    /**
     * A factory method to construct a hand from an array of codes
     *
     * @param codes is an array of two char strings [ "3D", "4S", ... ]
     * @return a new hand
     */
    public static Hand MakeHandFromArrayOfCodes(String[] codes) {
        // create empty cards array
        var cards = new Card[codes.length];
        for (var i = 0; i < codes.length; i++) {
            try {
                //make card using first and second char in each code, and add to array of cards
                cards[i] = Card.CreateCard(codes[i].charAt(0), codes[i].charAt(1));
            } catch (IndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("Invalid Card Code");
            }
        }
        return new Hand(cards);
    }
}
