public class MainTest {

    public static void main(String[] args) {

        testPrerequisiteCounts();
        System.out.println("---");
        testCourseCodeParsing();

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

        assertEqualString("INFT1032", course, "First element should be the course code");
        assertEqualString("COMP1043", course, "First element should be the course code");
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
