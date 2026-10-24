package datastructures.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * Class: ArrayOperations
 * Package: datastructures.array
 * Description: Comprehensive practical operations across fundamental array types:
 *   1. int[]    : Insert, Delete, Search, Reverse, Min/Max, Sum, Average
 *   2. char[]   : Vowels/Consonants count, Palindrome test, Frequencies, In-place Reverse
 *   3. double[] : Statistical Mean, Standard Deviation, Threshold Filtering, Min/Max
 *   4. String[] : Search, Prefix Filtering, Alphabetical Sorting, String Joining
 * ============================================================================
 */
public class ArrayOperations {

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("         ARRAY OPERATIONS & MANIPULATION LAB         ");
        System.out.println("=====================================================");

        demonstrateIntArray();
        demonstrateCharArray();
        demonstrateDoubleArray();
        demonstrateStringArray();

        System.out.println("\n=====================================================");
        System.out.println("         ALL ARRAY DEMONSTRATIONS COMPLETED          ");
        System.out.println("=====================================================");
    }

    // ========================================================================
    // 1. INT ARRAY OPERATIONS
    // ========================================================================
    public static void demonstrateIntArray() {
        System.out.println("\n--- 1. INTEGER ARRAY (int[]) OPERATIONS ---");
        int[] numbers = {10, 45, 23, 78, 56, 89, 12};
        System.out.println("  Original Array : " + Arrays.toString(numbers));

        // Min, Max, Sum, Average
        int min = numbers[0];
        int max = numbers[0];
        int sum = 0;
        for (int num : numbers) {
            if (num < min) min = num;
            if (num > max) max = num;
            sum += num;
        }
        double avg = (double) sum / numbers.length;

        System.out.println("  Min: " + min + " | Max: " + max + " | Sum: " + sum + " | Avg: " + String.format("%.2f", avg));

        // Reversing in-place
        int[] reversed = numbers.clone();
        for (int i = 0, j = reversed.length - 1; i < j; i++, j--) {
            int temp = reversed[i];
            reversed[i] = reversed[j];
            reversed[j] = temp;
        }
        System.out.println("  Reversed Array : " + Arrays.toString(reversed));

        // Insertion at index (requires creating new array of size + 1)
        int insertVal = 99;
        int insertIdx = 3;
        int[] expanded = new int[numbers.length + 1];
        for (int i = 0, j = 0; i < expanded.length; i++) {
            if (i == insertIdx) {
                expanded[i] = insertVal;
            } else {
                expanded[i] = numbers[j++];
            }
        }
        System.out.println("  After inserting " + insertVal + " at index " + insertIdx + ": " + Arrays.toString(expanded));

        // Deletion at index (requires creating new array of size - 1)
        int deleteIdx = 1; // Delete index 1
        int[] shrunk = new int[numbers.length - 1];
        for (int i = 0, j = 0; i < numbers.length; i++) {
            if (i != deleteIdx) {
                shrunk[j++] = numbers[i];
            }
        }
        System.out.println("  After deleting element at index " + deleteIdx + ": " + Arrays.toString(shrunk));
    }

    // ========================================================================
    // 2. CHAR ARRAY OPERATIONS
    // ========================================================================
    public static void demonstrateCharArray() {
        System.out.println("\n--- 2. CHARACTER ARRAY (char[]) OPERATIONS ---");
        char[] letters = {'r', 'a', 'c', 'e', 'c', 'a', 'r'};
        System.out.println("  Original Chars: " + Arrays.toString(letters));

        // Check if Palindrome
        boolean isPalindrome = true;
        for (int i = 0, j = letters.length - 1; i < j; i++, j--) {
            if (letters[i] != letters[j]) {
                isPalindrome = false;
                break;
            }
        }
        System.out.println("  Is Palindrome? " + isPalindrome);

        // Count Vowels & Consonants
        char[] sentence = "Hello World Java".toLowerCase().toCharArray();
        int vowels = 0, consonants = 0;
        for (char ch : sentence) {
            if (Character.isLetter(ch)) {
                if ("aeiou".indexOf(ch) != -1) vowels++;
                else consonants++;
            }
        }
        System.out.println("  Text: \"Hello World Java\" -> Vowels: " + vowels + " | Consonants: " + consonants);

        // Character Frequency Map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : letters) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        System.out.println("  Character Frequencies in 'racecar': " + freqMap);
    }

    // ========================================================================
    // 3. DOUBLE ARRAY OPERATIONS
    // ========================================================================
    public static void demonstrateDoubleArray() {
        System.out.println("\n--- 3. DOUBLE ARRAY (double[]) OPERATIONS ---");
        double[] metrics = {12.5, 45.8, 23.4, 88.2, 56.1, 99.4};
        System.out.println("  Data points: " + Arrays.toString(metrics));

        // Mean calculation
        double sum = 0.0;
        for (double val : metrics) sum += val;
        double mean = sum / metrics.length;

        // Standard Deviation
        double varianceSum = 0.0;
        for (double val : metrics) {
            varianceSum += Math.pow(val - mean, 2);
        }
        double stdDev = Math.sqrt(varianceSum / metrics.length);

        System.out.printf("  Mean: %.2f | Std Deviation: %.2f%n", mean, stdDev);

        // Filtering values above 50.0
        System.out.print("  Values > 50.0: [ ");
        for (double val : metrics) {
            if (val > 50.0) System.out.print(val + " ");
        }
        System.out.println("]");
    }

    // ========================================================================
    // 4. STRING ARRAY OPERATIONS
    // ========================================================================
    public static void demonstrateStringArray() {
        System.out.println("\n--- 4. STRING ARRAY (String[]) OPERATIONS ---");
        String[] languages = {"Java", "Python", "JavaScript", "C++", "Kotlin", "Go", "Rust"};
        System.out.println("  Original Array : " + Arrays.toString(languages));

        // Linear Search
        String target = "Kotlin";
        int foundIndex = -1;
        for (int i = 0; i < languages.length; i++) {
            if (languages[i].equalsIgnoreCase(target)) {
                foundIndex = i;
                break;
            }
        }
        System.out.println("  Search for '" + target + "': Found at index " + foundIndex);

        // Alphabetical Sort
        String[] sortedLanguages = languages.clone();
        Arrays.sort(sortedLanguages);
        System.out.println("  Alphabetical Sort: " + Arrays.toString(sortedLanguages));

        // Prefix Filtering (starts with "J")
        System.out.print("  Starts with 'J': [ ");
        for (String lang : languages) {
            if (lang.startsWith("J")) System.out.print(lang + " ");
        }
        System.out.println("]");

        // Joining elements
        String joined = String.join(" | ", languages);
        System.out.println("  String.join(): " + joined);
    }
}
