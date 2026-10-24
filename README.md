# ☕ my-java-practice

A modular, production-grade Java repository structured into industry-standard packages. Covers core OOP concepts, modern Java language features (Streams, Lambdas, Records, Type Inference), the Collections Framework, fundamental & advanced Data Structures (Linked Lists, Stacks, Queues, Trees, Graphs, Arrays), Strings, and Searching & Sorting algorithms with step-by-step visual dry runs.

---

## 📂 Modular Package Architecture

```text
my-java-practice/
│
├── datastructures/
│   ├── common/
│   │   ├── Node.java              # Generic Node<T> for linear structures (Singly, Doubly, Circular)
│   │   └── TreeNode.java          # Generic TreeNode<T> for hierarchical structures (BST, Binary Tree)
│   ├── linkedlist/
│   │   ├── SinglyLinkedList.java  # Singly Linked List (Insert, Delete, Display, Search)
│   │   ├── DoublyLinkedList.java  # Doubly Linked List (Bidirectional: Forward & Backward Traversal)
│   │   └── CircularLinkedList.java# Circular Linked List (Tail connected to Head, Loop-safe display)
│   ├── stack/
│   │   ├── Stack.java             # Array-based Stack (LIFO) with user-defined dynamic size & CLI
│   │   └── DynamicStack.java      # Resizable Stack auto-doubling capacity on overflow
│   ├── queue/
│   │   └── Queue.java             # Circular Queue (FIFO) with modulo wrap-around & CLI
│   ├── tree/
│   │   └── BinarySearchTree.java  # BST: Insert, Delete, Traversals, Height, Depth, 2D ASCII Display
│   ├── graph/
│   │   └── Graph.java             # Adjacency List Graph (BFS, DFS, Pathfinding, Cycle Detection)
│   └── array/
│       └── ArrayOperations.java   # Operations on int[], char[], double[], and String[]
│
├── strings/
│   └── StringOperations.java      # Methods, String Pool, Immutability, StringBuilder vs StringBuffer, Algorithms
│
├── algorithms/
│   ├── searching/
│   │   └── SearchAlgorithms.java  # Linear Search & Binary Search (Iterative & Recursive) with dry runs
│   └── sorting/
│       └── SortingAlgorithms.java # Bubble, Selection, Insertion & Quick Sort with visual dry runs
│
├── corejava/
│   ├── oop/
│   │   ├── OopsMastery.java       # Encapsulation, Inheritance, Polymorphism, Abstraction, static, final, finally
│   │   └── Hello.java             # Core OOP, Inner Classes, Exceptions, Concurrency & Lambdas
│   ├── collections/
│   │   ├── CollectionsMastery.java# Deep Dive: List, Set, Queue, Deque, Map, Iterators, LRU Cache
│   │   └── Demo.java              # Collections Framework, Comparable vs Comparator & Stream API
│   ├── streams/
│   │   └── StreamMastery.java     # Sources, Operations, Advanced Collectors, Parallelism, Lazy Evaluation
│   ├── lambdas/
│   │   └── LambdaMastery.java     # Evolution, Built-in Interfaces, Method Refs, Closures, Higher-Order Functions
│   └── advanced/
│       ├── AdvancedCoreJava.java  # Wrappers, Optional, Pass-by-Value, Objects in Collections
│       └── TypeInference.java     # Modern Java: Local Variable Type Inference (var) & Records
│
├── .gitignore                     # Git exclusion rules for compiled classes & system files
└── README.md                      # Project documentation and package guide
```

---

## 🎯 Package Breakdown & Features

### 1. `datastructures.common`
* **[`Node<T>`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/common/Node.java)**: Shared generic node with `data`, `next`, and `prev` pointers, powering Singly, Doubly, and Circular Linked Lists.
* **[`TreeNode<T>`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/common/TreeNode.java)**: Shared generic tree node with `data`, `left`, and `right` child pointers, powering Tree and BST structures.

### 2. `datastructures.linkedlist`
* **[`SinglyLinkedList`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/linkedlist/SinglyLinkedList.java)**: $\mathcal{O}(1)$ start insertion, $\mathcal{O}(n)$ end/position insertion and deletion.
* **[`DoublyLinkedList`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/linkedlist/DoublyLinkedList.java)**: Bidirectional pointer linkage with forward (`Head -> Tail`) and backward (`Tail -> Head`) traversals.
* **[`CircularLinkedList`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/linkedlist/CircularLinkedList.java)**: Tail node linked back to head (`tail.next == head`), with loop-safe traversal and deletion.

