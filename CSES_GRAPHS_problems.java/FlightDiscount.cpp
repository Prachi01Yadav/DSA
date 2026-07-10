// import java.io.*;
// import java.util.*;

// public class FlightDiscount {

//     public static void main(String[] args) throws Exception {
//         int t = 1;
//         while (t-- > 0) {
//             solve();
//         }
//         System.out.println(out);
//     }

//     /*
//      * PS:
//      * 
//      * Small Observations:
//      * 
//      * 
//      * 
//      * Claims on Algo:
//      */

//     static class Edge {
//         int coupanUsed;
//         int node;
//         long wt;

//         Edge(int coupanUsed, int node, long wt) {
//             this.coupanUsed = coupanUsed;
//             this.node = node;
//             this.wt = wt;
//         }
//     }

//     static void solve() throws Exception {
//         int n = fs.nextInt();
//         int m = fs.nextInt();

//         List<List<int[]>> g = new ArrayList<>();

//         for (int i = 0; i <= n; i++) {
//             g.add(new ArrayList<>());
//         }

//         for (int i = 0; i < m; i++) {
//             int u = fs.nextInt();
//             int v = fs.nextInt();
//             int w = fs.nextInt();
//             g.get(u).add(new int[] { v, w });
//         }

//         PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> {
//             return Long.compare(x.wt, y.wt);
//         });

//         long[][] dist = new long[n + 1][2];
//         for(long[] d:dist) Arrays.fill(d, Long.MAX_VALUE);
//         dist[1][0] = 0;

//         pq.add(new Edge(0, 1, 0));

//         while (!pq.isEmpty()) {
//             Edge curr = pq.poll();
//             int u = curr.node;
//             long wt = curr.wt;
//             int used = curr.coupanUsed;

//             if (wt > dist[u][used])
//                 continue;

//             for (int[] vv : g.get(u)) {
//                 int v = vv[0];
//                 int w = vv[1];

//                 // go as usual
//                 if (w + wt < dist[v][used]) {
//                     dist[v][used] = w + wt;
//                     pq.add(new Edge(used, v, dist[v][used]));
//                 }

//                 if (used == 0) {

//                     if (wt + w / 2 < dist[v][1]) dist[v][1] = dist[u][0] + w / 2;

//                     pq.add(new Edge(1, v,dist[v][1]));
//                 }
//             }
//         }

//         out.append(Math.min(dist[n][0],dist[n][1]));
//     }

//     // // nCr using factorial + modular inverse
//     // static final int MAX = 1000005;
//     // static final long MOD = 1_000_000_007;
//     // static final long inf = (long) 2e18;
//     //
//     // static long[] fact = new long[MAX];
//     // static long[] invFact = new long[MAX];
//     //
//     // // factorials and inverse factorials
//     // static void precompute() {
//     // fact[0] = 1;
//     // for (int i = 1; i < MAX; i++)
//     // fact[i] = (fact[i-1] * i) % MOD;
//     //
//     // invFact[MAX-1] = modPow(fact[MAX-1], MOD-2);
//     // for (int i = MAX-2; i >= 0; i--)
//     // invFact[i] = (invFact[i+1] * (i+1)) % MOD;
//     // }
//     //
//     // // Fast exponentiation
//     // static long modPow(long a, long b) {
//     // long res = 1;
//     // while (b > 0) {
//     // if ((b & 1) == 1)
//     // res = (res * a) % MOD;
//     // a = (a * a) % MOD;
//     // b >>= 1;
//     // }
//     // return res;
//     // }
//     //
//     // // nCr calculation
//     // static long nCr(int n, int r) {
//     // if (r > n || r < 0)
//     // return 0;
//     // return (((fact[n] * invFact[r]) % MOD) * invFact[n-r]) % MOD;
//     // }

//     static class MultiSet<T extends Comparable<T>> {
//         private TreeMap<T, Integer> map = new TreeMap<>();
//         private int totalSize = 0;

//         void add(T v) {
//             map.merge(v, 1, Integer::sum);
//             totalSize++;
//         } // O(log n)

//         boolean contains(T v) {
//             return map.containsKey(v);
//         } // O(log n)

//         int count(T v) {
//             return map.getOrDefault(v, 0);
//         } // O(log n)

//         int size() {
//             return totalSize;
//         } // O(1)

//         int distinctSize() {
//             return map.size();
//         } // O(1)

//         boolean isEmpty() {
//             return totalSize == 0;
//         } // O(1)

//         T first() {
//             return map.firstKey();
//         } // O(log n) — min element

//         T last() {
//             return map.lastKey();
//         } // O(log n) — max element

//         T lower(T v) {
//             return map.lowerKey(v);
//         } // O(log n) — largest element STRICTLY < v

