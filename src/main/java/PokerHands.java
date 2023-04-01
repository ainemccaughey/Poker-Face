import entities.Hand;
import entities.HandFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHands {

    public static void main(String[] args) throws FileNotFoundException {
        readTestsTxtFile("Hands.txt");
    }

    static void readTestsTxtFile(String fileName) throws FileNotFoundException {
        BufferedReader fileBuffer = new BufferedReader(new FileReader(fileName));
        List<String> lines = fileBuffer.lines().collect(Collectors.toList());

        for(String hand: lines) {
            Hand cards = HandFactory.MakeHandFromString(hand.trim());
            System.out.println(cards + " => " + cards.handName());
        }
    }
}
