import java.io.*;
import java.util.*;

public class Matryoshkas {

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

    // ---------- SHORT PRINT FUNCTION ----------
    static void print(Object x) { out.append(x).append('\n'); }

    //----------Logic here----------

    static void solve() throws Exception {
    int n = fs.nextInt();
    HashMap<Long, Integer> mp = new HashMap<>();

    for (int i = 0; i < n; i++) {
        long x = fs.nextLong();
        mp.put(x, mp.getOrDefault(x, 0) + 1);
    }

    ArrayList<Long> keys = new ArrayList<>(mp.keySet());
    Collections.sort(keys);

    long ans = 0;
    long prev = -1;
    long prevCnt = 0;

    for (long x : keys) {
        long cnt = mp.get(x);

        if (x != prev + 1) {
            ans += prevCnt;
        } else if (cnt < prevCnt) {
            ans += (prevCnt - cnt);
        }

        prev = x;
        prevCnt = cnt;
    }

    ans += prevCnt;

    System.out.println(ans);
}


    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}