import java.io.*;
import java.util.*;

public class FindDifferentOnes {

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

    // ---------- SHORT PRINT FUNCTION ----------
    static void print(Object x) {
        out.append(x).append('\n');
    }
    // ----Logic Here--------

    static void solve() throws Exception {
        int n = fs.nextInt();
        long[] arr = new long[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = fs.nextLong();
        }
        int[] nextDiff = new int[n + 2];
        nextDiff[n] = n+1;

        for (int i = n - 1; i >= 1; i--) {
            if (arr[i] == arr[i + 1]) {
                nextDiff[i] = nextDiff[i + 1];
            } else {
                nextDiff[i] = i + 1;
            }
        }

        long q = fs.nextLong();
        while (q-- > 0) {
            int l = fs.nextInt();
            int r = fs.nextInt();
            int[] ans = new int[2];
            if (nextDiff[l] <= r) {
                ans[0] = l;
                ans[1] = nextDiff[l];
            } else {
                ans[0] = -1;
                ans[1] = -1;
            }

            System.out.print(ans[0] + " " + ans[1]);
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}