import java.io.*;
import java.util.*;

public class B_Working_out {

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

        int[][] a = new int[n + 2][m + 2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = fs.nextInt();
            }
        }

        long[][] dp1 = new long[n + 2][m + 2];
        long[][] dp2 = new long[n + 2][m + 2];
        long[][] dp3 = new long[n + 2][m + 2];
        long[][] dp4 = new long[n + 2][m + 2];

        // From (1,1)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp1[i][j] = Math.max(dp1[i - 1][j], dp1[i][j - 1]) + a[i][j];
            }
        }

        // From (n,m)
        for (int i = n; i >= 1; i--) {
            for (int j = m; j >= 1; j--) {
                dp2[i][j] = Math.max(dp2[i + 1][j], dp2[i][j + 1]) + a[i][j];
            }
        }

        // From (n,1)
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= m; j++) {
                dp3[i][j] = Math.max(dp3[i + 1][j], dp3[i][j - 1]) + a[i][j];
            }
        }

        // From (1,m)
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                dp4[i][j] = Math.max(dp4[i - 1][j], dp4[i][j + 1]) + a[i][j];
            }
        }

        long ans = 0;

        for (int i = 2; i < n; i++) {
            for (int j = 2; j < m; j++) {

                long case1 = dp1[i][j - 1] +
                        dp2[i][j + 1] +
                        dp3[i + 1][j] +
                        dp4[i - 1][j];

                long case2 = dp1[i - 1][j] +
                        dp2[i + 1][j] +
                        dp3[i][j - 1] +
                        dp4[i][j + 1];

                ans = Math.max(ans, Math.max(case1, case2));
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