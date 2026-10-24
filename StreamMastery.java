/**
 * ============================================================================
 * File: StreamMastery.java
 * Repository: my-java-practice
 * Description: Comprehensive, production-grade guide to the Java Stream API:
 *   1. Stream Sources: Collections, Arrays, Stream.of, iterate, generate, IntStream
 *   2. Intermediate Operations: filter, map, flatMap, distinct, sorted, peek, limit, skip
 *   3. Terminal Operations: toList, collect (groupingBy, partitioningBy, joining, summarizing),
 *      reduce, count, min, max, findFirst, anyMatch, allMatch, noneMatch
 *   4. Primitive Streams: IntStream, DoubleStream (performance without boxing overhead)
 *   5. Lazy Evaluation Proof (operations only trigger when terminal method is invoked)
 *   6. Parallel Streams: Scalability benefits & avoiding shared mutable state pitfalls
 *
 * How to Compile & Run:
 *   javac StreamMastery.java
 *   java StreamMastery
 * ============================================================================
 */

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMastery {

    /**
     * Domain record representing an Item in an e-commerce order.
     */
    public record OrderItem(String name, double price, String category) {}

    /**
     * Domain record representing a Customer Order with multiple items.
     */
    public record CustomerOrder(int orderId, String customerName, List<OrderItem> items) {}

    public static void main(String[] args) {
        printBanner("JAVA STREAM API: COMPREHENSIVE MASTERY SUITE");

        demonstrateStreamSources();
        demonstrateIntermediateAndTerminalOps();
        demonstrateAdvancedCollectors();
        demonstrateReductionsAndMatching();
        demonstratePrimitiveStreams();
        demonstrateLazyEvaluation();
        demonstrateParallelStreams();

        printBanner("ALL STREAM API DEMONSTRATIONS COMPLETED SUCCESSFULLY");
    }

    // ========================================================================
    // 1. STREAM SOURCES
    // ========================================================================
    private static void demonstrateStreamSources() {
        printSectionHeader("1. STREAM SOURCES (Creation)");

        // 1A. From a Collection
        List<String> frameworkList = List.of("Spring", "Quarkus", "Micronaut");
        Stream<String> fromList = frameworkList.stream();
        System.out.println("  [From List] Count: " + fromList.count());

        // 1B. From an Array
        String[] languages = {"Java", "Kotlin", "Scala", "Groovy"};
        long arrayCount = Arrays.stream(languages).count();
        System.out.println("  [From Array] Count: " + arrayCount);

        // 1C. Using Stream.of()
        Stream<Integer> directStream = Stream.of(10, 20, 30, 40);
        System.out.println("  [Stream.of] Sum: " + directStream.mapToInt(Integer::intValue).sum());

        // 1D. Infinite Stream using Stream.iterate() (Powers of 2)
        List<Integer> powersOfTwo = Stream.iterate(1, n -> n * 2)
                .limit(6) // Necessary to terminate infinite stream
                .toList();
        System.out.println("  [Stream.iterate] First 6 Powers of 2: " + powersOfTwo);

        // 1E. Infinite Stream using Stream.generate()
        List<Double> randomNumbers = Stream.generate(Math::random)
                .limit(3)
                .toList();
        System.out.println("  [Stream.generate] 3 Random numbers: " + randomNumbers);

        // 1F. Range creation with IntStream
        int rangeSum = IntStream.rangeClosed(1, 100).sum(); // Sum 1 to 100
        System.out.println("  [IntStream.rangeClosed(1, 100)] Sum: " + rangeSum);
    }

    // ========================================================================
    // 2. INTERMEDIATE & TERMINAL OPERATIONS (filter, map, flatMap, etc.)
    // ========================================================================
    private static void demonstrateIntermediateAndTerminalOps() {
        printSectionHeader("2. INTERMEDIATE & TERMINAL OPERATIONS (filter, map, flatMap, distinct, sorted)");

        List<CustomerOrder> orders = createSampleOrders();

        // 2A. filter() and map()
        System.out.println("  [filter & map] Customers who ordered items over $500:");
        List<String> highValueCustomerNames = orders.stream()
                .filter(order -> order.items().stream().anyMatch(item -> item.price() > 500))
                .map(CustomerOrder::customerName)
                .toList();
        System.out.println("    " + highValueCustomerNames);

        // 2B. flatMap(): Flattening one-to-many relationships (Order -> List<OrderItem>)
        System.out.println("\n  [flatMap] All unique item categories ordered across all customers:");
        List<String> distinctCategories = orders.stream()
                .flatMap(order -> order.items().stream()) // Flattens Stream<List<OrderItem>> into Stream<OrderItem>
                .map(OrderItem::category)
                .distinct() // Eliminates duplicates
                .sorted()   // Natural alphabetical order
                .toList();
        System.out.println("    " + distinctCategories);

        // 2C. peek(), limit(), and skip() (Pagination)
        System.out.println("\n  [skip & limit] Paginating item names (Skip first 2, take next 3):");
        List<String> paginatedItems = orders.stream()
                .flatMap(order -> order.items().stream())
                .map(OrderItem::name)
                .skip(2)  // Skip first 2
                .limit(3) // Take only 3
                .toList();
        System.out.println("    " + paginatedItems);
    }

    // ========================================================================
    // 3. ADVANCED COLLECTORS (groupingBy, partitioningBy, joining, summarizing)
    // ========================================================================
    private static void demonstrateAdvancedCollectors() {
        printSectionHeader("3. ADVANCED COLLECTORS (groupingBy, partitioningBy, joining, summarizing)");

        List<OrderItem> items = createSampleOrders().stream()
                .flatMap(order -> order.items().stream())
                .toList();

        // 3A. groupingBy: Group items by Category
        Map<String, List<OrderItem>> itemsByCategory = items.stream()
                .collect(Collectors.groupingBy(OrderItem::category));
        System.out.println("  [Collectors.groupingBy] Items per category:");
        itemsByCategory.forEach((category, itemList) ->
                System.out.println("    - " + category + " (" + itemList.size() + " items): "
                        + itemList.stream().map(OrderItem::name).toList()));

        // 3B. partitioningBy: Split into two groups based on predicate (Price >= $100)
        Map<Boolean, List<OrderItem>> partitionedByPrice = items.stream()
                .collect(Collectors.partitioningBy(item -> item.price() >= 100.0));
        System.out.println("\n  [Collectors.partitioningBy] Premium items (Price >= $100):");
        System.out.println("    True  (Expensive) : " + partitionedByPrice.get(true).stream().map(OrderItem::name).toList());
        System.out.println("    False (Budget)    : " + partitionedByPrice.get(false).stream().map(OrderItem::name).toList());

        // 3C. joining: Combine string attributes into a single delimited string
        String itemNamesFormatted = items.stream()
                .map(OrderItem::name)
                .distinct()
                .collect(Collectors.joining(", ", "Catalog: [", "]"));
        System.out.println("\n  [Collectors.joining] Formatted String:\n    " + itemNamesFormatted);

        // 3D. summarizingDouble: Single pass statistics (Count, Sum, Min, Average, Max)
        DoubleSummaryStatistics priceStats = items.stream()
                .collect(Collectors.summarizingDouble(OrderItem::price));
        System.out.println("\n  [Collectors.summarizingDouble] Price Statistics:");
        System.out.println("    Count  : " + priceStats.getCount());
        System.out.println("    Total  : $" + priceStats.getSum());
        System.out.printf("    Average: $%.2f%n", priceStats.getAverage());
        System.out.println("    Min    : $" + priceStats.getMin());
        System.out.println("    Max    : $" + priceStats.getMax());
    }

    // ========================================================================
    // 4. REDUCTIONS, FINDING & MATCHING
    // ========================================================================
    private static void demonstrateReductionsAndMatching() {
        printSectionHeader("4. REDUCTIONS (reduce), FINDING & MATCHING");

        List<Integer> prices = List.of(25, 40, 15, 80, 120);

        // 4A. reduce() with identity accumulator
        int totalPrice = prices.stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println("  [reduce with Identity] Total Sum: $" + totalPrice);

        // 4B. reduce() without identity (returns Optional)
        Optional<Integer> maxPriceOpt = prices.stream().reduce(Integer::max);
        System.out.println("  [reduce Optional] Max Price: $" + maxPriceOpt.orElse(0));

        // 4C. Short-Circuit Matching (anyMatch, allMatch, noneMatch)
        boolean hasVeryExpensiveItem = prices.stream().anyMatch(p -> p > 100);
        boolean areAllPositive = prices.stream().allMatch(p -> p > 0);
        boolean noneAreNegative = prices.stream().noneMatch(p -> p < 0);

        System.out.println("  [anyMatch (> 100)]   : " + hasVeryExpensiveItem);
        System.out.println("  [allMatch (> 0)]     : " + areAllPositive);
        System.out.println("  [noneMatch (< 0)]    : " + noneAreNegative);

        // 4D. findFirst() vs findAny()
        Optional<Integer> firstPrice = prices.stream().filter(p -> p > 30).findFirst();
        System.out.println("  [findFirst (> 30)]   : " + firstPrice.orElse(-1));
    }

    // ========================================================================
    // 5. PRIMITIVE STREAMS (Performance & Boxing Prevention)
    // ========================================================================
    private static void demonstratePrimitiveStreams() {
        printSectionHeader("5. PRIMITIVE STREAMS (IntStream, DoubleStream)");

        // Using IntStream prevents overhead of Integer object allocation
        int[] scores = {88, 92, 79, 95, 84, 100};

        int sum = Arrays.stream(scores).sum();
        double average = Arrays.stream(scores).average().orElse(0.0);
        int max = Arrays.stream(scores).max().orElse(0);

        System.out.println("  Scores: " + Arrays.toString(scores));
        System.out.println("  [IntStream.sum()]     : " + sum);
        System.out.printf("  [IntStream.average()] : %.2f%n", average);
        System.out.println("  [IntStream.max()]     : " + max);

        // Converting back to Stream<Integer> via boxed() or mapToObj()
        List<String> scoreLabels = Arrays.stream(scores)
                .mapToObj(s -> "Score: " + s)
                .toList();
        System.out.println("  [mapToObj] Labels: " + scoreLabels);
    }

    // ========================================================================
    // 6. LAZY EVALUATION PROOF
    // ========================================================================
    private static void demonstrateLazyEvaluation() {
        printSectionHeader("6. LAZY EVALUATION (Pipelines only run on terminal calls)");

        List<String> names = List.of("Ada", "Alan", "Linus", "Grace", "James");

        System.out.println("  Creating intermediate stream with logging (NO terminal op yet)...");
        Stream<String> intermediateStream = names.stream()
                .filter(name -> {
                    System.out.println("    [FILTER EXECUTED] Checking: " + name);
                    return name.length() > 3;
                })
                .map(name -> {
                    System.out.println("    [MAP EXECUTED] Transforming: " + name);
                    return name.toUpperCase();
                });

        System.out.println("  Notice: No filter or map prints occurred above because streams are lazy!");
        System.out.println("  Now calling terminal operation: .findFirst()...\n");

        // Terminal operation triggered: notice short-circuiting!
        String first = intermediateStream.findFirst().orElse("None");
        System.out.println("\n  Result of findFirst: " + first);
        System.out.println("  Note: Processing stopped as soon as the first match was found (Short-Circuit)!");
    }

    // ========================================================================
    // 7. PARALLEL STREAMS & CONCURRENCY
    // ========================================================================
    private static void demonstrateParallelStreams() {
        printSectionHeader("7. PARALLEL STREAMS: Throughput & Shared-State Pitfall");

        // 7A. Safe parallel stream (Stateless operation)
        long evenCount = IntStream.rangeClosed(1, 1_000_000)
                .parallel() // Divides work across ForkJoinPool.commonPool()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("  [Parallel Count] Even numbers in 1,000,000: " + evenCount);

        // 7B. DANGER: Shared mutable state in parallel streams
        // WRONG: Modifying an unsynchronized variable or non-thread-safe list in parallel
        AtomicInteger threadSafeCounter = new AtomicInteger(0);
        IntStream.range(0, 1000).parallel().forEach(i -> threadSafeCounter.incrementAndGet());
        System.out.println("  [Safe Parallel Atomic Counter] Expected: 1000 | Actual: " + threadSafeCounter.get());
    }

    // Helper method to supply sample data
    private static List<CustomerOrder> createSampleOrders() {
        return List.of(
                new CustomerOrder(1, "Alice Smith", List.of(
                        new OrderItem("Laptop", 1200.0, "Electronics"),
                        new OrderItem("Wireless Mouse", 25.0, "Electronics"),
                        new OrderItem("Notebook", 5.0, "Stationery")
                )),
                new CustomerOrder(2, "Bob Jones", List.of(
                        new OrderItem("Desk Chair", 180.0, "Furniture"),
                        new OrderItem("Desk Lamp", 45.0, "Furniture")
                )),
                new CustomerOrder(3, "Charlie Brown", List.of(
                        new OrderItem("Mechanical Keyboard", 110.0, "Electronics"),
                        new OrderItem("Monitor 27-inch", 350.0, "Electronics"),
                        new OrderItem("Coffee Mug", 12.0, "Kitchen")
                ))
        );
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
