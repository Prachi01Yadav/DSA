import java.io.*;
import java.util.*;

public class D_Red_Green_Towers {

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
        solve();
    }

    /*
     * 
     * PS :
     * Small Observations:
     * 1. Have to use all red and green blocks...
     * Let h levels we made using all these blocks..at each level we need i blocks
     * (0<=i<=h)
     * r+g=1+2+3....+h
     * r+g=h(h+1)/2 for some integer h. , let say it's some integer S.
     * 
     * if no int h exits, ans is 0 , No valid blocks can be formed
     * else ans is count ways to make whatever this is, using exactly r red and g
     * green blocks
     * 
     * dp[level][redUsed]--> ways to make 0....level thing with using exactly
     * redUsed red blocks
     * 
     * final ans--> Summation over all dp[h][j] where j<=r and S-j<=g
     * 
     * Claims on Algo
     * 
     * 
     * 
     */

    // static void solve() throws Exception {
    // int r = fs.nextInt();
    // int g = fs.nextInt();

    // int h = 1;
    // while ((h * (h + 1) / 2) <= (r + g)) {
    // h++;
    // }
    // h--;
    // int s = h * (h + 1) / 2;

    // long[][] dp = new long[h + 1][r + 1];
    // for (int i = 0; i <= r; i++) {
    // dp[0][i] = 0;
    // }
    // dp[0][0] = 1;
    // for (int i = 1; i <= h; i++) {
    // for (int j = 0; j <= r; j++) {
    // dp[i][j] =(dp[i][j]+ dp[i - 1][j])%MOD;
    // if (j - i >= 0) {
    // dp[i][j] = (dp[i][j]+ dp[i - 1][j - i])%MOD;
    // }
    // }
    // }
    // long sum = 0;
    // for (int j = 0; j <= r; j++) {
    // if (j <= r && s - j <= g) {
    // sum = (sum+dp[h][j])%MOD;
    // }
    // }
    // for (int i = 0; i <= h; i++) {
    // for (int j = 0; j <= r; j++) {
    // System.out.print(dp[i][j] + " ");
    // }
    // System.out.println();
    // }
    //
    // System.out.println(sum);
    // }

    // static final long MOD = 1_000_000_007L;
    // static void solve() throws Exception {
    // int r = fs.nextInt();
    // int g = fs.nextInt();

    // int h = 1;
    // while (h * (h + 1) / 2 <= r + g)
    // h++;
    // h--;

    // long[] dp = new long[r + 1];
    // dp[0] = 1;

    // for (int i = 1; i <= h; i++) {
    // for (int j = r; j >= i; j--) {
    // dp[j] =(dp[j]+ dp[j - i])%MOD;
    // }
    // }

    // long sum = 0;
    // int totalBlocks = h * (h + 1) / 2;
    // for (int j = 0; j <= r; j++) {
    // int greenUsed = totalBlocks - j;
    // if (greenUsed <= g) {
    // sum = (sum+dp[j])%MOD;
    // }
    // }

    // System.out.println(sum);
    //

    static void solve() throws Exception {
        int r = fs.nextInt();
        int g = fs.nextInt();

        int h = 1;
        while ((h * (h + 1) / 2) <= (r + g)) {
            h++;
        }
        h--; // max poss height
        int totalBlocks = h * (h + 1) / 2;

        //dp[j] = numb. of ways to use exactly j red blocks
        int[] dp = new int[r + 1];
        final int MOD = 1_000_000_007;
        dp[0] = 1;

        for (int i = 1; i <= h; i++) {
            int maxJ = Math.min(r, totalBlocks);
            for (int j = maxJ; j >= i; j--) {
                dp[j] += dp[j - i];
                if (dp[j] >= MOD) {
                    dp[j] -= MOD;
                }
            }
        }

        int ans = 0;
        for (int j = 0; j <= r; j++) {
            int greenUsed = totalBlocks - j;
            if (greenUsed <= g) {
                ans += dp[j];
                if (ans >= MOD) {
                    ans -= MOD;
                }
            }
        }

        System.out.println(ans);
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