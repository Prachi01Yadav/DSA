import java.util.*;

// ╔══════════════════════════════════════════════════════════════════╗
// ║         MULTISET TEMPLATE — copy-paste into every solution       ║
// ╚══════════════════════════════════════════════════════════════════╝

class MultiSet<T extends Comparable<T>> {
    private TreeMap<T, Integer> map = new TreeMap<>();
    private int totalSize = 0;

    void add(T v)         { map.merge(v, 1, Integer::sum); totalSize++; }          // O(log n)
    boolean contains(T v) { return map.containsKey(v); }                           // O(log n)
    int count(T v)        { return map.getOrDefault(v, 0); }                       // O(log n)
    int size()            { return totalSize; }                                    // O(1)
    int distinctSize()    { return map.size(); }                                   // O(1)
    boolean isEmpty()     { return totalSize == 0; }                               // O(1)
    T first()             { return map.firstKey(); }                               // O(log n) — min element
    T last()              { return map.lastKey(); }                                // O(log n) — max element
    T lower(T v)          { return map.lowerKey(v); }                              // O(log n) — largest element STRICTLY < v
    T floor(T v)          { return map.floorKey(v); }                              // O(log n) — largest element <= v
    T ceiling(T v)        { return map.ceilingKey(v); }                            // O(log n) — smallest element >= v  [= lower_bound]
    T higher(T v)         { return map.higherKey(v); }                             // O(log n) — smallest element STRICTLY > v [= upper_bound]
    TreeMap<T,Integer> getMap() { return map; }                                    // O(1)

    // Remove ONE occurrence of v — O(log n)
    boolean remove(T v) {
        Integer c = map.get(v);
        if (c == null) return false;
        if (c == 1) map.remove(v); else map.put(v, c - 1);
        totalSize--;
        return true;
    }

    // Remove ALL occurrences of v — O(log n)
    boolean removeAll(T v) {
        Integer c = map.get(v);
        if (c == null) return false;
        map.remove(v);
        totalSize -= c;
        return true;
    }

    // Iterate over all elements in sorted order — O(n)
    // Usage: for (Map.Entry<T,Integer> e : ms.getMap().entrySet()) { T val=e.getKey(); int freq=e.getValue(); }

    void clear() { map.clear(); totalSize = 0; }  // O(n)

    @Override public String toString() { return map.toString(); }
}


// ╔══════════════════════════════════════════════════════════════════╗
// ║               FULL USAGE + TIME COMPLEXITY REFERENCE            ║
// ╚══════════════════════════════════════════════════════════════════╝

