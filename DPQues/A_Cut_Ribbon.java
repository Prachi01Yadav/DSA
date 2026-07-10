import java.io.*;
import java.util.*;

public class A_Cut_Ribbon {

    // -----Customize for each ques-----
    //
    // static long[] segTree;
    // static long[] arr;
    //
    // static void buildTree(int i,int l,int r){
    // if(l==r){
    // segTree[i]=arr[l];
    // return;
    // }
    // int mid= l + (r-l)/2;
    // buildTree(2*i +1,l,mid);
    // buildTree(2*i +2, mid+1,r);
    // segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
    // }
    //
    // static long query(int s,int e,int i,int l,int r){
    // if(e<l || s>r){
    // return -1;
    // }
    // if(l>=s && r<=e){
    // return segTree[i];
    // }
    // int mid=l+ (r-l)/2;
    // long left=query(s, e, 2*i +1, l, mid);
    // long right=query(s, e, 2*i +2, mid+1, r);
    // if(left==-1) return right;
    // if(right==-1) return left;
    // return (left & right);
    // }
    //
    //
    //
    public static void main(String[] args) throws Exception {
        solve();
    }

    static long[] dp;
    static int a, b, c;

    static void solve() throws Exception {
        int n = fs.nextInt();
        int a = fs.nextInt();
        int b = fs.nextInt();
        int c = fs.nextInt();

        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MIN_VALUE);

        dp[0] = 0; // base: 0 length → 0 pieces

        for (int i = 1; i <= n; i++) {
            if (i - a >= 0 && dp[i - a] != Long.MIN_VALUE)
                dp[i] = Math.max(dp[i], dp[i - a] + 1);

            if (i - b >= 0 && dp[i - b] != Long.MIN_VALUE)
                dp[i] = Math.max(dp[i], dp[i - b] + 1);

            if (i - c >= 0 && dp[i - c] != Long.MIN_VALUE)
                dp[i] = Math.max(dp[i], dp[i - c] + 1);
        }

        System.out.println(dp[n]);
    }

    // static void solve() throws Exception {
    // int n = fs.nextInt();
    // a = fs.nextInt();
    // b = fs.nextInt();
    // c = fs.nextInt();
    // dp=new long[n+1];
    // Arrays.fill(dp,-1);
    // dp = new long[n + 1][n + 1];
    // for (long[] d : dp) {
    // Arrays.fill(d, -1L);
    // }

    // long ans = rec(1, n);
    // rec(1,n);
    // System.out.println(dp[n]);

    // }

    // static long rec(int l, int r) {
    // int len=r-l+1;
    // if(dp[len]!=-1L){
    // return dp[len];
    // }

    // if (dp[l][r] != -1) {
    // return dp[l][r];
    // }
    // long ans = Long.MIN_VALUE;
    // if (len == a || len == b || len == c)
    // ans = 1;

    // for (int p = l ; p < r; p++) {
    // if (p - l + 1 == a || p - l + 1 == b || p - l + 1 == c) {
    // long left = rec(l, p);
    // long right = rec(p + 1, r);
    // if (left != Long.MIN_VALUE && right != Long.MIN_VALUE) {
    // ans = Math.max(ans, left + right);
    // }
    // }
    // }
    // return dp[len] = ans;
    // }

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