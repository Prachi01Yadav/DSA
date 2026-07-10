import java.io.*;
import java.util.*;

public class A_Weird_Sum {

    public static void main(String[] args) throws Exception {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        int m = fs.nextInt();

        // Group row and col indices by color separately
        Map<Integer, List<Integer>> rowsByColor = new HashMap<>();
        Map<Integer, List<Integer>> colsByColor = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int c = fs.nextInt();
                rowsByColor.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
                colsByColor.computeIfAbsent(c, k -> new ArrayList<>()).add(j);
            }
        }

        long ans = 0;

        for (int color : rowsByColor.keySet()) {
            List<Integer> rows = rowsByColor.get(color);
            List<Integer> cols = colsByColor.get(color);

            Collections.sort(rows);
            Collections.sort(cols);

            // Sum |r1 - r2| over all pairs of same color
            ans += pairwiseAbsSum(rows);
            // Sum |c1 - c2| over all pairs of same color
            ans += pairwiseAbsSum(cols);
        }

        out.append(ans).append('\n');
    }

    // Given a sorted list, computes sum of |a[i] - a[j]| for all i < j
    // For sorted array: contribution of a[i] = a[i]*i - prefixSum
    static long pairwiseAbsSum(List<Integer> a) {
        long result = 0;
        long prefixSum = 0;
        for (int i = 0; i < a.size(); i++) {
            result += (long) a.get(i) * i - prefixSum;
            prefixSum += a.get(i);
        }
        return result;
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
            while (c > ' ') { sb.append((char)c); c = readByte(); }
            return sb.toString();
        }
    }

    static final long INF = (long) 1e18;
    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder();
}