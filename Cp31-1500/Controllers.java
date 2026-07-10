import java.io.*;
import java.util.*;

public class Controllers {

    public static void main(String[] args) throws Exception {

        solve();

    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        String s = fs.next();
        long cntPos = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '+') {
                cntPos++;
            }
        }
        long cntNeg = n - cntPos;
        int q = fs.nextInt();
        while (q-- > 0) {

            long a = fs.nextLong();
            long b = fs.nextLong();

            if (cntNeg == cntPos) {
                System.out.println("YES");
            } else if (a == b) {
                System.out.println("NO");
            } else {
                long l = (1L * a * b) / gcd(a, b);

                long g = gcd(a, b);
                long x = b / g;
                long y = a / g;

                long diff = Math.abs(cntNeg - cntPos);

                if (diff % Math.abs(y - x) == 0) {
                    long p = diff / Math.abs(y - x);
                    if ((x + y) * p > n) {
                        System.out.println("NO");
                    } else {
                        System.out.println("YES");
                    }
                } else {
                    System.out.println("NO");
                }

            }

        }
    }

    static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
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