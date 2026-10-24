package corejava.oop;

/**
 * ============================================================================
 * File: Hello.java
 * Repository: my-java-practice
 * Description: Comprehensive demonstration of Core Java fundamental concepts:
 *   1. Inheritance & Constructor Chaining (super)
 *   2. Autoboxing & Unboxing (Primitive vs Wrapper)
 *   3. Member Inner Classes & Anonymous Inner Classes
 *   4. Method Overriding & Runtime Polymorphism (Dynamic Method Dispatch)
 *   5. Functional Interfaces & Lambda Expressions
 *   6. Custom Checked Exceptions & Robust Multi-Catch Exception Handling
 *   7. Multithreading, Thread Safety (synchronized) & Coordination (join)
 *   8. Stream I/O Demonstration (Scanner & BufferedReader)
 *
 * How to Compile & Run:
 *   javac Hello.java
 *   java Hello
 * ============================================================================
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

// ============================================================================
// 1. INHERITANCE & CONSTRUCTOR CHAINING
// ============================================================================

/**
 * Base Person class demonstrating encapsulation and base entity properties.
 */
class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void displayInfo() {
        System.out.println("  [Person] Name: " + name + " | Age: " + age);
    }
}

/**
 * Scholar class extending Person to demonstrate:
 *  - Inheritance (IS-A relationship)
 *  - Constructor chaining using super(name, age)
 *  - Method overriding (@Override)
 */
class Scholar extends Person {
    private final String major;

    public Scholar(String name, int age, String major) {
        super(name, age); // Invoking parent constructor
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public void displayInfo() {
        System.out.println("  [Scholar] Name: " + getName() + " | Age: " + getAge() + " | Major: " + major);
    }

    public void study() {
        System.out.println("  [Scholar] " + getName() + " is currently researching " + major + ".");
    }
}

// ============================================================================
// 2. INNER CLASSES (Member Inner Class & Anonymous Inner Class)
// ============================================================================

/**
 * Demonstrates a Member Inner Class accessing outer class private members.
 */
class OuterContainer {
    private final String outerSecret = "TopSecret-OuterVault-42";

    public class InnerVaultAccessor {
        public void revealSecret() {
            // Inner classes have direct access to private members of the enclosing class
            System.out.println("  [InnerClass] Successfully accessed outer private state: " + outerSecret);
        }
    }
}

/**
 * Contract used to demonstrate Anonymous Inner Classes and Lambda expressions.
 */
interface GreetingService {
    void greet(String message);
}

// ============================================================================
// 3. METHOD OVERRIDING & RUNTIME POLYMORPHISM
// ============================================================================

class NotificationService {
    public void send(String recipient, String message) {
        System.out.println("  [Base Notification] Sending generic notification to " + recipient + ": " + message);
    }
}

class EmailNotificationService extends NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("  [Email Notification] Dispatching secure email to " + recipient + ": \"" + message + "\"");
    }
}

// ============================================================================
// 4. FUNCTIONAL INTERFACE
// ============================================================================

/**
 * Functional interface containing exactly one abstract method (SAM).
 */
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

// ============================================================================
// 5. CUSTOM EXCEPTION & EXCEPTION HANDLING
// ============================================================================

/**
 * Custom checked exception to demonstrate domain-specific error handling.
 */
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class ExceptionDemoService {
    /**
     * Validates age and throws a custom exception if business rule is violated.
     */
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age " + age + " is below legal requirement (minimum 18).");
        }
        System.out.println("  [Validation] Age " + age + " is valid.");
    }

    /**
     * Demonstrates multi-catch block and finally execution guarantee.
     */
    public static void triggerArithmeticAndArrayHandling(boolean triggerError) {
        try {
            System.out.println("  [Try Block] Executing operations...");
            if (triggerError) {
                int numerator = 50;
                int denominator = 0;
                int quotient = numerator / denominator; // Throws ArithmeticException
                System.out.println("  Result: " + quotient);
            }
        } catch (ArithmeticException ex) {
            System.err.println("  [Caught ArithmeticException] Cannot divide by zero: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("  [Caught General Exception] " + ex.getMessage());
        } finally {
            System.out.println("  [Finally Block] Cleanup executed. (Guaranteed to run regardless of exceptions).");
        }
    }
}

