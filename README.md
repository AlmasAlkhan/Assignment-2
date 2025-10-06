# Assignment 2 - Min Heap Implementation

**Student A - Pair 4**  
**Algorithm:** Min-Heap with decrease-key and merge operations

## Overview

This project implements a Min-Heap data structure with advanced operations including decrease-key and merge functionality. The implementation includes comprehensive performance tracking and benchmarking capabilities for algorithmic analysis.

## Features

- **Core Operations**: insert, extractMin, peek
- **Advanced Operations**: decreaseKey, merge
- **Performance Tracking**: comparisons, swaps, execution time
- **Comprehensive Testing**: edge cases, correctness validation
- **Benchmarking**: CLI interface for performance analysis

## Complexity Analysis

### Time Complexity
- **insert**: O(log n) - heapify up operation
- **extractMin**: O(log n) - heapify down operation  
- **peek**: O(1) - direct access to root
- **decreaseKey**: O(log n) - heapify up from modified position
- **merge**: O(n + m) - where n, m are heap sizes

### Space Complexity
- **Storage**: O(n) for heap array and index map
- **Auxiliary**: O(1) for most operations, O(n + m) for merge

## Project Structure

```
assignment2-minheap/
├── src/main/java/
│   ├── algorithms/MinHeap.java          # Main heap implementation
│   ├── metrics/PerformanceTracker.java  # Performance metrics
│   └── cli/BenchmarkRunner.java         # CLI benchmarking tool
├── src/test/java/
│   └── algorithms/MinHeapTest.java      # Comprehensive test suite
├── docs/
│   ├── analysis-report.pdf             # Peer analysis report
│   └── performance-plots/                # Benchmark results
├── pom.xml                              # Maven configuration
└── README.md                            # This file
```

## Building and Running

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Build
```bash
mvn clean compile
```

### Run Tests
```bash
mvn test
```

### Package
```bash
mvn clean package
```

### Run Benchmarks
```bash
# Run all benchmarks with multiple sizes
java -jar target/assignment2-minheap-1.0.0.jar all

# Run specific operation
java -jar target/assignment2-minheap-1.0.0.jar insert 1000
java -jar target/assignment2-minheap-1.0.0.jar extract 1000
java -jar target/assignment2-minheap-1.0.0.jar decrease 1000
java -jar target/assignment2-minheap-1.0.0.jar merge 1000
```

## Usage Examples

### Basic Operations
```java
MinHeap<Integer> heap = new MinHeap<>();

// Insert elements
heap.insert(5);
heap.insert(3);
heap.insert(1);

// Peek at minimum
System.out.println(heap.peek()); // 1

// Extract minimum
int min = heap.extractMin(); // 1
```

### Advanced Operations
```java
// Decrease key
heap.insert(10);
heap.decreaseKey(10, 2); // Now minimum is 2

// Merge heaps
MinHeap<Integer> heap1 = new MinHeap<>();
MinHeap<Integer> heap2 = new MinHeap<>();
MinHeap<Integer> merged = heap1.merge(heap2);
```

### Performance Tracking
```java
PerformanceTracker tracker = heap.getPerformanceTracker();
System.out.println("Comparisons: " + tracker.getComparisons());
System.out.println("Swaps: " + tracker.getSwaps());
System.out.println("Execution Time: " + tracker.getExecutionTimeMillis() + " ms");
```

## Testing

The test suite covers:
- **Edge Cases**: empty heap, single element, duplicates
- **Correctness**: heap property maintenance, sorted extraction
- **Error Handling**: null elements, invalid operations
- **Performance**: large datasets, random operations
- **Integration**: merge operations, decrease-key scenarios

Run tests with:
```bash
mvn test
```

## Benchmarking

The CLI tool supports multiple benchmark scenarios:

### Insert Benchmark
Measures insertion performance across different input sizes:
- Input sizes: 100, 1000, 10000, 100000
- Metrics: comparisons, swaps, execution time
- Analysis: O(log n) complexity validation

### Extract Benchmark  
Measures extraction performance:
- Pre-filled heaps of various sizes
- Complete extraction until empty
- Metrics: total operations, time per extract

### DecreaseKey Benchmark
Measures decrease-key performance:
- Random decrease operations on pre-filled heap
- Complexity: O(log n) per operation
- Use case: priority queue updates

### Merge Benchmark
Measures merge operation performance:
- Two heaps of equal size
- Combined heap creation
- Complexity: O(n + m) analysis

## Performance Analysis

### Theoretical vs Empirical
- **Insert**: Expected O(log n), measured against n
- **Extract**: Expected O(log n), measured against n  
- **DecreaseKey**: Expected O(log n), measured against operations
- **Merge**: Expected O(n + m), measured against combined size

### Optimization Opportunities
1. **Index Map**: O(1) decrease-key lookup vs O(n) search
2. **Bottom-up Heapify**: O(n) construction vs O(n log n) insertion
3. **Memory Efficiency**: In-place operations where possible
4. **Cache Locality**: Array-based implementation for better performance

## Git Workflow

### Branch Strategy
- `main` - stable releases only
- `feature/algorithm` - core implementation
- `feature/metrics` - performance tracking
- `feature/testing` - test development
- `feature/optimization` - performance improvements

### Commit Convention
```
feat(algorithm): implement decrease-key operation
test(algorithm): add edge case tests for merge
perf(optimization): optimize heapify operations
docs(readme): add complexity analysis
```

## Deliverables

1. **Implementation**: Complete MinHeap with all operations
2. **Testing**: Comprehensive test suite with edge cases
3. **Benchmarking**: CLI tool for performance analysis
4. **Documentation**: README with usage and complexity analysis
5. **Analysis Report**: PDF report analyzing partner's algorithm

## Complexity Justification

### Insert Operation
- **Time**: O(log n) - heapify up from leaf to root
- **Space**: O(1) - constant auxiliary space
- **Worst Case**: Element is smaller than all ancestors

### ExtractMin Operation  
- **Time**: O(log n) - heapify down from root to leaf
- **Space**: O(1) - constant auxiliary space
- **Worst Case**: Element must move to leaf level

### DecreaseKey Operation
- **Time**: O(log n) - heapify up from modified position
- **Space**: O(1) - index map lookup + heapify up
- **Optimization**: Index map enables O(1) element location

### Merge Operation
- **Time**: O(n + m) - combine arrays + rebuild heap
- **Space**: O(n + m) - new heap storage
- **Optimization**: Bottom-up heapify for O(n) construction

## Author

**Student A - Pair 4**  
**Assignment 2 - Algorithmic Analysis and Peer Code Review**  
**AITU - Advanced Data Structures**
