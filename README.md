# ☕ my-java-practice

A comprehensive, production-grade Core Java practice repository demonstrating essential OOP principles, modern Java language features, multithreading, the Collections Framework, the Stream API, Lambda expressions, and fundamental data structures.

Every file in this repository is designed to be self-explanatory, featuring detailed inline Javadoc explanations, clear output messages, and runnable examples.

---

## 📂 Repository Structure

```text
my-java-practice/
├── OopsMastery.java        # Comprehensive OOP: Pillars, Constructors, static, final, Interfaces
├── CollectionsMastery.java # Deep Dive: List, Set, Queue, Deque, Map, Iterators, LRU Cache
├── StreamMastery.java      # Deep Dive: Sources, Operations, Advanced Collectors, Parallelism
├── LambdaMastery.java      # Deep Dive: Evolution, Built-in Interfaces, Method Refs, Closures
├── AdvancedCoreJava.java   # Deep Dive: Wrappers, Optional, Pass-by-Value, Objects in Collections
├── Hello.java              # Core OOP, Inner Classes, Exceptions, Concurrency & Lambdas
├── Demo.java               # Collections Framework, Comparable vs Comparator & Stream API
├── TypeInference.java      # Modern Java: Local Variable Type Inference (var) & Records
├── LinkedList.java         # Interactive Singly Linked List Data Structure CLI
├── Node.java               # Node class supporting the Singly Linked List
├── Stack.java              # Custom Resizable Stack (LIFO) Data Structure
├── .gitignore              # Git exclusion rules for compiled classes & system files
└── README.md               # Project documentation and usage guide
```

---

## 🎯 Modules & Concepts Covered

### 1. `OopsMastery.java` — Complete Object-Oriented Programming (OOP) Suite
* **Encapsulation & Data Hiding**: Private state, invariant validation, authorized getters/mutators, and defensive copying.
* **Constructors & Chaining**:
  * Default constructors, multi-parameter constructors, and Copy constructors.
  * Same-class chaining via `this(...)`.
  * Superclass chaining via `super(...)`.
* **Inheritance & Object Hierarchies**: Single, multilevel, and hierarchical relationships; IS-A (inheritance) vs HAS-A (composition/aggregation).
* **Polymorphism**:
  * *Compile-time (Static binding)*: Method overloading and automatic type promotion.
  * *Runtime (Dynamic binding)*: Method overriding (`@Override`), dynamic method dispatch, and covariant return types.
* **Abstraction**:
  * *Abstract Classes*: State retention, constructors, template method design pattern.
  * *Modern Interfaces (Java 8/9+)*: Abstract methods, `default` methods, `static` utility methods, `private` helper methods, and resolving the Diamond Problem.
* **The `static` Keyword**:
  * Static variables (class-level shared state) vs instance variables.
  * Static initialization block vs Instance initialization block execution lifecycle.
  * Static methods and compile-time **Method Hiding** (vs runtime method overriding).
* **The `final` Keyword**:
  * Final variables (compile-time constants & blank finals).
  * Final methods (tamper prevention / prohibiting override).
  * Final classes (preventing subclassing / immutability).
* **The `finally` Block**: Guaranteed execution (even with `return` statements) and comparative analysis: `final` vs `finally` vs `finalize()`.

---

### 2. `CollectionsMastery.java` — Complete Collections Framework
* **`List` Interface**: `ArrayList` ($\mathcal{O}(1)$ random access), `LinkedList` ($\mathcal{O}(1)$ head/tail), legacy synchronized `Vector`, and `Stack`.
* **`Set` Interface**: `HashSet` ($\mathcal{O}(1)$ unique), `LinkedHashSet` (insertion-ordered), and `TreeSet` (Red-Black tree, `NavigableSet` range operations).
* **`Queue` & `Deque` Interface**: `PriorityQueue` (min-heap) and `ArrayDeque` (modern LIFO/FIFO replacement).
* **`Map` Interface**: `HashMap`, `LinkedHashMap` (LRU access-order cache), `TreeMap` (key-sorted navigation), and `ConcurrentHashMap` (thread-safe bucket locks).
* **Iterators & Concurrency**: Unidirectional `Iterator` vs bidirectional `ListIterator`, safe deletion, and fail-fast `ConcurrentModificationException`.
* **Modern Factories & Utilities**: `List.of()`, `Set.of()`, `Map.of()`, and `Collections` algorithms.

---

### 3. `StreamMastery.java` — Comprehensive Stream API Guide
* **Stream Sources**: Creation from Collections, Arrays, `Stream.of()`, `Stream.iterate()`, `Stream.generate()`, and `IntStream`.
* **Intermediate Operations**: `filter()`, `map()`, `flatMap()` (1-to-many flattening), `distinct()`, `sorted()`, `peek()`, `skip()`, and `limit()`.
* **Terminal Operations & Advanced Collectors**: `Collectors.groupingBy()`, `partitioningBy()`, `joining()`, `summarizingDouble()`, and `reduce()`.
* **Primitive Streams**: `IntStream` and `DoubleStream` avoiding wrapper overhead.
* **Lazy Evaluation**: Proves intermediate operations execute strictly on terminal demand.
* **Parallel Streams**: Thread-safe concurrency scaling via `parallelStream()` and atomic synchronization.

---

### 4. `LambdaMastery.java` — Lambdas & Functional Programming
* **Evolution**: Anonymous Inner Class $\rightarrow$ explicit lambda $\rightarrow$ concise expression $\rightarrow$ Method Reference.
* **Core Interfaces**: `Predicate`, `BiPredicate`, `Function`, `BiFunction`, `Consumer`, `BiConsumer`, `Supplier`, `UnaryOperator`, `BinaryOperator`.
* **Composition & Chaining**: `.and()`, `.or()`, `.negate()`, `.andThen()`, `.compose()`.
* **The 4 Method Reference Types**: Static, bound instance, unbound instance, and constructor references.
* **Closures**: Variable capture and the "effectively final" rule.
* **Higher-Order Functions**: Methods taking and returning customized functions.

---

### 5. `AdvancedCoreJava.java` — Wrappers, Optional, References, Collections & Concurrency
* **Wrapper Classes & Memory Caching**: Integer Cache ($-128$ to $127$), reference vs value equality, and NPE unboxing pitfalls.
* **`Optional<T>`**: `of`, `ofNullable`, `ifPresentOrElse`, `map`, `filter`, and eager `orElse()` vs lazy `orElseGet()`.
* **Strict Pass-by-Value Semantics**: Clear demonstration of state mutation vs reference reassignment.
* **Objects in Collections**: The `equals()` and `hashCode()` contract, live duplicate bug demo with `BrokenKey`, and defensive copying.
* **Multithreading**: `Callable<V>`, `Future<V>`, `ExecutorService`, `ReentrantLock`, and `AtomicInteger` CAS.
* **Modern Records**: Local records and pattern matching deconstruction.

---

### 6. Core Practice & Data Structures
* **`Hello.java`**: Core OOP, constructor chaining, inner classes, runtime polymorphism, and synchronized multithreading.
* **`Demo.java`**: `Comparable<Student>` vs `Comparator` strategies, stream operations, and stream lifecycle.
* **`TypeInference.java`**: Local Variable Type Inference (`var`) and immutable Java records.
* **`LinkedList.java` & `Node.java`**: Singly linked list with an interactive console menu.
* **`Stack.java`**: Custom dynamic array-based stack with auto-resizing.

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

# Run Core Practice Demonstration
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

To push your latest commits to GitHub:

```bash
git push origin main
```

---

## 📝 License
This project is open-source and available under the [MIT License](LICENSE).