//         T floor(T v) {
//             return map.floorKey(v);
//         } // O(log n) — largest element <= v

//         T ceiling(T v) {
//             return map.ceilingKey(v);
//         } // O(log n) — smallest element >= v [= lower_bound]

//         T higher(T v) {
//             return map.higherKey(v);
//         } // O(log n) — smallest element STRICTLY > v [= upper_bound]

//         TreeMap<T, Integer> getMap() {
//             return map;
//         } // O(1)

//         // Remove ONE occurrence of v- O(log n)
//         boolean remove(T v) {
//             Integer c = map.get(v);
//             if (c == null)
//                 return false;
//             if (c == 1)
//                 map.remove(v);
//             else
//                 map.put(v, c - 1);
//             totalSize--;
//             return true;
//         }

//         // Remove ALL occurrences of v - O(log n)
//         boolean removeAll(T v) {
//             Integer c = map.get(v);
//             if (c == null)
//                 return false;
//             map.remove(v);
//             totalSize -= c;
//             return true;
//         }

//         // Iterate over all elements in sorted order - O(n)
//         // Usage: for (Map.Entry<T,Integer> e : ms.getMap().entrySet()) { T
//         // val=e.getKey(); int freq=e.getValue(); }

//         void clear() {
//             map.clear();
//             totalSize = 0;
//         } // O(n)

//         @Override
//         public String toString() {
//             return map.toString();
//         }
//     }

//     /*-------------For Fast I/O --------------------------*/
//     private static final class FastScanner {
//         private final byte[] buffer = new byte[1 << 16];
//         private int ptr = 0, len = 0;
//         private final InputStream in;

//         FastScanner(InputStream in) {
//             this.in = in;
//         }

//         private int readByte() throws IOException {
//             if (ptr >= len) {
//                 len = in.read(buffer);
//                 ptr = 0;
//                 if (len <= 0)
//                     return -1;
//             }
//             return buffer[ptr++];
//         }

//         long nextLong() throws IOException {
//             int c;
//             do {
//                 c = readByte();
//             } while (c <= ' ' && c != -1);
//             boolean neg = false;
//             if (c == '-') {
//                 neg = true;
//                 c = readByte();
//             }
//             long val = 0;
//             while (c > ' ') {
//                 val = val * 10 + (c - '0');
//                 c = readByte();
//             }
//             return neg ? -val : val;
//         }

//         int nextInt() throws IOException {
//             return (int) nextLong();
//         }

//         double nextDouble() throws IOException {
//             return Double.parseDouble(next());
//         }

//         String next() throws IOException {
//             int c;
//             do {
//                 c = readByte();
//             } while (c <= ' ' && c != -1);
//             if (c == -1)
//                 return "";
//             StringBuilder sb = new StringBuilder();
//             while (c > ' ') {
//                 sb.append((char) c);
//                 c = readByte();
//             }
//             return sb.toString();
//         }
//     }

//     static FastScanner fs = new FastScanner(System.in);
//     static StringBuilder out = new StringBuilder(1 << 21);
// }

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <climits>

using namespace std;

// Represents the state in the Priority Queue
struct State {
    int used;
    int node;
    long long wt;

    // Priority Queue is a max-heap by default, so we reverse the comparison
    bool operator>(const State& other) const {
        return wt > other.wt;
    }
};

void solve() {
    int n, m;
    if (!(cin >> n >> m)) return;

    // Adjacency list: g[u] contains pairs of {v, weight}
    vector<vector<pair<int, int>>> g(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        g[u].push_back({v, w});
    }

    // dist[node][couponUsed]
    vector<vector<long long>> dist(n + 1, vector<long long>(2, LLONG_MAX));
    priority_queue<State, vector<State>, greater<State>> pq;

    // Start at city 1, coupon not used (0)
    dist[1][0] = 0;
    pq.push({0, 1, 0});

    while (!pq.empty()) {
        State curr = pq.top();
        pq.pop();

        int u = curr.node;
        long long wt = curr.wt;
        int used = curr.used;

        if (wt > dist[u][used]) continue;

        for (auto& edge : g[u]) {
            int v = edge.first;
            int w = edge.second;

            // Move 1: Standard flight (full price)
            if (dist[u][used] + w < dist[v][used]) {
                dist[v][used] = dist[u][used] + w;
                pq.push({used, v, dist[v][used]});
            }

            // Move 2: Use coupon (half price) - only if used == 0
            if (used == 0) {
                if (dist[u][0] + (w / 2) < dist[v][1]) {
                    dist[v][1] = dist[u][0] + (w / 2);
                    pq.push({1, v, dist[v][1]});
                }
            }
        }
    }

    cout << min(dist[n][0], dist[n][1]) << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    solve();

    return 0;
}