import java.io.*;
import java.util.*;

public class JumpingSegments {

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
        
        long[][] arr=new long[n][2];
        long max=Long.MIN_VALUE;
        for(int i=0;i<n;i++){
            arr[i][0]=fs.nextLong();
            arr[i][1]=fs.nextLong();
            max=Math.max(max,arr[i][1]);
        }

        long s=0;
        long e=max;

        long ans=0;
        while(s<=e){
            long mid= s+ (e-s)/2;

            if(isPossible(mid,arr,n)){
                ans=mid;
                e=mid-1;
            }else{
                s=mid+1;
            }
        }
        System.out.println(ans);
        
    }

    private static boolean isPossible(long k,long[][] arr,int n){
        long l=0;
        long r=0;

        for(int i=0;i<n;i++){
            long l1=arr[i][0];
            long r1=arr[i][1];

            l-=k;
            r+=k;

            l=Math.max(l1,l);
            r=Math.min(r1,r);

            if(l>r) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}