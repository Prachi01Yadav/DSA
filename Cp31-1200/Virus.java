import java.io.*;
import java.util.*;

public class Virus {

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

/*-------------Logic Here--------------------------*/

    static void solve() throws Exception {
        int n=fs.nextInt();
        int m=fs.nextInt();

        long[] infec=new long[m];
        for(int i=0;i<m;i++){
            infec[i]=fs.nextLong();
        }
       Arrays.sort(infec);

        ArrayList<Long> seg=new ArrayList<>();

        for(int i=0;i<m-1;i++){
            seg.add(Math.abs(infec[i]-infec[i+1] )-1 );
        }
        seg.add(Math.abs(n - infec[m-1]) + Math.abs(infec[0]-1));
        
        Collections.sort(seg,Collections.reverseOrder());
        
        long days=0;
        long saved=0;

        for(long g:seg){
            long currentSeg= g - 2*days;

            if(currentSeg<=0) continue;

            if(currentSeg==1 || currentSeg==2){
                saved+=1;
                days+=1;
            }else{
                saved += currentSeg-1;
                days+=2;
            }

        }
        System.out.println(n-saved);
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}