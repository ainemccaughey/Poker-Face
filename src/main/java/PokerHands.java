import entities.Hand;
import entities.HandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PokerHands {

    public static void main(String[] args) throws IOException {
        PokerHands pokerHands = new PokerHands();
        pokerHands.readTestsTxtFile("Hands.txt");
    }

    private void readTestsTxtFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String hand;
        while ((hand = reader.readLine()) != null) {
            Hand cards = HandFactory.MakeHandFromString(hand.trim());
            System.out.println(cards + " => " + cards.handName());
        }
    }
}
