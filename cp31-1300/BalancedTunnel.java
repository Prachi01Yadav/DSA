import java.io.*;
import java.util.*;

public class BalancedTunnel {

    public static void main(String[] args) throws Exception {

        solve();

    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        int[] entry = new int[n];
        int[] exit = new int[n];

        int[] entryPos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            entry[i] = fs.nextInt();
            entryPos[entry[i]] = i;
        }
        for (int j = 0; j < n; j++) {
            exit[j] = fs.nextInt();
        }

        int[] realExit = new int[n];
        for (int i = 0; i < n; i++) {
            realExit[i] = entryPos[exit[i]];
        }

        int fined = 0;
        int minEntry = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {

            if (realExit[i] > minEntry) {
                fined++;
            } else {
                minEntry = realExit[i];
            }
        }

        System.out.println(fined);

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