import java.io.*;
import java.util.*;

public class StrangeBirthdayParty {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        int m=fs.nextInt();

        int[] k=new int[n];
        for(int i=0;i<n;i++){
            k[i]=fs.nextInt();
        }

        long[] c=new long[m];
        for(int i=0;i<m;i++){
            c[i]=fs.nextLong();
        }

        Arrays.sort(k);

        int i=n-1; // friends array that is k mein traverse kr rha from end
        long ans=0;
        int j=0; //cost array mein traverse kr rha from start
        while(i>=0){
            if(j<m && c[j]<= c[k[i]-1]){ 
                ans+=c[j];
                j++;
            }else{
                ans+=c[k[i]-1];
            }
            i--;
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