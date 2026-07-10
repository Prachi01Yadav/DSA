import java.io.*;
import java.util.*;

public class PaintTheArray {

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
        int n = fs.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }

        long gcd1=0;
        long gcd2=0;

        for (int i = 0; i < n; i += 2) {
            gcd1 = gcd(gcd1, arr[i]);
        }
        for (int i = 1; i < n; i += 2) {
            gcd2 = gcd(gcd2, arr[i]);
        }
        boolean poss1 = true;
        boolean poss2 = true;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 1) {
                if (arr[i] % gcd1 == 0) {
                    poss1 = false;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (arr[i] % gcd2 == 0) {
                    poss2 = false;
                    break;
                }
            }
        }
        if(poss1){
            System.out.println(gcd1);
        }else if(poss2){
            System.out.println(gcd2);
        }else{
            System.out.println(0);
        }
    }
    private static long gcd(long a,long b){
        if(b==0) return a;
        return gcd(b,a%b);
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}