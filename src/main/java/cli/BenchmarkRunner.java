package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;
import java.util.*;


public class BenchmarkRunner {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String operation = args[0].toLowerCase();

        switch (operation) {
            case "insert":
                benchmarkInsert(getSize(args));
                break;
            case "extract":
                benchmarkExtract(getSize(args));
                break;
            case "decrease":
                benchmarkDecreaseKey(getSize(args));
                break;
            case "merge":
                benchmarkMerge(getSize(args));
                break;
            case "all":
                runAllBenchmarks();
                break;
            default:
                System.out.println("Unknown operation: " + operation);
                printUsage();
        }
    }

    private static void benchmarkInsert(int size) {
        System.out.println("=== MinHeap Insert Benchmark (n=" + size + ") ===");

        MinHeap<Integer> heap = new MinHeap<>();
        PerformanceTracker tracker = heap.getPerformanceTracker();

        tracker.startTimer();

        Random random = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < size; i++) {
            heap.insert(random.nextInt(1000));
        }

        tracker.endTimer();

        System.out.println("Size: " + heap.size());
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Execution Time: " + tracker.getExecutionTimeMillis() + " ms");
        System.out.println("Average Time per Insert: " + (tracker.getExecutionTimeMillis() / size) + " ms");
    }

    private static void benchmarkExtract(int size) {
        System.out.println("=== MinHeap Extract Benchmark (n=" + size + ") ===");

        MinHeap<Integer> heap = new MinHeap<>();
        Random random = new Random(42);

        // Pre-fill heap
        for (int i = 0; i < size; i++) {
            heap.insert(random.nextInt(1000));
        }

        PerformanceTracker tracker = heap.getPerformanceTracker();
        tracker.reset();
        tracker.startTimer();

        // Extract all elements
        while (!heap.isEmpty()) {
            heap.extractMin();
        }

        tracker.endTimer();

        System.out.println("Extractions: " + size);
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Execution Time: " + tracker.getExecutionTimeMillis() + " ms");
        System.out.println("Average Time per Extract: " + (tracker.getExecutionTimeMillis() / size) + " ms");
    }

    private static void benchmarkDecreaseKey(int size) {
        System.out.println("=== MinHeap DecreaseKey Benchmark (n=" + size + ") ===");

        MinHeap<Integer> heap = new MinHeap<>();
        Random random = new Random(42);
        List<Integer> elements = new ArrayList<>();

        // Pre-fill heap
        for (int i = 0; i < size; i++) {
            int value = random.nextInt(1000) + 500; // Ensure we can decrease
            heap.insert(value);
            elements.add(value);
        }

        PerformanceTracker tracker = heap.getPerformanceTracker();
        tracker.reset();
        tracker.startTimer();

        // Decrease random elements
        for (int i = 0; i < size / 2; i++) {
            Integer element = elements.get(random.nextInt(elements.size()));
            heap.decreaseKey(element, element - random.nextInt(100));
        }

        tracker.endTimer();

        System.out.println("Decrease Operations: " + (size / 2));
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Execution Time: " + tracker.getExecutionTimeMillis() + " ms");
    }

    private static void benchmarkMerge(int size) {
        System.out.println("=== MinHeap Merge Benchmark (n=" + size + ") ===");

        MinHeap<Integer> heap1 = new MinHeap<>();
        MinHeap<Integer> heap2 = new MinHeap<>();
        Random random = new Random(42);

        // Fill both heaps
        for (int i = 0; i < size; i++) {
            heap1.insert(random.nextInt(1000));
            heap2.insert(random.nextInt(1000));
        }

        PerformanceTracker tracker = heap1.getPerformanceTracker();
        tracker.reset();
        tracker.startTimer();

        MinHeap<Integer> merged = heap1.merge(heap2);

        tracker.endTimer();

        System.out.println("Heap1 Size: " + heap1.size());
        System.out.println("Heap2 Size: " + heap2.size());
        System.out.println("Merged Size: " + merged.size());
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Execution Time: " + tracker.getExecutionTimeMillis() + " ms");
    }

    private static void runAllBenchmarks() {
        int[] sizes = {100, 1000, 10000, 100000};

        for (int size : sizes) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("BENCHMARKING SIZE: " + size);
            System.out.println("=".repeat(50));

            benchmarkInsert(size);
            System.out.println();
            benchmarkExtract(size);
            System.out.println();
            benchmarkDecreaseKey(size);
            System.out.println();
            benchmarkMerge(size);
        }
    }

    private static int getSize(String[] args) {
        if (args.length > 1) {
            try {
                return Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid size, using default: 1000");
            }
        }
        return 1000;
    }

    private static void printUsage() {
        System.out.println("MinHeap Benchmark Runner");
        System.out.println("Usage: java -jar assignment2-minheap.jar <operation> [size]");
        System.out.println();
        System.out.println("Operations:");
        System.out.println("  insert [size]  - Benchmark insert operations");
        System.out.println("  extract [size] - Benchmark extract operations");
        System.out.println("  decrease [size]- Benchmark decreaseKey operations");
        System.out.println("  merge [size]   - Benchmark merge operations");
        System.out.println("  all           - Run all benchmarks with multiple sizes");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java -jar assignment2-minheap.jar insert 1000");
        System.out.println("  java -jar assignment2-minheap.jar all");
    }
}
// CLI runner