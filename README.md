# ☕ my-java-practice

A comprehensive, production-grade Core Java practice repository demonstrating essential OOP principles, modern Java language features, multithreading, the Collections Framework, the Stream API, Lambda expressions, fundamental data structures, and searching & sorting algorithms with step-by-step visual dry runs.

Every file in this repository is designed to be self-explanatory, featuring detailed inline Javadoc explanations, clear output messages, and runnable examples.

---

## 📂 Repository Structure

```text
my-java-practice/
├── SearchAlgorithms.java   # Searching: Linear Search & Binary Search (Iterative & Recursive) with Dry Runs
├── SortingAlgorithms.java  # Sorting: Bubble, Selection, Insertion & Quick Sort with Visual Dry Runs
├── Queue.java              # Custom Circular Queue (FIFO) with User-defined Size CLI
├── Stack.java              # Custom Array-based Stack (LIFO) with User-defined Size CLI
├── DynamicStack.java       # Dynamic Stack Practice
├── LinkedList.java         # Interactive Singly Linked List Data Structure CLI
├── Node.java               # Node class supporting the Singly Linked List
├── OopsMastery.java        # Comprehensive OOP: Pillars, Constructors, static, final, Interfaces
├── CollectionsMastery.java # Deep Dive: List, Set, Queue, Deque, Map, Iterators, LRU Cache
├── StreamMastery.java      # Deep Dive: Sources, Operations, Advanced Collectors, Parallelism
├── LambdaMastery.java      # Deep Dive: Evolution, Built-in Interfaces, Method Refs, Closures
├── AdvancedCoreJava.java   # Deep Dive: Wrappers, Optional, Pass-by-Value, Objects in Collections
├── Hello.java              # Core OOP, Inner Classes, Exceptions, Concurrency & Lambdas
├── Demo.java               # Collections Framework, Comparable vs Comparator & Stream API
├── TypeInference.java      # Modern Java: Local Variable Type Inference (var) & Records
├── .gitignore              # Git exclusion rules for compiled classes & system files
└── README.md               # Project documentation and usage guide
```

---

## 🎯 Modules & Concepts Covered

### 1. `SearchAlgorithms.java` — Searching Algorithms with Step-by-Step Dry Runs
* **Linear Search (Sequential Scan)**:
  * Works on both unsorted and sorted arrays.
  * Live dry run showing element inspection, match confirmation, and comparison count.
  * Time Complexity: Best $\mathcal{O}(1)$, Worst $\mathcal{O}(n)$, Space $\mathcal{O}(1)$.
* **Binary Search (Iterative Divide & Conquer)**:
  * Requires a sorted array.
  * Pointer tracking (`low`, `mid`, `high`) with visual subarray display and search space halving.
  * Overflow-safe midpoint formula: `mid = low + (high - low) / 2`.
  * Time Complexity: Best $\mathcal{O}(1)$, Worst $\mathcal{O}(\log n)$, Space $\mathcal{O}(1)$.
* **Binary Search (Recursive Call Stack)**:
  * Step-by-step call stack tracing displaying recursion depth and active search bounds.
* **Complexity Comparison**: Summary table detailing when to choose linear vs binary search.

---

### 2. `SortingAlgorithms.java` — Sorting Algorithms with Visual Dry Runs
* **Bubble Sort**:
  * Step-by-step adjacent element comparisons and swaps.
  * Displays array state after every pass with sorted suffix partition marker (`|`).
  * Optimized with early termination flag (`swapped`) achieving $\mathcal{O}(n)$ best-case time.
* **Selection Sort**:
  * Unsorted subarray scanning to identify the global minimum element.
  * Swaps minimum into place with visual sorted prefix partition marker (`|`).
  * Time Complexity: $\mathcal{O}(n^2)$ across all cases, Space $\mathcal{O}(1)$.
* **Insertion Sort**:
  * Step-by-step card-deck pickup of `key` element.
  * Shows right-shifting of larger elements and exact insertion slot.
  * Best-case $\mathcal{O}(n)$ for nearly sorted data, Space $\mathcal{O}(1)$.
* **Quick Sort**:
  * Divide and Conquer using the **Lomuto Partitioning Scheme**.
  * Shows pivot selection (last element), boundary pointer movements, element swaps, and recursion tree.
  * Average time $\mathcal{O}(n \log n)$, Space $\mathcal{O}(\log n)$ stack.
