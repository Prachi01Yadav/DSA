import java.io.*;
import java.util.*;

public class C_Alyona_and_Spreadsheet {

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

    static void solve() throws Exception {
        int n = fs.nextInt();
        int m = fs.nextInt();

        long[][] arr = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = fs.nextLong();
            }
        }
        // boolean[][] dp = new boolean[n][m];
        // for (int i = 0; i < m; i++) {
            // dp[n - 1][i] = true;
        // }

        int[][] good=new int[n][m]; //how many consecutive valid els ending at this row in jth column


        // for (int j = 0; j < m; j++) {
            // for (int i = n - 2; i >= 0; i--) {
                // if (arr[i][j] <= arr[i + 1][j]) {
                    // dp[i][j] = true;
                // } else {
                    // dp[i][j] = false;
                // }
            // }
        // }
        for (int j = 0; j < m; j++) {
            good[0][j]=1;
            for (int i =1 ; i <n; i++) {
                if (arr[i][j] >= arr[i - 1][j]) {
                    good[i][j]=1+good[i-1][j];
                } else {
                    good[i][j]=1;
                }
            }
        }
        int[] maxGood=new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maxGood[i]=Math.max(maxGood[i],good[i][j]);
            }
        }
        // for(int i=0;i<n;i++){
        // for(int j=0;j<m;j++){
        // System.out.print(dp[i][j]+" ");
        // }
        // System.out.println();
        // }
        // System.out.println();
        int k = fs.nextInt();
        while (k-- > 0) {
            int l = fs.nextInt();
            int r = fs.nextInt();
            if(l==r){
                System.out.println("Yes");
                continue;
            }
            l--;
            r--;
            int need=r-l+1;
            if (maxGood[r]>=need) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

        }
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