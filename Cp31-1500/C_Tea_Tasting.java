import java.io.*;
import java.util.*;

public class C_Tea_Tasting {

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
     * maintain two arrays: full tasting, partial tasting
     * use difference array technique to fill them: in constant time
     * then ans array will be calc as: ans[i]=fullTasting[i]*a[i]+
     * partialTasting[i];
     * 
     * 
     * Claims on Algo
     * 
     * 
     * 
     */

    static void solve() throws Exception {
        int n = fs.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = fs.nextLong();
        }
        long[] preSum = new long[n];
        preSum[0] = b[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + b[i];
        }

        int[] fullTasting = new int[n];
        long[] partialTasting = new long[n];

        long[] ans = new long[n];

        for (int i = 0; i < n; i++) {
            long el = a[i];
            int ub = BS(el, preSum, i);
            long prev = (i > 0) ? preSum[i - 1] : 0;
            long fullConsumed = (ub > i) ? preSum[ub - 1] - prev : 0;
            if (ub > i) {
                fullTasting[i] += 1;
                if (ub < n)
                    fullTasting[ub] -= 1;
            }
            if (ub < n) {
                partialTasting[ub] += a[i] - fullConsumed;
            }
        }

        int[] preFull = new int[n];
        preFull[0] = fullTasting[0];
        for (int i = 1; i < n; i++) {
            preFull[i] = preFull[i - 1] + fullTasting[i];
        }
        for (int i = 0; i < n; i++) {
            ans[i] = 1L * preFull[i] * b[i] + partialTasting[i];
        }
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
        // take pprefixsum of full tasting and partial tasting
        // then just do a[i]*full+partial for each i

    }

    static int BS(long el, long[] arr, int i) {
        long prev = (i > 0) ? arr[i - 1] : 0;
        if (arr[i] - prev > el)
            return i;

        int s = i, e = arr.length - 1;
        while (s < e) {
            int mid = s + (e - s + 1) / 2;
            if (arr[mid] - prev <= el)
                s = mid;
            else
                e = mid - 1;
        }
        return s + 1;
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