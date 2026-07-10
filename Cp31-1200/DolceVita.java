import java.io.*;
import java.util.*;

public class DolceVita {

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
        long x=fs.nextLong();
        long[] arr=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=fs.nextLong();
        }
        Arrays.sort(arr);
        long[] preSum=new long[n];
        preSum[0]=arr[0];
        for(int i=1;i<n;i++){
            preSum[i]=preSum[i-1]+arr[i];
        }
        long ans=0;
        for(int i=0;i<n;i++){
            ans+=bs(preSum[i],i,x);
        }
        System.out.println(ans);
    }

    private static long bs(long sumUptoHere,int i,long x){
        long s=1;
        long e=1000000005;
        long ans=0;
        while(s<=e){
            long mid= s + (e-s)/2;
            if(isPossible(mid,sumUptoHere,i,x)){
                ans=mid;
                s=mid+1;
            }else{
                e=mid-1;
            }
        }
        return ans;
    }
    private static boolean isPossible(long days,long sumUptoHere,int i,long x){
        return (sumUptoHere + 1L * (days-1)*(i+1))<=x;
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}