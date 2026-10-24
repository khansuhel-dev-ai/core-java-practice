package corejava.oop;

/**
 * ============================================================================
 * File: OopsMastery.java
 * Repository: my-java-practice
 * Description: Exhaustive, production-grade guide to Object-Oriented Programming (OOP) in Java:
 *   1. Encapsulation: Data Hiding, Validation, Getters/Setters, Immutability
 *   2. Constructors & Overloading: Default, Parameterized, Copy, Chaining (this & super)
 *   3. Inheritance & Hierarchy: Single, Multilevel, Hierarchical, IS-A vs HAS-A (Composition)
 *   4. Polymorphism:
 *      - Compile-Time (Static Binding): Method Overloading & Type Promotion
 *      - Runtime (Dynamic Binding): Method Overriding, Dynamic Method Dispatch, Covariant Returns
 *   5. Abstraction:
 *      - Abstract Classes (State, Constructors, Template Method Pattern)
 *      - Modern Interfaces (Abstract, default, static, and private methods, Diamond Problem resolution)
 *   6. The 'static' Keyword:
 *      - Static Variables (Class-level shared memory)
 *      - Static Methods & Method Hiding (vs Overriding)
 *      - Static vs Instance Initializer Block Execution Lifecycle
 *      - Static Nested Class vs Non-Static Inner Class
 *   7. The 'final' Keyword:
 *      - Final Variables (Constants, Blank Finals)
 *      - Final Methods (Preventing Override)
 *      - Final Classes (Preventing Subclassing & Creating Immutable Types)
 *   8. The 'finally' Block:
 *      - Guaranteed Execution (even with return statements)
 *      - Comparison: final vs finally vs finalize()
 *
 * How to Compile & Run:
 *   javac OopsMastery.java
 *   java OopsMastery
 * ============================================================================
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OopsMastery {

    public static void main(String[] args) {
        printBanner("OBJECT-ORIENTED PROGRAMMING (OOP) MASTERY SUITE");

        demonstrateEncapsulation();
        demonstrateConstructorsAndChaining();
        demonstrateInheritanceAndComposition();
        demonstratePolymorphismOverloadingOverriding();
        demonstrateAbstractionAndModernInterfaces();
        demonstrateStaticLifecycleAndHiding();
        demonstrateFinalKeyword();
        demonstrateFinallyVsFinalVsFinalize();

        printBanner("ALL OOP DEMONSTRATIONS COMPLETED SUCCESSFULLY");
    }

    // ========================================================================
    // 1. ENCAPSULATION & DATA HIDING
    // ========================================================================

    /**
     * Demonstrates strict encapsulation:
     * - Fields are private (data hiding).
     * - Access and mutation are governed through validation in public methods.
     * - Returns defensive copies of mutable internal structures.
     */
    public static class BankAccount {
        private final String accountNumber;
        private double balance;
        private final List<String> transactionLogs;

        public BankAccount(String accountNumber, double initialBalance) {
            if (accountNumber == null || accountNumber.isBlank()) {
                throw new IllegalArgumentException("Account number cannot be null or empty.");
            }
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative.");
            }
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
            this.transactionLogs = new ArrayList<>();
            this.transactionLogs.add("Account created with balance: $" + initialBalance);
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public List<String> getTransactionLogs() {
            // Defensive copying: returns unmodifiable view so callers cannot corrupt internal logs
            return Collections.unmodifiableList(transactionLogs);
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("  [Deposit Failed] Amount must be positive. Provided: $" + amount);
                return;
            }
            this.balance += amount;
            this.transactionLogs.add("Deposited: $" + amount + " | New Balance: $" + balance);
            System.out.println("  [Deposit Success] Added $" + amount + " -> Current Balance: $" + balance);
        }

        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("  [Withdraw Failed] Amount must be positive.");
                return false;
            }
            if (amount > balance) {
                System.out.println("  [Withdraw Failed] Insufficient funds. Requested: $" + amount + " | Available: $" + balance);
                return false;
            }
            this.balance -= amount;
            this.transactionLogs.add("Withdrew: $" + amount + " | New Balance: $" + balance);
            System.out.println("  [Withdraw Success] Dispensed $" + amount + " -> Remaining Balance: $" + balance);
            return true;
        }
    }

    private static void demonstrateEncapsulation() {
        printSectionHeader("1. ENCAPSULATION & DATA HIDING");

        BankAccount account = new BankAccount("ACCT-987654", 500.0);
        System.out.println("  Account Opened: " + account.getAccountNumber() + " with $" + account.getBalance());

        // Modifying balance via authorized business methods
        account.deposit(250.0);
        account.withdraw(100.0);
        account.withdraw(1000.0); // Validated against overdraft

        System.out.println("\n  Audit Logs (Retrieved safely via defensive copy):");
        account.getTransactionLogs().forEach(log -> System.out.println("    - " + log));
    }

    // ========================================================================
    // 2. CONSTRUCTORS, OVERLOADING & CHAINING (this & super)
    // ========================================================================

    public static class Device {
        private final String serialNumber;
        private final String brand;

        // Base constructor
        public Device(String serialNumber, String brand) {
            this.serialNumber = serialNumber;
            this.brand = brand;
            System.out.println("    [super() Executed] Device base constructor initialized (" + brand + " - " + serialNumber + ")");
        }

        public String getBrand() { return brand; }
        public String getSerialNumber() { return serialNumber; }
    }

    public static class Smartphone extends Device {
        private final String model;
        private final int storageCapacityGb;
        private final double price;

        // Default constructor chaining to overloaded constructor using this(...)
        public Smartphone() {
            this("Generic-Phone", 64, 299.99); // Chaining to 3-arg constructor
            System.out.println("    [this() Chained] Smartphone default constructor completed.");
        }

        // Overloaded constructor with 3 parameters
        public Smartphone(String model, int storageGb, double price) {
            this("SN-" + System.nanoTime() % 100000, "ApexBrand", model, storageGb, price);
        }

        // Master constructor chaining to parent using super(...)
        public Smartphone(String serialNumber, String brand, String model, int storageGb, double price) {
            super(serialNumber, brand); // Explicit call to parent constructor MUST be first statement
            this.model = model;
            this.storageCapacityGb = storageGb;
            this.price = price;
            System.out.println("    [Constructor] Smartphone specialized constructor completed: " + model);
        }

        // Copy Constructor: Creates a new object cloned from an existing instance
        public Smartphone(Smartphone original) {
            this("SN-CLONE-" + (original.getSerialNumber()), original.getBrand(), original.model, original.storageCapacityGb, original.price);
            System.out.println("    [Copy Constructor] Created cloned instance of: " + original.model);
        }

        @Override
        public String toString() {
            return "Smartphone[" + getBrand() + " " + model + ", " + storageCapacityGb + "GB, $" + price + ", SN=" + getSerialNumber() + "]";
        }
    }

    private static void demonstrateConstructorsAndChaining() {
        printSectionHeader("2. CONSTRUCTORS, OVERLOADING & CHAINING (this() & super())");

        System.out.println("  A. Instantiating via Default Constructor (triggers this() chaining):");
        Smartphone phoneDefault = new Smartphone();
        System.out.println("     Result: " + phoneDefault);

        System.out.println("\n  B. Instantiating via Parameterized Constructor:");
        Smartphone phoneParam = new Smartphone("Galaxy Pro", 256, 999.0);
        System.out.println("     Result: " + phoneParam);

        System.out.println("\n  C. Instantiating via Copy Constructor:");
        Smartphone phoneClone = new Smartphone(phoneParam);
        System.out.println("     Result: " + phoneClone);
    }

    // ========================================================================
    // 3. INHERITANCE & COMPOSITION (IS-A vs HAS-A)
    // ========================================================================

    // Component for Composition (HAS-A)
    public static class Engine {
        private final String fuelType;
        private final int horsepower;

        public Engine(String fuelType, int horsepower) {
            this.fuelType = fuelType;
            this.horsepower = horsepower;
        }

        public void start() {
            System.out.println("      [Engine] " + horsepower + " HP " + fuelType + " engine ignited.");
        }
    }

    // Base Class for Inheritance (IS-A)
    public static class VehicleModel {
        private final String make;
        private final String model;
        // Composition: VehicleModel HAS-A Engine
        private final Engine engine;

        public VehicleModel(String make, String model, Engine engine) {
            this.make = make;
            this.model = model;
            this.engine = engine;
        }

        public void turnOn() {
            System.out.println("    [Vehicle] Starting " + make + " " + model + ":");
            engine.start(); // Delegating to composed engine
        }

        public void drive() {
            System.out.println("    [Vehicle] " + make + " " + model + " is cruising on the road.");
        }

        public String getMake() { return make; }
        public String getModel() { return model; }
    }

    // Child Class demonstrating Single & Multilevel Inheritance
    public static class ElectricCar extends VehicleModel {
        private final int batteryKwh;

        public ElectricCar(String make, String model, int batteryKwh, int horsepower) {
            // Passing a specialized Electric engine to super constructor (Composition inside Inheritance)
            super(make, model, new Engine("Electric Battery", horsepower));
            this.batteryKwh = batteryKwh;
        }

        // Method Overriding
        @Override
        public void drive() {
            System.out.println("    [ElectricCar Override] " + getMake() + " " + getModel() 
                    + " is gliding silently using its " + batteryKwh + " kWh battery pack.");
        }

        public void recharge() {
            System.out.println("    [ElectricCar Feature] Recharging battery to 100% capacity.");
        }
    }

    private static void demonstrateInheritanceAndComposition() {
        printSectionHeader("3. INHERITANCE (IS-A) & COMPOSITION (HAS-A)");

        System.out.println("  Composition in Action (ElectricCar HAS-A Engine):");
        ElectricCar tesla = new ElectricCar("Tesla", "Model S", 100, 670);
        tesla.turnOn();

        System.out.println("\n  Inheritance in Action (ElectricCar IS-A VehicleModel):");
        tesla.drive(); // Calls overridden method
        tesla.recharge(); // Calls specialized child method
    }

    // ========================================================================
    // 4. POLYMORPHISM: OVERLOADING (COMPILE-TIME) VS OVERRIDING (RUNTIME)
    // ========================================================================

    public static class Calculator {
        // Method Overloading: Same method name, different parameter types / counts
        public int compute(int a, int b) {
            return a + b;
        }

        public double compute(double a, double b) {
            return a + b;
        }

        public int compute(int a, int b, int c) {
            return a + b + c;
        }

        public String compute(String prefix, int a, int b) {
            return prefix + ": " + (a + b);
        }
    }

    // Covariant Return Types demonstration
    public static class Animal {
        public Animal reproduce() {
            return new Animal();
        }
        public void speak() {
            System.out.println("    [Animal] Generic sound.");
        }
    }

    public static class Dog extends Animal {
        // Covariant return type: Overridden method can return a subtype of the parent's return type!
        @Override
        public Dog reproduce() {
            return new Dog();
        }

        @Override
        public void speak() {
            System.out.println("    [Dog] Woof! Woof!");
        }

        public void fetch() {
            System.out.println("    [Dog] Fetching the tennis ball!");
        }
    }

    private static void demonstratePolymorphismOverloadingOverriding() {
        printSectionHeader("4. POLYMORPHISM: OVERLOADING (Static) & OVERRIDING (Dynamic)");

        // 4A. Compile-time Polymorphism (Method Overloading)
        System.out.println("  A. Compile-Time Polymorphism (Method Overloading):");
        Calculator calc = new Calculator();
        System.out.println("     compute(10, 20)           -> " + calc.compute(10, 20));
        System.out.println("     compute(2.5, 4.5)         -> " + calc.compute(2.5, 4.5));
        System.out.println("     compute(1, 2, 3)          -> " + calc.compute(1, 2, 3));
        System.out.println("     compute(\"Total\", 15, 30) -> " + calc.compute("Total", 15, 30));

        // 4B. Runtime Polymorphism (Dynamic Method Dispatch)
        System.out.println("\n  B. Runtime Polymorphism (Dynamic Method Dispatch):");
        Animal myAnimal = new Dog(); // Upcasting: Parent reference points to Child object
        myAnimal.speak(); // Resolves to Dog.speak() at runtime!

        // Covariant return type in action
        Animal newborn = myAnimal.reproduce();
        System.out.println("     Reproduced animal runtime class: " + newborn.getClass().getSimpleName());
    }

    // ========================================================================
    // 5. ABSTRACTION: ABSTRACT CLASSES VS MODERN INTERFACES
    // ========================================================================

    /**
     * Abstract Class: Can hold state, constructors, abstract & concrete methods.
     * Demonstrates the Template Method Design Pattern.
     */
    public static abstract class PaymentProcessor {
        private final String merchantId;

        public PaymentProcessor(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getMerchantId() {
            return merchantId;
        }

        // Abstract method: Must be implemented by concrete subclasses
        public abstract boolean executeTransaction(double amount);

        // Concrete Template Method: Dictates the overall transaction workflow
        public final void processPayment(double amount) {
            System.out.println("    [Workflow Start] Authenticating merchant: " + merchantId);
            boolean success = executeTransaction(amount);
            if (success) {
                recordSuccess(amount);
            } else {
                recordFailure(amount);
            }
            System.out.println("    [Workflow End] Transaction session closed.\n");
        }

        private void recordSuccess(double amount) {
            System.out.println("    [Audit] Settlement recorded: $" + amount);
        }

        private void recordFailure(double amount) {
            System.out.println("    [Audit] Transaction decline recorded: $" + amount);
        }
    }

    /**
     * Interface 1: Demonstrates modern Java interface capabilities.
     */
    public interface Refundable {
        // Pure abstract method
        void issueRefund(double amount);

        // Default method (Java 8+): Provides fallback behavior without breaking implementors
        default void logRefundPolicy() {
            formatLog("Standard 30-day refund window applies.");
        }

        // Static method (Java 8+): Utility method tied to the interface namespace
        static boolean isValidCurrency(String currencyCode) {
            return "USD".equalsIgnoreCase(currencyCode) || "EUR".equalsIgnoreCase(currencyCode);
        }

        // Private helper method (Java 9+): Eliminates duplication between default methods
        private void formatLog(String msg) {
            System.out.println("      [Refundable Policy Log] " + msg);
        }
    }

    /**
     * Interface 2: Used to demonstrate multiple interface implementation & diamond problem resolution.
     */
    public interface AuditTrail {
        default void logRefundPolicy() {
            System.out.println("      [AuditTrail Policy Log] Strict compliance ledger audit active.");
        }
    }

    /**
     * Concrete implementation combining an Abstract Class and Multiple Interfaces.
     */
    public static class CreditCardProcessor extends PaymentProcessor implements Refundable, AuditTrail {

        public CreditCardProcessor(String merchantId) {
            super(merchantId);
        }

        @Override
        public boolean executeTransaction(double amount) {
            System.out.println("      [CreditCard] Authorizing credit charge of $" + amount + " through payment gateway.");
            return true;
        }

        @Override
        public void issueRefund(double amount) {
            System.out.println("      [CreditCard] Issuing credit reversal refund of $" + amount + ".");
        }

        // Resolving the Diamond Problem:
        // Both Refundable and AuditTrail define logRefundPolicy(). We MUST override to disambiguate!
        @Override
        public void logRefundPolicy() {
            System.out.println("      [Resolved Diamond Problem] Explicitly combining both interface policies:");
            Refundable.super.logRefundPolicy(); // Call Refundable default method
            AuditTrail.super.logRefundPolicy(); // Call AuditTrail default method
        }
    }

    private static void demonstrateAbstractionAndModernInterfaces() {
        printSectionHeader("5. ABSTRACTION: ABSTRACT CLASSES & MODERN INTERFACES");

        CreditCardProcessor processor = new CreditCardProcessor("MERCHANT-VISA-007");

        System.out.println("  A. Executing Abstract Class Template Method Workflow:");
        processor.processPayment(350.0);

        System.out.println("  B. Interface Methods (Abstract, Default, Static, Diamond Resolution):");
        processor.issueRefund(50.0);

        System.out.println("\n  Calling resolved default method:");
        processor.logRefundPolicy();

        System.out.println("\n  Interface Static Method (Refundable.isValidCurrency):");
        System.out.println("    Is 'USD' valid? " + Refundable.isValidCurrency("USD"));
        System.out.println("    Is 'XYZ' valid? " + Refundable.isValidCurrency("XYZ"));
    }

    // ========================================================================
    // 6. THE 'static' KEYWORD: VARIABLES, METHODS, BLOCKS & METHOD HIDING
    // ========================================================================

    public static class StaticDemonstrator {
        // Static variable: Shared by ALL instances in a single memory slot in Metaspace/Heap
        public static int instanceCounter = 0;
        public final int instanceId;

        // Static Initialization Block: Runs ONCE when class is loaded into JVM
        static {
            System.out.println("    [1. STATIC BLOCK] Executed exactly ONCE when class is loaded.");
            instanceCounter = 1000;
        }

        // Instance Initializer Block: Runs before every constructor call
        {
            instanceCounter++;
            this.instanceId = instanceCounter;
            System.out.println("    [2. INSTANCE BLOCK] Executed before constructor. Instance ID assigned: " + instanceId);
        }

        public StaticDemonstrator() {
            System.out.println("    [3. CONSTRUCTOR] Executed for instance: " + instanceId);
        }

        // Static method: Belongs to class, cannot use 'this'
        public static void printTotalInstances() {
            System.out.println("    [Static Method] Total count across all instances: " + instanceCounter);
        }
    }

    // Method Hiding Demonstration (Static methods cannot be overridden, only hidden!)
    public static class ParentService {
        public static void executeStaticGreeting() {
            System.out.println("    [ParentService.executeStaticGreeting] Hello from Parent Static!");
        }
    }

    public static class ChildService extends ParentService {
        // This HIDES ParentService.executeStaticGreeting(), does NOT override it!
        public static void executeStaticGreeting() {
            System.out.println("    [ChildService.executeStaticGreeting] Hello from Child Static (Hiding parent)!");
        }
    }

    private static void demonstrateStaticLifecycleAndHiding() {
        printSectionHeader("6. THE 'static' KEYWORD: BLOCKS, LIFECYCLE & METHOD HIDING");

        System.out.println("  A. Observing Static Block -> Instance Block -> Constructor order:");
        StaticDemonstrator obj1 = new StaticDemonstrator();
        System.out.println("  Creating second instance:");
        StaticDemonstrator obj2 = new StaticDemonstrator();

        StaticDemonstrator.printTotalInstances();

        System.out.println("\n  B. Static Method Hiding vs Dynamic Overriding:");
        ParentService parentRefToChild = new ChildService();
        // Since executeStaticGreeting is static, compile-time reference type determines the method called!
        parentRefToChild.executeStaticGreeting(); // Calls ParentService method!
        ChildService.executeStaticGreeting();     // Calls ChildService method!
        System.out.println("  Explanation: Static methods are bound at compile time based on reference type, NOT runtime object!");
    }

    // ========================================================================
    // 7. THE 'final' KEYWORD: VARIABLES, METHODS & CLASSES
    // ========================================================================

    // Final Class: Cannot be extended by any subclass
    public static final class ImmutableCoordinate {
        // Final variable: Cannot be reassigned once initialized
        private final int x;
        private final int y;

        public ImmutableCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }
        public int getY() { return y; }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static class SecurityProtocol {
        // Final method: Cannot be overridden by child classes (Prevents security tampering)
        public final void verifyFingerprint(String hash) {
            System.out.println("    [Final Method] Verifying cryptographic biometric signature: " + hash);
        }
    }

    public static class CustomSecurity extends SecurityProtocol {
        // Attempting to override verifyFingerprint() causes COMPILE ERROR!
        // void verifyFingerprint(String h) {} // ILLEGAL: cannot override final method
    }

    private static void demonstrateFinalKeyword() {
        printSectionHeader("7. THE 'final' KEYWORD (Variables, Methods & Classes)");

        // Blank Final Variable: Must be initialized exactly once
        final int thresholdLimit;
        thresholdLimit = 95;
        // thresholdLimit = 100; // COMPILE ERROR: cannot assign a value to final variable

        System.out.println("  Final Constant Threshold: " + thresholdLimit);

        ImmutableCoordinate point = new ImmutableCoordinate(12, 45);
        System.out.println("  Immutable Final Class Instance: " + point);

        CustomSecurity security = new CustomSecurity();
        security.verifyFingerprint("SHA256-BIO-9941");
    }

    // ========================================================================
    // 8. THE 'finally' BLOCK (vs 'final' vs 'finalize')
    // ========================================================================

    private static int demonstrateFinallyExecutionWithReturn() {
        try {
            System.out.println("    [Try Block] Executing code with return statement...");
            return 42; // Returns 42, but finally will execute before method exits!
        } catch (Exception ex) {
            System.out.println("    [Catch Block] Not reached.");
            return 0;
        } finally {
            System.out.println("    [Finally Block] EXECUTED GUARANTEED! (Runs even after 'return 42')");
        }
    }

    private static void demonstrateFinallyVsFinalVsFinalize() {
        printSectionHeader("8. THE 'finally' BLOCK (Comparison: final vs finally vs finalize)");

        System.out.println("  A. Verifying finally block execution when try block returns:");
        int returnedValue = demonstrateFinallyExecutionWithReturn();
        System.out.println("  Returned Value received by caller: " + returnedValue);

        System.out.println("\n  B. Summary Comparison Table:");
        System.out.println("  +------------+------------------+-------------------------------------------------+");
        System.out.println("  | Keyword    | Nature           | Purpose                                         |");
        System.out.println("  +------------+------------------+-------------------------------------------------+");
        System.out.println("  | final      | Modifier         | Prevents reassignment, overriding, subclassing  |");
        System.out.println("  | finally    | Code Block       | Guarantees execution for resource cleanup       |");
        System.out.println("  | finalize() | Method (Object)  | Deprecated garbage collection cleanup hook      |");
        System.out.println("  +------------+------------------+-------------------------------------------------+");
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

