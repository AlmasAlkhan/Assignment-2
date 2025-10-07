package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


class MinHeapTest {

    private MinHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new MinHeap<>();
    }

    @Test
    @DisplayName("Test empty heap properties")
    void testEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, () -> heap.peek());
        assertThrows(NoSuchElementException.class, () -> heap.extractMin());
    }

    @Test
    @DisplayName("Test single element insertion and extraction")
    void testSingleElement() {
        heap.insert(42);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(42, heap.peek());
        assertEquals(42, heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    @DisplayName("Test multiple elements maintain heap property")
    void testMultipleElements() {
        Integer[] elements = {5, 2, 8, 1, 9, 3, 7, 4, 6};

        for (Integer element : elements) {
            heap.insert(element);
        }

        assertEquals(9, heap.size());
        assertFalse(heap.isEmpty());

        // Extract all elements and verify they come out in sorted order
        List<Integer> extracted = new ArrayList<>();
        while (!heap.isEmpty()) {
            extracted.add(heap.extractMin());
        }

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(expected, extracted);
    }

    @Test
    @DisplayName("Test heap construction from array")
    void testHeapFromArray() {
        Integer[] array = {9, 5, 2, 8, 1, 7, 3, 6, 4};
        MinHeap<Integer> heapFromArray = new MinHeap<>(array);

        assertEquals(9, heapFromArray.size());
        assertEquals(1, heapFromArray.peek());

        // Verify heap property
        List<Integer> extracted = new ArrayList<>();
        while (!heapFromArray.isEmpty()) {
            extracted.add(heapFromArray.extractMin());
        }

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(expected, extracted);
    }

    @Test
    @DisplayName("Test decreaseKey operation")
    void testDecreaseKey() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);

        // Decrease 30 to 5
        assertTrue(heap.decreaseKey(30, 5));
        assertEquals(5, heap.peek());

        // Decrease 20 to 1
        assertTrue(heap.decreaseKey(20, 1));
        assertEquals(1, heap.peek());

        // Try to decrease to larger value (should fail)
        assertFalse(heap.decreaseKey(10, 15));

        // Try to decrease non-existent element
        assertFalse(heap.decreaseKey(99, 1));
    }

    @Test
    @DisplayName("Test merge operation")
    void testMerge() {
        MinHeap<Integer> heap1 = new MinHeap<>();
        MinHeap<Integer> heap2 = new MinHeap<>();

        heap1.insert(5);
        heap1.insert(3);
        heap1.insert(1);

        heap2.insert(6);
        heap2.insert(4);
        heap2.insert(2);

        MinHeap<Integer> merged = heap1.merge(heap2);

        assertEquals(6, merged.size());
        assertEquals(1, merged.peek());

        // Extract all and verify sorted order
        List<Integer> extracted = new ArrayList<>();
        while (!merged.isEmpty()) {
            extracted.add(merged.extractMin());
        }

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertEquals(expected, extracted);
    }

    @Test
    @DisplayName("Test edge cases with duplicates")
    void testDuplicates() {
        heap.insert(5);
        heap.insert(5);
        heap.insert(5);
        heap.insert(3);
        heap.insert(3);

        assertEquals(5, heap.size());

        List<Integer> extracted = new ArrayList<>();
        while (!heap.isEmpty()) {
            extracted.add(heap.extractMin());
        }

        List<Integer> expected = Arrays.asList(3, 3, 5, 5, 5);
        assertEquals(expected, extracted);
    }

    @Test
    @DisplayName("Test null element handling")
    void testNullElements() {
        assertThrows(IllegalArgumentException.class, () -> heap.insert(null));
        assertThrows(IllegalArgumentException.class, () -> heap.decreaseKey(5, null));
        assertThrows(IllegalArgumentException.class, () -> heap.decreaseKey(null, 3));
    }

    @Test
    @DisplayName("Test merge with null heap")
    void testMergeWithNull() {
        assertThrows(IllegalArgumentException.class, () -> heap.merge(null));
    }

    @Test
    @DisplayName("Test decreaseKey with invalid values")
    void testDecreaseKeyInvalid() {
        heap.insert(10);

        // Try to increase value (should fail)
        assertThrows(IllegalArgumentException.class, () -> heap.decreaseKey(10, 15));

        // Try with null values
        assertThrows(IllegalArgumentException.class, () -> heap.decreaseKey(null, 5));
        assertThrows(IllegalArgumentException.class, () -> heap.decreaseKey(10, null));
    }

    @Test
    @DisplayName("Test performance metrics collection")
    void testPerformanceMetrics() {
        PerformanceTracker tracker = heap.getPerformanceTracker();

        // Insert some elements
        heap.insert(5);
        heap.insert(3);
        heap.insert(1);

        assertTrue(tracker.getComparisons() > 0);
        assertTrue(tracker.getSwaps() > 0);

        // Reset and verify
        heap.resetMetrics();
        assertEquals(0, tracker.getComparisons());
        assertEquals(0, tracker.getSwaps());
    }

    @Test
    @DisplayName("Test large dataset performance")
    void testLargeDataset() {
        Random random = new Random(42);
        int size = 10000;

        // Insert random elements
        for (int i = 0; i < size; i++) {
            heap.insert(random.nextInt(10000));
        }

        assertEquals(size, heap.size());

        // Extract all and verify sorted order
        List<Integer> extracted = new ArrayList<>();
        while (!heap.isEmpty()) {
            extracted.add(heap.extractMin());
        }

        // Verify sorted
        for (int i = 1; i < extracted.size(); i++) {
            assertTrue(extracted.get(i-1) <= extracted.get(i));
        }
    }

    @Test
    @DisplayName("Test heap property maintenance during operations")
    void testHeapPropertyMaintenance() {
        Random random = new Random(42);

        // Insert random elements
        for (int i = 0; i < 1000; i++) {
            heap.insert(random.nextInt(1000));
        }

        // Perform random decreaseKey operations
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Integer element = random.nextInt(1000);
            if (heap.decreaseKey(element, element - random.nextInt(100))) {
                elements.add(element);
            }
        }

        // Verify heap property still holds
        assertTrue(verifyHeapProperty(heap));
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(1);

        String str = heap.toString();
        assertTrue(str.contains("MinHeap"));
        assertTrue(str.contains("size=3"));
    }

    // Helper method to verify heap property
    private boolean verifyHeapProperty(MinHeap<Integer> h) {
        // This is a simplified check - in real implementation,
        // you'd need access to internal heap structure
        try {
            List<Integer> extracted = new ArrayList<>();
            MinHeap<Integer> copy = new MinHeap<>();

            // Copy heap
            while (!h.isEmpty()) {
                Integer min = h.extractMin();
                extracted.add(min);
                copy.insert(min);
            }

            // Restore original heap
            while (!copy.isEmpty()) {
                h.insert(copy.extractMin());
            }

            // Check if extracted elements are in sorted order
            for (int i = 1; i < extracted.size(); i++) {
                if (extracted.get(i-1) > extracted.get(i)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
// tests added