import java.io.*;
import java.util.*;

public class Tape {

    public static void main(String[] args) throws Exception {
        
        
            solve();
       
       
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        long m=fs.nextLong();
        long k=fs.nextLong();

        long[] b=new long[n];
        for(int i=0;i<n;i++){
            b[i]=fs.nextLong();
        }

        long[] diff=new long[n];
        diff[0]=0;
        for(int i=1;i<n;i++){
            diff[i]=b[i]-b[i-1];
        }
        Arrays.sort(diff);
        int i=n-1;
        int cnt=0;
        while(k>1 && i>=0){
            k--;
            i--;
            cnt++;
        }
        long sum=0;
        for(int idx=0;idx<=i;idx++){
            sum+=diff[idx];
        }
        System.out.println(sum+cnt+1);
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