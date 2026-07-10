import java.io.*;
import java.util.*;

public class BallInBerland {

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

/*-------------Logic Here--------------------------*/

    static void solve() throws Exception {
        int a = fs.nextInt();
            int b = fs.nextInt();
            int k = fs.nextInt();
            
            int[] boys = new int[k];
            int[] girls = new int[k];
            int[] boyCount = new int[a + 1];
            int[] girlCount = new int[b + 1];
            
            for (int i = 0; i < k; i++) {
                boys[i] = fs.nextInt();
                boyCount[boys[i]]++;
            }
            for (int i = 0; i < k; i++) {
                girls[i] = fs.nextInt();
                girlCount[girls[i]]++;
            }
            
            long totalWays = 0;
            for (int i = 0; i < k; i++) {
                int invalid = (boyCount[boys[i]] - 1) + (girlCount[girls[i]] - 1);
                totalWays += (k - 1 - invalid);
            }
        
        System.out.println(totalWays/2);
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}