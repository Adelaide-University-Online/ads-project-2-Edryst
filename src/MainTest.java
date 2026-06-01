import java.util.ArrayList;
import java.util.HashMap;

/**
 * File: MainTest.java
 * Description: Test File
 * Author: Edward Still
 * Student ID: 3133105
 * Email ID: a3133105@adelaide.edu.au || edward.still@student.adelaide.edu.au
 * AI Tool Used: ChatGPT for concept explanation, assistance in debugging and code review.
 * This is my own work as defined by the University's Academic Integrity Policy.
 **/

public class MainTest {

    public static void main(String[] args) {

        testPrerequisiteCounts();
        System.out.println("---");
        testCourseCodeParsing();
        System.out.println("---");
        testFindAvailableCourses();
        System.out.println("---");
        testPrerequisiteProgression();
        System.out.println("---");
        testCircularDependencyAndStandardMethods();
    }

    public static void testPrerequisiteCounts() {

        System.out.println("Running prerequisite count test...");

        int[] array1 = {2, 2};
        int[] array2 = {2, 1};

        String message = "Value test";

        assertEqual(array1[0], array1[1], message);
        assertEqual(array2[0], array2[1], message);

    }

    public static void testCourseCodeParsing() {

        System.out.println("Running course code parsing test...");

        String line = "INFT1032, COMP1043, INFT1024";

        // create a new element after each , and assign it to parts array
        String[] parts = line.split(",");

        // trim whitespace
        String course = parts[0].trim();
        String prereq = parts[1].trim();

        assertEqualString("INFT1032", course, "First element should be the course code");
        assertEqualString("COMP1043", prereq, "Second element should be the first prerequisite code");
    }


    public static void testFindAvailableCourses() {

        System.out.println("Running findAvailableCourses Test...");

        // Initialise test course codes into List
        ArrayList<String> allCourses = new ArrayList<>();
        allCourses.add("A");
        allCourses.add("B");
        allCourses.add("C");

        // Initialise test prerequisite counts into hashmap
        HashMap<String, Integer> prerequisiteCounts = new HashMap<>();
        prerequisiteCounts.put("A", 0);
        prerequisiteCounts.put("B", 1);
        prerequisiteCounts.put("C", 0);

        // Added completed courses to fill final parameter
        ArrayList<String> completedCourses = new ArrayList<>();

        ArrayList<String> availableCourses = CoursePlanner.findAvailableCourses(
                allCourses,
                prerequisiteCounts,
                completedCourses
        );

        assertEqualString("A", availableCourses.get(0), "First available course should be A");
        assertEqualString("C", availableCourses.get(1), "Second available course should be C");
        assertEqual(2, availableCourses.size(), "There should be 2 available courses");
    }

    public static void testPrerequisiteProgression() {

        System.out.println("Running prerequisite progression test...");

        ArrayList<String> allCourses = new ArrayList<>();

        allCourses.add("A");
        allCourses.add("B");
        allCourses.add("C");

        HashMap<String, ArrayList<String>> courseStructureMap = new HashMap<>();

        ArrayList<String> prerequisitesForA = new ArrayList<>();
        ArrayList<String> prerequisitesForB = new ArrayList<>();
        prerequisitesForB.add("A");
        ArrayList<String> prerequisitesForC = new ArrayList<>();
        prerequisitesForC.add("B");

        courseStructureMap.put("A", prerequisitesForA);
        courseStructureMap.put("B", prerequisitesForB);
        courseStructureMap.put("C", prerequisitesForC);

        HashMap<String, Integer> numOfPrerequisitesMap = new HashMap<>();

        numOfPrerequisitesMap.put("A", 0);
        numOfPrerequisitesMap.put("B", 1);
        numOfPrerequisitesMap.put("C", 1);

        DegreeData degreeData = new DegreeData(
                allCourses,
                courseStructureMap,
                numOfPrerequisitesMap
                );

        ArrayList<String> completedCourses = new ArrayList<>();

        ArrayList<String> availableCourses = CoursePlanner.findAvailableCourses(
                degreeData.getAllCourses(),
                degreeData.getNumOfPrerequisitesMap(),
                completedCourses
        );

        assertEqualString("A", availableCourses.get(0), "A should be available first");
        assertEqual(1, availableCourses.size(), "Only A should be available first");

        CoursePlanner.updatePrerequisiteCounts(degreeData, "A");
        completedCourses.add("A");

        availableCourses = CoursePlanner.findAvailableCourses(
                degreeData.getAllCourses(),
                degreeData.getNumOfPrerequisitesMap(),
                completedCourses
        );

        assertEqualString("B", availableCourses.get(0), "B should become available after A");
        assertEqual(1, availableCourses.size(), "Only B should be available after A");
    }

    public static void testCircularDependencyAndStandardMethods() {

        System.out.println("Running circular dependency test");

        ArrayList<String> allCourses = new ArrayList<>();

        allCourses.add("A");
        allCourses.add("B");

        HashMap<String, ArrayList<String>> courseStructureMap = new HashMap<>();

        ArrayList<String> prerequisiteForA = new ArrayList<>();
        prerequisiteForA.add("B");

        ArrayList<String> prerequisiteForB = new ArrayList<>();
        prerequisiteForB.add("A");

        courseStructureMap.put("A", prerequisiteForA);
        courseStructureMap.put("B", prerequisiteForB);

        HashMap<String, Integer> numOfPrerequisitesMap = new HashMap<>();
        numOfPrerequisitesMap.put("A", 1);
        numOfPrerequisitesMap.put("B", 2);

        DegreeData degreeData1 = new DegreeData(
                allCourses,
                courseStructureMap,
                numOfPrerequisitesMap
        );

        ArrayList<String> completedCourses = new ArrayList<>();

        ArrayList<String> availableCourses = CoursePlanner.findAvailableCourses(
                degreeData1.getAllCourses(),
                degreeData1.getNumOfPrerequisitesMap(),
                completedCourses
        );

        assertEqual(0, availableCourses.size(), "No courses should be available when " +
                "there is a circular dependency (A requires B, and vice versa)\n---");

        DegreeData degreeData2 = new DegreeData(
                allCourses,
                courseStructureMap,
                numOfPrerequisitesMap
        );

        ArrayList<String> allCourses2 = new ArrayList<>();
        allCourses2.add("B");
        allCourses2.add("C");

        DegreeData degreeData3 = new DegreeData(
                allCourses2,
                courseStructureMap,
                numOfPrerequisitesMap
        );

        // Test equal objects
        System.out.println("Running DegreeData standard methods test...\n---");
        System.out.println("Running equals test methods...");
        if (degreeData1.equals(degreeData2)) {
            System.out.println("PASS: Equal DegreeData objects");
        } else {
            System.out.println("FAIL: Equal DegreeData objects");
        }

        // Test unequal objects
        if (degreeData1.equals(degreeData3)) {
            System.out.println("FAIL: Equal DegreeData objects");
        } else {
            System.out.println("PASS: Not equal DegreeData objects");
        }
    }

    public static void assertEqual (int expected, int actual, String message) {

        if (expected == actual) {

            System.out.println("PASS: " + message);

        } else {

            System.out.println(
                    "FAIL: " + message
                    + " | Expected: " + expected
                    + " but got: " + actual
            );
        }
    }

    public static void assertEqualString(String expected, String actual, String message) {

        if (expected.equals(actual)) {
            System.out.println("PASS: " + message);

        } else {

            System.out.println(
                    "FAIL: " + message
                            + " | Expected: " + expected
                            + " but got: " + actual
            );
        }
    }
}
