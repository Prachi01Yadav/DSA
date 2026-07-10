import java.io.*;
import java.util.*;

public class C_The_Values_You_Can_Make {

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
      
     * dp[j][x]--> outer subset of sum=j , inner subset of value x,
     * dp[j][x]=true if there is an outer subset summing j and inner subset summing x
     * 
     * 
     * So every coin has two nested decisions:
     * Pari: put in bag? → YES (j += c)
     * 
     * Arya: use from bag? → YES (x += c)
     * NO (x stays)
     * 
     * ---> "Pari fills the bag (outer subset),
     *      Arya robs the bag (inner subset),
     *      DP tracks both simultaneously,
     *      one coin at a time!"
     *      
     *      
     * ----> Look at dp[18][x]
     *       Every x that is TRUE means:
     *       "There EXISTS a way for Pari
     *       to pay exactly 18,
     *       such that Arya can make x
     *       from those coins!"
     * 
     * ----> When Pari picks up coin c, she thinks:
     *       "Should I put this in my payment bag?"
     *       If YES, Arya immediately thinks:
     *       "Should I use this coin
     *       to make my own sum?"
     * 
     */

    static void solve() throws Exception {
        int n = fs.nextInt();
        int k = fs.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = fs.nextInt();
        }

        boolean[][] dp = new boolean[k + 1][k + 1];

        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            for (int j = k - coin; j >= 0; j--) {
                for (int x = j; x >= 0; x--) {
                    if (dp[j][x]) {
                        dp[j + coin][x + coin] = true;
                        dp[j + coin][x] = true;
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int x = 0; x <= k; x++) {
            if (dp[k][x]) {
                ans.add(x);
            }
        }
        System.out.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();

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