/**
 * ============================================================================
 * File: SortingAlgorithms.java
 * Repository: my-java-practice
 * Description: Master-level implementation of core sorting algorithms with
 *              vivid, step-by-step visual dry-run traces for every pass,
 *              comparison, swap, shift, and recursive partition:
 *
 * Algorithms Covered:
 *   1. Bubble Sort (Optimized with early termination flag)
 *   2. Selection Sort (Min-finding and placement)
 *   3. Insertion Sort (Card-deck pickup and shifting)
 *   4. Quick Sort (Divide and Conquer with Lomuto Partitioning)
 *   5. Comprehensive Time & Space Complexity Comparison Table
 *
 * How to Compile & Run:
 *   javac SortingAlgorithms.java
 *   java SortingAlgorithms
 * ============================================================================
 */

import java.util.Arrays;

public class SortingAlgorithms {

    public static void main(String[] args) {
        printBanner("SORTING ALGORITHMS WITH STEP-BY-STEP VISUAL DRY RUNS");

        demonstrateBubbleSort();
        demonstrateSelectionSort();
        demonstrateInsertionSort();
        demonstrateQuickSort();
        displaySortingComplexityTable();

        printBanner("ALL SORTING DEMONSTRATIONS COMPLETED SUCCESSFULLY");
    }

    // ========================================================================
    // 1. BUBBLE SORT (WITH STEP-BY-STEP DRY RUN & OPTIMIZATION)
    // ========================================================================

    /**
     * Sorts an array using Bubble Sort with pass-by-pass and swap-by-swap dry run traces.
     * Includes the boolean 'swapped' optimization for O(n) best-case execution.
     */
    public static void bubbleSortWithDryRun(int[] arr) {
        int n = arr.length;
        System.out.println("  Initial Array : " + Arrays.toString(arr));
        System.out.println("  Concept       : Repeatedly swap adjacent elements if they are in the wrong order.");
        System.out.println("  -------------------------------------------------------------------");

        int totalComparisons = 0;
        int totalSwaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            System.out.println("\n  --- Pass " + (i + 1) + " (Targeting largest remaining element to index " + (n - 1 - i) + ") ---");

            for (int j = 0; j < n - 1 - i; j++) {
                totalComparisons++;
                System.out.print("    Compare arr[" + j + "]=" + arr[j] + " and arr[" + (j + 1) + "]=" + arr[j + 1]);

                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    totalSwaps++;
                    System.out.println(" -> SWAP! => " + Arrays.toString(arr));
                } else {
                    System.out.println(" -> In order (no swap)");
                }
            }

            System.out.print("    End of Pass " + (i + 1) + " Array: [");
            for (int k = 0; k < n; k++) {
                if (k == n - 1 - i) {
                    System.out.print("| " + arr[k] + " "); // Mark sorted boundary
                } else {
                    System.out.print(arr[k] + (k < n - 1 ? ", " : ""));
                }
            }
            System.out.println("] (Elements after '|' are permanently sorted)");

