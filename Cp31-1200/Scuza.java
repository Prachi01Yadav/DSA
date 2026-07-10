import java.io.*;
import java.util.*;

public class Scuza {

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

        String next() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    static final long INF = (long) 1e18;
    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder();

    /*-------------Logic Here--------------------------*/
    static long[] pref;
    static long[] maxStep;

    static void solve() throws Exception {
        int n = fs.nextInt();
        int q = fs.nextInt();

        long[] a = new long[n];
        long sumA = 0;
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
            sumA += a[i];
        }

        long[] k = new long[q];
        for (int i = 0; i < q; i++) {
            k[i] = fs.nextLong();
        }
        pref = new long[n];
        maxStep = new long[n];
        maxStep[0] = a[0];
        pref[0] = a[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + a[i];
            maxStep[i] = Math.max(maxStep[i - 1], a[i]);
        }
        long[] ans = new long[q];
        for (int i = 0; i < q; i++) {
            long s = 0;
            long e = sumA;

            while (s <= e) {
                long mid = s + (e - s) / 2;
                if (isPossible(k[i], mid, a)) {
                    ans[i] = mid;
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }

        }

        for (int i = 0; i < q; i++) {
            out.append(ans[i]).append(" ");
        }
        out.append("\n");
    }

    private static boolean isPossible(long k, long maxH, long[] a) {
        if (maxH <= 0)
            return true;

        int idx = Arrays.binarySearch(pref, maxH);
        if (idx < 0) {
            idx = -(idx + 1);
        }

        if (idx >= maxStep.length)
            return false;

        return maxStep[idx] <= k;
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}