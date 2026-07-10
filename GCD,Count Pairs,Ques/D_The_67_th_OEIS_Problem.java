import java.io.*;
import java.util.*;

public class D_The_67_th_OEIS_Problem {

    static boolean[] isPrime;
    static List<Integer> primes;
    static long[] p;

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        isPrime = new boolean[1000000];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i < 1000000; i++) {
            if (isPrime[i]) {
                for (long j = (long) i * i; j < 1000000; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }
        primes = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        int sz = primes.size();
        p = new long[sz - 1];
        for (int i = 0; i < sz - 1; i++) {
            p[i] = (long) primes.get(i) * primes.get(i + 1);
        }

        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        for (int i = 0; i < n; i++) {
            out.append(p[i]).append(' ');
        }
        out.append('\n');
    }

    static final int MAX = 1000005;
    static final long MOD = 1_000_000_007;

    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    static void precompute() {
        fact[0] = 1;
        for (int i = 1; i < MAX; i++)
            fact[i] = (fact[i - 1] * i) % MOD;
        invFact[MAX - 1] = modPow(fact[MAX - 1], MOD - 2);
        for (int i = MAX - 2; i >= 0; i--)
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
    }

    static long modPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    static long nCr(int n, int r) {
        if (r > n || r < 0)
            return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

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