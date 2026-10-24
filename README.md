# ☕ my-java-practice

A comprehensive, production-grade Core Java practice repository demonstrating essential OOP principles, modern Java language features, multithreading, the Collections Framework, the Stream API, Lambda expressions, fundamental data structures (Tree, Queue, Stack, LinkedList), and searching & sorting algorithms with step-by-step visual dry runs.

Every file in this repository is designed to be self-explanatory, featuring detailed inline Javadoc explanations, clear output messages, and runnable examples.

---

## 📂 Repository Structure

```text
my-java-practice/
├── BinarySearchTree.java   # Full-Fledged BST: Insert, Delete, Search, Traversals, Height, Depth, 2D Tree Diagram
├── Tree.java               # Direct CLI alias for BinarySearchTree
├── Queue.java              # Custom Circular Queue (FIFO) with User-defined Size CLI
├── Stack.java              # Custom Array-based Stack (LIFO) with User-defined Size CLI
├── DynamicStack.java       # Dynamic Stack Practice
├── LinkedList.java         # Interactive Singly Linked List Data Structure CLI
├── Node.java               # Node class supporting the Singly Linked List
├── SearchAlgorithms.java   # Searching: Linear Search & Binary Search (Iterative & Recursive) with Dry Runs
├── SortingAlgorithms.java  # Sorting: Bubble, Selection, Insertion & Quick Sort with Visual Dry Runs
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

### 1. `BinarySearchTree.java` & `Tree.java` — Complete Binary Search Tree (BST)
* **Core Operations**:
  * `insert(val)`: Recursive insertion with duplicate-rejection warning ($\mathcal{O}(\log n)$ average).
  * `delete(val)`: Handles all 3 structural cases:
    1. Leaf node (0 children) $\rightarrow$ direct pruning.
    2. Node with 1 child $\rightarrow$ bypass replacement.
    3. Node with 2 children $\rightarrow$ swap with **In-Order Successor** (smallest in right subtree) and recurse.
  * `contains(val)` / `search(val)`: Binary search navigation down the tree.
* **Traversals**:
  * **In-Order** (Left $\rightarrow$ Root $\rightarrow$ Right): Generates ascending sorted elements.
  * **Pre-Order** (Root $\rightarrow$ Left $\rightarrow$ Right): Useful for tree cloning and serialization.
  * **Post-Order** (Left $\rightarrow$ Right $\rightarrow$ Root): Useful for bottom-up node destruction and expression trees.
  * **Level-Order (Breadth-First Search / BFS)**: Traverses horizontally level-by-level using a FIFO Queue.
* **Tree Metrics & Properties**:
  * `getHeight()`: Edge count on the longest root-to-leaf path.
  * `getDepth(val)`: Edge count from root to the target node.
  * `getSize()`: Total number of active nodes.
  * `getMin()` & `getMax()`: Leftmost and rightmost extremes.
  * `countLeaves()`: Total count of childless leaf nodes.
* **Visual 2D ASCII Tree Diagram**: Renders the complete tree hierarchy in terminal with branched ASCII connectors (`└──`, `├──`, `│`).

---

### 2. Data Structures Suite (Queue, Stack, LinkedList)
* **`Queue.java`**: Circular Array-based Queue implementation (FIFO) with dynamic user capacity, modulo wrap-around `(index + 1) % capacity`, and full CLI menu (`enqueue`, `dequeue`, `peek`, `display`).
* **`Stack.java`**: Array-based Stack implementation (LIFO) with user-defined initial capacity via `Scanner` and full interactive CLI menu (`push`, `pop`, `peek`, `display`).
* **`LinkedList.java` & `Node.java`**: Singly linked list with an interactive console menu and documented time complexities.

---

### 3. `SearchAlgorithms.java` & `SortingAlgorithms.java`
* **Searching Algorithms**:
  * Linear Search with live index-by-index comparison dry run.
  * Binary Search (Iterative & Recursive) with search-space halving and pointer tracking (`low`, `mid`, `high`).
* **Sorting Algorithms**:
  * Bubble Sort with optimized `swapped` early-exit flag.
  * Selection Sort with live minimum scan.
  * Insertion Sort with card-deck shifting.
  * Quick Sort with **Lomuto Partitioning Scheme** recursion trace.

---

### 4. Core Java & OOP Suites
* **`OopsMastery.java`**: Encapsulation, inheritance, polymorphism (static vs dynamic), abstraction, interfaces, `static`, `final`, `finally`.
* **`CollectionsMastery.java`**: Complete Collections Framework (`List`, `Set`, `Queue`, `Deque`, `Map`, LRU cache, iterators).
* **`StreamMastery.java`**: Stream sources, intermediate operations, advanced collectors, reductions, lazy evaluation, and parallel streams.
* **`LambdaMastery.java`**: Built-in functional interfaces, chaining, method references, and closures.
* **`AdvancedCoreJava.java`**: Integer cache, `Optional`, strict pass-by-value proof, `equals`/`hashCode` contract, and multithreading (`Callable`, `Future`, `ExecutorService`).

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
# Run Binary Search Tree (BST)
java BinarySearchTree
# or simply:
java Tree

# Run Queue Data Structure (FIFO)
java Queue

# Run Stack Data Structure (LIFO)
java Stack

# Run Singly Linked List Interactive CLI
java LinkedList

# Run Searching Algorithms with Visual Dry Runs
java SearchAlgorithms

# Run Sorting Algorithms with Visual Dry Runs
java SortingAlgorithms

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
