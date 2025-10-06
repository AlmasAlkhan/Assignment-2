package metrics;

/**
 * Tracks performance metrics for algorithm analysis
 */
public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long memoryAllocations;
    private long startTime;
    private long endTime;
    
    public PerformanceTracker() {
        reset();
    }
    
    public void incrementComparisons() {
        comparisons++;
    }
    
    public void incrementSwaps() {
        swaps++;
    }
    
    public void incrementArrayAccesses() {
        arrayAccesses++;
    }
    
    public void incrementMemoryAllocations() {
        memoryAllocations++;
    }
    
    public void startTimer() {
        startTime = System.nanoTime();
    }
    
    public void endTimer() {
        endTime = System.nanoTime();
    }
    
    public long getComparisons() {
        return comparisons;
    }
    
    public long getSwaps() {
        return swaps;
    }
    
    public long getArrayAccesses() {
        return arrayAccesses;
    }
    
    public long getMemoryAllocations() {
        return memoryAllocations;
    }
    
    public long getExecutionTimeNanos() {
        return endTime - startTime;
    }
    
    public double getExecutionTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }
    
    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
        startTime = 0;
        endTime = 0;
    }
    
    @Override
    public String toString() {
        return String.format("PerformanceTracker{comparisons=%d, swaps=%d, arrayAccesses=%d, " +
                "memoryAllocations=%d, executionTime=%.2fms}",
                comparisons, swaps, arrayAccesses, memoryAllocations, getExecutionTimeMillis());
    }
}
