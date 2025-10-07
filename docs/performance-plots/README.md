# Performance Plots

This directory contains performance analysis plots for the Min-Heap implementation.

## Running Benchmarks

To generate performance data:

```bash
# Compile the project
mvn clean package

# Run all benchmarks
java -jar target/assignment2-minheap-1.0.0.jar all

# Run specific benchmarks
java -jar target/assignment2-minheap-1.0.0.jar insert 100000
java -jar target/assignment2-minheap-1.0.0.jar extract 100000
java -jar target/assignment2-minheap-1.0.0.jar decrease 100000
java -jar target/assignment2-minheap-1.0.0.jar merge 50000
```

## Expected Plots

1. **insert-performance.png** - Insert operation time vs input size
2. **extract-performance.png** - ExtractMin operation time vs input size
3. **decrease-performance.png** - DecreaseKey operation time vs input size
4. **merge-performance.png** - Merge operation time vs combined heap size
5. **comparisons.png** - Number of comparisons vs input size
6. **swaps.png** - Number of swaps vs input size

## Generating Plots

You can use Python, Excel, or any plotting tool to visualize the CSV output from benchmarks.

### Python Example:

```python
import matplotlib.pyplot as plt
import pandas as pd

# Read benchmark data
data = pd.read_csv('benchmark-results.csv')

# Plot insert performance
plt.figure(figsize=(10, 6))
plt.plot(data['size'], data['insert_time'], marker='o')
plt.xlabel('Input Size (n)')
plt.ylabel('Time (ms)')
plt.title('Insert Performance - Min Heap')
plt.grid(True)
plt.savefig('insert-performance.png')
```

## Expected Complexity

- **Insert:** O(log n) per operation
- **ExtractMin:** O(log n) per operation
- **DecreaseKey:** O(log n) per operation (with index map)
- **Merge:** O(n + m) for heaps of size n and m


