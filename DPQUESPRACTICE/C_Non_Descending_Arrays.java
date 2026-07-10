package DPQUESPRACTICE;
import java.io.*;
import java.util.*;

public class C_Non_Descending_Arrays {

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

    static int[] a;
    static int[] b;
    static int n;
    static long[][] dp;

    static void solve() throws Exception {
        n = fs.nextInt();
        a = new int[n + 1];
        b = new int[n + 1];
        dp = new long[n + 1][2];
        for (long[] d : dp)
            Arrays.fill(d, -1);

        for (int i = 1; i <= n; i++)
            a[i] = fs.nextInt();
        for (int i = 1; i <= n; i++)
            b[i] = fs.nextInt();

        out.append(rec(0, 0, 1)).append('\n');
    }

    static long rec(int prevA, int prevB, int i) {
        if (i > n)
            return 1;

        int choice = (i == 1) ? 0 : (prevA == a[i - 1] ? 0 : 1);

        if (dp[i][choice] != -1)
            return dp[i][choice];

        long ans = 0;
        if (a[i] >= prevA && b[i] >= prevB)
            ans = rec(a[i], b[i], i + 1) % MOD;
        if (b[i] >= prevA && a[i] >= prevB)
            ans = (ans + rec(b[i], a[i], i + 1)) % MOD;

        return dp[i][choice] = ans;
    }
    // static void solve() throws Exception {
    // n = fs.nextInt();
    // a = new int[n+1];
    // b = new int[n + 1];
    // dp=new long[10001][10001][n+1];
    // for(long[][] d:dp){
    // for(long[] t:d){
    // Arrays.fill(t,-1);
    // }
    // }

    // for (int i = 1; i <= n; i++) {
    // a[i] = fs.nextInt();
    // }
    // for (int i = 1; i <= n; i++) {
    // b[i] = fs.nextInt();
    // }

    // long ans=rec(0,0,1);
    // System.out.println(ans);

    // }
    // static long rec(int prevA,int prevB,int i){
    // if(i>n) return 1;

    // if(dp[prevA][prevB][i]!=-1){
    // return dp[prevA][prevB][i];
    // }
    // long ans=0;
    // if(a[i]>=prevA && b[i]>=prevB){
    // ans=(rec(a[i], b[i], i+1))%MOD;
    // }
    // if(prevB<=a[i] && prevA<=b[i]){
    // ans=(ans+rec(b[i],a[i],i+1))%MOD;
    // }
    // return dp[prevA][prevB][i]= ans;
    // }

    // nCr using factorial + modular inverse -> Works when MOD is prime (ex: 1e9+7)

    static final int MAX = 1000005;
    static final long MOD = 998244353;

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