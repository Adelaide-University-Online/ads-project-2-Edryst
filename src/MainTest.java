public class MainTest {

    public static void main(String[] args) {

        testPrerequisiteCounts();

    }

    public static void testPrerequisiteCounts() {

        System.out.println("Running prerequisite count test...");

        int[] array1 = {2, 2};
        int[] array2 = {2, 1};

        String message = "Value test";

        assertEqual(array1[0], array1[1], message);
        assertEqual(array2[0], array2[1], message);

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
}
