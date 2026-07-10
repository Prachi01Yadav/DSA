import java.io.*;
import java.util.*;

public class C_Mortal_Kombat_Tower {


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
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }


/*

PS : 
 Small Observations: 


Claims on Algo



*/

    static void solve() throws Exception {
        n=fs.nextInt();
        a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=fs.nextInt();
        }
        dp=new int[n][2];
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        int ans=rec(0,1);
        System.out.println(ans);

    }
    static int[][] dp;
    static int n;
    static int[] a;
    static int rec(int i,int turn){
        if(i>=n) return 0;
        if(dp[i][turn]!=-1){
            return dp[i][turn];
        }
        int kill1=Integer.MAX_VALUE;
        int kill2=Integer.MAX_VALUE;
        if(turn==0){
             kill1=rec(i+1,1-turn);
             kill2=rec(i+2,1-turn);
            return dp[i][turn]=Math.min(kill1,kill2);
        }
        if(turn==1){
            //kill 1
            if(a[i]==0){
                kill1=rec(i+1,1-turn);
            }else{
                kill1=rec(i+1,1-turn)+1;
            }
            //kill 2
            if(i+1<n && ((a[i]==1 && a[i+1]==0)|| (a[i]==0 && a[i+1]==1))){
                kill2=rec(i+2,1-turn)+1;
            }else if(i+1<n && a[i]==0 && a[i+1]==0){
                kill2=rec(i+2,1-turn);
            }else if(i+1<n && a[i]==1 && a[i+1]==1){
                kill2=rec(i+2,1-turn)+2;
            }
        }
        return dp[i][turn]=Math.min(kill1,kill2);
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