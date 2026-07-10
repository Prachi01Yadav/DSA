import java.io.*;
import java.util.*;

public class WowFactor {

    public static void main(String[] args) throws Exception {
     
      
            solve();
       
       
    }

    static void solve() throws Exception {
        String s=fs.next();

        int n=s.length();
        long[] pref=new long[n];
        long[] suff=new long[n];

        for(int i=1;i<n;i++){
            pref[i]=pref[i-1];
            if(s.charAt(i)=='v' && s.charAt(i-1)=='v'){
                pref[i]++;
            }
        }

        for(int j=n-2;j>=0;j--){
            suff[j]=suff[j+1];
            if(s.charAt(j)=='v' && s.charAt(j+1)=='v'){
                suff[j]++;
            }
        }
        long ans=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='o'){
                ans+=(pref[i]*suff[i]);
            }
        }
        System.out.println(ans);

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