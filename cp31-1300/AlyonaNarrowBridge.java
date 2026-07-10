import java.io.*;
import java.util.*;

public class AlyonaNarrowBridge {

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
    static long h;

    static void solve() throws Exception {
        long n = fs.nextLong();
        h = fs.nextLong();

        long[] arr = new long[(int) n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }

        long ans = 1;
        long l = 1;
        long r = n;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (isPossible(mid, arr)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean isPossible(long k, long[] arr) {

        long[] temp = new long[(int) k];
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        Arrays.sort(temp);

        int cnt = 0;
        long max = h;
        for (long i = k - 1; i >= 0; i -= 2) {
            if (max - temp[(int) i] >= 0) {
                max -= temp[(int) i];
                cnt += 2;
            } else {
                break;
            }
        }
        return cnt >= k;
    }

    public static void main(String[] args) throws Exception {

        solve();

    }
}