import entities.Hand;
import entities.HandFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/**
 * A driver for the poker Hand class
 *
 * @author aine mccaughey
 * @version 1.0
 */
public class PokerHands {
    /**
     *
     * @param args accepts a filename for a text file containing poker hands
     * @throws FileNotFoundException when file given cannot be found
     */
    public static void main(String[] args) throws FileNotFoundException {
        String filename = null;

        //check if file path has been passed in command line args
        if(args.length > 0) {
            filename = args[0];
        }

        //if filename not passed, prompt for file path to be entered
        if(filename == null) {
            System.out.println("Welcome to Poker Face!");
            System.out.println("To get started, enter the full file path of the file you would like evaluated");

            //read the filepath from console
            Scanner scanner = new Scanner(System.in);
            filename = scanner.nextLine();
        }

        readTestsTxtFile(filename);
    }

    /**
     * Reads the given file from the filesystem and determines the hands within each line of the file
     *
     * @param fileName String
     * @throws FileNotFoundException when file given cannot be found
     */
    static void readTestsTxtFile(String fileName) throws FileNotFoundException {
        BufferedReader fileBuffer = new BufferedReader(new FileReader(fileName));
        List<String> lines = fileBuffer.lines().toList();

        //read each line from file and make a card
        for(String hand: lines) {
            Hand cards = HandFactory.MakeHandFromString(hand.trim());
            System.out.println(cards + " => " + cards.handName());
        }
    }
}
