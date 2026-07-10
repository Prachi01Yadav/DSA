import java.io.*;
import java.util.*;

public class HGridEduDpContest {

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
     * 
     * 
     * Claims on Algo
     * 
     * 
     * 
     */
    static final long MOD = 1_000_000_007;

    static void solve() throws Exception {
        int n = fs.nextInt();
        int m = fs.nextInt();

        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            String row = fs.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j);
            }
        }
        if (grid[0][0] == '#' || grid[n - 1][m - 1] == '#') {
            System.out.println(0);
            return;
        }
        long[][] dp = new long[n][m];
        for (int i = 0; i < m; i++) {
            if (grid[0][i] == '#') {
                for (int j = i; j < m; j++) {
                    dp[0][j] = 0;
                }
                break;
            } else {
                dp[0][i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == '#') {
                for (int j = i; j < n; j++) {
                    dp[j][0] = 0;
                }
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        dp[0][0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] != '#') {
                    dp[i][j] = ( dp[i - 1][j]  + dp[i][j - 1] )%MOD;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        System.out.println(dp[n - 1][m - 1]);

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

        String nextLine() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while (true) {
                c = readByte();
                if (c == -1 || c == '\n')
                    break;
                if (c != '\r')
                    sb.append((char) c);
            }
            return sb.toString();
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