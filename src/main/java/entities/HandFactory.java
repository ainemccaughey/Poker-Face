package entities;

public class HandFactory {

    public static Hand MakeHandFromString(String hand) {
        return MakeHandFromArrayOfCodes(hand.split(" "));
    }

    //codes is an array of two char strings [ "3D", "4S", ... ]
    public static Hand MakeHandFromArrayOfCodes(String[] codes) {
        // create empty cards array
        var cards = new Card[codes.length];
        for (var i = 0; i < codes.length; i++) {
            try {
                //make card and add to array of cards
                cards[i] = Card.CreateCard(codes[i].charAt(0), codes[i].charAt(1));
            } catch (IndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("Invalid Card Code");
            }
        }
        return new Hand(cards);
    }
}
