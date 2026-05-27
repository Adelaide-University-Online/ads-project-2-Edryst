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

        // Attempt to read the file and handle an error if it is not found.
        try {

            // Create a reference to XBIT.txt and assign it to 'file'
            File file = new File("XBIT.txt");

            // Create a scanner variable to be able to read the file.
            Scanner scanner = new Scanner(file);

            // Use the scanner to iterate over 'file' while a line with data is
            // still beneath the current line.
            while (scanner.hasNextLine()) {

                // Assign text on each line to variable 'line'
                String line = scanner.nextLine();

                // Split the line at each comma and store the results in an array.
                String[] parts = line.split(",");

                //  Assign the first element of each line to 'course'
                String course = parts[0].trim();

                System.out.println("Course: " + course);

                // iterate over the remaining elements in the array
                for (int i = 1; i < parts.length; i++) {
                    String prerequisite = parts[i].trim();
                    System.out.println("Prerequisite: " + prerequisite);
                }

                System.out.println("---");
            }

            // Close the scanner to free up system resources
            scanner.close();

            // Throw an exception in case the file isn't found.
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
        }
    }
}