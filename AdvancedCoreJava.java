/**
 * ============================================================================
 * File: AdvancedCoreJava.java
 * Repository: my-java-practice
 * Description: Master-level guide to critical Core Java concepts:
 *   1. Wrapper Classes: Autoboxing, Cache Pool (-128 to 127), and Null Safety
 *   2. Optional<T>: Idiomatic null-safety, map/flatMap, orElse vs orElseGet
 *   3. Java Parameter Passing Semantics: Strict Pass-by-Value Explained
 *      (Mutating object state vs Reassigning reference parameter)
 *   4. Using Objects in Collections: equals() & hashCode() Contract,
 *      Mutable Key Dangers, and Defensive Copying
 *   5. Multithreading Deep Dive: Callable, Future, ExecutorService, ReentrantLock, AtomicInteger
 *   6. Advanced Records: Local Records & Pattern Matching
 *
 * How to Compile & Run:
 *   javac AdvancedCoreJava.java
 *   java AdvancedCoreJava
 * ============================================================================
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AdvancedCoreJava {

    public static void main(String[] args) {
        printBanner("ADVANCED CORE JAVA: WRAPPERS, OPTIONAL, REFERENCES & CONCURRENCY");

        demonstrateWrapperClassesAndCache();
        demonstrateOptionalNullSafety();
        demonstratePassByValueSemantics();
        demonstrateObjectsInCollectionsAndContract();
        demonstrateMultithreadingAndExecutors();
        demonstrateAdvancedRecords();

        printBanner("ALL ADVANCED TOPICS DEMONSTRATED SUCCESSFULLY");
    }

    // ========================================================================
    // 1. WRAPPER CLASSES & INTEGER CACHE POOL
    // ========================================================================
    private static void demonstrateWrapperClassesAndCache() {
        printSectionHeader("1. WRAPPER CLASSES & THE INTEGER CACHE POOL");

        // 1A. Integer Cache (-128 to 127)
        // Java caches Integer objects in range [-128, 127] for performance.
        Integer a = 100;
        Integer b = 100;
        System.out.println("  Integer a = 100, b = 100;");
        System.out.println("  [Reference Equality (a == b)] : " + (a == b) + " (Cached in memory pool!)");

        Integer x = 200;
        Integer y = 200;
        System.out.println("\n  Integer x = 200, y = 200;");
        System.out.println("  [Reference Equality (x == y)] : " + (x == y) + " (Outside cache! Two distinct objects)");
        System.out.println("  [Value Equality (x.equals(y))]: " + x.equals(y) + " (ALWAYS use .equals() for wrappers!)");

        // 1B. Pitfall: NullPointerException during auto-unboxing
        Integer nullableWrapper = null;
        try {
            System.out.println("\n  Attempting to auto-unbox null Integer into primitive int...");
            int primitiveVal = nullableWrapper; // Compiles fine, but throws NPE at runtime!
        } catch (NullPointerException ex) {
            System.err.println("  [Caught NullPointerException] Auto-unboxing null wrapper throws NPE!");
        }

        // 1C. Useful Static Utilities
        System.out.println("\n  Wrapper Utilities:");
        System.out.println("    Binary of 42      : " + Integer.toBinaryString(42));
        System.out.println("    Double.isNaN(0/0.): " + Double.isNaN(0.0 / 0.0));
    }

    // ========================================================================
    // 2. OPTIONAL<T>: IDIOMATIC NULL SAFETY
    // ========================================================================
    private static void demonstrateOptionalNullSafety() {
        printSectionHeader("2. OPTIONAL<T>: IDIOMATIC NULL SAFETY");

        // 2A. Creation
        Optional<String> presentOpt = Optional.of("Antigravity Dev");
        Optional<String> nullableOpt = Optional.ofNullable(null);
        Optional<String> emptyOpt = Optional.empty();

        System.out.println("  presentOpt.isPresent(): " + presentOpt.isPresent());
        System.out.println("  nullableOpt.isEmpty() : " + nullableOpt.isEmpty());

        // 2B. ifPresent & ifPresentOrElse
        presentOpt.ifPresent(val -> System.out.println("  [ifPresent] Found: " + val));
        nullableOpt.ifPresentOrElse(
                val -> System.out.println("Found: " + val),
                () -> System.out.println("  [ifPresentOrElse] Handled absent value gracefully without NullPointerException.")
        );

        // 2C. map, filter & flatMap
        Optional<String> uppercaseBadge = presentOpt
                .filter(name -> name.startsWith("Anti"))
                .map(String::toUpperCase);
        System.out.println("  [filter + map] Result: " + uppercaseBadge.orElse("Default"));

        // 2D. orElse vs orElseGet (Eager vs Lazy Evaluation)
        System.out.println("\n  Demonstrating orElse vs orElseGet when value is present:");
        // orElse evaluates the fallback immediately even if not needed
        String resultOrElse = presentOpt.orElse(computeFallback("orElse (Eager)"));
        // orElseGet calls supplier ONLY if Optional is empty
        String resultOrElseGet = presentOpt.orElseGet(() -> computeFallback("orElseGet (Lazy)"));
        System.out.println("  Notice: 'orElseGet' supplier was NOT executed because value was present!");
    }

    private static String computeFallback(String source) {
        System.out.println("    [COMPUTING FALLBACK] Executed for: " + source);
        return "Fallback Value";
    }

    // ========================================================================
    // 3. JAVA PARAMETER PASSING: STRICT PASS-BY-VALUE
    // ========================================================================

    public static class MutableBox {
        public String label;
        public MutableBox(String label) { this.label = label; }
        @Override public String toString() { return "MutableBox[label=" + label + "]"; }
    }

    private static void demonstratePassByValueSemantics() {
        printSectionHeader("3. STRICT PASS-BY-VALUE SEMANTICS IN JAVA");

        // Case 1: Primitive pass-by-value
        int originalPrimitive = 50;
        modifyPrimitive(originalPrimitive);
        System.out.println("  [Primitives] Original value after method call: " + originalPrimitive + " (Unchanged)");

        // Case 2: Object Reference pass-by-value - Mutating internal state
        MutableBox boxA = new MutableBox("Original State");
        System.out.println("\n  Before mutateBoxState(): " + boxA);
        mutateBoxState(boxA);
        System.out.println("  After mutateBoxState() : " + boxA + " (Internal state mutated via reference copy)");

        // Case 3: Object Reference pass-by-value - Reassigning the parameter reference
        MutableBox boxB = new MutableBox("Initial Reference");
        System.out.println("\n  Before reassignBoxReference(): " + boxB);
        reassignBoxReference(boxB);
        System.out.println("  After reassignBoxReference() : " + boxB + " (Caller reference is UNCHANGED!)");
        System.out.println("  Explanation: Java passes the object reference BY VALUE (a copy of the pointer).");
        System.out.println("               Reassigning the parameter points the copy to a new address; caller is unaffected.");
    }

    private static void modifyPrimitive(int val) {
        val = 999; // Modifies local copy only
    }

    private static void mutateBoxState(MutableBox box) {
        box.label = "Mutated State!"; // Reaches into the object on the heap
    }

    private static void reassignBoxReference(MutableBox box) {
        box = new MutableBox("Brand New Heap Object"); // Reassigns local parameter copy only
    }

    // ========================================================================
    // 4. USING OBJECTS IN COLLECTIONS: equals() & hashCode() CONTRACT
    // ========================================================================

    /**
     * Faulty key class: Overrides equals() but FAILS to override hashCode()!
     */
    public static class BrokenKey {
        private final int id;
        public BrokenKey(int id) { this.id = id; }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return id == ((BrokenKey) o).id;
        }
        // Missing hashCode()! Uses default Object.hashCode() based on memory address
    }

    /**
     * Proper key class: Implements both equals() and hashCode() correctly.
     */
    public static class ProperKey {
        private final int id;
        public ProperKey(int id) { this.id = id; }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return id == ((ProperKey) o).id;
        }
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private static void demonstrateObjectsInCollectionsAndContract() {
        printSectionHeader("4. USING OBJECTS IN COLLECTIONS: equals() & hashCode() CONTRACT");

        // 4A. Broken hashCode demonstration in HashSet
        Set<BrokenKey> brokenSet = new HashSet<>();
        brokenSet.add(new BrokenKey(101));
        brokenSet.add(new BrokenKey(101)); // Equal logically, but gets different hash codes!

        System.out.println("  [BrokenKey in HashSet] Added two equal keys (id=101):");
        System.out.println("    HashSet size: " + brokenSet.size() + " (FAILED! Contains duplicate due to missing hashCode)");

        // 4B. Proper hashCode demonstration in HashSet
        Set<ProperKey> properSet = new HashSet<>();
        properSet.add(new ProperKey(101));
        properSet.add(new ProperKey(101)); // Equal logically, produces matching hash code

        System.out.println("\n  [ProperKey in HashSet] Added two equal keys (id=101):");
        System.out.println("    HashSet size: " + properSet.size() + " (SUCCESS! Duplicate prevented)");

        // 4C. Defensive Copying when passing Collections
        List<String> originalList = new ArrayList<>(List.of("A", "B", "C"));
        List<String> encapsulatedList = new ArrayList<>(originalList); // Defensive copy
        List<String> readOnlyView = Collections.unmodifiableList(encapsulatedList);

        System.out.println("\n  [Defensive Copying] Original list: " + originalList);
        originalList.add("D"); // Mutate external caller list
        System.out.println("  Internal Encapsulated copy remains protected: " + encapsulatedList);
        System.out.println("  Read-only view prevents mutation: " + readOnlyView);
    }

    // ========================================================================
    // 5. MULTITHREADING: CALLABLE, FUTURE, EXECUTOR & LOCKS
    // ========================================================================
    private static void demonstrateMultithreadingAndExecutors() {
        printSectionHeader("5. MULTITHREADING: Callable, Future, ExecutorService, ReentrantLock & Atomic");

        // 5A. Callable & Future via ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> heavyTask = () -> {
            TimeUnit.MILLISECONDS.sleep(150); // Simulate background work
            return 42 * 10;
        };

        Future<Integer> futureResult = executor.submit(heavyTask);
        System.out.println("  Task submitted to thread pool asynchronously. Is done? " + futureResult.isDone());

        try {
            // Blocks until the callable completes or timeout occurs
            int computedValue = futureResult.get(1, TimeUnit.SECONDS);
            System.out.println("  [Future.get()] Async computation result: " + computedValue);
        } catch (InterruptedException | ExecutionException | java.util.concurrent.TimeoutException e) {
            System.err.println("  Execution error: " + e.getMessage());
        }

        // 5B. Explicit Synchronization with ReentrantLock
        ReentrantLock explicitLock = new ReentrantLock();
        explicitLock.lock();
        try {
            System.out.println("\n  [ReentrantLock] Critical section entered with explicit lock.");
        } finally {
            explicitLock.unlock(); // Always release in finally block
            System.out.println("  [ReentrantLock] Lock released safely.");
        }

        // 5C. Lock-Free Thread Safety with AtomicInteger (Compare-And-Swap)
        AtomicInteger atomicCount = new AtomicInteger(10);
        int afterIncrement = atomicCount.incrementAndGet();
        boolean swapped = atomicCount.compareAndSet(11, 20); // If current == 11, set to 20

        System.out.println("\n  [AtomicInteger] Incremented to: " + afterIncrement);
        System.out.println("  [AtomicInteger CAS (11 -> 20)] Succeeded? " + swapped + " | Current value: " + atomicCount.get());

        // Graceful Executor Shutdown
        executor.shutdown();
        try {
            if (!executor.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    // ========================================================================
    // 6. ADVANCED RECORDS: LOCAL RECORDS & PATTERN MATCHING
    // ========================================================================
    private static void demonstrateAdvancedRecords() {
        printSectionHeader("6. ADVANCED RECORDS: LOCAL RECORDS & PATTERN MATCHING");

        // 6A. Local Record: Declared directly inside a method scope for lightweight data transfer
        record CityStat(String city, long population) {}

        List<CityStat> cities = List.of(
                new CityStat("Tokyo", 37_400_000L),
                new CityStat("Delhi", 32_900_000L),
                new CityStat("New York", 8_800_000L)
        );

        System.out.println("  [Local Record inside method] Megacities (> 30M population):");
        cities.stream()
                .filter(c -> c.population() > 30_000_000L)
                .forEach(c -> System.out.println("    - " + c.city() + ": " + c.population()));

        // 6B. Pattern Matching for instanceof with Records (Java 16+)
        Object mysteryData = new CityStat("San Francisco", 873_965L);

        if (mysteryData instanceof CityStat(String city, long pop)) {
            System.out.println("\n  [Record Pattern Matching deconstruction]:");
            System.out.println("    City Name       : " + city);
            System.out.println("    City Population : " + pop);
        }
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

