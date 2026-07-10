import java.io.*;
import java.util.*;

public class C_Factorials_and_Powers_of_Two {

    static long[] factorials = new long[15];

    static void precompute() {
        factorials[0] = 1;
        for (int i = 1; i <= 14; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
    }

    static int solve(long n) {
        int ans = Integer.MAX_VALUE;

        for (int mask = 0; mask < (1 << 15); mask++) {
            long rem = n;
            int cnt = 0;
            boolean valid = true;

            for (int i = 0; i < 15; i++) {
                if ((mask & (1 << i)) != 0) {
                    rem -= factorials[i];
                    cnt++;
                    if (rem < 0) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            if ((rem & 1) != 0 && usedFactorial(mask, 1)) valid = false;
            if ((rem & 2) != 0 && usedFactorial(mask, 2)) valid = false;

            if (!valid) continue;

            ans = Math.min(ans, cnt + Long.bitCount(rem));
        }

        return ans;
    }

    static boolean usedFactorial(int mask, long val) {
        for (int i = 0; i < 15; i++) {
            if ((mask & (1 << i)) != 0 && factorials[i] == val) return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        precompute();
        int t = fs.nextInt();
        while (t-- > 0) {
            out.append(solve(fs.nextLong())).append("\n");
        }
        System.out.print(out);
    }

    static final FastScanner fs = new FastScanner(System.in);
    static final StringBuilder out = new StringBuilder();

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
    }
}