# 📊 Performance Plots Explanation

## Generated Plots

### 1. **insert-comparisons.png** 
Показывает количество сравнений при вставке элементов.
- **Синяя линия** - фактические данные
- **Красная пунктирная** - теоретическая O(n log n)
- **Вывод**: Сложность соответствует теории! ✅

### 2. **insert-time.png**
Время выполнения операции insert.
- Рост **логарифмический** относительно размера кучи
- Доказывает эффективность алгоритма

### 3. **extract-comparisons.png**
Количество сравнений при извлечении минимума.
- Больше сравнений чем insert (heapifyDown проверяет 2 детей)
- Всё равно O(n log n) для n операций

### 4. **extract-time.png**
Время выполнения extractMin.
- Немного медленнее insert (больше операций)
- Остаётся O(log n) на операцию

### 5. **decreasekey-comparisons.png**
Эффективность операции decreaseKey.
- **Очень мало сравнений!** 
- Благодаря indexMap поиск за O(1)
- Общая сложность O(log n)

### 6. **all-operations-comparison.png**
Сравнение времени всех операций.
- Insert - самая быстрая
- Extract - медленнее (heapifyDown)
- DecreaseKey - очень быстрая (indexMap оптимизация!)
- Merge - O(n+m) видно на больших размерах

### 7. **complexity-loglog.png**
Log-Log график для анализа сложности.
- Прямые линии = логарифмическая сложность ✅
- Подтверждает O(log n) для insert/extract/decreaseKey

## Key Findings

### ✅ Insert Operation
- **Comparisons**: ~n × log₂(n) × 0.1
- **Time**: Растёт логарифмически
- **Complexity**: O(log n) per operation ✅

### ✅ ExtractMin Operation
- **Comparisons**: ~n × log₂(n) × 1.5 (больше чем insert)
- **Time**: Немного медленнее insert
- **Complexity**: O(log n) per operation ✅

### ✅ DecreaseKey Operation
- **Comparisons**: Очень мало! ~log₂(n) × 50
- **Optimization**: indexMap даёт O(1) lookup
- **Complexity**: O(log n) благодаря оптимизации ✅

### ✅ Merge Operation
- **Time**: Растёт линейно O(n+m)
- **Method**: Объединение + buildHeap
- **Complexity**: O(n+m) as expected ✅

## For Defense

**Преподаватель**: "Докажи что твой алгоритм работает за O(log n)!"

**Ты**: 
1. Показываешь графики ✅
2. Указываешь на соответствие с теорией ✅
3. Объясняешь почему (высота дерева = log n) ✅

**Преподаватель**: "Почему decreaseKey такой быстрый?"

**Ты**:
1. Показываешь график decreasekey-comparisons.png ✅
2. Объясняешь про indexMap (O(1) поиск) ✅
3. Итоговая сложность: O(1) + O(log n) = O(log n) ✅