### 3. `datastructures.stack`
* **[`Stack`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/stack/Stack.java)**: Array-based LIFO stack with user-defined initial capacity via `Scanner` and interactive CLI.
* **[`DynamicStack`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/stack/DynamicStack.java)**: Resizable array stack that automatically doubles capacity when threshold is reached.

### 4. `datastructures.queue`
* **[`Queue`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/queue/Queue.java)**: Array-based Circular FIFO Queue utilizing modulo arithmetic `(index + 1) % capacity` to prevent false overflow and guarantee $\mathcal{O}(1)$ operations.

### 5. `datastructures.tree`
* **[`BinarySearchTree`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/tree/BinarySearchTree.java)**:
  * Insertion ($\mathcal{O}(\log n)$ average) and Deletion handling leaf, single-child, and two-child scenarios (via In-Order Successor).
  * Traversals: In-Order (sorted), Pre-Order, Post-Order, and Level-Order (BFS).
  * Metrics: Height, Node Depth, Min, Max, and Leaf Count.
  * Hierarchical 2D ASCII Tree Diagram.

### 6. `datastructures.graph`
* **[`Graph`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/graph/Graph.java)**:
  * Adjacency list representation supporting Directed and Undirected graphs.
  * Breadth-First Search (BFS) and Depth-First Search (DFS).
  * Path existence checking (`hasPath`) and cycle detection.

### 7. `datastructures.array`
* **[`ArrayOperations`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/datastructures/array/ArrayOperations.java)**:
  * `int[]`: Insert, delete, reverse in-place, min, max, sum, average.
  * `char[]`: Vowel/consonant count, palindrome check, frequency map.
  * `double[]`: Mean, variance, standard deviation, threshold filtering.
  * `String[]`: Search, prefix matching, alphabetical sorting, string joining.

### 8. `strings`
* **[`StringOperations`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/strings/StringOperations.java)**:
  * Core methods (`substring`, `indexOf`, `split`, `replace`).
  * String Constant Pool (SCP) memory mechanics & immutability proof (`==` vs `.equals()`, `intern()`).
  * `String` vs `StringBuilder` vs `StringBuffer` benchmarks.
  * Classic interview algorithms: Palindrome, Anagram, Reversal, Word/Character counts.

### 9. `algorithms`
* **[`SearchAlgorithms`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/algorithms/searching/SearchAlgorithms.java)**: Linear & Binary Search (Iterative & Recursive) with step-by-step dry runs.
* **[`SortingAlgorithms`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/algorithms/sorting/SortingAlgorithms.java)**: Bubble, Selection, Insertion, and Quick Sort with visual step-by-step passes and swaps.

### 10. `corejava`
* **[`corejava.oop`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/corejava/oop/OopsMastery.java)**: Complete OOP pillars, constructors, `static`, `final`, `finally`, interfaces.
* **[`corejava.collections`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/corejava/collections/CollectionsMastery.java)**: Collections framework (`List`, `Set`, `Queue`, `Map`, LRU cache, iterators).
* **[`corejava.streams`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/corejava/streams/StreamMastery.java)**: Stream API pipelines, advanced collectors, parallelism, lazy evaluation.
* **[`corejava.lambdas`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/corejava/lambdas/LambdaMastery.java)**: Built-in functional interfaces, chaining, method references, closures.
* **[`corejava.advanced`](file:///Users/apple/Desktop/My-practice/Java_learning/core-java-practice/corejava/advanced/AdvancedCoreJava.java)**: Wrappers, `Optional`, strict pass-by-value proof, `equals`/`hashCode`, concurrency (`ExecutorService`, locks, atomics).

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
Compile all packages and files across the repository in a single command from project root:
```bash
javac $(find datastructures algorithms corejava strings -name "*.java")
```

---

### Execution Commands (From Project Root)

```bash
# Data Structures: Linked Lists
java datastructures.linkedlist.SinglyLinkedList
java datastructures.linkedlist.DoublyLinkedList
java datastructures.linkedlist.CircularLinkedList

# Data Structures: Stack & Queue
java datastructures.stack.Stack
java datastructures.stack.DynamicStack
java datastructures.queue.Queue

# Data Structures: Tree & Graph
java datastructures.tree.BinarySearchTree
java datastructures.graph.Graph

# Data Structures: Array Operations
java datastructures.array.ArrayOperations

# Strings Lab
java strings.StringOperations

# Algorithms: Searching & Sorting
java algorithms.searching.SearchAlgorithms
java algorithms.sorting.SortingAlgorithms

# Core Java Mastery
java corejava.oop.OopsMastery
java corejava.collections.CollectionsMastery
java corejava.streams.StreamMastery
java corejava.lambdas.LambdaMastery
java corejava.advanced.AdvancedCoreJava
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
