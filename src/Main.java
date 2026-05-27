/**
 * File: Main.java
 * Description: Testing file reading for now
 * Author: Edward Still
 * Student ID: 3133105
 * Email ID: a3133105@adelaide.edu.au || edward.still@student.adelaide.edu.au
 * AI Tool Used: ChatGPT for concept explanation, assistance in debugging and code review.
 * This is my own work as defined by the University's Academic Integrity Policy.
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Attempt to read the file, throw error if not found.
        try {

            // Create a reference to XBIT.txt and assign it to 'file'
            File file = new File("XBIT.txt");

            // Create a scanner variable to be able to read the file.
            Scanner scanner = new Scanner(file);

            // Use the scanner to iterate over 'file' while a line with data is
            // still beneath the current line and print the contents to the screen.
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                System.out.println(line);
            }

            // Close the scanner to free up system resources
            scanner.close();

            // Throw an exception in case the file isn't found.
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
        }
    }
}