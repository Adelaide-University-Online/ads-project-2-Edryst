/**
 * File: Main.java
 * Description: Takes in user input with a scanner. Parses details into DegreeParser
 * for error handling, before generating the study plan.
 * Author: Edward Still
 * Student ID: 3133105
 * Email ID: a3133105@adelaide.edu.au || edward.still@student.adelaide.edu.au
 * AI Tool Used: ChatGPT for concept explanation, assistance in debugging and code review.
 * This is my own work as defined by the University's Academic Integrity Policy.
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    /**
     * Starts the application, validates user input,
     * loads the degree file and generates a study plan.
     *
     * @param args Command line arguments.
     */

    public static void main(String[] args) {

        // ========================= TAKE IN USER INPUT ===========================//

        // Create a scanner that reads in user input for file name.
        Scanner inputScanner = new Scanner(System.in);

        // Create a scanner variable to be able to read the file.
        Scanner scanner = null;

        // Keep looping until valid input is received/scanner no longer == null.
        while (scanner == null) {

            // Attempt to read the file and handle an error if it is not found.
            try {

                System.out.print("Enter file name: ");
                String fileName = inputScanner.nextLine();

                // Create file reference to user defined file name
                File file = new File(fileName);

                // Try and open the defined file and assign it a scanner
                scanner = new Scanner(file);

            } catch (FileNotFoundException e) {

                System.out.println("Could not find file. Please try again.");
            }
        }

        // Initialise default courses per period variable.
        int maxCoursesPerStudyPeriod = 0;

        while (true) {

            // Attempt to receive user input for number of courses per period.
            try {

                System.out.print("Enter maximum courses per study period: ");
                maxCoursesPerStudyPeriod = inputScanner.nextInt();

                // Validate user input to ensure they don't select a number less than 1
                if (maxCoursesPerStudyPeriod <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                    continue;
                }

                break; // valid input, leave loop

            } catch (InputMismatchException e) {

                System.out.println("Please enter a valid whole number.");

                inputScanner.nextLine(); // discard bad input
            }
        }

        // Parse in the file dictated by user into DegreeParser to clean the txt file and
        // assign the return to a DegreeData object.
        DegreeData degreeData = DegreeParser.parseFile(scanner);

        // Parse the DegreeData object and user defined number of courses per period into the CoursePlanner
        // to define which courses should be taken in each period
        CoursePlanner.generateStudyPlan(degreeData, maxCoursesPerStudyPeriod);

        // Close the scanner to free up system resources
        scanner.close();
    }
}