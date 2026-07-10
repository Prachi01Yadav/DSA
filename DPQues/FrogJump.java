import java.io.*;
import java.util.*;

public class FrogJump {

//-----Customize for each ques-----

    static long[] segTree;
    static long[] arr;

    static void buildTree(int i,int l,int r){
        if(l==r){
            segTree[i]=arr[l];
            return;
        }
        int mid= l + (r-l)/2;
        buildTree(2*i +1,l,mid);
        buildTree(2*i +2, mid+1,r);
        segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
    }

    static long query(int s,int e,int i,int l,int r){
        if(e<l || s>r){
            return -1;
        }
        if(l>=s && r<=e){
            return segTree[i];
        }
        int mid=l+ (r-l)/2;
        long left=query(s, e, 2*i +1, l, mid);
        long right=query(s, e, 2*i +2, mid+1, r);
        if(left==-1) return right;
        if(right==-1) return left;
        return (left & right);
    }


    static int n;
    public static void main(String[] args) throws Exception {
            solve();
    }
    static int k;
    static int[] h;
    static int[] dp;
    static void solve() throws Exception {
        n=fs.nextInt();
        k=fs.nextInt();
        h=new int[n+1];
        dp=new int[n+1];
        Arrays.fill(dp,-1);
        for(int i=1;i<=n;i++){
            h[i]=fs.nextInt();
        }

        // dp[n]=0;
        // for(int i=n-1;i>=0;i--){
            // dp[i]=Integer.MAX_VALUE;
        //    for(int j = i+1; j <= Math.min(i+k, n); j++){
            // int cost=Math.abs(h[j]-h[i]) + dp[j];
            // dp[i]=Math.min(dp[i],cost);
        //   }
        // }
        // System.out.println(dp[1]);

        int ans=rec(1);
        System.out.println(ans);
    }
    static int rec(int level){ //minm cost from level....n-1
        // pruning
        // basecase
        if(level>=n) return 0;
        // cache check
        if(dp[level]!=-1){
            return dp[level];
        }
        // compute
        int cost=0;
        int ans=Integer.MAX_VALUE;
        for(int step=1;step<=k;step++){
            if(step+level<=n){
                 cost=Math.abs(h[step+level]-h[level]) + rec(step+level);
                 ans=Math.min(ans,cost);
            }
        }
        // save and return
        return dp[level]=ans;
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