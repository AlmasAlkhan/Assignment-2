package algorithms;

import metrics.PerformanceTracker;
import java.util.*;

/**
 * Min-Heap implementation with decrease-key and merge operations.
 * 
 * Time Complexity:
 * - insert: O(log n)
 * - extractMin: O(log n)
 * - peek: O(1)
 * - decreaseKey: O(log n)
 * - merge: O(n + m) where n, m are heap sizes
 * 
 * Space Complexity: O(n) for heap storage
 * 
 * @author Student A - Pair 4
 * @version 1.0
 */
public class MinHeap<T extends Comparable<T>> {
    private final List<T> heap;
    private final Map<T, Integer> indexMap; // For O(1) decrease-key lookup
    private final PerformanceTracker tracker;
    
    /**
     * Constructs an empty MinHeap
     */
    public MinHeap() {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.tracker = new PerformanceTracker();
    }
    
    /**
     * Constructs MinHeap from array
     * @param array initial elements
     */
    public MinHeap(T[] array) {
        this.heap = new ArrayList<>(Arrays.asList(array));
        this.indexMap = new HashMap<>();
        this.tracker = new PerformanceTracker();
        
        // Build heap in O(n) time using bottom-up approach
        buildHeap();
    }
    
    /**
     * Inserts element into heap
     * @param element to insert
     * @return true if successful
     */
    public boolean insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot insert null element");
        }
        
        tracker.incrementComparisons();
        tracker.incrementSwaps();
        
        heap.add(element);
        indexMap.put(element, heap.size() - 1);
        heapifyUp(heap.size() - 1);
        
        return true;
    }
    
    /**
     * Extracts and removes minimum element
     * @return minimum element
     * @throws NoSuchElementException if heap is empty
     */
    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        
        tracker.incrementComparisons();
        tracker.incrementSwaps();
        
        T min = heap.get(0);
        T last = heap.get(heap.size() - 1);
        
        heap.set(0, last);
        heap.remove(heap.size() - 1);
        indexMap.remove(min);
        
        if (!heap.isEmpty()) {
            indexMap.put(last, 0);
            heapifyDown(0);
        }
        
        return min;
    }
    
    /**
     * Returns minimum element without removing
     * @return minimum element
     * @throws NoSuchElementException if heap is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }
    
    /**
     * Decreases key of element (optimization for nearly-sorted data)
     * @param element to decrease
     * @param newValue new smaller value
     * @return true if successful
     */
    public boolean decreaseKey(T element, T newValue) {
        if (element == null || newValue == null) {
            throw new IllegalArgumentException("Cannot use null values");
        }
        
        if (newValue.compareTo(element) > 0) {
            throw new IllegalArgumentException("New value must be smaller than current");
        }
        
        Integer index = indexMap.get(element);
        if (index == null) {
            return false; // Element not found
        }
        
        tracker.incrementComparisons();
        tracker.incrementSwaps();
        
        heap.set(index, newValue);
        indexMap.remove(element);
        indexMap.put(newValue, index);
        heapifyUp(index);
        
        return true;
    }
    
    /**
     * Merges another heap into this heap
     * @param other heap to merge
     * @return new merged heap
     */
    public MinHeap<T> merge(MinHeap<T> other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot merge with null heap");
        }
        
        tracker.incrementComparisons();
        
        // Create new heap with combined elements
        List<T> combined = new ArrayList<>(this.heap);
        combined.addAll(other.heap);
        
        MinHeap<T> merged = new MinHeap<>();
        merged.heap.addAll(combined);
        
        // Rebuild index map
        for (int i = 0; i < merged.heap.size(); i++) {
            merged.indexMap.put(merged.heap.get(i), i);
        }
        
        // Build heap in O(n) time
        merged.buildHeap();
        
        return merged;
    }
    
    /**
     * Returns heap size
     * @return number of elements
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Checks if heap is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * Returns performance metrics
     * @return PerformanceTracker with metrics
     */
    public PerformanceTracker getPerformanceTracker() {
        return tracker;
    }
    
    /**
     * Resets performance metrics
     */
    public void resetMetrics() {
        tracker.reset();
    }
    
    // Private helper methods
    
    private void buildHeap() {
        // Bottom-up heapify: O(n) time complexity
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }
    
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            
            tracker.incrementComparisons();
            if (heap.get(index).compareTo(heap.get(parent)) >= 0) {
                break;
            }
            
            swap(index, parent);
            index = parent;
        }
    }
    
    private void heapifyDown(int index) {
        while (true) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            
            tracker.incrementComparisons();
            if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                smallest = left;
            }
            
            tracker.incrementComparisons();
            if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                smallest = right;
            }
            
            if (smallest == index) {
                break;
            }
            
            swap(index, smallest);
            index = smallest;
        }
    }
    
    private void swap(int i, int j) {
        tracker.incrementSwaps();
        
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        
        // Update index map
        indexMap.put(heap.get(i), i);
        indexMap.put(heap.get(j), j);
    }
    
    @Override
    public String toString() {
        return "MinHeap{" +
                "size=" + heap.size() +
                ", heap=" + heap +
                '}';
    }
}
// MinHeap baseline
// optimized decreaseKey
