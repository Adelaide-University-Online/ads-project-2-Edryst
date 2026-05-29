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
import java.util.InputMismatchException;

public class Main {

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

        // Parse in the file into DegreeParser selected by the user.
        DegreeData degreeData = DegreeParser.parseFile(scanner);

        System.out.println("---");
        System.out.println("Course Requirement Map:");
        System.out.println(degreeData.getCourseStructureMap());
        System.out.println("---");
        System.out.println("Prerequisite Count Map:");
        System.out.println(degreeData.getNumOfPrerequisitesMap());
        System.out.println("---");

        // ========================= DETERMINE WHICH COURSES ARE AVAILABLE ===========================//

        // Create new List to hold completed courses
        ArrayList<String> completedCourses = new ArrayList<>();

        // Initialise study period counter and set max courses per period
        int studyPeriod = 1;

        // Loop until completed courses is equal to all courses
        while (completedCourses.size() < degreeData.getAllCourses().size()) {

            System.out.println("---");
            System.out.println("Study Period " + studyPeriod + ": ");

            ArrayList<String> availableCourses = findAvailableCourses(
                    degreeData.getAllCourses(), // List of all courses
                    degreeData.getNumOfPrerequisitesMap(), // Map of number of prerequisites per course
                    completedCourses // List of courses already completed
            );

            ArrayList<String> coursesToStudyThisPeriod = new ArrayList<>();

            for (int i = 0; i < availableCourses.size() && i < maxCoursesPerStudyPeriod; i++) {
                coursesToStudyThisPeriod.add(availableCourses.get(i));
            }

            System.out.println(coursesToStudyThisPeriod);

            for (String completedCourse : coursesToStudyThisPeriod) {
                System.out.println("Completed: " + completedCourse);

                completedCourses.add(completedCourse);

                // Iterate through every course list for the current course.
                for (String course : degreeData.getCourseStructureMap().keySet()) {

                    // Get the prerequisite list for the current course.
                    ArrayList<String> prerequisiteList = degreeData.getCourseStructureMap().get(course);

                    // If the completed course exists in the prerequisite list,
                    // reduce the prerequisite count by 1

                    if (prerequisiteList.contains(completedCourse)) {

                        int currentCount = degreeData.getNumOfPrerequisitesMap().get(course);

                        degreeData.getNumOfPrerequisitesMap().put(course, currentCount - 1);

                        System.out.println(
                                course + " prerequisite count reduced to "
                                        + degreeData.getNumOfPrerequisitesMap().get(course)
                        );
                    }
                }
            }
            studyPeriod++;
        }

        System.out.println("---");
        System.out.println("Course newly available after Study Period");

        // ========================= PRINTING AVAILABLE COURSES TO OUTPUT===========================//

        ArrayList<String> newlyAvailableCourses = findAvailableCourses(
                degreeData.getAllCourses(),
                degreeData.getNumOfPrerequisitesMap(),
                completedCourses
        );

        System.out.println(newlyAvailableCourses);

        // Close the scanner to free up system resources
        scanner.close();
    }

    // Takes in all courses, their number of prerequisites, and courses already available
    // and returns all courses available but not taken yet
    public static ArrayList<String> findAvailableCourses(
            ArrayList<String> allCourses,
            HashMap<String, Integer> numOfPrerequisitesMap,
            ArrayList<String> alreadyAvailableCourses
    ) {

        ArrayList<String> availableCourses = new ArrayList<>();

        // Iterate through all courses and check whether they:
        // 1. Have 0 remaining prerequisites
        // 2. Are not already available courses
        for (String course : allCourses) {

            if (
                    numOfPrerequisitesMap.get(course) == 0 &&
                            !alreadyAvailableCourses.contains(course)
            ) {
                availableCourses.add(course);
            }
        }
        return availableCourses;
    }
}