#!/usr/bin/env python3
"""
Generate performance plots for MinHeap analysis
"""

import matplotlib.pyplot as plt
import numpy as np

# Benchmark data from results
sizes = [100, 1000, 10000, 100000]

# INSERT operation
insert_comparisons = [197, 2205, 22655, 227243]
insert_time = [0.445958, 0.544042, 2.069042, 8.722875]

# EXTRACT operation
extract_comparisons = [1028, 16682, 233526, 2999412]
extract_time = [0.363541, 1.370166, 8.88375, 73.194625]

# DECREASE KEY operation
decrease_comparisons = [91, 816, 4496, 8913]
decrease_time = [0.064125, 0.207209, 1.098042, 5.920208]

# MERGE operation
merge_time = [0.109125, 0.348833, 1.983625, 21.630875]

# Theoretical O(log n)
log_n = [np.log2(n) for n in sizes]

# Set style
plt.style.use('seaborn-v0_8-darkgrid')

# Figure 1: Insert - Comparisons vs Size
plt.figure(figsize=(10, 6))
plt.plot(sizes, insert_comparisons, 'bo-', linewidth=2, markersize=8, label='Actual')
plt.plot(sizes, [n * np.log2(n) * 0.1 for n in sizes], 'r--', linewidth=2, label='O(n log n) theoretical')
plt.xlabel('Heap Size (n)', fontsize=12)
plt.ylabel('Number of Comparisons', fontsize=12)
plt.title('Insert Operation - Comparisons Analysis', fontsize=14, fontweight='bold')
plt.legend(fontsize=10)
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('docs/performance-plots/insert-comparisons.png', dpi=300, bbox_inches='tight')
print("✅ Created: insert-comparisons.png")

# Figure 2: Insert - Time vs Size
plt.figure(figsize=(10, 6))
plt.plot(sizes, insert_time, 'go-', linewidth=2, markersize=8)
plt.xlabel('Heap Size (n)', fontsize=12)
plt.ylabel('Execution Time (ms)', fontsize=12)
plt.title('Insert Operation - Time Performance', fontsize=14, fontweight='bold')
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('docs/performance-plots/insert-time.png', dpi=300, bbox_inches='tight')
print("✅ Created: insert-time.png")

# Figure 3: Extract - Comparisons vs Size
plt.figure(figsize=(10, 6))
plt.plot(sizes, extract_comparisons, 'mo-', linewidth=2, markersize=8, label='Actual')
plt.plot(sizes, [n * np.log2(n) * 1.5 for n in sizes], 'r--', linewidth=2, label='O(n log n) theoretical')
plt.xlabel('Heap Size (n)', fontsize=12)
plt.ylabel('Number of Comparisons', fontsize=12)
plt.title('ExtractMin Operation - Comparisons Analysis', fontsize=14, fontweight='bold')
plt.legend(fontsize=10)
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('docs/performance-plots/extract-comparisons.png', dpi=300, bbox_inches='tight')
print("✅ Created: extract-comparisons.png")

# Figure 4: Extract - Time vs Size
plt.figure(figsize=(10, 6))
plt.plot(sizes, extract_time, 'co-', linewidth=2, markersize=8)
plt.xlabel('Heap Size (n)', fontsize=12)
plt.ylabel('Execution Time (ms)', fontsize=12)
plt.title('ExtractMin Operation - Time Performance', fontsize=14, fontweight='bold')
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('docs/performance-plots/extract-time.png', dpi=300, bbox_inches='tight')
print("✅ Created: extract-time.png")

# Figure 5: DecreaseKey - Comparisons vs Size
plt.figure(figsize=(10, 6))
plt.plot(sizes, decrease_comparisons, 'yo-', linewidth=2, markersize=8, label='Actual')
plt.plot(sizes, [np.log2(n) * 50 for n in sizes], 'r--', linewidth=2, label='O(log n) theoretical')
plt.xlabel('Heap Size (n)', fontsize=12)
plt.ylabel('Number of Comparisons', fontsize=12)
plt.title('DecreaseKey Operation - Comparisons Analysis', fontsize=14, fontweight='bold')
plt.legend(fontsize=10)
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('docs/performance-plots/decreasekey-comparisons.png', dpi=300, bbox_inches='tight')
print("✅ Created: decreasekey-comparisons.png")

# Figure 6: All Operations - Time Comparison
plt.figure(figsize=(12, 7))
width = 0.2
x = np.arange(len(sizes))
plt.bar(x - width*1.5, insert_time, width, label='Insert', alpha=0.8)
plt.bar(x - width*0.5, extract_time, width, label='Extract', alpha=0.8)
plt.bar(x + width*0.5, decrease_time, width, label='DecreaseKey', alpha=0.8)
plt.bar(x + width*1.5, merge_time, width, label='Merge', alpha=0.8)
plt.xlabel('Heap Size', fontsize=12)
plt.ylabel('Execution Time (ms)', fontsize=12)
plt.title('All Operations - Time Comparison', fontsize=14, fontweight='bold')
plt.xticks(x, [f'{s:,}' for s in sizes])
plt.legend(fontsize=10)
plt.grid(True, alpha=0.3, axis='y')
plt.tight_layout()
plt.savefig('docs/performance-plots/all-operations-comparison.png', dpi=300, bbox_inches='tight')
print("✅ Created: all-operations-comparison.png")

# Figure 7: Complexity Analysis - Log scale
plt.figure(figsize=(10, 6))
plt.loglog(sizes, insert_comparisons, 'bo-', linewidth=2, markersize=8, label='Insert')
plt.loglog(sizes, extract_comparisons, 'ro-', linewidth=2, markersize=8, label='Extract')
plt.loglog(sizes, decrease_comparisons, 'go-', linewidth=2, markersize=8, label='DecreaseKey')
plt.xlabel('Heap Size (n) - log scale', fontsize=12)
plt.ylabel('Comparisons - log scale', fontsize=12)
plt.title('Complexity Analysis - All Operations (Log-Log Plot)', fontsize=14, fontweight='bold')
plt.legend(fontsize=10)
plt.grid(True, alpha=0.3, which='both')
plt.tight_layout()
plt.savefig('docs/performance-plots/complexity-loglog.png', dpi=300, bbox_inches='tight')
print("✅ Created: complexity-loglog.png")

print("\n🎉 All plots generated successfully!")
print(f"📁 Location: docs/performance-plots/")
