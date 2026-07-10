import java.io.*;
import java.util.*;

public class PreparingOlympiad {

    public static void main(String[] args) throws Exception {
        
        
            solve();
        
        
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        long[] c=new long[n];
        long l=fs.nextLong();
        long r=fs.nextLong();
        long x=fs.nextLong();

        for(int i=0;i<n;i++){
            c[i]=fs.nextLong();
        }
        Arrays.sort(c);
        int cnt=0;
        for(int i=1;i<(1<<15);i++){
            int last = Integer.numberOfTrailingZeros(i);  
            int first = 31 - Integer.numberOfLeadingZeros(i);
            if(last>=n){
                break;
            }
            int sum=0;
            for(int k=0;k<n;k++){
                if((i & (1<<k))!=0){
                    sum+=c[k];
                }
            }

            if(sum>=l && sum<=r &&  Math.abs(c[first]-c[last]) >= x){
                cnt++;
            }
        }
        System.out.println(cnt);
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