public class CPTemplates {
    public static void main(String[] args) {

        // ────────────────────────────────────────────────────────────
        // 1. MULTISET
        // Sorted, allows duplicates. Backed by TreeMap internally.
        // ────────────────────────────────────────────────────────────
        MultiSet<Integer> ms = new MultiSet<>();

        ms.add(5);                   // O(log n)
        ms.add(3);                   // O(log n)
        ms.add(5);                   // O(log n)
        ms.add(1);                   // O(log n)
        // ms = {1→1, 3→1, 5→2}

        ms.contains(5);              // O(log n) → true
        ms.count(5);                 // O(log n) → 2
        ms.size();                   // O(1)     → 4 (total including duplicates)
        ms.distinctSize();           // O(1)     → 3 (unique values)
        ms.isEmpty();                // O(1)     → false

        ms.first();                  // O(log n) → 1   (min)
        ms.last();                   // O(log n) → 5   (max)

        ms.lower(5);                 // O(log n) → 3   largest element STRICTLY < 5
        ms.floor(5);                 // O(log n) → 5   largest element <= 5
        ms.ceiling(4);               // O(log n) → 5   smallest element >= 4  ← lower_bound
        ms.higher(3);                // O(log n) → 5   smallest element STRICTLY > 3 ← upper_bound

        ms.remove(5);                // O(log n) removes ONE 5  → {1→1, 3→1, 5→1}
        ms.removeAll(5);             // O(log n) removes ALL 5s → {1→1, 3→1}
        ms.clear();                  // O(n)

        // Iterate sorted order:
        ms.add(3); ms.add(1); ms.add(3);
        for (Map.Entry<Integer, Integer> e : ms.getMap().entrySet()) {
            int val  = e.getKey();   // element value
            int freq = e.getValue(); // how many times it appears
            System.out.println(val + " appears " + freq + " times");
        }


        // ────────────────────────────────────────────────────────────
        // 2. SET  →  TreeSet (sorted, no duplicates)
        //            HashSet (unsorted, no duplicates, faster)
        // ────────────────────────────────────────────────────────────
        TreeSet<Integer> set = new TreeSet<>();

        set.add(5);                  // O(log n)
        set.add(3);                  // O(log n)
        set.add(5);                  // O(log n) → ignored, already exists
        set.remove(3);               // O(log n)
        set.contains(5);             // O(log n) → true
        set.size();                  // O(1)
        set.isEmpty();               // O(1)

        set.first();                 // O(log n) → min
        set.last();                  // O(log n) → max

        // lower_bound / upper_bound equivalents:
        set.lower(5);                // O(log n) → largest element STRICTLY < 5
        set.floor(5);                // O(log n) → largest element <= 5
        set.ceiling(4);              // O(log n) → smallest element >= 4  ← lower_bound
        set.higher(3);               // O(log n) → smallest element STRICTLY > 3 ← upper_bound

        set.pollFirst();             // O(log n) → remove and return min
        set.pollLast();              // O(log n) → remove and return max

        // Iterate sorted order — O(n)
        for (int x : set) System.out.println(x);

        // ── HashSet (when you don't need sorting) ──
        HashSet<Integer> hset = new HashSet<>();
        hset.add(5);                 // O(1) avg
        hset.remove(5);              // O(1) avg
        hset.contains(5);            // O(1) avg
        // ❌ no first/last/floor/ceiling


        // ────────────────────────────────────────────────────────────
        // 3. MAP  →  TreeMap (sorted by key)
        //            HashMap (unsorted, faster)
        // ────────────────────────────────────────────────────────────
        TreeMap<Integer, Integer> tmap = new TreeMap<>();

        tmap.put(3, 100);            // O(log n)
        tmap.put(1, 200);            // O(log n)
        tmap.put(5, 300);            // O(log n)
        tmap.get(3);                 // O(log n) → 100
        tmap.getOrDefault(9, -1);    // O(log n) → -1 (key doesn't exist)
        tmap.containsKey(3);         // O(log n) → true
        tmap.containsValue(100);     // O(n)     ← slow!
        tmap.remove(3);              // O(log n)
        tmap.size();                 // O(1)
        tmap.isEmpty();              // O(1)

        tmap.firstKey();             // O(log n) → min key
        tmap.lastKey();              // O(log n) → max key
        tmap.firstEntry();           // O(log n) → Map.Entry with min key
        tmap.lastEntry();            // O(log n) → Map.Entry with max key

        // lower_bound / upper_bound on keys:
        tmap.lowerKey(5);            // O(log n) → largest key STRICTLY < 5
        tmap.floorKey(5);            // O(log n) → largest key <= 5
        tmap.ceilingKey(4);          // O(log n) → smallest key >= 4  ← lower_bound
        tmap.higherKey(3);           // O(log n) → smallest key STRICTLY > 3 ← upper_bound

        // Also works with entries:
        tmap.put(1,10); tmap.put(3,30); tmap.put(5,50);
        tmap.floorEntry(4);          // O(log n) → entry {3=30}
        tmap.ceilingEntry(4);        // O(log n) → entry {5=50}

        // Iterate sorted by key — O(n)
        for (Map.Entry<Integer, Integer> e : tmap.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());

        // ── HashMap (when you don't need sorted keys) ──
        HashMap<Integer, Integer> hmap = new HashMap<>();
        hmap.put(3, 100);            // O(1) avg
        hmap.get(3);                 // O(1) avg
        hmap.getOrDefault(9, 0);     // O(1) avg
        hmap.containsKey(3);         // O(1) avg
        hmap.remove(3);              // O(1) avg
        hmap.merge(5, 1, Integer::sum); // O(1) avg — freq counter trick
        // ❌ no firstKey/lastKey/floorKey/ceilingKey


        // ────────────────────────────────────────────────────────────
        // 4. PRIORITY QUEUE (Heap)
        // ⚠️ Java DEFAULT = MIN heap  (C++ default = MAX heap)
        // ────────────────────────────────────────────────────────────
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();            // MIN heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // MAX heap

        minHeap.add(5);              // O(log n)  [also: offer()]
        minHeap.add(1);
        minHeap.add(3);

        minHeap.peek();              // O(1)      → 1 (see min, don't remove)
        minHeap.poll();              // O(log n)  → 1 (remove and return min)
        minHeap.size();              // O(1)
        minHeap.isEmpty();           // O(1)
        minHeap.contains(3);         // O(n) ← slow! avoid in tight loops

        // PQ of int[] pairs, sorted by first element (like pair<int,int>)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{3, 100});   // O(log n)
        pq.add(new int[]{1, 200});
        int[] top = pq.poll();       // O(log n) → {1, 200}

        // PQ sorted by second element descending
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((a, b) -> b[1] - a[1]);


        // ────────────────────────────────────────────────────────────
        // 5. STACK
        // ────────────────────────────────────────────────────────────
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);               // O(1) amortized — add to TOP
        stack.push(2);
        stack.push(3);

