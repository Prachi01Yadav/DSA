import java.io.*;
import java.util.*;

public class DivisiblePairs {

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

    // ------------ Logic Here---------------

    static void solve() throws Exception {
        long n = fs.nextLong();
        long x = fs.nextLong();
        long y = fs.nextLong();

        long[] arr = new long[(int) n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }

        long cnt = 0;

        Map<List<Long>, Long> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long a = arr[i];
            long remX = arr[i] % x;
            long remY = arr[i] % y;

            long bX = (x - remX) % x;
            long bY = remY;

            List<Long> key = Arrays.asList(bX, bY);
            cnt += mp.getOrDefault(key, 0L);

            List<Long> currKey = Arrays.asList(remX, remY);
            mp.put(currKey, mp.getOrDefault(currKey, 0L) + 1);

        }
        System.out.println(cnt);

    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}