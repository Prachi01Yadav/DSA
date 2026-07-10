import java.io.*;
import java.util.*;

public class A_Boredom {

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



    public static void main(String[] args) throws Exception {
            solve();
    }


    static void solve() throws Exception {
        int n=fs.nextInt();
        int[] arr=new int[n];
        int max=1;
        for(int i=0;i<n;i++){
            arr[i]=fs.nextInt();
            max=Math.max(max,arr[i]);
        }
        long[] cnt=new long[max+1];
        for(int i=0;i<n;i++){
            cnt[arr[i]]++;
        }
        long[] dp=new long[max+1];
        dp[0]=0;
        if(max>=1){
          dp[1]=1L*cnt[1]*1;
        }
        for(int i=2;i<=max;i++){
            dp[i]=Math.max(dp[i-1],1L*cnt[i]*i+dp[i-2]);
        }
        for(int i=0;i<=max;i++){
            System.out.print(dp[i]+" ");
        }
        System.out.println();
        System.out.println("ans"+ dp[max]);
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