import java.io.*;
import java.util.*;

public class C_Woodcutters {

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
     * -->
     * 
     * 
     * Claims on Algo
     * 
     * 
     * 
     */

    static void solve() throws Exception {
        int n = fs.nextInt();
        List<long[]> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long x = fs.nextLong();
            long h = fs.nextLong();
            ls.add(new long[] { x, h });
        }
        Collections.sort(ls, (u, v) -> Long.compare(u[0], v[0]));
        if (n == 1) {
            System.out.println(1);
            return;
        }
        long[] pair = ls.get(0);
        long lastpos = pair[0];
        long ans = 2;
        for (int i = 1; i < n - 1; i++) {
            long[] p = ls.get(i);
            long[] next = ls.get(i + 1);
            if (p[0] - p[1] > lastpos) {
                ans++;
                lastpos = p[0];
            } else if (p[0] + p[1] < next[0]) {
                ans++;
                lastpos = p[0] + p[1];
            } else {
                lastpos = p[0];
            }
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