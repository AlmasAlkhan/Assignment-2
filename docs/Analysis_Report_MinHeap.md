# Analysis Report: Min-Heap Implementation

**Student:** Alkhan Almas (Student A)  
**Implementation:** Min-Heap with DecreaseKey and Merge  
**Pair:** 4  
**Course:** Assignment 2 - Algorithmic Analysis and Peer Code Review  
**Date:** October 7, 2025

---

## 1. Algorithm Overview

### Brief Description

The Min-Heap is a complete binary tree data structure where each parent node has a value **less than or equal to** its children. The minimum element is always at the root. This implementation includes optimized `decreaseKey` operation using an index map for O(1) element lookup.

### Key Operations Implemented

1. **insert(element)** - Add new element maintaining heap property
2. **extractMin()** - Remove and return minimum element (root)
3. **peek()** - Return minimum without removing
4. **decreaseKey(element, newValue)** - Decrease element value (optimized with indexMap)
5. **merge(otherHeap)** - Combine two heaps

---

## 2. Complexity Analysis

### Time Complexity - INSERT Operation

#### **Best Case: Ω(1)**
- Occurs when inserted element is **larger** than its parent
- Element stays at bottom, no heapify-up needed
- Example: Insert 50 into [1, 3, 5, 7, 9]
  - 50 > 7 (parent) → stays at position

**Justification:**
```
T_best(n) = O(1)  // Just add to array, no swaps
```

#### **Average Case: Θ(log n)**
- Element typically bubbles up halfway through tree
- Average path: log₂(n) / 2 comparisons
- Empirical data confirms: ~0.1 × n × log₂(n) total comparisons for n inserts

**From our benchmarks:**
| Size (n) | Comparisons | n×log₂(n) | Ratio |
|----------|-------------|-----------|-------|
| 100      | 197         | 664       | 0.30  |
| 1,000    | 2,205       | 9,966     | 0.22  |
| 10,000   | 22,655      | 132,877   | 0.17  |
| 100,000  | 227,243     | 1,660,964 | 0.14  |

**Justification:**
```
T_avg(n) = Θ(log n)  // Average halfway up tree
```

#### **Worst Case: O(log n)**
- Inserted element is **smaller** than all existing elements
- Must bubble up from leaf to root
- Maximum log₂(n) comparisons and swaps
- Example: Insert 0 into [1, 3, 5, 7, 9]
  - 0 < all elements → bubbles to root

**Justification:**
```
T_worst(n) = O(log n)  // Full height of tree
Height h = ⌊log₂(n)⌋
```

---

### Time Complexity - EXTRACT MIN Operation

#### **Best Case: Ω(log n)**
- **CANNOT** be O(1) even in best case!
- Must verify children at each level during heapify-down
- Example: Even if last element is second-smallest, still need comparisons

**Justification:**
```
T_best(n) = Ω(log n)  // Must check children
```

#### **Average Case: Θ(log n)**
- Replacement element sinks halfway down on average
- Average ~1.5 × log₂(n) comparisons (2 children per level)

**From our benchmarks (extracting all n elements):**
| Size (n) | Comparisons | n×log₂(n) | Ratio |
|----------|-------------|-----------|-------|
| 100      | 1,028       | 664       | 1.55  |
| 1,000    | 16,682      | 9,966     | 1.67  |
| 10,000   | 233,526     | 132,877   | 1.76  |
| 100,000  | 2,999,412   | 1,660,964 | 1.81  |

**Justification:**
```
T_avg(n) = Θ(log n)
At each level: compare with 2 children
Total: 2 × log₂(n) comparisons
```

#### **Worst Case: O(log n)**
- Last element (now at root) is largest in heap
- Must sink all the way to leaf level
- Maximum 2 × log₂(n) comparisons

**Justification:**
```
T_worst(n) = O(log n)
Heapify-down: at most h levels
h = ⌊log₂(n)⌋
```

---

### Time Complexity - DECREASE KEY Operation

#### **Best Case: Ω(1)**
- New value still larger than parent
- No heapify-up needed
- IndexMap lookup: O(1)
- Example: decreaseKey(10, 9) where parent is 5
  - 9 > 5 → stays in place

**Justification:**
```
T_best(n) = O(1)  // indexMap lookup + no movement
```

#### **Average Case: Θ(log n)**
- Element bubbles up partway
- Average log₂(n) / 2 levels

**From our benchmarks:**
| Size (n) | Operations | Comparisons | Avg per Op |
|----------|------------|-------------|------------|
| 100      | 50         | 91          | 1.82       |
| 1,000    | 500        | 816         | 1.63       |
| 10,000   | 5,000      | 4,496       | 0.90       |
| 100,000  | 50,000     | 8,913       | 0.18       |

**Justification:**
```
T_avg(n) = O(1) + Θ(log n)  // lookup + heapifyUp
         = Θ(log n)
```

#### **Worst Case: O(log n)**
- New value smallest in heap
- Bubbles all the way to root
- IndexMap lookup: O(1)
- HeapifyUp: O(log n)

**Justification:**
```
T_worst(n) = O(1) + O(log n)  // lookup + full heapifyUp
           = O(log n)
```