* **Algorithm Comparison Table**: In-depth cheat sheet covering Best, Average, Worst times, Auxiliary Space, Stability, and In-Place attributes.

---

### 3. Data Structures Suite
* **`Queue.java`**: Circular Array-based Queue implementation (FIFO) with dynamic user capacity, modulo wrap-around `(index + 1) % capacity`, and full CLI menu (`enqueue`, `dequeue`, `peek`, `display`).
* **`Stack.java`**: Array-based Stack implementation (LIFO) with user-defined initial capacity via `Scanner` and full interactive CLI menu (`push`, `pop`, `peek`, `display`).
* **`LinkedList.java` & `Node.java`**: Singly linked list with an interactive console menu and documented time complexities.

---

### 4. `OopsMastery.java` — Complete Object-Oriented Programming (OOP) Suite
* **Encapsulation & Data Hiding**: Private state, invariant validation, authorized getters/mutators, and defensive copying.
* **Constructors & Chaining**: Default, parameterized, and copy constructors with `this(...)` and `super(...)` chaining.
* **Inheritance & Hierarchy**: Single, multilevel, hierarchical; IS-A vs HAS-A (Composition).
* **Polymorphism**: Compile-time method overloading vs Runtime dynamic method dispatch (`@Override`) and covariant return types.
* **Abstraction**: Abstract classes (Template Method Pattern) vs Modern Interfaces (`default`, `static`, and `private` methods, Diamond problem resolution).
* **Keywords Deep Dive**: `static` (variables, methods, blocks, method hiding), `final` (variables, methods, classes), and `finally` block execution guarantee.

---

### 5. `CollectionsMastery.java`, `StreamMastery.java` & `LambdaMastery.java`
* **Collections**: `List` (ArrayList, LinkedList, Vector, Stack), `Set` (HashSet, LinkedHashSet, TreeSet), `Queue`/`Deque` (PriorityQueue, ArrayDeque), `Map` (HashMap, LinkedHashMap with LRU cache, TreeMap, ConcurrentHashMap), and fail-fast iterators.
* **Streams**: Creation, intermediate operations (`filter`, `map`, `flatMap`, `distinct`, `sorted`, `peek`, `limit`, `skip`), advanced collectors (`groupingBy`, `partitioningBy`, `joining`, `summarizingDouble`), reductions, and parallel streams.
* **Lambdas**: Evolution from anonymous classes, standard `java.util.function` interfaces, chaining (`and`, `or`, `andThen`, `compose`), method references, and closures.

---

### 6. `AdvancedCoreJava.java`
* **Wrappers & Memory**: Integer cache pool ($-128$ to $127$), `==` vs `.equals()`, unboxing NPE.
* **`Optional<T>`**: Idiomatic null safety, `ifPresentOrElse`, `map`, eager `orElse()` vs lazy `orElseGet()`.
* **Strict Pass-by-Value**: State mutation vs reference reassignment.
* **Objects in Collections**: `equals()` and `hashCode()` contract, live duplicate bug demo with `BrokenKey`, and defensive copying.
* **Concurrency**: `Callable<V>`, `Future<V>`, `ExecutorService`, `ReentrantLock`, and `AtomicInteger` CAS.

---

## 🚀 How to Compile & Run

### Prerequisites
* **Java Development Kit (JDK)**: Version 17 or 21 LTS recommended.
* Verify your Java installation:
  ```bash
  java -version
  javac -version
  ```

---

### Compilation Command
Compile the entire repository with a single command:
```bash
javac *.java
```

---

### Execution Commands

```bash
# Run Searching Algorithms with Visual Dry Runs
java SearchAlgorithms

# Run Sorting Algorithms with Visual Dry Runs
java SortingAlgorithms

# Run Queue Data Structure (FIFO)
java Queue

# Run Stack Data Structure (LIFO)
java Stack

# Run Complete OOP Suite
java OopsMastery

# Run Collections Deep Dive
java CollectionsMastery

# Run Stream API Guide
java StreamMastery

# Run Lambda Expressions & Functional Programming
java LambdaMastery

# Run Advanced Core Java (Wrappers, Optional, References, Concurrency)
java AdvancedCoreJava

# Run Singly Linked List Interactive CLI
java LinkedList
```

---

## 🌐 Pushing to GitHub

To push your latest commits to GitHub:

```bash
git push origin main
```

---

## 📝 License
This project is open-source and available under the [MIT License](LICENSE).
