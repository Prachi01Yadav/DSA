import java.io.*;
import java.util.*;

public class Dances {

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

    // ---------Logic here-----------

    static void solve() throws Exception {
        int n = fs.nextInt();

        ArrayList<Long> a = new ArrayList<>();
        ArrayList<Long> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(fs.nextLong());
        }

        for (int i = 0; i < n; i++) {
            b.add(fs.nextLong());
        }

        Collections.sort(b);
        long ans = 0;

        for (int i = a.size() - 1; i >= 0; i--) {
            int idx = BS(a.get(i), b);
            if (idx == -1) {
                a.remove(i);
                ans++;
            } else {
                b.remove(idx);
            }
        }
        System.out.println(ans);
    }

    private static int BS(long target, ArrayList<Long> list) {
        int s = 0;
        int n=list.size();
        int e =  n-1;
        int res=-1;
        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (list.get(mid) > target) {
                res=mid;
                e=mid-1;
            } else {
                s = mid + 1;
            }
        }
        return res;

    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}