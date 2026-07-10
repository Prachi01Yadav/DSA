import java.io.*;
import java.util.*;

public class DoraSearch {

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

    // ---------- SHORT PRINT FUNCTION ----------
    static void print(Object x) { out.append(x).append('\n'); }

    //------------Logic here-----------
    static void solve() throws Exception {
        int n=fs.nextInt();
        long[] arr=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=fs.nextLong();
        }

        int l=0;
        int r=n-1;
        long min=1;
        long max=n;

        long[] ans=new long[2];
        Arrays.fill(ans,-1);
        while(l<r){
            if(arr[l] != min && arr[l] != max && arr[r] != min && arr[r] != max){
                ans[0]=l+1;
                ans[1]=r+1;
                break;
            }

            if(arr[l]==min){
                min++;
                l++;
            }else if(arr[r]==min){
                min++;
                r--;
            }else if(arr[l]==max){
                max--;
                l++;
            }else if(arr[r]==max){
                max--;
                r--;
            }
        }
        if(ans[0]==-1 && ans[1]==-1){
            System.out.println(-1);
        }else{
            System.out.println(ans[0]+" "+ans[1]);
        }
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}