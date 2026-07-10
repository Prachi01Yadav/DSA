import java.io.*;
import java.util.*;

public class RomanticGlasses {

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

    // --------Logic Here------------

    static void solve() throws Exception {
        int n = fs.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }

        long[] preOddSum = new long[n];
        long[] preEvenSum = new long[n];

        boolean exists = false;
        Map<Long, Integer> mp = new HashMap<>();

        preEvenSum[0] = arr[0];
        preOddSum[0] = 0;
        mp.put(preOddSum[0] - preEvenSum[0], 1);
        for (int i = 1; i < n; i++) {
            preOddSum[i] = preOddSum[i - 1];
            preEvenSum[i] = preEvenSum[i - 1];
            if (i % 2 == 0) {
                preEvenSum[i] = preEvenSum[i - 1] + arr[i];
            } else {
                preOddSum[i] = preOddSum[i - 1] + arr[i];
            }

            long key = preOddSum[i] - preEvenSum[i];
            mp.put(key, mp.getOrDefault(key, 0) + 1);

            if (mp.get(key) == 2 || key == 0) {
                exists = true;
                break;
            }
        }

        if (exists) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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