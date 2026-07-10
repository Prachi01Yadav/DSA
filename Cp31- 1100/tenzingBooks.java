import java.io.*;
import java.util.*;

public class tenzingBooks {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        long x = fs.nextLong();

        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = fs.nextLong();
        }
        for (int i = 0; i < n; i++) {
            c[i] = fs.nextLong();
        }

        Stack<Long> st1 = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            st1.push(a[i]);
        }
        Stack<Long> st2 = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            st2.push(b[i]);
        }
        Stack<Long> st3 = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            st3.push(c[i]);
        }
        long tor = 0;

        while (!st1.isEmpty()) {
            long v = st1.peek();
            if ((v | x) == x) {
                tor |= v;
                st1.pop();
            } else
                break;
        }

        while (!st2.isEmpty()) {
            long v = st2.peek();
            if ((v | x) == x) {
                tor |= v;
                st2.pop();
            } else
                break;
        }

        while (!st3.isEmpty()) {
            long v = st3.peek();
            if ((v | x) == x) {
                tor |= v;
                st3.pop();
            } else
                break;
        }

        System.out.println(tor == x ? "YES" : "NO");
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