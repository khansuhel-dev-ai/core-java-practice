package corejava.lambdas;

/**
 * ============================================================================
 * File: LambdaMastery.java
 * Repository: my-java-practice
 * Description: Master-level guide to Java Lambda Expressions & Functional Programming:
 *   1. Evolution: Anonymous Classes -> Verbose Lambdas -> Concise Lambdas -> Method References
 *   2. Core java.util.function Interfaces:
 *      - Predicate & BiPredicate (boolean tests)
 *      - Function & BiFunction (transformations)
 *      - Consumer & BiConsumer (side effects)
 *      - Supplier (lazy factory)
 *      - UnaryOperator & BinaryOperator
 *   3. Composition & Chaining (and, or, negate, andThen, compose)
 *   4. All 4 Types of Method References (Static, Bound Instance, Unbound Instance, Constructor)
 *   5. Variable Capture & "Effectively Final" Rule (Closures in Java)
 *   6. Advanced Higher-Order Functions (Functions as Parameters & Functions Returning Functions)
 *
 * How to Compile & Run:
 *   javac LambdaMastery.java
 *   java LambdaMastery
 * ============================================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaMastery {

    public record User(String username, int age, String role) {}

    public static void main(String[] args) {
        printBanner("JAVA LAMBDAS & FUNCTIONAL PROGRAMMING MASTERY");

        demonstrateEvolution();
        demonstrateCoreFunctionalInterfaces();
        demonstrateCompositionAndChaining();
        demonstrateMethodReferences();
        demonstrateVariableCaptureAndEffectivelyFinal();
        demonstrateHigherOrderFunctions();

        printBanner("ALL LAMBDA DEMONSTRATIONS COMPLETED SUCCESSFULLY");
    }

    // ========================================================================
    // 1. EVOLUTION OF LAMBDAS
    // ========================================================================
    private static void demonstrateEvolution() {
        printSectionHeader("1. EVOLUTION: Anonymous Inner Class to Lambda & Method Reference");

        List<String> names = new ArrayList<>(Arrays.asList("charlie", "alice", "bob"));

        // Step 1: Anonymous Inner Class (Verbose)
        names.sort(new java.util.Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        System.out.println("  [Step 1 - Anonymous Inner Class] " + names);

        // Step 2: Full Lambda with explicit types and block body
        names.sort((String s1, String s2) -> {
            return s2.compareTo(s1); // Descending
        });
        System.out.println("  [Step 2 - Explicit Body Lambda]  " + names);

        // Step 3: Concise Expression Lambda (Type inference + single line return)
        names.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("  [Step 3 - Concise Lambda]        " + names);

        // Step 4: Method Reference (Ultimate readability)
        names.sort(String::compareToIgnoreCase);
        System.out.println("  [Step 4 - Method Reference]      " + names);
    }

    // ========================================================================
    // 2. CORE java.util.function INTERFACES
    // ========================================================================
    private static void demonstrateCoreFunctionalInterfaces() {
        printSectionHeader("2. CORE FUNCTIONAL INTERFACES (java.util.function)");

        // 2A. Predicate<T>: (T) -> boolean
        Predicate<Integer> isAdult = age -> age >= 18;
        System.out.println("  [Predicate] isAdult(20) : " + isAdult.test(20));
        System.out.println("  [Predicate] isAdult(15) : " + isAdult.test(15));

        // 2B. BiPredicate<T, U>: (T, U) -> boolean
        BiPredicate<String, Integer> hasMinLength = (str, len) -> str.length() >= len;
        System.out.println("  [BiPredicate] 'Enterprise' has min length 8: " + hasMinLength.test("Enterprise", 8));

        // 2C. Function<T, R>: (T) -> R
        Function<String, Integer> stringLengthExtractor = String::length;
        System.out.println("  [Function] Length of 'Microservices': " + stringLengthExtractor.apply("Microservices"));

        // 2D. BiFunction<T, U, R>: (T, U) -> R
        BiFunction<String, Integer, String> repeatFunction = (str, times) -> str.repeat(times);
        System.out.println("  [BiFunction] Repeat 'Echo! ' x3: " + repeatFunction.apply("Echo! ", 3));

        // 2E. Consumer<T>: (T) -> void (Side effect, e.g. logging/printing)
        Consumer<String> logger = msg -> System.out.println("  [Consumer Log] INFO: " + msg);
        logger.accept("System initialised with Zero Downtime.");

        // 2F. BiConsumer<T, U>: (T, U) -> void
        BiConsumer<String, Integer> scoreReporter = (player, score) ->
                System.out.println("  [BiConsumer] Player '" + player + "' scored " + score + " points.");
        scoreReporter.accept("Suhel", 98);

        // 2G. Supplier<T>: () -> T (Lazy evaluation / factory)
        Supplier<Double> randomSeedSupplier = Math::random;
        System.out.println("  [Supplier] Generated value on demand: " + randomSeedSupplier.get());

        // 2H. UnaryOperator<T>: (T) -> T (Function where parameter and return type match)
        UnaryOperator<String> sanitize = input -> input.trim().toLowerCase();
        System.out.println("  [UnaryOperator] Sanitized: '" + sanitize.apply("   ADMIN@DOMAIN.COM   ") + "'");

        // 2I. BinaryOperator<T>: (T, T) -> T
        BinaryOperator<Integer> powerMultiplier = (base, exponent) -> (int) Math.pow(base, exponent);
        System.out.println("  [BinaryOperator] 2 ^ 8 = " + powerMultiplier.apply(2, 8));
    }

    // ========================================================================
    // 3. COMPOSITION & CHAINING (and, or, negate, andThen, compose)
    // ========================================================================
    private static void demonstrateCompositionAndChaining() {
        printSectionHeader("3. FUNCTION COMPOSITION & CHAINING");

        // 3A. Predicate Chaining: and(), or(), negate()
        Predicate<String> startsWithJ = s -> s.startsWith("J");
        Predicate<String> hasLengthGreaterThan4 = s -> s.length() > 4;

        Predicate<String> combinedAnd = startsWithJ.and(hasLengthGreaterThan4);
        Predicate<String> combinedOr = startsWithJ.or(hasLengthGreaterThan4);
        Predicate<String> notStartsWithJ = startsWithJ.negate();

        System.out.println("  [Predicate .and()]    'Javascript' starts with J & len > 4: " + combinedAnd.test("Javascript"));
        System.out.println("  [Predicate .and()]    'Java' starts with J & len > 4      : " + combinedAnd.test("Java"));
        System.out.println("  [Predicate .or()]     'Python' matches J or len > 4       : " + combinedOr.test("Python"));
        System.out.println("  [Predicate .negate()] 'Python' does NOT start with J      : " + notStartsWithJ.test("Python"));

        // 3B. Function Chaining: andThen() vs compose()
        // andThen: First apply f1, then apply f2 to the result -> f2(f1(x))
        // compose: First apply f2, then apply f1 to the result -> f1(f2(x))
        Function<Integer, Integer> multiplyByTwo = n -> n * 2;
        Function<Integer, Integer> addTen = n -> n + 10;

        Function<Integer, Integer> multiplyThenAdd = multiplyByTwo.andThen(addTen); // (5 * 2) + 10 = 20
        Function<Integer, Integer> addThenMultiply = multiplyByTwo.compose(addTen); // (5 + 10) * 2 = 30

        System.out.println("\n  Starting with input: 5");
        System.out.println("  [andThen] (5 * 2) + 10 = " + multiplyThenAdd.apply(5));
        System.out.println("  [compose] (5 + 10) * 2 = " + addThenMultiply.apply(5));
    }

    // ========================================================================
    // 4. THE 4 TYPES OF METHOD REFERENCES
    // ========================================================================
    private static void demonstrateMethodReferences() {
        printSectionHeader("4. THE 4 TYPES OF METHOD REFERENCES");

        // Type 1: Reference to a Static Method (Class::staticMethod)
        Function<String, Double> parseDoubleRef = Double::parseDouble;
        System.out.println("  [Type 1 - Static Method Ref] Double::parseDouble(\"42.75\"): " + parseDoubleRef.apply("42.75"));

        // Type 2: Reference to an Instance Method of a Particular Object (instance::method)
        java.io.PrintStream outputChannel = System.out;
        Consumer<String> auditLogger = outputChannel::println; // Bound to outputChannel instance
        auditLogger.accept("  [Type 2 - Bound Instance Ref] Audit logged to console via outputChannel::println");

        // Type 3: Reference to an Instance Method of an Arbitrary Object of a type (Class::method)
        Function<String, String> upperCaseRef = String::toUpperCase;
        System.out.println("  [Type 3 - Arbitrary Object Ref] String::toUpperCase(\"lambda\"): " + upperCaseRef.apply("lambda"));

        // Type 4: Reference to a Constructor (Class::new)
        BiFunction<String, Integer, UserAccount> userFactory = UserAccount::new;
        UserAccount newUser = userFactory.apply("khansuhel", 25);
        System.out.println("  [Type 4 - Constructor Ref] Created via UserAccount::new -> " + newUser);
    }

    // Helper class for constructor reference demonstration
    public record UserAccount(String username, int age) {}

    // ========================================================================
    // 5. VARIABLE CAPTURE & EFFECTIVELY FINAL (Closures in Java)
    // ========================================================================
    private static void demonstrateVariableCaptureAndEffectivelyFinal() {
        printSectionHeader("5. CLOSURES & EFFECTIVELY FINAL RULE");

        // A local variable is "effectively final" if it is never reassigned after initialization.
        int taxPercentage = 15; // Effectively final

        Function<Double, Double> taxCalculator = grossAmount -> {
            // Lambdas capture variables from enclosing scope by value
            return grossAmount + (grossAmount * taxPercentage / 100.0);
        };

        System.out.println("  Original Tax %: " + taxPercentage);
        System.out.println("  Gross $200 with tax: $" + taxCalculator.apply(200.0));

        // UNCOMMENTING BELOW CAUSES COMPILE ERROR:
        // taxPercentage = 20;
        // Error: local variables referenced from a lambda expression must be final or effectively final!
        System.out.println("  Rule: Local variables referenced inside lambdas cannot be modified elsewhere in the method.");
    }

    // ========================================================================
    // 6. HIGHER-ORDER FUNCTIONS
    // ========================================================================
    private static void demonstrateHigherOrderFunctions() {
        printSectionHeader("6. HIGHER-ORDER FUNCTIONS (Passing & Returning Functions)");

        // 6A. Passing function as parameter (Template / Strategy Pattern)
        String rawText = "  Hello Reactive World!  ";
        String cleanResult = processString(rawText, str -> str.trim().replace("Reactive", "Functional"));
        System.out.println("  [Higher-Order Param] Processed: '" + cleanResult + "'");

        // 6B. Function returning another function (Factory / Currying)
        Function<Integer, Integer> tripleMultiplier = createMultiplier(3);
        Function<Integer, Integer> tenfoldMultiplier = createMultiplier(10);

        System.out.println("  [Higher-Order Return] 3 * 7  = " + tripleMultiplier.apply(7));
        System.out.println("  [Higher-Order Return] 10 * 7 = " + tenfoldMultiplier.apply(7));
    }

    /**
     * Higher-order method: accepts a transformation function and applies it.
     */
    private static String processString(String input, Function<String, String> transformer) {
        return transformer.apply(input);
    }

    /**
     * Higher-order method: returns a customized multiplier function.
     */
    private static Function<Integer, Integer> createMultiplier(int factor) {
        return (Integer value) -> value * factor;
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
