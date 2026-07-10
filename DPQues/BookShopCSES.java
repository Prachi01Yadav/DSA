import java.io.*;
import java.util.*;

public class BookShopCSES {

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
     * TC: O(n*x)
     * SC: O(n*x)
     * 
     * Claims on Algo
     * 
     * 
     * 
     */

    static void solve() throws Exception {

        int n = fs.nextInt();
        int x = fs.nextInt();

        int[] price = new int[n];
        int[] pages = new int[n];

        for (int i = 0; i < n; i++)
            price[i] = fs.nextInt();
        for (int i = 0; i < n; i++)
            pages[i] = fs.nextInt();

        long[] dp = new long[x + 1];

        for (int i = 0; i < n; i++) {
            for (int j = x; j >= price[i]; j--) {
                dp[j] = Math.max(dp[j], pages[i] + dp[j - price[i]]);
            }
        }

        System.out.println(dp[x]);
    }

    // static void solve() throws Exception {
    // int n = fs.nextInt();
    // int x = fs.nextInt();
    // int[] price = new int[n];
    // int[] pages = new int[n];

    // for (int i = 0; i < n; i++) {
    // price[i] = fs.nextInt();
    // }
    // for (int i = 0; i < n; i++) {
    // pages[i] = fs.nextInt();
    // }

    // long[][] dp=new long[n+1][x+1]; //OG knapsack when W is small
    // long[][] dp = new long[n + 1][Vmax + 1]; // W is big biggie boyyy
    // dpc[i][j]---> minimum price req to make these Vmax pages using first i items
    // for(long[] arr:dp){
    // Arrays.fill(arr,Long.MAX_VALUE);
    // }
    // for(int i=0;i<=Vmax;i++){
    // dp[0][i]=Long.MAX_VALUE;
    // }
    // dp[0][0]=0;
    // for (int i = 1; i <= n; i++) {
    // for (int j = 1; j <= x; j++) {
    // if (price[i - 1] <= j) {
    // dp[i][j] = Math.max(dp[i - 1][j], pages[i - 1] + dp[i - 1][j - price[i -
    // 1]]);
    // } else {
    // dp[i][j] = dp[i - 1][j];
    // }
    // }
    // }

    // for (int i = 1; i <= n; i++) {
    // for (int j = 1; j <= Vmax; j++) {
    // if (pages[i - 1] <= j && dp[i-1][j-pages[i-1]]!=Long.MAX_VALUE) {
    // dp[i][j] = Math.min(dp[i - 1][j], price[i - 1] + dp[i - 1][j - pages[i -
    // 1]]);
    // } else {
    // dp[i][j] = dp[i - 1][j];
    // }
    // }
    // }
    // long ans=0;
    // for(int i=Vmax;i>=0;i--){
    // if(dp[n][i]<=x){
    // ans=i;
    // break;
    // }
    // }
    // System.out.println(ans);
    // System.out.println(dp[n][x]);
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