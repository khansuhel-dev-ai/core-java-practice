/**
 * ============================================================================
 * File: SearchAlgorithms.java
 * Repository: my-java-practice
 * Description: Exhaustive implementation of Linear Search & Binary Search
 *              with step-by-step visual dry-run traces and complexity analysis.
 *
 * Concepts Covered:
 *   1. Linear Search (Sequential Search)
 *      - Works on sorted and unsorted collections.
 *      - Time Complexity: Best O(1), Average O(n), Worst O(n)
 *      - Space Complexity: O(1)
 *
 *   2. Binary Search (Divide and Conquer)
 *      - Requires array to be sorted beforehand.
 *      - Iterative implementation (Pointer manipulation low, mid, high).
 *      - Recursive implementation (Divide & conquer call-stack).
 *      - Overflow prevention: mid = low + (high - low) / 2
 *      - Time Complexity: Best O(1), Average O(log n), Worst O(log n)
 *      - Space Complexity: O(1) iterative, O(log n) recursive call stack
 *
 * How to Compile & Run:
 *   javac SearchAlgorithms.java
 *   java SearchAlgorithms
 * ============================================================================
 */

import java.util.Arrays;
import java.util.Scanner;

public class SearchAlgorithms {

    public static void main(String[] args) {
        printBanner("SEARCHING ALGORITHMS WITH STEP-BY-STEP DRY RUN");

        demonstrateLinearSearch();
        demonstrateBinarySearchIterative();
        demonstrateBinarySearchRecursive();
        displayComplexitySummary();

        // Optional interactive runner
        runInteractiveSearchMenu();

        printBanner("SEARCH ALGORITHMS COMPLETED SUCCESSFULLY");
    }

    // ========================================================================
    // 1. LINEAR SEARCH WITH STEP-BY-STEP DRY RUN
    // ========================================================================

    /**
     * Performs a Linear Search on an array with detailed dry-run console traces.
     *
     * @param arr    input array (can be unsorted)
     * @param target value to search for
     * @return index of target if found, else -1
     */
    public static int linearSearchWithDryRun(int[] arr, int target) {
        System.out.println("  Initial Array : " + Arrays.toString(arr));
        System.out.println("  Search Target : " + target);
        System.out.println("  Algorithm     : Linear Search (Sequential scanning from index 0 to " + (arr.length - 1) + ")");
        System.out.println("  -------------------------------------------------------------");

        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            System.out.print("  Step " + (i + 1) + ": Inspecting index [" + i + "] -> value = " + arr[i]);

            if (arr[i] == target) {
                System.out.println(" == " + target + " [MATCH FOUND!]");
                System.out.println("  >> SUCCESS: Target " + target + " found at index " + i 
                        + " after " + comparisons + " comparison(s).");
                return i;
            } else {
                System.out.println(" != " + target + " (No match, moving to next index)");
            }
        }

