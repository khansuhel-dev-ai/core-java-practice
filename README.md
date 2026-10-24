# ☕ my-java-practice

A comprehensive, production-grade Core Java practice repository demonstrating essential OOP principles, modern Java language features, multithreading, the Collections Framework, the Stream API, Lambda expressions, and fundamental data structures.

Every file in this repository is designed to be self-explanatory, featuring detailed inline Javadoc explanations, clear output messages, and runnable examples.

---

## 📂 Repository Structure

```text
my-java-practice/
├── Hello.java              # Core OOP, Inner Classes, Exceptions, Concurrency & Lambdas
├── Demo.java               # Collections Framework, Comparable vs Comparator & Stream API
├── TypeInference.java      # Modern Java: Local Variable Type Inference (var) & Records
├── CollectionsMastery.java # Deep Dive: List, Set, Queue, Deque, Map, Iterators, LRU Cache
├── StreamMastery.java      # Deep Dive: Sources, Operations, Advanced Collectors, Parallelism
├── LambdaMastery.java      # Deep Dive: Evolution, Built-in Interfaces, Method Refs, Closures
├── AdvancedCoreJava.java   # Deep Dive: Wrappers, Optional, Pass-by-Value, Objects in Collections
├── LinkedList.java         # Interactive Singly Linked List Data Structure CLI
├── Node.java               # Node class supporting the Singly Linked List
├── Stack.java              # Custom Resizable Stack (LIFO) Data Structure
├── .gitignore              # Git exclusion rules for compiled classes & system files
└── README.md               # Project documentation and usage guide
```

---

## 🎯 Modules & Concepts Covered

### 1. `CollectionsMastery.java` — Complete Collections Framework
* **`List` Interface**:
  * `ArrayList`: Dynamic array, fast indexed random access $\mathcal{O}(1)$.
  * `LinkedList`: Doubly-linked list, fast head/tail manipulation $\mathcal{O}(1)$.
  * `Vector` & `Stack`: Synchronized legacy collections vs modern alternatives.
* **`Set` Interface**:
  * `HashSet`: Unordered, null-permitting, unique elements with average $\mathcal{O}(1)$ operations.
  * `LinkedHashSet`: Maintains predictable element insertion order.
  * `TreeSet`: Red-Black tree backed, natural/custom sorting, and `NavigableSet` range operations (`subSet`, `lower`, `higher`, `descendingSet`).
* **`Queue` & `Deque` Interface**:
  * `PriorityQueue`: Min-heap priority processing.
  * `ArrayDeque`: Modern high-performance replacement for both `Stack` (LIFO) and `Queue` (FIFO).
* **`Map` Interface**:
  * `HashMap`: Fast $\mathcal{O}(1)$ key-value lookups, null handling, `getOrDefault()`.
  * `LinkedHashMap`: Access-order LRU (Least Recently Used) cache demonstration with eviction policy.
  * `TreeMap`: Key-sorted navigation (`firstKey`, `headMap`).
  * `ConcurrentHashMap`: Thread-safe segment locking without global synchronization.
* **Iterators & Concurrency**:
  * `Iterator` vs `ListIterator` (bidirectional traversal, index retrieval).
  * Safe in-flight deletion with `iterator.remove()` vs fail-fast `ConcurrentModificationException`.
* **Modern Collection Factories & Utilities**:
  * `List.of()`, `Set.of()`, `Map.of()` immutable collections.
  * `Collections.sort()`, `frequency()`, `binarySearch()`, `shuffle()`, `reverse()`, `min()`, `max()`.

---

### 2. `StreamMastery.java` — Comprehensive Stream API Guide
* **Stream Sources**: Creation from Collections, Arrays, `Stream.of()`, `Stream.iterate()`, `Stream.generate()`, and `IntStream.rangeClosed()`.
* **Intermediate Operations (Lazy Pipelines)**:
  * `filter()`, `map()`, and `flatMap()` (flattening 1-to-many relationships).
  * `distinct()`, `sorted()`, and non-intrusive debugging with `peek()`.
  * Pagination with `skip()` and `limit()`.
* **Terminal Operations & Advanced Collectors**:
  * `Collectors.groupingBy()`: Multi-level classification.
  * `Collectors.partitioningBy()`: Boolean predicate partitioning.
  * `Collectors.joining()`: Formatted delimited string output.
  * `Collectors.summarizingDouble()`: Single-pass statistical analysis (count, sum, min, average, max).
* **Reductions & Queries**: `reduce()` with/without identity, short-circuit matching (`anyMatch`, `allMatch`, `noneMatch`), and `findFirst()`.
* **Primitive Streams**: `IntStream` and `DoubleStream` avoiding wrapper boxing overhead.
* **Lazy Evaluation Verification**: Proving intermediate steps execute only when terminal operations are invoked.
* **Parallel Streams**: Multi-core throughput via `parallelStream()` and avoiding shared mutable state pitfalls using `AtomicInteger`.

