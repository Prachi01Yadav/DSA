# Java Equivalents of C++ STL for Competitive Programming


## 1. MULTISET → `TreeMap<Integer, Integer>`

### What is a multiset?
A sorted collection that **allows duplicates**. Internally stores each value and how many times it appears.

### How the TreeMap trick works

TreeMap<Integer, Integer> ms = new TreeMap<>();

Think of it as: `{ value → how many times it appears }`

For example, after adding 3, 5, 5, 7:

{ 3→1, 5→2, 7→1 }


### Insert

ms.merge(val, 1, Integer::sum);

**Plain English:**
- `merge(val, 1, Integer::sum)` means:
  - If `val` is NOT in map → set count to `1`
  - If `val` IS in map → set count to `old count + 1`
- `Integer::sum` is just a shorthand for `(a, b) -> a + b`

### Remove (one occurrence)

ms.merge(val, -1, Integer::sum);
if (ms.get(val) == 0) ms.remove(val);  // cleanup when count hits 0

**Plain English:**
- Decrease count by 1
- If count becomes 0, remove the key entirely (so it doesn't show up as "still exists")

### Check if exists

ms.containsKey(val);   // true/false
ms.getOrDefault(val, 0);  // get count, returns 0 if not present


### Min and Max

ms.firstKey();   // smallest element  → like *ms.begin() in C++
ms.lastKey();    // largest element   → like *ms.rbegin() in C++


### Lower bound / Upper bound

ms.ceilingKey(val);   // smallest key >= val  → like lower_bound in C++
ms.higherKey(val);    // smallest key > val   → like upper_bound in C++
ms.floorKey(val);     // largest key <= val
ms.lowerKey(val);     // largest key < val


### Full Example

TreeMap<Integer, Integer> ms = new TreeMap<>();

// Insert
ms.merge(5, 1, Integer::sum);
ms.merge(3, 1, Integer::sum);
ms.merge(5, 1, Integer::sum);
// ms = {3→1, 5→2}

// Count
ms.getOrDefault(5, 0);   // 2
ms.getOrDefault(9, 0);   // 0

// Min / Max
ms.firstKey();   // 3
ms.lastKey();    // 5

// Remove one 5
ms.merge(5, -1, Integer::sum);
if (ms.get(5) == 0) ms.remove(5);
// ms = {3→1}

// Lower bound: smallest element >= 4
ms.ceilingKey(4);   // 5 (before removal)

## 2. SET → `TreeSet<Integer>`

### What is a set?
A **sorted collection with NO duplicates**.

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(5);
set.add(3);
set.add(5);      // ignored, already exists

set.contains(5); // true
set.remove(3);

set.first();     // min element
set.last();      // max element

// Lower bound / Upper bound
set.ceiling(val);  // smallest element >= val  (lower_bound)
set.higher(val);   // smallest element > val   (upper_bound)
set.floor(val);    // largest element <= val
set.lower(val);    // largest element < val
```

### Unordered Set (faster, no sorting)

HashSet<Integer> set = new HashSet<>();
set.add(5);
set.contains(5);  // O(1) average
set.remove(5);
// ❌ No firstKey/lastKey — no ordering


## 3. MAP → `TreeMap<Integer, Integer>`

### What is a map?
Stores **key → value** pairs, sorted by key. No duplicate keys.

TreeMap<Integer, Integer> map = new TreeMap<>();

map.put(3, 100);
map.put(1, 200);
map.put(5, 300);

map.get(3);           // 100
map.getOrDefault(9, -1);  // -1 (key doesn't exist)
map.containsKey(3);   // true

map.firstKey();       // 1 (smallest key)
map.lastKey();        // 5 (largest key)

// Iterate in sorted order
for (Map.Entry<Integer, Integer> e : map.entrySet()) {
    System.out.println(e.getKey() + " -> " + e.getValue());
}


### Unordered Map (faster, no sorting)

HashMap<Integer, Integer> map = new HashMap<>();
map.put(3, 100);
map.get(3);        // 100
map.getOrDefault(9, 0);  // 0
// ❌ No firstKey/lastKey — no ordering


## 4. MULTIMAP → `TreeMap<Integer, List<Integer>>`


### What is a multimap?
A map where **one key can have multiple values**.

```java
TreeMap<Integer, List<Integer>> mmap = new TreeMap<>();

// Insert
mmap.computeIfAbsent(3, k -> new ArrayList<>()).add(100);
mmap.computeIfAbsent(3, k -> new ArrayList<>()).add(200);
mmap.computeIfAbsent(5, k -> new ArrayList<>()).add(300);
// { 3 → [100, 200], 5 → [300] }

// Get all values for a key
List<Integer> vals = mmap.getOrDefault(3, new ArrayList<>());
// [100, 200]

// Remove a specific value from a key
mmap.get(3).remove(Integer.valueOf(100));
```

---

## 5. PRIORITY QUEUE (Heap)

### Min Heap (smallest element comes out first)
```java
// In Java, PriorityQueue is MIN heap by default
// ⚠️ OPPOSITE of C++ which is MAX heap by default!
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

minHeap.add(5);
minHeap.add(1);
minHeap.add(3);

minHeap.peek();   // 1  (see min without removing)
minHeap.poll();   // 1  (remove and return min)
minHeap.poll();   // 3
```

### Max Heap (largest element comes out first)
```java
// Use Collections.reverseOrder() for MAX heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

maxHeap.add(5);
maxHeap.add(1);
maxHeap.add(3);

maxHeap.peek();   // 5  (see max without removing)
maxHeap.poll();   // 5  (remove and return max)
```

### Priority Queue of Pairs (sorted by first element)
```java
// Use int[] as pair: int[]{first, second}
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

pq.add(new int[]{3, 100});
pq.add(new int[]{1, 200});
pq.add(new int[]{5, 50});

int[] top = pq.poll();   // {1, 200}  — smallest first value
```

---

## 6. STACK

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(1);    // add to top
stack.push(2);
stack.push(3);

stack.peek();     // 3 (see top without removing)
stack.pop();      // 3 (remove and return top)
stack.isEmpty();  // false
stack.size();     // 2
```

---

## 7. QUEUE

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offer(1);   // add to back
queue.offer(2);
queue.offer(3);

queue.peek();     // 1 (see front without removing)
queue.poll();     // 1 (remove and return front)
queue.isEmpty();  // false
```

---

## 8. DEQUE (Double-Ended Queue)

```java
Deque<Integer> dq = new ArrayDeque<>();

// Add
dq.addFirst(1);   // add to front
dq.addLast(2);    // add to back

// Peek
dq.peekFirst();   // see front
dq.peekLast();    // see back

// Remove
dq.pollFirst();   // remove from front
dq.pollLast();    // remove from back
```

---

## 9. VECTOR → `ArrayList`

```java
ArrayList<Integer> v = new ArrayList<>();

v.add(10);          // push_back
v.add(20);
v.get(0);           // access by index → 10
v.set(0, 99);       // update index 0 to 99
v.remove(v.size()-1); // pop_back (remove last)
v.size();           // size
Collections.sort(v);  // sort

// 2D vector
ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
grid.add(new ArrayList<>());
grid.get(0).add(5);
```

---

## 10. PAIR → `int[]` array

Java has no built-in pair. Use an `int[]` of size 2:

```java
int[] pair = new int[]{3, 7};  // {first, second}
pair[0];  // first  → 3
pair[1];  // second → 7

// Array of pairs
int[][] pairs = new int[][]{{1,2}, {3,4}, {5,6}};

// Sort pairs by first element
Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

// Sort pairs by second element
Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
```
