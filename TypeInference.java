/**
 * ============================================================================
 * File: TypeInference.java
 * Repository: my-java-practice
 * Description: Exploration of Modern Java Language Enhancements:
 *   1. Local Variable Type Inference (`var`) introduced in Java 10
 *   2. Compile-Time Static Type Safety of `var`
 *   3. Golden Rules & Restrictions when using `var`
 *   4. Java Records (Java 14+, standardized in Java 16) as Immutable Data Carriers
 *   5. Auto-generated Record Features: Accessors, equals(), hashCode(), toString()
 *   6. Compact Constructors & Validation inside Records
 *
 * How to Compile & Run:
 *   javac TypeInference.java
 *   java TypeInference
 * ============================================================================
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ============================================================================
// JAVA RECORDS (Introduced in Java 14, standard in Java 16)
// ============================================================================

/**
 * An immutable data-carrier record representing an Alien.
 * The compiler automatically provides:
 *  - Private final fields for all components (age, name, homePlanet)
 *  - Canonical constructor
 *  - Component accessors: age(), name(), homePlanet()
 *  - Correct equals() and hashCode() implementations based on state
 *  - Informative toString() representation
 */
record Alien(int age, String name, String homePlanet) {

    /**
     * Compact Constructor:
     * Used for argument validation and normalization without boilerplate parameter assignments.
     */
    public Alien {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative. Provided: " + age);
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (homePlanet == null || homePlanet.isBlank()) {
            homePlanet = "Unknown Sector";
        }
    }

    /**
     * Overloaded constructor providing default planet if not specified.
     */
    public Alien(int age, String name) {
        this(age, name, "Deep Space");
    }

    /**
     * Custom instance method inside a record.
     */
    public String transmitGreeting() {
        return "Greetings from " + name + " of " + homePlanet + " (Age: " + age + " Earth years)";
    }
}

// ============================================================================
// MAIN DEMONSTRATION CLASS
// ============================================================================

public class TypeInference {

    public static void main(String[] args) {
        printBanner("MODERN JAVA: TYPE INFERENCE (var) & RECORDS");

        // --------------------------------------------------------------------
        // Section 1: Local Variable Type Inference (`var`)
        // --------------------------------------------------------------------
        printSectionHeader("1. Local Variable Type Inference (`var`)");

        // Primitive type inference
        var primitiveInt = 42; // Inferred as int
        var primitiveDouble = 3.14159; // Inferred as double
        var flag = true; // Inferred as boolean

        // Object and String inference
        var greeting = "Hello, Modern Java!"; // Inferred as String

        // Array inference
        var numberArray = new int[]{10, 20, 30, 40, 50}; // Inferred as int[]

        // Complex generic collection inference (reduces boilerplate)
        var alienDirectory = new HashMap<String, List<Alien>>(); // Inferred as HashMap<String, List<Alien>>
        alienDirectory.put("SolarSystem", new ArrayList<>());

        System.out.println("  Inferred int value     : " + primitiveInt);
        System.out.println("  Inferred double value  : " + primitiveDouble);
        System.out.println("  Inferred boolean value : " + flag);
        System.out.println("  Inferred String value  : " + greeting);
        System.out.println("  Inferred Array length  : " + numberArray.length);
        System.out.println("  Inferred Map type      : " + alienDirectory.getClass().getSimpleName());

        // --------------------------------------------------------------------
        // Section 2: Compile-Time Type Safety of `var`
        // --------------------------------------------------------------------
        printSectionHeader("2. Static Typing & Compiler Verification");
        System.out.println("  Note: 'var' in Java is NOT dynamic typing (unlike JavaScript or Python).");
        System.out.println("        It is strictly resolved at compile-time by the Java compiler.");
        
        var counter = 100;
        // counter = "A String"; // COMPILE ERROR: incompatible types: String cannot be converted to int
        counter += 50; // Perfectly valid, counter remains an int
        System.out.println("  Counter after modification: " + counter);

        // --------------------------------------------------------------------
        // Section 3: Golden Rules and Limitations of `var`
        // --------------------------------------------------------------------
        printSectionHeader("3. Golden Rules & Constraints of `var`");
        System.out.println("  [RULE 1] Must have an explicit initializer: var x;           // ILLEGAL");
        System.out.println("  [RULE 2] Cannot be initialized to null:     var y = null;    // ILLEGAL");
        System.out.println("  [RULE 3] Cannot be used as class fields:    private var z;   // ILLEGAL");
        System.out.println("  [RULE 4] Cannot be method parameters:       void foo(var a)  // ILLEGAL");
        System.out.println("  [RULE 5] Cannot be method return types:     var bar()        // ILLEGAL");

        // --------------------------------------------------------------------
        // Section 4: Java Records (Immutable Data Carriers)
        // --------------------------------------------------------------------
        printSectionHeader("4. Java Records (Immutable Data Carriers)");

        // Creating records using canonical and overloaded constructors
        var alien1 = new Alien(900, "Yoda", "Dagobah");
        var alien2 = new Alien(5, "E.T.", "Brodo Asogi");
        var alien3 = new Alien(900, "Yoda", "Dagobah"); // Identical content to alien1
        var alien4 = new Alien(120, "Zorg"); // Uses overloaded constructor

        // Accessor methods (note: record uses componentName() instead of getComponentName())
        System.out.println("  Alien 1 Name       : " + alien1.name());
        System.out.println("  Alien 1 Age        : " + alien1.age());
        System.out.println("  Alien 1 Home Planet: " + alien1.homePlanet());

        // Auto-generated toString()
        System.out.println("\n  Auto-generated toString():");
        System.out.println("    Alien 1 -> " + alien1);
        System.out.println("    Alien 2 -> " + alien2);
        System.out.println("    Alien 4 -> " + alien4);

        // Custom instance method invocation
        System.out.println("\n  Custom Method Invocation:");
        System.out.println("    " + alien1.transmitGreeting());

        // --------------------------------------------------------------------
        // Section 5: Value-Based Equality & HashCode in Records
        // --------------------------------------------------------------------
        printSectionHeader("5. Value-Based Equality (equals and hashCode)");

        System.out.println("  alien1: " + alien1);
        System.out.println("  alien3: " + alien3);

        boolean referenceEquality = (alien1 == alien3);
        boolean valueEquality = alien1.equals(alien3);

        System.out.println("\n  Reference Equality (alien1 == alien3)     : " + referenceEquality 
                + " (Different heap instances)");
        System.out.println("  Value-Based Equality (alien1.equals(alien3)): " + valueEquality 
                + " (All component fields match!)");
        System.out.println("  HashCode Match: " + (alien1.hashCode() == alien3.hashCode()) 
                + " (Hash codes: " + alien1.hashCode() + " vs " + alien3.hashCode() + ")");

        // Validation test through compact constructor
        System.out.println("\n  Compact Constructor Validation Test:");
        try {
            var invalidAlien = new Alien(-10, "NegativeAgeAlien");
        } catch (IllegalArgumentException ex) {
            System.err.println("  [Validation Caught] " + ex.getMessage());
        }

        printBanner("TYPE INFERENCE & RECORDS PRACTICE COMPLETED");
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