---

### 3. `LambdaMastery.java` — Lambdas & Functional Programming
* **Evolution of Functional Code**: From verbose Anonymous Inner Classes to block lambdas, concise expressions, and Method References.
* **Standard `java.util.function` Interfaces**:
  * `Predicate<T>` & `BiPredicate<T, U>`
  * `Function<T, R>` & `BiFunction<T, U, R>`
  * `Consumer<T>` & `BiConsumer<T, U>`
  * `Supplier<T>`
  * `UnaryOperator<T>` & `BinaryOperator<T>`
* **Composition & Chaining**:
  * Predicate logic: `.and()`, `.or()`, `.negate()`.
  * Function pipelines: `.andThen()` vs `.compose()`.
* **The 4 Types of Method References**:
  1. Static method reference (`Double::parseDouble`)
  2. Bound instance method reference (`outputChannel::println`)
  3. Unbound instance method reference of an arbitrary object (`String::toUpperCase`)
  4. Constructor reference (`UserAccount::new`)
* **Closures & Variable Capture**: The "effectively final" rule and compiler restrictions.
* **Higher-Order Functions**: Passing functions as arguments and returning dynamically generated functions (currying/factories).

---

### 4. `AdvancedCoreJava.java` — Critical Core Java Concepts
* **Wrapper Classes & Memory Caching**:
  * Primitive vs Object wrappers, autoboxing, and auto-unboxing.
  * The `IntegerCache` pool ($-128$ to $127$) proving why reference equality (`==`) fails on wrappers and `.equals()` is required.
  * Avoiding `NullPointerException` during unboxing.
* **`Optional<T>` (Idiomatic Null Safety)**:
  * Creation (`of`, `ofNullable`, `empty`).
  * Inspection (`isPresent`, `isEmpty`, `ifPresent`, `ifPresentOrElse`).
  * Transformation (`map`, `filter`, `flatMap`).
  * Fallbacks: Eager `orElse()` vs Lazy `orElseGet()` and `orElseThrow()`.
* **Strict Pass-by-Value Semantics in Java**:
  * Primitives passed by value (original remains unchanged).
  * Object references passed by value (a copy of the reference handle is passed):
    * Mutating internal object state affects the original heap object.
    * Reassigning the parameter reference has ZERO effect on caller's reference.
* **Using Objects in Collections (`equals` & `hashCode` Contract)**:
  * The mathematical and logical requirement for overriding both `equals()` and `hashCode()`.
  * Live demonstration of duplicate entries inside a `HashSet` when `hashCode()` is omitted.
  * Dangers of mutating objects after using them as Map keys.
  * Defensive copying pattern (`new ArrayList<>(list)`, `Collections.unmodifiableList(list)`).
* **Multithreading & Concurrency**:
  * `Callable<V>` returning values and throwing checked exceptions.
  * `Future<V>` asynchronous retrieval via `ExecutorService`.
  * Thread synchronization with `ReentrantLock`.
  * Lock-free atomic operations via `AtomicInteger` (Compare-And-Swap).
* **Advanced Records**: Local records within method scopes and pattern matching with records (`instanceof Point(int x, int y)`).

---

### 5. `Hello.java`, `Demo.java`, `TypeInference.java` & Data Structures
* **`Hello.java`**: Core OOP, constructor chaining (`super`), member/anonymous inner classes, runtime polymorphism, custom exceptions, and synchronized multithreading.
* **`Demo.java`**: Natural sorting with `Comparable<Student>` vs custom `Comparator` strategies, stream filtering, and stream single-use lifecycle.
* **`TypeInference.java`**: Local Variable Type Inference (`var`) rules and immutable Java `record Alien`.
* **`LinkedList.java` & `Node.java`**: Singly linked list with an interactive menu and documented time complexities.
* **`Stack.java`**: Custom dynamic array-based stack with auto-resizing and underflow protection.

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
# Run Collections Deep Dive
java CollectionsMastery

# Run Stream API Guide
java StreamMastery

# Run Lambda Expressions & Functional Programming
java LambdaMastery

# Run Advanced Core Java (Wrappers, Optional, References, Concurrency)
java AdvancedCoreJava

# Run Core OOP Demonstration
java Hello

# Run Collections & Streams Demo
java Demo

# Run Type Inference & Records
java TypeInference

# Run Singly Linked List Interactive CLI
java LinkedList

# Run Custom Stack Demo
java Stack
```

---

## 🌐 Pushing to GitHub

To push this repository to your GitHub account under `my-java-practice`:

1. Create a new repository on [GitHub](https://github.com/new) named **`my-java-practice`** (leave **Initialize this repository with a README** unchecked).
2. Run the following commands in your terminal:
   ```bash
   git remote add origin https://github.com/<your-username>/my-java-practice.git
   git branch -M main
   git push -u origin main
   ```

---

## 📝 License
This project is open-source and available under the [MIT License](LICENSE).
