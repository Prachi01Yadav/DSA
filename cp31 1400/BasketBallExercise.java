import java.io.*;
import java.util.*;

public class BasketBallExercise {

    // -----Customize for each ques-----

    static long[] segTree;
    static long[] arr;

    static void buildTree(int i, int l, int r) {
        if (l == r) {
            segTree[i] = arr[l];
            return;
        }
        int mid = l + (r - l) / 2;
        buildTree(2 * i + 1, l, mid);
        buildTree(2 * i + 2, mid + 1, r);
        segTree[i] = (segTree[2 * i + 1] & segTree[2 * i + 2]);
    }

    static long query(int s, int e, int i, int l, int r) {
        if (e < l || s > r) {
            return -1;
        }
        if (l >= s && r <= e) {
            return segTree[i];
        }
        int mid = l + (r - l) / 2;
        long left = query(s, e, 2 * i + 1, l, mid);
        long right = query(s, e, 2 * i + 2, mid + 1, r);
        if (left == -1)
            return right;
        if (right == -1)
            return left;
        return (left & right);
    }

    public static void main(String[] args) throws Exception {
            solve();
    }
    static int n;

    static void solve() throws Exception {
        int n=fs.nextInt();
        long[] h1=new long[n];
        long[] h2=new long[n];
        for(int i=0;i<n;i++){
            h1[i]=fs.nextLong();
        }
        for(int i=0;i<n;i++){
            h2[i]=fs.nextLong();
        }

        long best1=0;
        long best2=0;

        long ans=0;

        for(int i=0;i<n;i++){
            long dp1 = best2+h1[i]; //Abhi tak jo 2nd row tak MaxSum tha usme first row ka ith element bhi lelo
            long dp2 = best1 + h2[i];  //Abhi tak jo 1st row ka maximum sum tha usme 2nd row ka ith element lelo

            best1 = Math.max(best1, dp1); 
            best2 = Math.max(best2, dp2); 

            ans=Math.max(ans,Math.max(dp1,dp2));
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