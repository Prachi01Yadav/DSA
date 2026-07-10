import java.io.*;
import java.util.*;

public class B_Mashmokh_and_ACM {

//-----Customize for each ques-----

    // static long[] segTree;
    // static long[] arr;

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


    /* 

      Small observations:

        1. dp[i][j] = Number of good sequences of length i and last element j.
        2.  dp[i][j] = sum of dp[i-1][k] for all divisiors of j--> k1,k2,k3,k4..
        3.  We can precompute the divisors of all numbers from 1 to m and then use that to fill our dp table.
    
    */

    public static void main(String[] args) throws Exception {
        // int t = fs.nextInt();
        // while (t-- > 0) {
            solve();
        // }
        // System.out.print(out);
    }

    static int mod= (int)1e9 +7;
    static void solve() throws Exception {
        int n=fs.nextInt();
        int k=fs.nextInt();
        
        int[][] dp= new int[n+1][k+1];
        int[] divisors= new int[k+1];
        for(int i=1;i<=k;i++){
            for(int j=i;j<=k;j+=i){
                divisors[j]++;
            }
        }
        for(int i=1;i<=k;i++){
            dp[1][i]=1;
        }
        for(int i=2;i<=n;i++){
            for(int j=1;j<=divisors.length-1;j++){
                for(int d=1;d*j<=k;d++){
                    dp[i][j]=(dp[i][j]+dp[i-1][d*j])%mod;
                }
            }
        }
        int ans=0;
        for(int i=1;i<=k;i++){
            ans=(ans+dp[n][i])%mod;
        }
        out.append(ans).append("\n");
    }



/*-------------For Fast I/O --------------------------*/

    private static final class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            boolean neg = false; if (c == '-') { neg = true; c = readByte(); }
            long val = 0;
            while (c > ' ') { val = val * 10 + (c - '0'); c = readByte(); }
            return neg ? -val : val;
        }

        int nextInt() throws IOException { return (int) nextLong(); }
        String next() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') { sb.append((char)c); c = readByte(); }
            return sb.toString();
        }
    }

    static final long INF = (long) 1e18;
    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder();

}