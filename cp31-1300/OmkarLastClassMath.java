import java.io.*;
import java.util.*;

public class OmkarLastClassMath {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        long n = fs.nextLong();
        long largestDiv=1;
        for(int i=2;i*i<=n;i++){
            if(n%i==0){
                largestDiv=Math.max(largestDiv,n/i);
            }
        }
        if(largestDiv==1){
            System.out.println(1+" "+(n-1));
            return;
        }
        long a=largestDiv;
        long b=n-largestDiv;
        System.out.println(a+" "+b);
    }
// 
        // if (n % 2 == 1) {
            // long oddN = n;
            // long i = 1;
            // long j = oddN - 1;
            // long minLcm = Long.MAX_VALUE;
            // long[] ans = new long[2];
            // while (i < j) {
                // long g = gcd(i, j);
                // long lcm = (i * j) / g;
                // if (lcm < minLcm) {
                    // ans[0] = i;
                    // ans[1] = j;
                    // minLcm = lcm;
                // }
                // i++;
                // j--;
            // }
            // System.out.println(ans[0]+" " +ans[1]);
        // } else {
            // long evenN = n;
            // long i = 1;
            // long j = evenN - 1;
            // long[] ans = new long[2];
            // long minLcm = Long.MAX_VALUE;
            // while (i <= j) {
                // long g = gcd(i, j);
                // long lcm = (i * j) / g;
                // if (lcm < minLcm) {
                    // ans[0] = i;
                    // ans[1] = j;
                    // minLcm = lcm;
                // }
                // i++;
                // j--;
            // }
            // System.out.println(ans[0] + " " + ans[1]);
        // }
    // }
// 
    // private static long gcd(long a, long b) {
        // if (b == 0)
            // return a;
        // return gcd(b, a % b);
    // }

    /*-------------For Fast I/O --------------------------*/

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

}