# Poker Face

The Poker Face program will take a file containing a set of poker hands either as a command line parameter or via console input and determine the type of hand for each set of cards.

## Sample Data
A sample file to run with the program can be found within the resources directory

## Running instructions

This project is a maven project, and so should be packaged/run using maven.

To do this locally you can follow the below instructions.

Prerequisites:
- The instructions below assume you already have maven installed on your local machine. If you do not, follow instructions for your own operating system to do this

Instructions:
- To compile the project, run `mvn compile`
- To package the project, run `mvn package` (this will also run the tests)
- To execute the generated jar file run `java -jar target/Poker-Face-1.0-SNAPSHOT.jar Hands.txt` where `Poker-Face-1.0-SNAPSHOT.jar` is the name of the generated jar file within the target directory and `Hands.txt` is the name of the file you wish to execute the program against
- Note, if you do not supply a path file when running the jar, you will be prompted to supply one at the start of the program