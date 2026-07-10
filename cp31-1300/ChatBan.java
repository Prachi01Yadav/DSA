import java.io.*;
import java.util.*;

public class ChatBan {

    private static final class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = readByte();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    static final long INF = (long) 1e18;
    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder();

    /*-------------Logic Here--------------------------*/

    static void solve() throws Exception {
        long k = fs.nextLong();
        long x = fs.nextLong();

        long n = 2 * k - 1;

        if(k==1){
            System.out.println(1);
            return;
        }
        long l = 1;
        long r = n;
        long ans = n;
        while (l <= r) {
            long mid = l + (r - l) / 2;

            if (isPossible(mid, x, n, k)) {
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        System.out.println(ans);

    }

    private static boolean isPossible(long mid, long x, long n, long k) {
        long sum=0;
        if(mid>k){
            long sum1= k * (k+1)/2;
            long rem=mid-k;
            long lastrow=k-1-rem;
            long sum2 = k* (k-1)/2;
            long exSum= lastrow * (lastrow+1)/2;
            sum= sum1 + sum2 -exSum;
        }else{
            sum= mid * (mid+1)/2;
        }
        return sum<x;
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}