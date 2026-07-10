import java.io.*;
import java.util.*;

public class BePositive {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        int[] arr=new int[n];
        int cntNeg=0;
        int cntZero=0;
        for(int i=0;i<n;i++){
            arr[i]=fs.nextInt();
            if(arr[i]==-1){
                cntNeg++;
            }
            if(arr[i]==0){
                cntZero++;
            }
        }
        
        if(cntZero==0){
            if(cntNeg%2==0){
            System.out.println(0);
            }else{
                System.out.println(2);
            }
        }else{
            if(cntNeg%2==0){
                System.out.println(cntZero);
            }else{
                System.out.println(cntZero+2);
            }
        }

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