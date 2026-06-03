/**
 * File: CoursePlanner.java
 * Description: Finds available courses by analysing which courses have been
 * completed and which are available and returns the available courses.
 * Author: Edward Still
 * Student ID: 3133105
 * Email ID: a3133105@adelaide.edu.au || edward.still@student.adelaide.edu.au
 * AI Tool Used: ChatGPT for concept explanation, assistance in debugging and code review.
 * This is my own work as defined by the University's Academic Integrity Policy.
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class CoursePlanner {

    // Takes in all courses, their number of prerequisites, and courses already available
    // and returns all courses available but not completed yet
    public static ArrayList<String> findAvailableCourses(
            ArrayList<String> allCourses,
            HashMap<String, Integer> numOfPrerequisitesMap,
            ArrayList<String> completedCourses
    ) {

        ArrayList<String> availableCourses = new ArrayList<>();

        // Iterate through all courses and check whether they:
        // 1. Have 0 remaining prerequisites
        // 2. Are not already available courses
        for (String course : allCourses) {

            if (
                    numOfPrerequisitesMap.get(course) == 0 &&
                            !completedCourses.contains(course)
            ) {
                availableCourses.add(course);
            }
        }
        return availableCourses;
    }

    public static void updatePrerequisiteCounts(
            DegreeData degreeData,
            String completedCourse
    ) {

        // Iterate through every course list for the current course.
        for (String course : degreeData.getCourseStructureMap().keySet()) {

            // Get the prerequisite list for the current course.
            ArrayList<String> prerequisiteList = degreeData.getCourseStructureMap().get(course);

            // If the completed course exists in the prerequisite list,
            // reduce the prerequisite count by 1

            if (prerequisiteList.contains(completedCourse)) {

                int currentCount = degreeData.getNumOfPrerequisitesMap().get(course);

                degreeData.getNumOfPrerequisitesMap().put(course, currentCount - 1);
            }
        }
    }

    public static void generateStudyPlan(
            DegreeData degreeData,
            int maxCoursesPerStudyPeriod
    ) {

        // Create new List to hold completed courses
        ArrayList<String> completedCourses = new ArrayList<>();

        // Initialise study period counter and set max courses per period
        int studyPeriod = 1;

        // Loop until completed courses is equal to all courses
        while (completedCourses.size() < degreeData.getAllCourses().size()) {

            System.out.println("---");
            System.out.println("Study Period " + studyPeriod + ": ");

            ArrayList<String> availableCourses = CoursePlanner.findAvailableCourses(
                    degreeData.getAllCourses(), // List of all courses
                    degreeData.getNumOfPrerequisitesMap(), // Map of number of prerequisites per course
                    completedCourses // List of courses already completed
            );

            ArrayList<String> coursesToStudyThisPeriod = new ArrayList<>();

            // Add courses to this study period until either:
            // 1. No more courses are available, or
            // 2. The maximum study load for the period is reached.
            for (int i = 0; i < availableCourses.size() && i < maxCoursesPerStudyPeriod; i++) {
                coursesToStudyThisPeriod.add(availableCourses.get(i));
            }

            for (String course : coursesToStudyThisPeriod) {
                System.out.println("- " + course);
            }

            // Circular dependency detection to avoid infinite looping
            if (coursesToStudyThisPeriod.isEmpty()) {
                System.out.println("Error: No available courses found.");
                System.out.println("This may indicate a circular prerequisite dependency. ");
                return;
            }

            for (String completedCourse : coursesToStudyThisPeriod) {

                completedCourses.add(completedCourse);

                updatePrerequisiteCounts(degreeData, completedCourse);

            }
            studyPeriod++;
        }

        System.out.println();
        System.out.println(
                "Degree completed in " + (studyPeriod - 1) + " study periods."
        );
    }
}