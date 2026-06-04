/**
 * File: DegreeParser.java
 * Description: Parses in course information and cleans and structures it and
 * returns a DegreeData object with the cleaned attributes.
 * Author: Edward Still
 * Student ID: 3133105
 * Email ID: a3133105@adelaide.edu.au || edward.still@student.adelaide.edu.au
 * AI Tool Used: ChatGPT for concept explanation, assistance in debugging and code review.
 * This is my own work as defined by the University's Academic Integrity Policy.
 **/

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class DegreeParser {

    /**
     * Reads a degree file and constructs the corresponding
     * DegreeData object.
     *
     * @param scanner Scanner connected to the degree file.
     * @return A populated DegreeData object.
     */

    public static DegreeData parseFile(Scanner scanner) {

        // Create a list to store every course required for the degree.
        ArrayList<String> allCourses = new ArrayList<>();

        // Create a HashMap to store each course and its list of prerequisites.
        HashMap<String, ArrayList<String>> courseStructureMap = new HashMap<>();

        // Create a HashMap to store number of prerequisites per course.
        HashMap<String, Integer> numOfPrerequisitesMap = new HashMap<>();

        // Read the first line of the text file which has all the courses in the degree
        String courseListLine = scanner.nextLine();

        // Split up each code into its own element in an array. Splitting at the comma
        String[] courseCodes = courseListLine.split(",");

        // Iterate through each course code, remove whitespace, and add it to allCourses.
        for (String courseCode : courseCodes) {
            String cleanCourseCode = courseCode.trim();
            allCourses.add(cleanCourseCode);

            // Start each course with 0 prerequisites until added to
            numOfPrerequisitesMap.put(cleanCourseCode, 0);
        }

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

            // Iterate over the remaining elements in the array.
            for (int i = 1; i < parts.length; i++) {

                // Trim whitespace and add the prerequisite to the prerequisite list.
                String prerequisite = parts[i].trim();
                prerequisiteList.add(prerequisite);
            }

            courseStructureMap.put(course, prerequisiteList);
            numOfPrerequisitesMap.put(course, prerequisiteList.size());
        }

        // Return new Degree Data object with cleaned attributes.
        return new DegreeData(
                allCourses,
                courseStructureMap,
                numOfPrerequisitesMap
                );
    }
}
