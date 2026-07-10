import java.io.*;
import java.util.*;

public class PetrAndCombinationLock {

    public static void main(String[] args) throws Exception {
       
       
            solve();
        
        
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=fs.nextInt();
        }
        boolean done=false;
        for(int i=1;i<=Math.pow(2,15);i++){
            
            int cnt=0;
            for(int k=0;k<n;k++){
                if((i & (1<<k))!=0){
                    cnt+=arr[k];
                }else{
                    cnt-=arr[k];
                }
            }
            if(cnt%360 ==0){
                done=true;
                break;
            }
        }
        if(done){
            System.out.println("YES");
        }else{
            System.out.println("NO");
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