            // Optimization: If no two elements were swapped in this pass, array is already sorted
            if (!swapped) {
                System.out.println("    >> OPTIMIZATION TRIGGERED: No swaps in Pass " + (i + 1) 
                        + ". Array is fully sorted! Terminating early.");
                break;
            }
        }

        System.out.println("\n  >> BUBBLE SORT COMPLETE: " + Arrays.toString(arr));
        System.out.println("     Total Comparisons: " + totalComparisons + " | Total Swaps: " + totalSwaps);
    }

    private static void demonstrateBubbleSort() {
        printSectionHeader("1. BUBBLE SORT (Adjacent Element Comparison & Bubbling)");

        int[] sample = {64, 34, 25, 12, 22};
        bubbleSortWithDryRun(sample);
    }

    // ========================================================================
    // 2. SELECTION SORT (WITH STEP-BY-STEP DRY RUN)
    // ========================================================================

    /**
     * Sorts an array using Selection Sort with minimum search visual traces.
     */
    public static void selectionSortWithDryRun(int[] arr) {
        int n = arr.length;
        System.out.println("  Initial Array : " + Arrays.toString(arr));
        System.out.println("  Concept       : Select the smallest unsorted element and swap it into its correct position.");
        System.out.println("  -------------------------------------------------------------------");

        int totalComparisons = 0;
        int totalSwaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            System.out.println("\n  --- Pass " + (i + 1) + " (Searching minimum for index " + i + ") ---");
            System.out.println("    Initial minimum assumed at index [" + i + "] = " + arr[i]);

            for (int j = i + 1; j < n; j++) {
                totalComparisons++;
                System.out.print("    Comparing arr[" + j + "]=" + arr[j] + " with current min arr[" + minIndex + "]=" + arr[minIndex]);

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    System.out.println(" -> NEW MINIMUM FOUND at index [" + minIndex + "] = " + arr[minIndex]);
                } else {
                    System.out.println(" -> (no change)");
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                totalSwaps++;
                System.out.println("    >> Action: Swapped index [" + i + "] (" + temp + ") with min index [" + minIndex + "] (" + arr[i] + ")");
            } else {
                System.out.println("    >> Action: Element " + arr[i] + " was already in its correct position. (0 swaps)");
            }

            System.out.print("    Array after Pass " + (i + 1) + ": [");
            for (int k = 0; k < n; k++) {
                System.out.print(arr[k]);
                if (k == i) {
                    System.out.print(" | "); // Sorted partition boundary
                } else if (k < n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("] (Elements before '|' are sorted)");
        }

        System.out.println("\n  >> SELECTION SORT COMPLETE: " + Arrays.toString(arr));
        System.out.println("     Total Comparisons: " + totalComparisons + " | Total Swaps: " + totalSwaps);
    }

    private static void demonstrateSelectionSort() {
        printSectionHeader("2. SELECTION SORT (Minimum Scan & Placement)");

        int[] sample = {29, 10, 14, 37, 13};
        selectionSortWithDryRun(sample);
    }

    // ========================================================================
    // 3. INSERTION SORT (WITH STEP-BY-STEP DRY RUN)
    // ========================================================================

    /**
     * Sorts an array using Insertion Sort with element pick-up and shifting dry runs.
     */
    public static void insertionSortWithDryRun(int[] arr) {
        int n = arr.length;
        System.out.println("  Initial Array : " + Arrays.toString(arr));
        System.out.println("  Concept       : Pick an element (key) and shift larger sorted elements right to insert it.");
        System.out.println("  -------------------------------------------------------------------");

        int totalShifts = 0;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            System.out.println("\n  --- Step " + i + " (Inserting key = " + key + " from index " + i + ") ---");
            System.out.println("    Sorted subarray before insertion: " + Arrays.toString(Arrays.copyOfRange(arr, 0, i)));

            int shiftsInStep = 0;
            while (j >= 0 && arr[j] > key) {
                System.out.println("    arr[" + j + "]=" + arr[j] + " > key (" + key + ") -> Shifting " + arr[j] + " right to index [" + (j + 1) + "]");
                arr[j + 1] = arr[j]; // Shift element right
                j--;
                shiftsInStep++;
                totalShifts++;
            }

            arr[j + 1] = key; // Place key in its sorted position
            System.out.println("    >> Inserted key " + key + " at index [" + (j + 1) + "] (Shifts: " + shiftsInStep + ")");
            System.out.println("    Current Array State: " + Arrays.toString(arr));
        }

        System.out.println("\n  >> INSERTION SORT COMPLETE: " + Arrays.toString(arr));
        System.out.println("     Total Shifts Performed: " + totalShifts);
    }

    private static void demonstrateInsertionSort() {
        printSectionHeader("3. INSERTION SORT (Card-Deck Pick & Shift)");

        int[] sample = {12, 11, 13, 5, 6};
        insertionSortWithDryRun(sample);
    }

    // ========================================================================
    // 4. QUICK SORT (DIVIDE & CONQUER WITH VISUAL PARTITIONING DRY RUN)
    // ========================================================================

    /**
     * Driver for Quick Sort.
     */
    public static void quickSortWithDryRun(int[] arr) {
        System.out.println("  Initial Array : " + Arrays.toString(arr));
        System.out.println("  Concept       : Pick a pivot, partition smaller elements to left and larger to right, recurse.");
        System.out.println("  Scheme        : Lomuto Partition Scheme (Pivot = arr[high])");
        System.out.println("  -------------------------------------------------------------------");

        quickSortHelper(arr, 0, arr.length - 1, 1);

        System.out.println("\n  >> QUICK SORT COMPLETE: " + Arrays.toString(arr));
    }

    private static void quickSortHelper(int[] arr, int low, int high, int depth) {
        if (low < high) {
            String indent = "  ".repeat(depth);
            System.out.println("\n" + indent + "[Call Depth " + depth + "] Partitioning Range [" + low + " ... " + high + "]:");
            System.out.println(indent + "Subarray View: " + Arrays.toString(Arrays.copyOfRange(arr, low, high + 1)));

            // Partitioning step
            int pivotIndex = partition(arr, low, high, depth);

            System.out.println(indent + ">> Pivot element locked at index [" + pivotIndex + "] = " + arr[pivotIndex]);
            System.out.println(indent + "Array after partition: " + Arrays.toString(arr));

            // Recurse on left and right partitions
            quickSortHelper(arr, low, pivotIndex - 1, depth + 1);
            quickSortHelper(arr, pivotIndex + 1, high, depth + 1);
        }
    }

    private static int partition(int[] arr, int low, int high, int depth) {
        String indent = "  ".repeat(depth) + "  ";
        int pivot = arr[high]; // Choosing the last element as pivot
        System.out.println(indent + "Selected Pivot = " + pivot + " (at index " + high + ")");

        int i = low - 1; // Index of smaller element boundary

        for (int j = low; j < high; j++) {
            System.out.print(indent + "Scanning arr[" + j + "]=" + arr[j]);
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                System.out.println(" <= pivot (" + pivot + ") -> Swap with arr[" + i + "] => " + Arrays.toString(arr));
            } else {
                System.out.println(" > pivot (" + pivot + ") -> Remains in right partition");
            }
        }

        // Place pivot at its final sorted boundary (i + 1)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        System.out.println(indent + "Finalizing Pivot: Swap pivot arr[" + high + "]=" + pivot 
                + " with arr[" + (i + 1) + "]=" + temp + " => " + Arrays.toString(arr));

        return i + 1;
    }

    private static void demonstrateQuickSort() {
        printSectionHeader("4. QUICK SORT (Divide & Conquer Lomuto Partitioning)");

        int[] sample = {10, 80, 30, 90, 40, 50, 70};
        quickSortWithDryRun(sample);
    }

    // ========================================================================
    // 5. SORTING ALGORITHMS COMPLEXITY & CHARACTERISTICS TABLE
    // ========================================================================
    private static void displaySortingComplexityTable() {
        printSectionHeader("5. ALGORITHM CHARACTERISTICS & COMPLEXITY CHEAT SHEET");

        System.out.println("  +----------------+-------------+-------------+-------------+-------------+----------+------------+");
        System.out.println("  | Algorithm      | Best Time   | Avg Time    | Worst Time  | Space       | Stable?  | In-Place?  |");
        System.out.println("  +----------------+-------------+-------------+-------------+-------------+----------+------------+");
        System.out.println("  | Bubble Sort    | O(n)        | O(n^2)      | O(n^2)      | O(1)        | Yes      | Yes        |");
        System.out.println("  | Selection Sort | O(n^2)      | O(n^2)      | O(n^2)      | O(1)        | No       | Yes        |");
        System.out.println("  | Insertion Sort | O(n)        | O(n^2)      | O(n^2)      | O(1)        | Yes      | Yes        |");
        System.out.println("  | Quick Sort     | O(n log n)  | O(n log n)  | O(n^2)      | O(log n)    | No       | Yes        |");
        System.out.println("  +----------------+-------------+-------------+-------------+-------------+----------+------------+");
        System.out.println("\n  Algorithm Selection Guide:");
        System.out.println("    - Use Insertion Sort: When the array is small (<= 20-50 elements) or already nearly sorted.");
        System.out.println("    - Use Quick Sort: For general purpose in-memory sorting with exceptional cache locality.");
        System.out.println("    - Stability Note: Stable sorting preserves the relative order of elements with equal keys.");
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
