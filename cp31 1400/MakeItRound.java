import java.io.*;
import java.util.*;

public class MakeItRound {

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
        long n = fs.nextLong();
        long m = fs.nextLong();

        int c2 = 0;
        int c5 = 0;

        long num2 = n;
        long num5 = n;

        while (num2 > 0 && num2%2==0) {
            c2++;
            num2 = num2 / 2;
        }

        while (num5 > 0 && num5%5==0) {
            c5++;
            num5 = num5 / 5;
        }
        long k=1;

        while(true){
            if(c2<c5 && k*2 <=m){
                k*=2;
                c2+=1;
            }else if(c2>c5 && k*5 <=m){
                k*=5;
                c5++;
            }else if(k*10 <=m){
                k*=10;
            }else{
                break;
            }
        }

        k= m - (m%k);
        System.out.println(n*k);
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}