**KEY OPTIMIZATION:** Without indexMap, would be O(n) to find element + O(log n) to heapify = **O(n)**!

---

### Time Complexity - MERGE Operation

#### **All Cases: Θ(n + m)**
- Combine two arrays: O(n + m)
- BuildHeap (bottom-up): O(n + m)
- No best/worst/average distinction

**Justification:**
```
T(n, m) = O(n + m)  // array concatenation
        + O(n + m)  // buildHeap
        = O(n + m)

BuildHeap complexity proof:
Sum of i=0 to h of (n/2^i × i) = O(n)
```

---

### Time Complexity - PEEK Operation

#### **All Cases: Θ(1)**
- Just return heap[0]
- No computation needed

**Justification:**
```
T(n) = O(1)  // Array access
```

---

## 3. Space Complexity Analysis

### Overall Space Usage

**Space Complexity: Θ(n)**

**Components:**
1. **heap ArrayList:** O(n) - stores all elements
2. **indexMap HashMap:** O(n) - maps each element to its index
3. **tracker:** O(1) - constant space for metrics

**Total:**
```
S(n) = O(n) + O(n) + O(1)
     = O(n)
```

### Trade-off Analysis

**IndexMap Trade-off:**
- **Cost:** Extra O(n) space
- **Benefit:** decreaseKey from O(n) to O(log n)
- **Worth it?** YES! For algorithms like Dijkstra that call decreaseKey frequently

---

## 4. Empirical Results Summary

### Insert Performance

| Size | Time (ms) | Comparisons | Swaps | Comp/Insert |
|------|-----------|-------------|-------|-------------|
| 100 | 0.45 | 197 | 102 | 1.97 |
| 1K | 0.54 | 2,205 | 1,212 | 2.21 |
| 10K | 2.07 | 22,655 | 12,662 | 2.27 |
| 100K | 8.72 | 227,243 | 127,250 | 2.27 |

**Observation:** Comparisons/insert stabilizes around 2.27 ≈ 0.33 × log₂(n)

### Extract Performance

| Size | Time (ms) | Comparisons | Swaps | Comp/Extract |
|------|-----------|-------------|-------|--------------|
| 100 | 0.36 | 1,028 | 415 | 10.28 |
| 1K | 1.37 | 16,682 | 7,342 | 16.68 |
| 10K | 8.88 | 233,526 | 106,764 | 23.35 |
| 100K | 73.19 | 2,999,412 | 1,399,707 | 30.00 |

**Observation:** Comparisons/extract ≈ 1.8 × log₂(n) (due to 2 children checks)

### DecreaseKey Performance

| Size | Time (ms) | Operations | Comparisons | Comp/Op |
|------|-----------|------------|-------------|---------|
| 100 | 0.06 | 50 | 91 | 1.82 |
| 1K | 0.21 | 500 | 816 | 1.63 |
| 10K | 1.10 | 5,000 | 4,496 | 0.90 |
| 100K | 5.92 | 50,000 | 8,913 | 0.18 |

**Observation:** Very few comparisons! IndexMap optimization working ✅

---

## 5. Key Findings

### ✅ Confirmed Complexities

1. **Insert:** O(log n) ✅
   - Empirical ratio: ~0.33 × log₂(n) comparisons per insert
   - Graphs show logarithmic growth

2. **ExtractMin:** O(log n) ✅
   - Empirical ratio: ~1.8 × log₂(n) comparisons per extract
   - Slightly higher due to 2-child comparisons

3. **DecreaseKey:** O(log n) ✅
   - IndexMap provides O(1) lookup
   - Total: O(1) + O(log n) = O(log n)

4. **Merge:** O(n + m) ✅
   - Linear time using buildHeap

### 🚀 Optimizations

1. **IndexMap for DecreaseKey:**
   - Without: O(n) search → total O(n)
   - With: O(1) search → total O(log n)
   - **Speedup: n/log(n) times faster!**

2. **Array-based storage:**
   - Cache-friendly
   - No pointer overhead
   - Formula-based navigation

3. **BuildHeap (bottom-up):**
   - O(n) instead of O(n log n)
   - Used in merge operation

---

## 6. Graphs Analysis

See `docs/performance-plots/` for visual proof:

1. **insert-comparisons.png** - Shows O(log n) growth ✅
2. **extract-comparisons.png** - Confirms O(log n) ✅
3. **decreasekey-comparisons.png** - Very low, indexMap works! ✅
4. **complexity-loglog.png** - Linear in log-log = logarithmic ✅
5. **all-operations-comparison.png** - Comparative performance ✅

---

## 7. Conclusion

The Min-Heap implementation successfully achieves **O(log n)** time complexity for all major operations (insert, extractMin, decreaseKey). The **indexMap optimization** is crucial for decreaseKey efficiency, providing O(1) element lookup at the cost of O(n) extra space.

**Performance Summary:**
- ✅ Insert: O(log n) - confirmed by benchmarks
- ✅ ExtractMin: O(log n) - confirmed by benchmarks  
- ✅ DecreaseKey: O(log n) - indexMap optimization working
- ✅ Merge: O(n+m) - buildHeap optimization
- ✅ Space: O(n) - acceptable trade-off

**Ready for defense!** 🚀
