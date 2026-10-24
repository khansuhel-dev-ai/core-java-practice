# ☕ my-java-practice

A comprehensive, production-grade Core Java practice repository demonstrating essential OOP principles, modern Java language features, multithreading, the Collections Framework, the Stream API, and fundamental data structures.

Every file in this repository is designed to be self-explanatory, featuring detailed inline Javadoc explanations, clear output messages, and runnable examples.

---

## 📂 Repository Structure

```text
my-java-practice/
├── Hello.java          # Core OOP, Inner Classes, Exceptions, Concurrency & Lambdas
├── Demo.java           # Collections Framework, Comparable vs Comparator & Stream API
├── TypeInference.java  # Modern Java: Local Variable Type Inference (var) & Records
├── LinkedList.java     # Interactive Singly Linked List Data Structure CLI
├── Node.java           # Node class supporting the Singly Linked List
├── Stack.java          # Custom Resizable Stack (LIFO) Data Structure
├── .gitignore          # Git exclusion rules for compiled classes & system files
└── README.md           # Project documentation and usage guide
```

---

## 🎯 Modules & Concepts Covered

### 1. `Hello.java` — Core Java & OOP Fundamentals
* **Inheritance & Constructor Chaining (`super`)**: `Person` base class extended by `Scholar`, chaining parent constructors and overriding methods.
* **Primitive Boxing, Autoboxing & Unboxing**: Seamless transitions between primitives (`int`) and wrappers (`Integer`), utilizing modern `Integer.valueOf()` practices.
* **Inner Classes**:
  * *Member Inner Class*: Demonstrates access to private enclosing state (`OuterContainer.InnerVaultAccessor`).
  * *Anonymous Inner Class*: On-the-fly implementation of `GreetingService`.
* **Runtime Polymorphism (Dynamic Method Dispatch)**: Method overriding with `@Override` through base reference `NotificationService` resolved to `EmailNotificationService`.
* **Functional Interfaces & Lambda Expressions**: Single Abstract Method (SAM) contracts evaluated with concise lambda syntax (`(a, b) -> a + b`).
* **Custom Checked Exceptions & Try-Catch-Finally**: Domain-specific `InvalidAgeException`, multi-catch blocks, and guaranteed cleanup execution in `finally`.
* **Multithreading & Thread Safety**: Shared mutable state managed by `SynchronizedCounter`, concurrent worker threads, and coordination using `join()`.
* **Console & Stream I/O**: Safe stream parsing with `Scanner` and `BufferedReader`.

### 2. `Demo.java` — Collections Framework & Stream API
* **`Comparable<T>` (Natural Ordering)**: `Student` entity implementing `compareTo` based on age.
* **`Comparator<T>` (Custom Strategies)**:
  * Sorting descending via lambda: `(s1, s2) -> Integer.compare(s2.getAge(), s1.getAge())`.
  * Sorting alphabetically via method reference: `Comparator.comparing(Student::getName)`.
* **Java Stream API**:
  * `filter()`: Filtering elements by predicate.
  * `map()`: Transforming strings (`String::toUpperCase`) and invoking constructor references (`Student::new`).
  * `toList()`: Collecting elements directly into unmodifiable lists.
* **Stream Lifecycle & Single-Use Rule**: Demonstrates and catches `IllegalStateException` when attempting to reuse an already-consumed stream pipeline.
* **Set & Iterator Pattern**: `TreeSet` maintaining sorted, distinct items traversed with `Iterator.hasNext()` and `Iterator.next()`.
* **Map Operations**: `HashMap` key-value mappings, entry iterations (`entrySet`), and fallback retrieval (`getOrDefault`).

### 3. `TypeInference.java` — Modern Java Features
* **Local Variable Type Inference (`var`) (Java 10+)**:
  * Type inference for primitives, strings, arrays, and complex generic maps.
  * Static type safety verification: `var` is resolved at compile time, preserving strict type checking.
  * Constraints and compiler rules for `var`.
* **Java Records (Java 14+, standard in Java 16+)**:
  * Immutable data carrier `record Alien(int age, String name, String homePlanet)`.
  * Compact constructors for input validation and normalization.
  * Auto-generated canonical constructors, getters (`alien.name()`), `toString()`, `equals()`, and `hashCode()`.
  * Value-based equality vs reference equality (`==` vs `.equals()`).

### 4. `LinkedList.java` & `Node.java` — Data Structures
* Singly Linked List implementation with:
  * `insertAtStart(data)` — $\mathcal{O}(1)$
  * `insertAtEnd(data)` — $\mathcal{O}(n)$
  * `insertAtPosition(data, pos)` — $\mathcal{O}(n)$
  * `deleteAtPosition(pos)` — $\mathcal{O}(n)$
  * `display()` — $\mathcal{O}(n)$
* Interactive console menu driver with bounds checking and error handling.

### 5. `Stack.java` — Resizable LIFO Data Structure
* Custom array-based Stack supporting:
  * `push(data)` — $\mathcal{O}(1)$ amortized (auto-resizing backing array)
  * `pop()` — $\mathcal{O}(1)$ with underflow protection (`EmptyStackException`)
  * `peek()` — $\mathcal{O}(1)$ top element inspection
  * `isEmpty()`, `size()`, and `display()` methods.

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

### Step-by-Step Execution Commands

#### Compile All Programs
```bash
javac *.java
```

#### Run `Hello.java`
```bash
java Hello
```

#### Run `Demo.java`
```bash
java Demo
```

#### Run `TypeInference.java`
```bash
java TypeInference
```

#### Run `LinkedList.java`
```bash
java LinkedList
```

#### Run `Stack.java`
```bash
java Stack
```

---

## 🌐 Pushing to GitHub

To push this repository to your GitHub account under `my-java-practice`:

1. Create a new repository on [GitHub](https://github.com/new) named **`my-java-practice`** (do not initialize with a README since we already have one).
2. Link your local repository to GitHub and push:
   ```bash
   git remote add origin https://github.com/<your-username>/my-java-practice.git
   git branch -M main
   git push -u origin main
   ```

---

## 📝 License
This project is open-source and available under the [MIT License](LICENSE).
