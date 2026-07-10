import java.io.*;
import java.util.*;

public class C_Palindrome_Basis {

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

    static List<Integer> pal = new ArrayList<>();
    static long[] dp;

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        for (int i = 1; i <= 9; i++) { // a,b,c...9
            pal.add(i);
        }
        for (int i = 11; i <= 99; i += 11) { // aa,bb,cc...99
            pal.add(i);
        }
        for (int a = 1; a <= 9; a++) { // aba--->a from 1 to 9 and b from 0 to 9...so this becomes a*100+b*10+a
            for (int b = 0; b <= 9; b++) {
                int num = a * 100 + b * 10 + a;
                pal.add(num);
            }
        }
        for (int a = 1; a <= 9; a++) { // abba->a*1000+b*100+b*10+a
            for (int b = 0; b <= 9; b++) {
                int num = a * 1000 + b * 100 + b * 10 + a;
                pal.add(num);
            }
        }
        for (int a = 1; a <= 9; a++) { //5 digit
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    int num = a * 10000 + b * 1000 + c * 100 + b * 10 + a;
                    if (num <= 40000)
                        pal.add(num);
                }
            }
        }

        int sum = 40000;
        dp = new long[sum + 1];
        dp[0] = 1;
        for (int x : pal) {
            for (int j = x; j <= sum; j++) {
                dp[j] = (dp[j] + dp[j - x]) % mod;
            }
        }
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    /*
     * 
     * PS :
     * Small Observations:
     * I will precompute all the palindromes in the range 1..10^4...which are
     * around 9+9+90+90=198
     * 
     * then just check how many subsets are there in this array for sum=n, and
     * return that
     * 
     * Claims on Algo
     * 
     * 
     * 
     */

    static long mod = (int) 1e9 + 7;

    static void solve() throws Exception {
        int n = fs.nextInt();
        System.out.println(dp[n]);

        // int s = pal.size();
        // long[][] dp = new long[s + 1][sum + 1];
        // for (int j = 0; j <= sum; j++) {
        // dp[0][j] = (j == 0 ? 1 : 0);
        // }

        // for (int i = 1; i <= s; i++) {
        // for (int j = 0; j <= sum; j++) {
        // if (j == 0) {
        // dp[i][j] = 1;
        // continue;
        // }
        // if (pal.get(i - 1) <= j) {
        // dp[i][j] = (dp[i][j - pal.get(i - 1)] + dp[i - 1][j]) % mod;
        // } else {
        // dp[i][j] = dp[i - 1][j] % mod;
        // }
        // }
        // }

        // System.out.println(dp[s][sum]);
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