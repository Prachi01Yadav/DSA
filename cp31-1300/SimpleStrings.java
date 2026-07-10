import java.io.*;
import java.util.*;

public class SimpleStrings {

    public static void main(String[] args) throws Exception {
        solve();
    }

    static void solve() throws Exception {
    String s = fs.next();
    int n = s.length();

    StringBuilder sb = new StringBuilder();
    sb.append(s.charAt(0));

    for (int i = 1; i < n; i++) {
        char prev = sb.charAt(sb.length() - 1);
        char curr = s.charAt(i);

        if (curr != prev) {
            sb.append(curr);
        } else {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != prev && (i + 1 == n || c != s.charAt(i + 1))) {
                    sb.append(c);
                    break;
                }
            }
        }
    }

    System.out.println(sb.toString());
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