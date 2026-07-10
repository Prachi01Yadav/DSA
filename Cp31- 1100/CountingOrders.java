import java.io.*;
import java.util.*;

public class CountingOrders {

    private static final long MOD = 1000000007L;
    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        Long[] a = new Long[n];
        Long[] b = new Long[n];
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = fs.nextLong();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        long ans = 1;
        boolean possible = true;
        for (int i = n - 1; i >= 0; i--) {
            long el = b[i];
            int s = 0;
            int e = n - 1;
            int possforEl = 0;
            while (s <= e) {
                int mid = s + (e - s) / 2;
                if (a[mid] > el) {
                    possforEl = n - mid;
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            }
            int used = (n - 1) - i;
            if (possforEl - used <= 0) {
                ans = 0;
                possible = false;
                break;
            }
            ans = (ans * (possforEl - used)) % MOD;
        }
        if(possible){
            System.out.println(ans);
        }else{
            System.out.println(0);
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