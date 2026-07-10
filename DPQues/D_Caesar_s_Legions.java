import java.io.*;
import java.util.*;

public class D_Caesar_s_Legions {


//-----Customize for each ques-----

 /*   static long[] segTree;
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
    }*/ 




    public static void main(String[] args) throws Exception {
        
            solve();
    }


/*

PS : 
 Small Observations: 


Claims on Algo



*/
    static int MOD=100000000;
    static int[][][][] dp;
    static void solve() throws Exception {
        int n1=fs.nextInt();
        int n2=fs.nextInt();
        k1=fs.nextInt();
        k2=fs.nextInt();
        dp=new int[n1+1][n2+1][k1+1][k2+1];
        for(int[][][] k:dp){
            for(int[][] t:k){
                for(int[] l:t){
                    Arrays.fill(l,-1);
                }
            }
        }
        int ans=rec(n1,n2,0,0);
        System.out.println(ans);

    }
    static int k1,k2;
    static int rec(int remainF,int remainH,int consF,int consH){
       
        if(remainF==0 && remainH==0){
           return 1;
        }
        if(dp[remainF][remainH][consF][consH]!=-1){
            return dp[remainF][remainH][consF][consH];
        }
        int ways=0;

        if(remainF > 0 && consF+1 <= k1){
            ways=(ways+rec(remainF-1,remainH,consF+1,0))%MOD;
        }
        if(remainH>0 && consH+1<=k2){
            ways=(ways + rec(remainF, remainH-1, 0, consH+1))%MOD;
        }
        return dp[remainF][remainH][consF][consH]= ways;

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