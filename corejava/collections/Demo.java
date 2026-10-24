package corejava.collections;

/**
 * ============================================================================
 * File: Demo.java
 * Repository: my-java-practice
 * Description: In-depth exploration of Java Collections Framework and Stream API:
 *   1. Comparable (Natural Ordering) vs Comparator (Custom Sorting Strategies)
 *   2. Stream API Operations: filter(), map(), Method References, toList()
 *   3. Stream Lifecycle & Single-Use Rule (Handling IllegalStateException)
 *   4. Set Collection (TreeSet) & Classic Iterator Traversal Pattern
 *   5. Map Collection (HashMap) & Key-Value Pair Operations
 *
 * How to Compile & Run:
 *   javac Demo.java
 *   java Demo
 * ============================================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * Domain model representing a Student.
 * Implements Comparable<Student> to establish a natural sorting order by age.
 */
class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student() {
        this("Unknown", 18);
    }

    public Student(String name) {
        this(name, 18);
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Natural Ordering defined by age (Ascending).
     * Returns a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

public class Demo {

    public static void main(String[] args) {
        printBanner("JAVA COLLECTIONS FRAMEWORK & STREAM API PRACTICE");

        // --------------------------------------------------------------------
        // Section 1: Comparable vs Comparator
        // --------------------------------------------------------------------
        printSectionHeader("1. Comparable (Natural Order) vs Comparator (Custom Strategy)");

        List<Student> students = new ArrayList<>();
        students.add(new Student("Charlie", 24));
        students.add(new Student("Alice", 21));
        students.add(new Student("Bob", 26));
        students.add(new Student("Diana", 19));

        System.out.println("  Original List:");
        students.forEach(s -> System.out.println("    " + s));

        // 1A. Natural Sorting via Comparable (by age ascending)
        Collections.sort(students);
        System.out.println("\n  Sorted by Comparable (Natural Order: Age Ascending):");
        students.forEach(s -> System.out.println("    " + s));

        // 1B. Custom Sorting via Comparator lambda (by Age Descending)
        Comparator<Student> ageDescending = (s1, s2) -> Integer.compare(s2.getAge(), s1.getAge());
        students.sort(ageDescending);
        System.out.println("\n  Sorted by Comparator Lambda (Age Descending):");
        students.forEach(s -> System.out.println("    " + s));

        // 1C. Custom Sorting via Comparator.comparing (by Name Alphabetical)
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("\n  Sorted by Comparator.comparing(Student::getName):");
        students.forEach(s -> System.out.println("    " + s));

        // --------------------------------------------------------------------
        // Section 2: Stream API Operations (filter, map, method references)
        // --------------------------------------------------------------------
        printSectionHeader("2. Stream API: filter, map & method references");

        // Filtering students older than 22
        List<Student> matureStudents = students.stream()
                .filter(s -> s.getAge() > 22)
                .toList();

        System.out.println("  Students older than 22 (filter):");
        matureStudents.forEach(s -> System.out.println("    " + s));

        // Mapping string names to uppercase
        List<String> rawNames = Arrays.asList("Alice", "Bob", "Charlie", "David");
        List<String> upperCaseNames = rawNames.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("\n  Transformed Names (map with String::toUpperCase): " + upperCaseNames);

        // Constructor Reference Mapping (String -> Student object)
        List<Student> mappedStudents = rawNames.stream()
                .map(Student::new) // Constructor reference
                .toList();
        System.out.println("\n  Generated Student Objects from names (map with Student::new):");
        mappedStudents.forEach(s -> System.out.println("    " + s));

        // --------------------------------------------------------------------
        // Section 3: Stream Lifecycle & Single-Use Rule
        // --------------------------------------------------------------------
        printSectionHeader("3. Stream Lifecycle & Single-Use Rule");
        Stream<String> reusableStreamCandidate = rawNames.stream();

        // First terminal operation consumes the stream
        long count = reusableStreamCandidate.count();
        System.out.println("  First terminal operation (.count()): " + count);

        // Attempting a second operation on the same stream instance
        try {
            System.out.println("  Attempting to reuse the consumed stream...");
            reusableStreamCandidate.forEach(System.out::println);
        } catch (IllegalStateException ex) {
            System.err.println("  [Expected Exception Caught] " + ex.getMessage());
            System.out.println("  Explanation: Java Streams are single-use pipelines. Once consumed or closed,");
            System.out.println("               a new stream must be created from the backing collection.");
        }

        // --------------------------------------------------------------------
        // Section 4: Sorted Set (TreeSet) & Iterator Traversal
        // --------------------------------------------------------------------
        printSectionHeader("4. Set Collection (TreeSet) & Iterator Pattern");

        // TreeSet automatically sorts elements in natural order and guarantees uniqueness
        Set<Integer> sortedNumbers = new TreeSet<>();
        sortedNumbers.add(50);
        sortedNumbers.add(10);
        sortedNumbers.add(30);
        sortedNumbers.add(10); // Duplicate, ignored by Set contract
        sortedNumbers.add(20);

        System.out.println("  TreeSet elements (Sorted & Unique): " + sortedNumbers);

        System.out.print("  Traversing TreeSet using classic Iterator: ");
        Iterator<Integer> iterator = sortedNumbers.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + (iterator.hasNext() ? " -> " : "\n"));
        }

        // --------------------------------------------------------------------
        // Section 5: Key-Value Mapping (HashMap)
        // --------------------------------------------------------------------
        printSectionHeader("5. Map Collection (HashMap) & Key-Value Operations");

        Map<Integer, String> studentRegistry = new HashMap<>();
        studentRegistry.put(101, "Alice");
        studentRegistry.put(102, "Bob");
        studentRegistry.put(103, "Charlie");

        // Reading entries using entrySet
        System.out.println("  Iterating through Map entries:");
        for (Map.Entry<Integer, String> entry : studentRegistry.entrySet()) {
            System.out.println("    ID: " + entry.getKey() + " -> Student: " + entry.getValue());
        }

        // Safe lookup using getOrDefault
        String student104 = studentRegistry.getOrDefault(104, "Not Found / Unregistered");
        System.out.println("  Lookup ID 104 with getOrDefault: " + student104);

        printBanner("DEMO SUITE COMPLETED SUCCESSFULLY");
    }

    private static void printBanner(String title) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  " + title);
        System.out.println("=".repeat(70));
    }

    private static void printSectionHeader(String sectionTitle) {
        System.out.println("\n--- " + sectionTitle + " ---");
    }
}
