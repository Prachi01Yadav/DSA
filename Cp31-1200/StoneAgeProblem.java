import java.io.*;
import java.util.*;

public class StoneAgeProblem {

    public static void main(String[] args) throws Exception {

        solve();

    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        int q = fs.nextInt();

        long sum = 0;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
            sum += arr[i];
        }

        long prev = -1;
        HashMap<Integer, Long> map = new HashMap<>();

        while (q-- > 0) {
            int t = fs.nextInt();

            if (t == 1) {
                int i = fs.nextInt() - 1;
                long x = fs.nextLong();

                long old;
                if (prev == -1) {
                    old = arr[i];
                } else {
                    old = map.containsKey(i) ? map.get(i) : prev;
                }

                sum = sum - old + x;
                map.put(i, x);
                arr[i] = x;

                System.out.println(sum);

            } else {
                long x = fs.nextLong();
                prev = x;
                sum = (long) n * x;
                map.clear();
                System.out.println(sum);
            }
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

}