import java.io.*;
import java.util.*;

public class CopilCapac {

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

    /*-------------Logic Here--------------------------*/

    static List<List<Pair>> g;
    static int ans;

    static void solve() throws Exception {

        int n = fs.nextInt();
        g = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            g.add(new ArrayList<>());

        for (int i = 1; i < n; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            g.get(u).add(new Pair(v, i));
            g.get(v).add(new Pair(u, i));
        }
        ans = 0;
        dfs(1, 0, 0, 1);
        System.out.println(ans);

    }

    static void dfs(int u, int p, int last, int cur) {
        ans = Math.max(ans, cur);
        for (Pair e : g.get(u)) {
            if (e.v == p)
                continue;
            dfs(e.v, u, e.idx, e.idx < last ? cur + 1 : cur);
        }
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static class Pair {
        int v, idx;

        Pair(int v, int idx) {
            this.v = v;
            this.idx = idx;
        }
    }
}