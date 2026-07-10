import java.io.*;
import java.util.*;

class TwoSets {

    public static void main(String[] args) throws Exception {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        long n = fs.nextLong();
        
        long s = n * (n + 1) / 2;
        if (s % 2 == 1) {
            out.append("NO\n");
            return;
        }
        out.append("YES\n");
         List<Long> ff = new ArrayList<>();
         List<Long> ss = new ArrayList<>();
        long s1=s/2;
         for(long i=n;i>=1;i--){
            if(s1>=i){
                ff.add(i);
                s1-=i;
            }else{
                ss.add(i);
            }
         }
         out.append(ff.size()).append('\n');
         

         for(long x:ff){
            out.append(x).append(' ');
         }
         out.append('\n');
         out.append(ss.size()).append('\n');
         for(long x:ss){
            out.append(x).append(' ');
         }
         out.append('\n');




        // if (n == 3) {
        //     out.append(2).append("\n");
        //     out.append(1).append(" ").append(2).append("\n");
        //     out.append(1).append("\n");
        //     out.append(3).append("\n");
        //     return;
        // }
        // List<Long> ff = new ArrayList<>();
        // List<Long> ss = new ArrayList<>();
        
        // if (n % 2 == 0) {
        //     for (long i = 1; i <= n / 2; i++) {
        //         if (i % 2 == 0) {
        //             ss.add(i);
        //             ss.add(n - i + 1);
        //         } else {
        //             ff.add(i);
        //             ff.add(n - i + 1);
        //         }
        //     }
        //     out.append(ss.size()).append("\n");
        //     for (long x : ss) {
        //         out.append(x).append(" ");
        //     }
        //     out.append("\n");
        //     out.append(ff.size()).append("\n");
        //     for (long x : ff) {
        //         out.append(x).append(" ");
        //     }
        //     out.append("\n");
        // } else {
        //     for (long i = 2; i <= n / 2; i++) {
        //         if (i % 2 == 0) {
        //             ff.add(i);
        //             ff.add(n - i + 2);
        //         } else {
        //             ss.add(i);
        //             ss.add(n - i + 2);
        //         }
        //     }
        //     ss.add(1L);
        //     ss.add((n + 1) / 2);
        //     ff.add((n + 1) / 2 + 1);

        //     out.append(ss.size()).append("\n");
        //     for (long x : ss) {
        //         out.append(x).append(" ");
        //     }
        //     out.append("\n");
        //     out.append(ff.size()).append("\n");
        //     for (long x : ff) {
        //         out.append(x).append(" ");
        //     }
        //     out.append("\n");
        // }
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
}