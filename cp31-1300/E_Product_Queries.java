import java.io.*;
import java.util.*;

public class E_Product_Queries {

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

    static List<Long> fact(int x){
        List<Long> res=new ArrayList<>();
        for(long i=1;i*i<=x;i++){
            if(x%i==0){
                if(i!=1) res.add(i);
                if(x/i != i) res.add(x/i);
            }
        }
        return res;
    }


    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        long[] exists=new long[n+1];
        for(int i=0;i<n;i++){
            long el=fs.nextLong();
            exists[(int)el]=1;
        }

        long[] dp=new long[n+1];
        Arrays.fill(dp,Long.MAX_VALUE/10);

        for(int i=1;i<=n;i++){
            if(exists[i]==1){
                dp[i]=1;
                continue;
            }
            List<Long> factors=fact(i);
            for(long x:factors){
                if(exists[(int)x]==1){
                dp[i]=Math.min(dp[i],1+dp[(int)(i/x)]);
                }
            }
        }
        for(int i=1;i<=n;i++){
            if(dp[i]==Long.MAX_VALUE/10){
                System.out.print(-1 + " ");
            }else{
                System.out.print(dp[i]+" ");
            }
        }
        System.out.println();
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