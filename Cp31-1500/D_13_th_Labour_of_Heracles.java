import java.io.*;
import java.util.*;

public class D_13_th_Labour_of_Heracles {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

/*

PS :
 Small Observations:
   - Each node v with degree d can appear in up to d different color subgraphs.
   - At k=1, every node contributes w[v] once (whole tree is one component).
   - Each extra color gives node v one more chance to be counted → (degree[v]-1) gains of w[v].
   - Total gains available = n-2 (one per color step after the first).

Claims on Algo:
   - answer(k=1) = sum of all weights (tree is fully connected).
   - Greedily pick the largest gains first for each additional color.
   - answer(k) = totalWeight + sum of top (k-1) gains.
   - Gains list: for each node v, push w[v] exactly (degree[v]-1) times, sort descending.

*/

    static void solve() throws Exception {
        int n = fs.nextInt();

        long[] w = new long[n + 1];
        long totalWeight = 0;
        for (int i = 1; i <= n; i++) {
            w[i] = fs.nextLong();
            totalWeight += w[i];
        }

        int[] degree = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            degree[u]++;
            degree[v]++;
        }

        
        long[] gains = new long[n - 1]; 
        int idx = 0;
        for (int v = 1; v <= n; v++) {
            for (int j = 0; j < degree[v] - 1; j++) {
                gains[idx++] = w[v];
            }
        }

        
        Arrays.sort(gains, 0, idx);
        for (int l = 0, r = idx - 1; l < r; l++, r--) {
            long tmp = gains[l]; gains[l] = gains[r]; gains[r] = tmp;
        }

        //ans is totalWeight + prefix sum of first (k-1) gains..
        long running = totalWeight;
        for (int k = 1; k <= n - 1; k++) {
            if (k > 1) running += gains[k - 2];
            out.append(running);
            if (k < n - 1) out.append(' ');
        }
        out.append('\n');
    }


    // nCr using factorial + modular inverse -> Works when MOD is prime (ex: 1e9+7)

    static final int MAX = 1000005;
    static final long MOD = 1_000_000_007;

    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    static void precompute() {
        fact[0] = 1;
        for (int i = 1; i < MAX; i++)
            fact[i] = (fact[i-1] * i) % MOD;
        invFact[MAX-1] = modPow(fact[MAX-1], MOD - 2);
        for (int i = MAX-2; i >= 0; i--)
            invFact[i] = (invFact[i+1] * (i+1)) % MOD;
    }

    static long modPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    static long nCr(int n, int r) {
        if (r > n || r < 0) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n-r]) % MOD;
    }

    /*-------------For Fast I/O --------------------------*/

    private static final class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            boolean neg = false; if (c == '-') { neg = true; c = readByte(); }
            long val = 0;
            while (c > ' ') { val = val * 10 + (c - '0'); c = readByte(); }
            return neg ? -val : val;
        }

        int nextInt() throws IOException { return (int) nextLong(); }
        String next() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') { sb.append((char) c); c = readByte(); }
            return sb.toString();
        }
    }

    static final long INF = (long) 1e18;
    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder();
}