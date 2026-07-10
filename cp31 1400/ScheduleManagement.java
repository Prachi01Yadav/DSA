import java.io.*;
import java.util.*;

public class ScheduleManagement {

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
        int[] count=new int[n+1];
        int[] arr=new int[m];
        for(int i=0;i<m;i++){
            arr[i]=fs.nextInt();
            count[arr[i]]++;
        }
        long ans=1;
        long s=1;
        long e=2L*m;

        while(s<=e){
            long mid=s+ (e-s)/2;

            if(isPossible(mid,count,n)){
                ans=mid;
                e=mid-1;
            }else{
                s=mid+1;
            }
        }
        System.out.println(ans);
    }

    private static boolean isPossible(long minT,int[] count,int n){
        long extraHrs=0;
        long kamHours=0;
        for(int i=1;i<=n;i++){
            if(count[i]>minT){
                kamHours+=(count[i]-minT);
            }else{
                extraHrs+=(minT - count[i])/2;
            }
        }
        return extraHrs>=kamHours;
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}