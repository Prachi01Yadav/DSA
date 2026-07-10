import java.io.*;
import java.util.*;

public class IvaAndPav {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
    static long[] segTree;
    static long[] arr;

    static void solve() throws Exception {
        int n=fs.nextInt();
        segTree=new long[4*n];
        arr=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=fs.nextLong();
        }
        int q=fs.nextInt();

        buildTree(0, 0, n-1);

        while(q-- > 0){
            int l=fs.nextInt()-1;
            long k=fs.nextLong();

            int low=l;
            int high=n-1;
            int ans=-1;
            while(low<=high){
                int m = low + (high-low)/2;
                long val=query(l, m, 0,0, n-1);
                if(val>=k){
                    ans=m;
                    low=m+1;
                }else{
                    high=m-1;
                }
            }
            System.out.print((ans==-1?-1:ans+1) + " ");
        }
        System.out.println();
    }
    
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