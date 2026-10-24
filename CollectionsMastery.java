/**
 * ============================================================================
 * File: CollectionsMastery.java
 * Repository: my-java-practice
 * Description: Master-level guide and live implementation of the entire Java
 *              Collections Framework (JCF):
 *   1. List Interface: ArrayList, LinkedList, Vector, Stack
 *   2. Set Interface: HashSet, LinkedHashSet, TreeSet (NavigableSet)
 *   3. Queue & Deque Interface: PriorityQueue, ArrayDeque (as Queue & Stack)
 *   4. Map Interface: HashMap, LinkedHashMap (LRU demonstration), TreeMap, ConcurrentHashMap
 *   5. Iterator vs ListIterator & Fail-Fast (ConcurrentModificationException)
 *   6. Modern Factory Methods (List.of, Set.of, Map.of) & Collections Utilities
 *
 * How to Compile & Run:
 *   javac CollectionsMastery.java
 *   java CollectionsMastery
 * ============================================================================
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionsMastery {

    public static void main(String[] args) {
        printBanner("JAVA COLLECTIONS FRAMEWORK: COMPLETE DEEP DIVE");

        demonstrateLists();
        demonstrateSets();
        demonstrateQueuesAndDeques();
        demonstrateMaps();
        demonstrateIteratorsAndFailFast();
        demonstrateModernFactoriesAndUtilities();

        printBanner("ALL COLLECTIONS DEMONSTRATIONS COMPLETED SUCCESSFULLY");
    }

    // ========================================================================
    // 1. LIST IMPLEMENTATIONS: ArrayList, LinkedList, Vector, Stack
    // ========================================================================
    private static void demonstrateLists() {
        printSectionHeader("1. LIST INTERFACE: ArrayList, LinkedList, Vector, Stack");

        // 1A. ArrayList: Backed by a dynamic array. Fast random access O(1), slow insertion/deletion in middle O(n).
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Kotlin");
        arrayList.add("Scala");
        arrayList.add(1, "Go"); // Insert at index 1 (shifts subsequent elements)
        System.out.println("  [ArrayList] Fast indexed access (get(2)): " + arrayList.get(2));
        System.out.println("  [ArrayList] Elements: " + arrayList);

        // 1B. LinkedList: Backed by a doubly-linked list. Fast head/tail operations O(1), slow random access O(n).
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Node A");
        linkedList.addFirst("Head Node"); // O(1)
        linkedList.addLast("Tail Node");  // O(1)
        System.out.println("\n  [LinkedList] Head: " + linkedList.getFirst() + " | Tail: " + linkedList.getLast());
        System.out.println("  [LinkedList] Elements: " + linkedList);

        // 1C. Vector: Synchronized dynamic array (legacy, thread-safe, has locking overhead).
        Vector<Integer> vector = new Vector<>();
        vector.add(100);
        vector.add(200);
        System.out.println("\n  [Vector] Synchronized legacy list size: " + vector.size() + " | Elements: " + vector);

        // 1D. Stack: Subclass of Vector implementing LIFO (legacy, ArrayDeque is preferred in modern code).
        Stack<String> stack = new Stack<>();
        stack.push("Layer 1");
        stack.push("Layer 2");
        stack.push("Layer 3");
        System.out.println("\n  [Stack] Top element (peek): " + stack.peek());
        System.out.println("  [Stack] Popped: " + stack.pop() + " | Remaining: " + stack);
    }

    // ========================================================================
    // 2. SET IMPLEMENTATIONS: HashSet, LinkedHashSet, TreeSet
    // ========================================================================
    private static void demonstrateSets() {
        printSectionHeader("2. SET INTERFACE: HashSet, LinkedHashSet, TreeSet");

        // 2A. HashSet: Backed by HashMap. Unordered, allows one null, average O(1) operations.
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Banana");
        hashSet.add("Apple");
        hashSet.add("Orange");
        hashSet.add("Apple"); // Duplicate ignored
        hashSet.add(null);    // HashSet permits null
        System.out.println("  [HashSet] Unordered & Unique (Duplicates ignored): " + hashSet);

        // 2B. LinkedHashSet: Maintains insertion-order via doubly-linked buckets.
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");
        linkedHashSet.add("Second"); // Duplicate ignored
        System.out.println("\n  [LinkedHashSet] Preserves Insertion Order: " + linkedHashSet);

        // 2C. TreeSet: Backed by Red-Black Tree. Sorted order, no nulls allowed, O(log n) operations.
        NavigableSet<Integer> treeSet = new TreeSet<>();
        treeSet.addAll(Arrays.asList(45, 12, 89, 34, 67, 23));
        System.out.println("\n  [TreeSet] Naturally Sorted: " + treeSet);
        System.out.println("  [TreeSet] First: " + treeSet.first() + " | Last: " + treeSet.last());
        System.out.println("  [TreeSet] Lower than 34: " + treeSet.lower(34) + " | Higher than 34: " + treeSet.higher(34));
        System.out.println("  [TreeSet] SubSet [23 to 67): " + treeSet.subSet(23, 67));
        System.out.println("  [TreeSet] Descending View: " + treeSet.descendingSet());
    }

    // ========================================================================
    // 3. QUEUE & DEQUE: PriorityQueue, ArrayDeque
    // ========================================================================
    private static void demonstrateQueuesAndDeques() {
        printSectionHeader("3. QUEUE & DEQUE: PriorityQueue, ArrayDeque");

        // 3A. PriorityQueue: Min-heap by default. Elements processed by natural ordering or custom Comparator.
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(40);
        priorityQueue.offer(10);
        priorityQueue.offer(30);
        priorityQueue.offer(20);

        System.out.print("  [PriorityQueue] Polling by natural priority (Min-Heap): ");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + (priorityQueue.isEmpty() ? "\n" : " -> "));
        }

        // 3B. ArrayDeque: Resizable array implementation of Deque.
        // Recommended modern replacement for both Stack (LIFO) and Queue (FIFO).
        Deque<String> deque = new ArrayDeque<>();

        // Using as FIFO Queue
        deque.offerLast("Request 1");
        deque.offerLast("Request 2");
        deque.offerLast("Request 3");
        System.out.println("\n  [ArrayDeque as FIFO Queue] Served: " + deque.pollFirst() + " | Remaining: " + deque);

        // Using as LIFO Stack
        deque.push("Action Alpha");
        deque.push("Action Beta");
        System.out.println("  [ArrayDeque as LIFO Stack] Popped: " + deque.pop() + " | Remaining: " + deque);
    }

    // ========================================================================
    // 4. MAP IMPLEMENTATIONS: HashMap, LinkedHashMap, TreeMap, ConcurrentHashMap
    // ========================================================================
    private static void demonstrateMaps() {
        printSectionHeader("4. MAP INTERFACE: HashMap, LinkedHashMap, TreeMap, ConcurrentHashMap");

        // 4A. HashMap: O(1) average lookup/insertion. Permits one null key and multiple null values.
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 3);
        hashMap.put("Banana", 5);
        hashMap.put("Cherry", 8);
        hashMap.put(null, 0); // null key supported
        System.out.println("  [HashMap] Fast Key-Value Storage: " + hashMap);
        System.out.println("  [HashMap] getOrDefault('Mango', 0): " + hashMap.getOrDefault("Mango", 0));

        // 4B. LinkedHashMap: Preserves insertion order (or access order for LRU caches).
        // Access-order enabled with load factor and true flag:
        Map<Integer, String> lruCache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 3; // Keep maximum 3 entries
            }
        };
        lruCache.put(1, "Page 1");
        lruCache.put(2, "Page 2");
        lruCache.put(3, "Page 3");
        lruCache.get(1); // Access Page 1 (moves to most recently used)
        lruCache.put(4, "Page 4"); // Evicts least recently used (Page 2)
        System.out.println("\n  [LinkedHashMap as LRU Cache (Max 3)] After accessing 1 and adding 4: " + lruCache);

        // 4C. TreeMap: Sorted keys based on Red-Black Tree. Does not allow null keys.
        NavigableMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("US", "United States");
        treeMap.put("IN", "India");
        treeMap.put("DE", "Germany");
        treeMap.put("JP", "Japan");
        System.out.println("\n  [TreeMap] Sorted by Keys: " + treeMap);
        System.out.println("  [TreeMap] First Key: " + treeMap.firstKey() + " | Last Key: " + treeMap.lastKey());
        System.out.println("  [TreeMap] HeadMap before 'JP': " + treeMap.headMap("JP"));

        // 4D. ConcurrentHashMap: Thread-safe without locking the whole map (bucket-level concurrency).
        // Does not allow null keys or null values!
        Map<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("ConfigA", "Active");
        concurrentMap.put("ConfigB", "Standby");
        concurrentMap.putIfAbsent("ConfigA", "Overridden?"); // Won't overwrite
        System.out.println("\n  [ConcurrentHashMap] Thread-Safe Map: " + concurrentMap);
    }

    // ========================================================================
    // 5. ITERATOR VS LISTITERATOR & FAIL-FAST DEMO
    // ========================================================================
    private static void demonstrateIteratorsAndFailFast() {
        printSectionHeader("5. ITERATOR VS LISTITERATOR & FAIL-FAST BEHAVIOR");

        List<String> frameworkList = new ArrayList<>(Arrays.asList("Spring", "Hibernate", "Quarkus", "Micronaut"));

        // 5A. Classic Iterator (Forward only)
        System.out.print("  [Iterator] Forward Traversal: ");
        Iterator<String> iterator = frameworkList.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.print(item + (iterator.hasNext() ? " -> " : "\n"));
        }

        // 5B. ListIterator (Bidirectional, index-aware, element modification)
        System.out.print("  [ListIterator] Backward Traversal: ");
        ListIterator<String> listIterator = frameworkList.listIterator(frameworkList.size()); // Start at the end
        while (listIterator.hasPrevious()) {
            int prevIndex = listIterator.previousIndex();
            String prevItem = listIterator.previous();
            System.out.print("[" + prevIndex + "]=" + prevItem + (listIterator.hasPrevious() ? " -> " : "\n"));
        }

        // 5C. Safe Removal during iteration vs ConcurrentModificationException
        System.out.println("\n  Safe removal using iterator.remove():");
        Iterator<String> safeIterator = frameworkList.iterator();
        while (safeIterator.hasNext()) {
            if ("Hibernate".equals(safeIterator.next())) {
                safeIterator.remove(); // Safe modification
            }
        }
        System.out.println("  List after safe removal: " + frameworkList);

        // 5D. Fail-Fast demonstration (Direct collection modification while iterating)
        try {
            System.out.println("  Attempting direct modification during for-each iteration...");
            for (String framework : frameworkList) {
                if ("Spring".equals(framework)) {
                    frameworkList.add("Struts"); // Throws ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException ex) {
            System.err.println("  [Caught ConcurrentModificationException] Directly modifying collection during iteration!");
            System.out.println("  Explanation: Iterators use a modCount check. Direct modification invalidates the iterator.");
        }
    }

    // ========================================================================
    // 6. MODERN FACTORY METHODS & COLLECTIONS UTILITIES
    // ========================================================================
    private static void demonstrateModernFactoriesAndUtilities() {
        printSectionHeader("6. MODERN FACTORIES (List.of, Set.of, Map.of) & UTILITIES");

        // Java 9+ Immutable Collection Factories
        List<String> immutableList = List.of("Alpha", "Beta", "Gamma");
        Set<String> immutableSet = Set.of("Red", "Green", "Blue");
        Map<String, Integer> immutableMap = Map.of("Ten", 10, "Twenty", 20);

        System.out.println("  [List.of] Immutable List: " + immutableList);
        System.out.println("  [Set.of]  Immutable Set : " + immutableSet);
        System.out.println("  [Map.of]  Immutable Map : " + immutableMap);

        // Attempting to modify an immutable collection
        try {
            immutableList.add("Delta");
        } catch (UnsupportedOperationException ex) {
            System.err.println("  [Caught Expected Exception] Immutable collection cannot be mutated via add()!");
        }

        // Collections Utility Methods
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 40, 20, 30, 20, 50, 20));
        System.out.println("\n  Original Numbers: " + numbers);

        Collections.sort(numbers);
        System.out.println("  Collections.sort(): " + numbers);

        int freq = Collections.frequency(numbers, 20);
        System.out.println("  Collections.frequency(numbers, 20): " + freq);

        int binarySearchIdx = Collections.binarySearch(numbers, 30);
        System.out.println("  Collections.binarySearch(numbers, 30): Index " + binarySearchIdx);

        Collections.shuffle(numbers);
        System.out.println("  Collections.shuffle(): " + numbers);

        Collections.reverse(numbers);
        System.out.println("  Collections.reverse(): " + numbers);

        System.out.println("  Collections.min(): " + Collections.min(numbers) + " | Collections.max(): " + Collections.max(numbers));
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