// ============================================================================
// 6. MULTITHREADING & SYNCHRONIZATION
// ============================================================================

/**
 * Thread-safe counter demonstrating synchronization on shared mutable state.
 */
class SynchronizedCounter {
    private int count = 0;

    /**
     * Synchronized method prevents race conditions between competing threads.
     */
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

// ============================================================================
// MAIN DRIVER CLASS
// ============================================================================

public class Hello {

    public static void main(String[] args) {
        printBanner("CORE JAVA PRACTICE & DEMONSTRATION SUITE");

        // --------------------------------------------------------------------
        // Section 1: Inheritance, Polymorphism & Constructor Chaining
        // --------------------------------------------------------------------
        printSectionHeader("1. Inheritance & Constructor Chaining (super)");
        Person basePerson = new Person("Ada Lovelace", 36);
        basePerson.displayInfo();

        Scholar scholar = new Scholar("Alan Turing", 41, "Computer Science");
        scholar.displayInfo(); // Polymorphic method call
        scholar.study();

        // --------------------------------------------------------------------
        // Section 2: Primitive Boxing, Autoboxing & Unboxing
        // --------------------------------------------------------------------
        printSectionHeader("2. Primitive Boxing, Autoboxing & Unboxing");
        int primitiveNumber = 42;

        // Manual Boxing (using valueOf instead of deprecated new Integer())
        Integer manuallyBoxed = Integer.valueOf(primitiveNumber);

        // Autoboxing (automatic primitive to object wrapper conversion)
        Integer autoBoxed = primitiveNumber;

        // Manual Unboxing
        int manuallyUnboxed = autoBoxed.intValue();

        // Auto-unboxing (automatic object wrapper to primitive conversion)
        int autoUnboxed = autoBoxed;

        System.out.println("  Primitive value : " + primitiveNumber);
        System.out.println("  Manually Boxed  : " + manuallyBoxed + " (Type: " + manuallyBoxed.getClass().getSimpleName() + ")");
        System.out.println("  Autoboxed       : " + autoBoxed);
        System.out.println("  Manually Unboxed: " + manuallyUnboxed);
        System.out.println("  Auto-unboxed    : " + autoUnboxed);

        // --------------------------------------------------------------------
        // Section 3: Inner Classes (Member Inner & Anonymous Inner)
        // --------------------------------------------------------------------
        printSectionHeader("3. Inner Classes: Member Inner & Anonymous");
        // Instantiating member inner class through outer instance
        OuterContainer outer = new OuterContainer();
        OuterContainer.InnerVaultAccessor inner = outer.new InnerVaultAccessor();
        inner.revealSecret();

        // Anonymous Inner Class implementing GreetingService
        GreetingService anonymousGreeter = new GreetingService() {
            @Override
            public void greet(String message) {
                System.out.println("  [Anonymous Inner Class] Greeting: " + message);
            }
        };
        anonymousGreeter.greet("Welcome from an Anonymous Inner Class!");

        // --------------------------------------------------------------------
        // Section 4: Method Overriding & Runtime Polymorphism
        // --------------------------------------------------------------------
        printSectionHeader("4. Runtime Polymorphism (Dynamic Method Dispatch)");
        // Base reference pointing to derived object
        NotificationService notifier = new EmailNotificationService();
        notifier.send("dev@example.com", "System deployment completed successfully!");

        // --------------------------------------------------------------------
        // Section 5: Functional Interfaces & Lambda Expressions
        // --------------------------------------------------------------------
        printSectionHeader("5. Functional Interfaces & Lambda Expressions");
        // Implementing Single Abstract Method via lambda expressions
        MathOperation addition = (a, b) -> a + b;
        MathOperation multiplication = (a, b) -> a * b;

        int sumResult = addition.operate(15, 27);
        int productResult = multiplication.operate(6, 7);

        System.out.println("  Lambda Addition (15 + 27)      : " + sumResult);
        System.out.println("  Lambda Multiplication (6 * 7)  : " + productResult);

        // Lambda replacing anonymous inner class
        GreetingService lambdaGreeter = msg -> System.out.println("  [Lambda Service] Message: " + msg);
        lambdaGreeter.greet("Modern and concise lambda syntax in Java!");

        // --------------------------------------------------------------------
        // Section 6: Custom Exceptions & Robust Multi-Catch Handling
        // --------------------------------------------------------------------
        printSectionHeader("6. Custom Exceptions & Try-Catch-Finally");
        try {
            System.out.println("  Attempting to validate legal age for 16:");
            ExceptionDemoService.validateAge(16);
        } catch (InvalidAgeException e) {
            System.err.println("  [Caught Custom Exception] " + e.getMessage());
        }

        try {
            System.out.println("  Attempting to validate legal age for 21:");
            ExceptionDemoService.validateAge(21);
        } catch (InvalidAgeException e) {
            System.err.println("  [Caught Custom Exception] " + e.getMessage());
        }

        // Arithmetic exception handling and finally guarantee
        ExceptionDemoService.triggerArithmeticAndArrayHandling(true);

        // --------------------------------------------------------------------
        // Section 7: Multithreading, Synchronization & Thread Coordination
        // --------------------------------------------------------------------
        printSectionHeader("7. Multithreading, Thread Safety (synchronized) & join()");
        SynchronizedCounter sharedCounter = new SynchronizedCounter();

        int thread1Iterations = 2000;
        int thread2Iterations = 2000;

        // Two worker threads updating the same counter concurrently
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < thread1Iterations; i++) {
                sharedCounter.increment();
            }
        }, "Worker-Thread-1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < thread2Iterations; i++) {
                sharedCounter.increment();
            }
        }, "Worker-Thread-2");

        thread1.start();
        thread2.start();

        try {
            // join() ensures the main thread waits for both workers to terminate
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("  Thread interrupted: " + e.getMessage());
        }

        int expectedCount = thread1Iterations + thread2Iterations;
        System.out.println("  Worker 1 Iterations : " + thread1Iterations);
        System.out.println("  Worker 2 Iterations : " + thread2Iterations);
        System.out.println("  Expected Total Count: " + expectedCount);
        System.out.println("  Actual Counter Value: " + sharedCounter.getCount() + " (Thread-safe)");

        // --------------------------------------------------------------------
        // Section 8: Stream I/O Demonstration (Scanner & BufferedReader)
        // --------------------------------------------------------------------
        printSectionHeader("8. Stream I/O Demonstration (Scanner & BufferedReader)");
        // Demonstration using simulated stream data to ensure smooth, non-blocking execution.
        // For standard console input, replace StringReader with new InputStreamReader(System.in).
        String sampleInputData = "Developer 28\n";

        // Scanner Demonstration
        try (Scanner scanner = new Scanner(sampleInputData)) {
            String scannedName = scanner.next();
            int scannedAge = scanner.nextInt();
            System.out.println("  [Scanner Parsing] Parsed Name: " + scannedName + " | Age: " + scannedAge);
        }

        // BufferedReader Demonstration
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader("Engineering Team\n"))) {
            String line = bufferedReader.readLine();
            System.out.println("  [BufferedReader Parsing] Read Line: " + line);
        } catch (IOException e) {
            System.err.println("  [I/O Error] " + e.getMessage());
        }

        printBanner("ALL PRACTICE DEMONSTRATIONS EXECUTED SUCCESSFULLY");
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
