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
import java.util.HashMap;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Attempt to read the file and handle an error if it is not found.
        try {

            // Create a reference to XBIT.txt and assign it to 'file'
            File file = new File("XBIT.txt");

            // Create a scanner variable to be able to read the file.
            Scanner scanner = new Scanner(file);

            // Create a list to store every course required for the degree.
            ArrayList<String> allCourses = new ArrayList<>();

            // Create a HashMap to store each course and its list of prerequisites.
            HashMap<String, ArrayList<String>> courseStructure = new HashMap<>();

            // Read the first line of the file, which contains the full course list.
            String courseListLine = scanner.nextLine();

            // Split the first line at each comma and store the course codes in an array.
            String[] courseCodes = courseListLine.split(",");

            // Iterate through each course code, remove whitespace, and add it to allCourses.
            for (String courseCode : courseCodes) {
                allCourses.add(courseCode.trim());
            }

            System.out.println("All courses: " + allCourses);
            System.out.println("---");

            // Use the scanner to iterate over the remaining lines in the file.
            // Each remaining line represents one course and its prerequisites.
            while (scanner.hasNextLine()) {

                // Assign text on each line to variable 'line'
                String line = scanner.nextLine();

                // Split the line at each comma and store the results in an array.
                String[] parts = line.split(",");

                // Assign the first element of each line to 'course'
                String course = parts[0].trim();

                // Create a list to store this course's prerequisites.
                ArrayList<String> prerequisiteList = new ArrayList<>();

                System.out.println("Course: " + course);

                // Iterate over the remaining elements in the array.
                for (int i = 1; i < parts.length; i++) {

                    // Trim whitespace and add the prerequisite to the prerequisite list.
                    String prerequisite = parts[i].trim();
                    prerequisiteList.add(prerequisite);

                    System.out.println("Prerequisite: " + prerequisite);
                }

                // Store the course and its prerequisite list in the HashMap.
                courseStructure.put(course, prerequisiteList);

                System.out.println("---");
            }

            System.out.println("Prerequisite map:");
            System.out.println(courseStructure);

            // Close the scanner to free up system resources
            scanner.close();

            // Handle the exception if the file is not found.
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
        }
    }
}