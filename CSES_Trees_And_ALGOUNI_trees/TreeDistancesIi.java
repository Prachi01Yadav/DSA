import java.io.*;
import java.util.*;

public class TreeDistancesIi {

    public static void main(String[] args) throws Exception {
        // Run everything cleanly inside the custom thread
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    solve();
                    // Print the output ONLY after solve() completes completely
                    System.out.print(out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 27).start(); // 134 MB Stack Size
    }
    // public static void main(String[] args) throws Exception {
    // new Thread(null, new Runnable() {
    // public void run() {
    // try {
    // solve();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }, "1", 1 << 27).start();
    // int t = 1;
    // while (t-- > 0) {
    // solve();
    // }
    // System.out.println(out);
    // }

    /*
     * PS:
     * 
     * Small Observations:
     * 
     * 
     * 
     * Claims on Algo:
     */

    static void solve() throws Exception {
        g = new ArrayList<>();
        n = fs.nextInt();
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        ans = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
        parent = new int[n + 1];
        parent[1] = 1;

        dfs(1, -1);
        // for(int i=1;i<=n;i++){
        // System.out.print(ans[i]+" ");
        // }

        dist = new long[n + 1];
        dfs1(1, -1, 0);
        long s = 0;
        for (int i = 1; i <= n; i++) {
            s += dist[i];
        }

        long[] res = new long[n + 1];
        res[1] = s;
        dfs2(1, -1, res);
        for (int i = 1; i <= n; i++) {
            out.append(res[i]).append(" ");
        }
        out.append('\n');

    }

    static int[] ans;
    static int[] parent;
    static List<List<Integer>> g;

    static void dfs(int node, int par) {
        ans[node] = 1;
        for (int v : g.get(node)) {
            if (v == par)
                continue;
            dfs(v, node);
            ans[node] += ans[v];
            parent[v] = node;
        }
    }

    static int n;

    static void dfs2(int node, int par, long[] res) {
        for (int v : g.get(node)) {
            if (v == par)
                continue;
            res[v] = res[node] - ans[v] + (n - ans[v]);
            dfs2(v, node, res);
        }
    }

    static long[] dist;

    static void dfs1(int node, int par, int d) {
        dist[node] = d;
        for (int v : g.get(node)) {
            if (v == par)
                continue;
            dfs1(v, node, d + 1);
        }
    }

    // // nCr using factorial + modular inverse
    // static final int MAX = 1000005;
    // static final long MOD = 1_000_000_007;
    // static final long inf = (long) 2e18;
    //
    // static long[] fact = new long[MAX];
    // static long[] invFact = new long[MAX];
    //
    // // factorials and inverse factorials
    // static void precompute() {
    // fact[0] = 1;
    // for (int i = 1; i < MAX; i++)
    // fact[i] = (fact[i-1] * i) % MOD;
    //
    // invFact[MAX-1] = modPow(fact[MAX-1], MOD-2);
    // for (int i = MAX-2; i >= 0; i--)
    // invFact[i] = (invFact[i+1] * (i+1)) % MOD;
    // }
    //
    // // Fast exponentiation
    // static long modPow(long a, long b) {
    // long res = 1;
    // while (b > 0) {
    // if ((b & 1) == 1)
    // res = (res * a) % MOD;
    // a = (a * a) % MOD;
    // b >>= 1;
    // }
    // return res;
    // }
    //
    // // nCr calculation
    // static long nCr(int n, int r) {
    // if (r > n || r < 0)
    // return 0;
    // return (((fact[n] * invFact[r]) % MOD) * invFact[n-r]) % MOD;
    // }

    static class MultiSet<T extends Comparable<T>> {
        private TreeMap<T, Integer> map = new TreeMap<>();
        private int totalSize = 0;

        void add(T v) {
            map.merge(v, 1, Integer::sum);
            totalSize++;
        } // O(log n)

        boolean contains(T v) {
            return map.containsKey(v);
        } // O(log n)

        int count(T v) {
            return map.getOrDefault(v, 0);
        } // O(log n)

        int size() {
            return totalSize;
        } // O(1)

        int distinctSize() {
            return map.size();
        } // O(1)

        boolean isEmpty() {
            return totalSize == 0;
        } // O(1)

        T first() {
            return map.firstKey();
        } // O(log n) — min element

        T last() {
            return map.lastKey();
        } // O(log n) — max element

        T lower(T v) {
            return map.lowerKey(v);
        } // O(log n) — largest element STRICTLY < v

        T floor(T v) {
            return map.floorKey(v);
        } // O(log n) — largest element <= v

        T ceiling(T v) {
            return map.ceilingKey(v);
        } // O(log n) — smallest element >= v [= lower_bound]

        T higher(T v) {
            return map.higherKey(v);
        } // O(log n) — smallest element STRICTLY > v [= upper_bound]

        TreeMap<T, Integer> getMap() {
            return map;
        } // O(1)