        stack.peek();                // O(1) → 3 (see top, don't remove)
        stack.pop();                 // O(1) → 3 (remove and return top)
        stack.isEmpty();             // O(1)
        stack.size();                // O(1)


        // ────────────────────────────────────────────────────────────
        // 6. QUEUE
        // ────────────────────────────────────────────────────────────
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(1);              // O(1) amortized — add to BACK
        queue.offer(2);
        queue.offer(3);

        queue.peek();                // O(1) → 1 (see front, don't remove)
        queue.poll();                // O(1) → 1 (remove and return front)
        queue.isEmpty();             // O(1)
        queue.size();                // O(1)


        // ────────────────────────────────────────────────────────────
        // 7. DEQUE (Double-ended queue)
        // ────────────────────────────────────────────────────────────
        Deque<Integer> dq = new ArrayDeque<>();

        dq.addFirst(1);              // O(1) — add to front
        dq.addLast(2);               // O(1) — add to back
        dq.offerFirst(0);            // O(1) — same as addFirst
        dq.offerLast(3);             // O(1) — same as addLast

        dq.peekFirst();              // O(1) — see front
        dq.peekLast();               // O(1) — see back

        dq.pollFirst();              // O(1) — remove front
        dq.pollLast();               // O(1) — remove back

        dq.size();                   // O(1)
        dq.isEmpty();                // O(1)


        // ────────────────────────────────────────────────────────────
        // 8. VECTOR  →  ArrayList
        // ────────────────────────────────────────────────────────────
        ArrayList<Integer> v = new ArrayList<>();

        v.add(10);                   // O(1) amortized — add to END
        v.add(20);
        v.add(1, 99);                // O(n) — insert at index 1

        v.get(0);                    // O(1) — access by index
        v.set(0, 55);                // O(1) — update by index

        v.remove(v.size() - 1);      // O(1) — remove LAST (pop_back)
        v.remove(Integer.valueOf(99)); // O(n) — remove by VALUE
        v.remove(0);                 // O(n) — remove by INDEX (shifts elements)

        v.contains(10);              // O(n) — linear search
        v.indexOf(10);               // O(n)
        v.size();                    // O(1)
        v.isEmpty();                 // O(1)

        Collections.sort(v);         // O(n log n) — sort ascending
        Collections.sort(v, Collections.reverseOrder()); // O(n log n) — sort descending
        Collections.reverse(v);      // O(n)

        // Sort with custom comparator
        ArrayList<int[]> list = new ArrayList<>();
        list.sort((a, b) -> a[0] - b[0]);   // O(n log n) — sort by first element

        // Binary search on SORTED ArrayList — O(log n)
        Collections.sort(v);
        int idx = Collections.binarySearch(v, 10); // returns index, negative if not found


        // ────────────────────────────────────────────────────────────
        // 9. ARRAYS
        // ────────────────────────────────────────────────────────────
        int[] arr = {5, 3, 1, 4, 2};

        Arrays.sort(arr);                      // O(n log n)
        Arrays.sort(arr, 1, 4);                // O(k log k) — sort subarray [1, 4)
        Arrays.fill(arr, 0);                   // O(n)
        Arrays.copyOf(arr, arr.length);        // O(n) — full copy
        Arrays.copyOfRange(arr, 1, 4);         // O(k) — subarray copy

        // Binary search on SORTED array — O(log n)
        Arrays.sort(arr);
        int pos = Arrays.binarySearch(arr, 3); // index if found, negative if not

        // ── Manual lower_bound (first index where arr[i] >= target) ──
        // O(log n)
        int target = 3;
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        // lo = lower_bound index

        // ── Manual upper_bound (first index where arr[i] > target) ──
        // O(log n)
        lo = 0; hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        // lo = upper_bound index


        // ────────────────────────────────────────────────────────────
        // 10. PAIR  →  int[] of size 2
        // ────────────────────────────────────────────────────────────
        int[] pair = {3, 7};         // pair.first = pair[0], pair.second = pair[1]