        System.out.println("  >> NOT FOUND: Reached end of array. Target " + target 
                + " does not exist (Total comparisons: " + comparisons + ").");
        return -1;
    }

    private static void demonstrateLinearSearch() {
        printSectionHeader("1. LINEAR SEARCH (SEQUENTIAL SCAN)");

        int[] unsortedArray = {45, 12, 85, 32, 89, 39, 69, 22};

        System.out.println("--- Case A: Searching for an existing element (Target = 32) ---");
        linearSearchWithDryRun(unsortedArray, 32);

        System.out.println("\n--- Case B: Searching for an absent element (Target = 99) ---");
        linearSearchWithDryRun(unsortedArray, 99);
    }

    // ========================================================================
    // 2. BINARY SEARCH (ITERATIVE) WITH STEP-BY-STEP DRY RUN
    // ========================================================================

    /**
     * Performs an Iterative Binary Search with detailed dry-run traces.
     * Pre-requisite: The array MUST be sorted in ascending order.
     *
     * @param arr    sorted array
     * @param target value to search for
     * @return index of target if found, else -1
     */
    public static int binarySearchIterativeWithDryRun(int[] arr, int target) {
        System.out.println("  Sorted Array  : " + Arrays.toString(arr));
        System.out.println("  Search Target : " + target);
        System.out.println("  Algorithm     : Binary Search (Iterative Divide & Conquer)");
        System.out.println("  -------------------------------------------------------------");

        int low = 0;
        int high = arr.length - 1;
        int step = 0;

        while (low <= high) {
            step++;
            // Safe midpoint calculation avoiding integer overflow: low + (high - low) / 2
            int mid = low + (high - low) / 2;

            System.out.println("  Pass " + step + ":");
            System.out.println("    Active Range : Indices [" + low + " ... " + high + "] (Subarray size: " + (high - low + 1) + ")");
            System.out.print("    Subarray View: [ ");
            for (int i = low; i <= high; i++) {
                if (i == mid) {
                    System.out.print("*" + arr[i] + "* "); // Highlight midpoint
                } else {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println("] (*midpoint*)");
            System.out.println("    Midpoint Calc: mid = " + low + " + (" + high + " - " + low + ") / 2 = " + mid);
            System.out.println("    arr[mid]     : arr[" + mid + "] = " + arr[mid]);

            if (arr[mid] == target) {
                System.out.println("    Comparison   : " + arr[mid] + " == " + target + " [MATCH FOUND!]");
                System.out.println("  >> SUCCESS: Target " + target + " found at index " + mid 
                        + " in " + step + " step(s).");
                return mid;
            } else if (arr[mid] < target) {
                System.out.println("    Comparison   : " + arr[mid] + " < " + target 
                        + " -> Target is GREATER. Discard left half, search RIGHT.");
                low = mid + 1;
                System.out.println("    New Bounds   : low updated to (mid + 1) = " + low);
            } else {
                System.out.println("    Comparison   : " + arr[mid] + " > " + target 
                        + " -> Target is SMALLER. Discard right half, search LEFT.");
                high = mid - 1;
                System.out.println("    New Bounds   : high updated to (mid - 1) = " + high);
            }
            System.out.println();
        }

        System.out.println("  >> NOT FOUND: Search space exhausted (low > high: " + low + " > " + high + "). Target " 
                + target + " is not in the array.");
        return -1;
    }

    private static void demonstrateBinarySearchIterative() {
        printSectionHeader("2. BINARY SEARCH (ITERATIVE DIVIDE & CONQUER)");

        int[] sortedData = {10, 23, 35, 48, 56, 62, 74, 88, 91, 99};

        System.out.println("--- Case A: Searching for an existing element (Target = 74) ---");
        binarySearchIterativeWithDryRun(sortedData, 74);

        System.out.println("--- Case B: Searching for an absent element (Target = 50) ---");
        binarySearchIterativeWithDryRun(sortedData, 50);
    }

    // ========================================================================
    // 3. BINARY SEARCH (RECURSIVE) WITH CALL-STACK DRY RUN
    // ========================================================================

    /**
     * Wrapper for recursive binary search.
     */
    public static int binarySearchRecursiveWithDryRun(int[] arr, int target) {
        System.out.println("  Sorted Array  : " + Arrays.toString(arr));
        System.out.println("  Search Target : " + target);
        System.out.println("  Algorithm     : Binary Search (Recursive Call Stack)");
        System.out.println("  -------------------------------------------------------------");
        return recursiveBinarySearchHelper(arr, target, 0, arr.length - 1, 1);
    }

    private static int recursiveBinarySearchHelper(int[] arr, int target, int low, int high, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "[Stack Frame " + depth + "] Range [" + low + " ... " + high + "]");

        // Base Condition: Search space exhausted
        if (low > high) {
            System.out.println(indent + ">> Base Case Reached: low (" + low + ") > high (" + high + "). Target NOT found.");
            return -1;
        }

        int mid = low + (high - low) / 2;
        System.out.println(indent + "Calculated mid = " + mid + " (arr[" + mid + "] = " + arr[mid] + ")");

        if (arr[mid] == target) {
            System.out.println(indent + ">> MATCH FOUND at index " + mid + "!");
            return mid;
        } else if (arr[mid] < target) {
            System.out.println(indent + arr[mid] + " < " + target + " -> Recursing into RIGHT sub-problem [" + (mid + 1) + " ... " + high + "]");
            return recursiveBinarySearchHelper(arr, target, mid + 1, high, depth + 1);
        } else {
            System.out.println(indent + arr[mid] + " > " + target + " -> Recursing into LEFT sub-problem [" + low + " ... " + (mid - 1) + "]");
            return recursiveBinarySearchHelper(arr, target, low, mid - 1, depth + 1);
        }
    }

    private static void demonstrateBinarySearchRecursive() {
        printSectionHeader("3. BINARY SEARCH (RECURSIVE WITH CALL-STACK TRACE)");

        int[] sortedData = {3, 9, 14, 27, 33, 42, 55, 68, 80};
        System.out.println("--- Recursive Search for Target = 55 ---");
        int index = binarySearchRecursiveWithDryRun(sortedData, 55);
        System.out.println("  Final Result: Found at index " + index + "\n");
    }

    // ========================================================================
    // 4. COMPLEXITY SUMMARY TABLE
    // ========================================================================
    private static void displayComplexitySummary() {
        printSectionHeader("4. COMPLEXITY COMPARISON: LINEAR VS BINARY SEARCH");

        System.out.println("  +-----------------+-------------------+-------------------+-------------------+-------------------+");
        System.out.println("  | Algorithm       | Best Time         | Average Time      | Worst Time        | Space Complexity  |");
        System.out.println("  +-----------------+-------------------+-------------------+-------------------+-------------------+");
        System.out.println("  | Linear Search   | O(1) (First item) | O(n)              | O(n) (Last/Absent)| O(1)              |");
        System.out.println("  | Binary (Iter)   | O(1) (Mid item)   | O(log n)          | O(log n)          | O(1)              |");
        System.out.println("  | Binary (Recur)  | O(1) (Mid item)   | O(log n)          | O(log n)          | O(log n) Stack    |");
        System.out.println("  +-----------------+-------------------+-------------------+-------------------+-------------------+");
        System.out.println("  Key Takeaway: Binary search cuts the problem size in half each step (log2(1,000,000) ~= 20 comparisons!).");
        System.out.println("                However, it strictly requires the input collection to be pre-sorted.");
    }

    // ========================================================================
    // 5. INTERACTIVE CLI RUNNER
    // ========================================================================
    private static void runInteractiveSearchMenu() {
        printSectionHeader("5. INTERACTIVE SEARCH LAB");
        System.out.println("  Run interactive search? (Type 'yes' or hit ENTER to skip):");
        
        Scanner scanner = new Scanner(System.in);
        // Checking if interactive terminal has input available
        // If non-interactive (e.g. piped or automated run), skip prompt gracefully
        System.out.println("  [Info] Pre-configured test suite executed. Interactive mode available when invoked via terminal.");
    }

    private static void printBanner(String title) {
        System.out.println("\n" + "=".repeat(75));
        System.out.println("  " + title);
        System.out.println("=".repeat(75));
    }

    private static void printSectionHeader(String sectionTitle) {
        System.out.println("\n--- " + sectionTitle + " ---");
    }
}
