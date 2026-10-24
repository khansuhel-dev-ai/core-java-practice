package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * Class: StringOperations
 * Package: strings
 * Description: Essential guide to Java Strings, JVM String Pool, and Algorithms:
 *   1. Core String Methods (substring, indexOf, contains, replace, split)
 *   2. String Constant Pool & Immutability (== vs .equals() and .intern())
 *   3. String vs StringBuilder vs StringBuffer (Mutability & Concurrency)
 *   4. Classic String Algorithms:
 *      - Palindrome Verification
 *      - Anagram Verification
 *      - String Reversal Techniques
 *      - Word Count & Character Frequency Analysis
 * ============================================================================
 */
public class StringOperations {

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("        STRING OPERATIONS & ALGORITHMS GUIDE         ");
        System.out.println("=====================================================");

        demonstrateCoreMethods();
        demonstrateStringPoolAndImmutability();
        demonstrateStringVsBuilderVsBuffer();
        demonstrateClassicAlgorithms();

        System.out.println("\n=====================================================");
        System.out.println("       ALL STRING DEMONSTRATIONS COMPLETED           ");
        System.out.println("=====================================================");
    }

    // ========================================================================
    // 1. CORE STRING METHODS
    // ========================================================================
    public static void demonstrateCoreMethods() {
        System.out.println("\n--- 1. ESSENTIAL STRING METHODS ---");
        String text = "  Core Java Programming Practice!  ";

        System.out.println("  Original Text   : \"" + text + "\"");
        System.out.println("  length()        : " + text.length());
        System.out.println("  trim()          : \"" + text.trim() + "\"");
        System.out.println("  charAt(7)       : '" + text.charAt(7) + "'");
        System.out.println("  substring(2, 6) : \"" + text.substring(2, 6) + "\"");
        System.out.println("  indexOf(\"Java\") : " + text.indexOf("Java"));
        System.out.println("  contains(\"Code\"): " + text.contains("Code"));
        System.out.println("  replace('!', '.'): \"" + text.trim().replace('!', '.') + "\"");

        // Split by delimiter
        String csv = "Apple,Banana,Orange,Mango,Peach";
        String[] fruits = csv.split(",");
        System.out.println("  split(\",\")      : " + Arrays.toString(fruits));
    }

    // ========================================================================
    // 2. STRING CONSTANT POOL & IMMUTABILITY
    // ========================================================================
    public static void demonstrateStringPoolAndImmutability() {
        System.out.println("\n--- 2. STRING POOL (SCP) & IMMUTABILITY ---");

        // Literal stored in String Constant Pool (SCP)
        String s1 = "Hello";
        String s2 = "Hello";

        // Explicit new keyword forces object creation on heap outside SCP
        String s3 = new String("Hello");

        System.out.println("  s1 = \"Hello\"; s2 = \"Hello\"; s3 = new String(\"Hello\");");
        System.out.println("  [s1 == s2]     : " + (s1 == s2) + " (Points to identical SCP memory address)");
        System.out.println("  [s1 == s3]     : " + (s1 == s3) + " (FALSE! s3 is a separate heap instance)");
        System.out.println("  [s1.equals(s3)]: " + s1.equals(s3) + " (TRUE! Value equality matches)");

        // Interning s3 moves its reference lookup to SCP
        String s4 = s3.intern();
        System.out.println("  [s1 == s3.intern()]: " + (s1 == s4) + " (Intern returns pooled instance)");

        // Immutability Demonstration
        String original = "Java";
        original.concat(" Virtual Machine"); // Discarded return value
        System.out.println("\n  After original.concat(\" Virtual Machine\"): \"" + original + "\" (Unchanged! Strings are immutable)");
    }

    // ========================================================================
    // 3. STRING VS STRINGBUILDER VS STRINGBUFFER
    // ========================================================================
    public static void demonstrateStringVsBuilderVsBuffer() {
        System.out.println("\n--- 3. STRING vs STRINGBUILDER vs STRINGBUFFER ---");

        // StringBuilder: Mutable, fast, not thread-safe (recommended for local loops)
        StringBuilder sb = new StringBuilder("Start");
        sb.append(" -> Middle").append(" -> End");
        System.out.println("  StringBuilder (Mutable): " + sb);

        sb.insert(5, " [Inserted]");
        System.out.println("  After insert()         : " + sb);

        sb.reverse();
        System.out.println("  After reverse()        : " + sb);

        // StringBuffer: Mutable, thread-safe via synchronized methods (legacy)
        StringBuffer sbuf = new StringBuffer("SynchronizedBuffer");
        sbuf.append(" (ThreadSafe)");
        System.out.println("\n  StringBuffer (Thread-Safe): " + sbuf);
    }

    // ========================================================================
    // 4. CLASSIC INTERVIEW STRING ALGORITHMS
    // ========================================================================
    public static void demonstrateClassicAlgorithms() {
        System.out.println("\n--- 4. CLASSIC STRING ALGORITHMS ---");

        // 4A. Palindrome Check
        String testWord1 = "madam";
        String testWord2 = "developer";
        System.out.println("  isPalindrome(\"" + testWord1 + "\"): " + isPalindrome(testWord1));
        System.out.println("  isPalindrome(\"" + testWord2 + "\"): " + isPalindrome(testWord2));

        // 4B. Anagram Check
        String str1 = "listen";
        String str2 = "silent";
        System.out.println("\n  isAnagram(\"" + str1 + "\", \"" + str2 + "\"): " + isAnagram(str1, str2));
        System.out.println("  isAnagram(\"apple\", \"pale\"): " + isAnagram("apple", "pale"));

        // 4C. Character Frequencies
        String sample = "datastructures";
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : sample.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        System.out.println("\n  Character Frequencies in \"" + sample + "\": " + freqMap);
    }

    /**
     * Palindrome check using two pointers: O(n) time, O(1) space.
     */
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Anagram check using character frequency array: O(n) time, O(1) extra space.
     */
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            counts[s1.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
        }
        for (int count : counts) {
            if (count != 0) return false;
        }
        return true;
    }
}