        int[][] pairs = {{3,1}, {1,4}, {2,2}};

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);         // sort by first
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);         // sort by second
        Arrays.sort(pairs, (a, b) -> a[0] != b[0]          // sort by first, break ties by second
                ? a[0] - b[0] : a[1] - b[1]);
    }
}


// ╔══════════════════════════════════════════════════════════════════╗
// ║                    TIME COMPLEXITY CHEAT SHEET                  ║
// ╚══════════════════════════════════════════════════════════════════╝
//
//  MULTISET (backed by TreeMap<T,Integer>)
//  ─────────────────────────────────────
//  add(v)           O(log n)
//  remove(v)        O(log n)   — removes ONE occurrence
//  removeAll(v)     O(log n)   — removes ALL occurrences
//  contains(v)      O(log n)
//  count(v)         O(log n)
//  first()          O(log n)   — min
//  last()           O(log n)   — max
//  lower(v)         O(log n)   — largest element STRICTLY < v
//  floor(v)         O(log n)   — largest element <= v
//  ceiling(v)       O(log n)   — smallest element >= v  [lower_bound]
//  higher(v)        O(log n)   — smallest element STRICTLY > v [upper_bound]
//  size()           O(1)
//  distinctSize()   O(1)
//  isEmpty()        O(1)
//  clear()          O(n)
//  iterate          O(n)
//
//  TREESET
//  ─────────────────────────────────────
//  add(v)           O(log n)
//  remove(v)        O(log n)
//  contains(v)      O(log n)
//  first()          O(log n)
//  last()           O(log n)
//  lower(v)         O(log n)
//  floor(v)         O(log n)
//  ceiling(v)       O(log n)   ← lower_bound
//  higher(v)        O(log n)   ← upper_bound
//  pollFirst()      O(log n)
//  pollLast()       O(log n)
//  size()           O(1)
//  iterate          O(n)
//
//  HASHSET
//  ─────────────────────────────────────
//  add/remove/contains   O(1) avg
//  size()                O(1)
//  ❌ no floor/ceiling/first/last
//
//  TREEMAP
//  ─────────────────────────────────────
//  put(k,v)         O(log n)
//  get(k)           O(log n)
//  remove(k)        O(log n)
//  containsKey(k)   O(log n)
//  containsValue(v) O(n) ← slow!
//  firstKey()       O(log n)
//  lastKey()        O(log n)
//  lowerKey(k)      O(log n)
//  floorKey(k)      O(log n)
//  ceilingKey(k)    O(log n)   ← lower_bound
//  higherKey(k)     O(log n)   ← upper_bound
//  size()           O(1)
//  iterate          O(n)
//
//  HASHMAP
//  ─────────────────────────────────────
//  put/get/remove/containsKey   O(1) avg
//  size()                       O(1)
//  ❌ no firstKey/lastKey/floorKey/ceilingKey
//
//  PRIORITYQUEUE
//  ─────────────────────────────────────
//  add/offer        O(log n)
//  peek             O(1)
//  poll             O(log n)
//  contains         O(n) ← slow!
//  size()           O(1)
//  isEmpty()        O(1)
//
//  ARRAYDEQUE (Stack / Queue / Deque)
//  ─────────────────────────────────────
//  push/pop/peek (stack)           O(1)
//  offer/poll/peek (queue)         O(1)
//  addFirst/addLast                O(1) amortized
//  pollFirst/pollLast              O(1)
//  peekFirst/peekLast              O(1)
//  contains                        O(n) ← slow!
//  size()                          O(1)
//
//  ARRAYLIST
//  ─────────────────────────────────────
//  add(end)         O(1) amortized
//  add(index)       O(n)
//  get(index)       O(1)
//  set(index)       O(1)
//  remove(last)     O(1)
//  remove(index)    O(n)
//  remove(value)    O(n)
//  contains         O(n)
//  sort             O(n log n)
//  binarySearch     O(log n)  — only on SORTED list
//  size()           O(1)
//
//  ARRAYS (primitives)
//  ─────────────────────────────────────
//  Arrays.sort(arr)              O(n log n)
//  Arrays.fill(arr, v)           O(n)
//  Arrays.copyOf(arr, n)         O(n)
//  Arrays.binarySearch(arr, v)   O(log n)  — only on SORTED array
//  Manual lower_bound            O(log n)
//  Manual upper_bound            O(log n)
