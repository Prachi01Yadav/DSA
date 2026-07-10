import java.io.*;
import java.util.*;

public class DMaximumPrefixSums {

    // -----Customize for each ques-----

    /*
     * static long[] segTree;
     * static long[] arr;
     * 
     * static void buildTree(int i,int l,int r){
     * if(l==r){
     * segTree[i]=arr[l];
     * return;
     * }
     * int mid= l + (r-l)/2;
     * buildTree(2*i +1,l,mid);
     * buildTree(2*i +2, mid+1,r);
     * segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
     * }
     * 
     * static long query(int s,int e,int i,int l,int r){
     * if(e<l || s>r){
     * return -1;
     * }
     * if(l>=s && r<=e){
     * return segTree[i];
     * }
     * int mid=l+ (r-l)/2;
     * long left=query(s, e, 2*i +1, l, mid);
     * long right=query(s, e, 2*i +2, mid+1, r);
     * if(left==-1) return right;
     * if(right==-1) return left;
     * return (left & right);
     * }
     */

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    /*
     * 
     * PS :
     * Small Observations:
     * 
     * 
     * Claims on Algo
     * 
     * 
     * 
     */

    static void solve() throws Exception {
        int n = fs.nextInt();
        String s = fs.next();
        long[] a = new long[n];
        long[] c = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
        }
        Arrays.fill(b, (long) -1e16);
        boolean[] known = new boolean[n];
        for (int i = 0; i < n; i++) {
            c[i] = fs.nextLong();
        }
        if (n == 1) {
            if (s.charAt(0) == '1') {
                if (a[0] != c[0]) {
                    System.out.println("No");
                    return;
                } else {
                    System.out.println("Yes");
                    System.out.println(a[0]);
                    return;
                }
            } else {
                System.out.println("Yes");
                System.out.println(c[0]);
                return;
            }
        }
        b[0] = c[0];
        known[0] = true;

        for (int i = 1; i < n; i++) {
            if (c[i] == c[i - 1])
                continue;
            b[i] = c[i];
            known[i] = true;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '1' && known[i - 1]) {
                b[i] = a[i] + b[i - 1];
            }
        }
        for (int i = n-1; i >0; i--) {
            if (s.charAt(i) == '1' && known[i]) {
                b[i-1] = b[i] - a[i];
                known[i-1]=true;
            }
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '1' && known[i - 1]) {
                b[i] = a[i] + b[i - 1];
            }
        }

        for (int i = 1; i < n; i++) {
            if (!known[i]) {
                if (s.charAt(i) == '1') {
                    b[i] = b[i - 1] + a[i];
                    known[i]=true;
                }
            }
        }

        boolean make = true;
        for (int i = 0; i < n - 1; i++) {
            if (c[i] > c[i + 1]) {
                make = false;
                break;
            }
        }
        long[] ccb = new long[n];
        ccb[0] = b[0];
        for (int i = 1; i < n; i++) {
            ccb[i] = Math.max(ccb[i - 1], b[i]);
        }
        for (int i = 0; i < n; i++) {
            if (c[i] != ccb[i]) {
                make = false;
                break;
            }
        }
        a[0] = s.charAt(0) == '1' ? a[0] : b[0];
        for (int i = 1; i < n; i++) {
                a[i] = b[i] - b[i - 1];
            
        }
        if (a[0] != b[0] || b[0] != c[0] || c[0] != a[0]) {
            make = false;
        }
        if (!make) {
            System.out.println("No");
            return;
        }
        System.out.println("Yes");
        for (long x : a) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    // nCr using factorial + modular inverse -> Works when MOD is prime (ex: 1e9+7)

    static final int MAX = 1000005;
    static final long MOD = 1_000_000_007;

    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    // Precompute factorials and inverse factorials
    static void precompute() {

        fact[0] = 1;

        for (int i = 1; i < MAX; i++)
            fact[i] = (fact[i - 1] * i) % MOD;

        invFact[MAX - 1] = modPow(fact[MAX - 1], MOD - 2);

        for (int i = MAX - 2; i >= 0; i--)
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
    }

    // Fast exponentiation
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

    // nCr calculation
    static long nCr(int n, int r) {

        if (r > n || r < 0)
            return 0;

        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
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