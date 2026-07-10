import java.io.*;
import java.util.*;

public class NumProductsArray {

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
        long[] arr=new long[n];

        for(int i=0;i<n;i++){
            arr[i]=fs.nextLong();
        }

        long[][] dp=new long[2][n];
        dp[0][0]=arr[0] > 0 ? 1:0;
        dp[1][0]=arr[0] <0 ? 1:0;

        for(int i=1;i<n;i++){
            if(arr[i]>0){
                dp[0][i]=1+dp[0][i-1];
                dp[1][i]=dp[1][i-1];
            }else{
                dp[0][i]=dp[1][i-1];
                dp[1][i]=1+dp[0][i-1];
            }
        }
        long sumPos=0;
        long sumNeg=0;
        for(int i=0;i<n;i++){
            sumPos+=dp[0][i];
            sumNeg+=dp[1][i];
        }

        System.out.println(sumNeg + " "+sumPos);
    }


    public static void main(String[] args) throws Exception {
        
        
            solve();
       
       
    }
}