        // Remove ONE occurrence of v- O(log n)
        boolean remove(T v) {
            Integer c = map.get(v);
            if (c == null)
                return false;
            if (c == 1)
                map.remove(v);
            else
                map.put(v, c - 1);
            totalSize--;
            return true;
        }

        // Remove ALL occurrences of v - O(log n)
        boolean removeAll(T v) {
            Integer c = map.get(v);
            if (c == null)
                return false;
            map.remove(v);
            totalSize -= c;
            return true;
        }

        // Iterate over all elements in sorted order - O(n)
        // Usage: for (Map.Entry<T,Integer> e : ms.getMap().entrySet()) { T
        // val=e.getKey(); int freq=e.getValue(); }

        void clear() {
            map.clear();
            totalSize = 0;
        } // O(n)

        @Override
        public String toString() {
            return map.toString();
        }
    }

    /*-------------For Fast I/O --------------------------*/
    private static final class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = readByte();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            if (c == -1)
                return "";
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder(1 << 21);
}

// #include <iostream>
// #include <vector>
// #include <map>
// #include <string>

// using namespace std;

// // Global variables matching your Java fields
// int n;
// vector<vector<int>> g;
// vector<int> ans;
// vector<int> parent_node; // renamed from 'parent' to avoid potential naming
// conflicts
// vector<long long> dist;

// // First DFS: Calculates subtree sizes
// void dfs(int node, int par) {
// ans[node] = 1;
// for (int v : g[node]) {
// if (v == par) continue;
// dfs(v, node);
// ans[node] += ans[v];
// parent_node[v] = node;
// }
// }

// // Second DFS: Calculates raw depths from root (node 1)
// void dfs1(int node, int par, int d) {
// dist[node] = d;
// for (int v : g[node]) {
// if (v == par) continue;
// dfs1(v, node, d + 1);
// }
// }

// // Third DFS: Rerooting DP to calculate final distance sums for all nodes
// void dfs2(int node, int par, vector<long long>& res) {
// for (int v : g[node]) {
// if (v == par) continue;
// res[v] = res[node] - ans[v] + (n - ans[v]);
// dfs2(v, node, res);
// }
// }

// void solve() {
// if (!(cin >> n)) return;

// g.assign(n + 1, vector<int>());
// ans.assign(n + 1, 0);
// parent_node.assign(n + 1, 0);
// dist.assign(n + 1, 0);

// for (int i = 0; i < n - 1; i++) {
// int u, v;
// cin >> u >> v;
// g[u].push_back(v);
// g[v].push_back(u);
// }

// parent_node[1] = 1;
// dfs(1, -1);
// dfs1(1, -1, 0);

// long long s = 0;
// for (int i = 1; i <= n; i++) {
// s += dist[i];
// }

// vector<long long> res(n + 1, 0);
// res[1] = s;
// dfs2(1, -1, res);

// for (int i = 1; i <= n; i++) {
// cout << res[i] << " ";
// }
// cout << "\n";
// }

// int main() {
// // Optimize standard I/O operations for performance (replaces FastScanner)
// ios_base::sync_with_stdio(false);
// cin.tie(NULL);

// int t = 1;
// while (t-- > 0) {
// solve();
// }

// return 0;
// }

// /*------------- Custom MultiSet Wrapper (Equivalent to your Java Template)
// -------------*/
// template <typename T>
// class MultiSet {
// private:
// map<T, int> m_map;
// int totalSize = 0;

// public:
// void add(T v) { m_map[v]++; totalSize++; }
// bool contains(T v) { return m_map.find(v) != m_map.end(); }
// int count(T v) { return m_map.count(v) ? m_map[v] : 0; }
// int size() { return totalSize; }
// int distinctSize() { return m_map.size(); }
// bool isEmpty() { return totalSize == 0; }

// T first() { return m_map.begin()->first; }
// T last() { return m_map.rbegin()->first; }

// // Strict lower: largest element STRICTLY < v
// T lower(T v) {
// auto it = m_map.lower_bound(v);
// if (it == m_map.begin()) return T(); // or throw exception
// return (--it)->first;
// }

// // Floor: largest element <= v
// T floor(T v) {
// auto it = m_map.upper_bound(v);
// if (it == m_map.begin()) return T();
// return (--it)->first;
// }

// // Ceiling: smallest element >= v
// T ceiling(T v) {
// auto it = m_map.lower_bound(v);
// if (it == m_map.end()) return T();
// return it->first;
// }

// // Higher: smallest element STRICTLY > v
// T higher(T v) {
// auto it = m_map.upper_bound(v);
// if (it == m_map.end()) return T();
// return it->first;
// }

// bool remove(T v) {
// auto it = m_map.find(v);
// if (it == m_map.end()) return false;
// if (it->second == 1) m_map.erase(it);
// else it->second--;
// totalSize--;
// return true;
// }

// bool removeAll(T v) {
// auto it = m_map.find(v);
// if (it == m_map.end()) return false;
// totalSize -= it->second;
// m_map.erase(it);
// return true;
// }

// void clear() { m_map.clear(); totalSize = 0; }
// map<T, int>& getMap() { return m_map; }
// };