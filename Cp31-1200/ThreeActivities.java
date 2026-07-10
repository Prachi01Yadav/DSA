import java.io.*;
import java.util.*;

public class ThreeActivities {

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

    // ---------- SHORT PRINT FUNCTION ----------
    static void print(Object x) {
        out.append(x).append('\n');
    }

    static void solve() throws Exception {
        long n = fs.nextLong();
        long[] a = new long[(int) n];
        long[] b = new long[(int) n];
        long[] c = new long[(int) n];

        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = fs.nextLong();
        }
        for (int i = 0; i < n; i++) {
            c[i] = fs.nextLong();
        }

        long[][] aNew = new long[(int) n][2];
        for (int i = 0; i < n; i++) {
            aNew[i][0] = a[i];
            aNew[i][1] = i ;
        }

        long[][] bNew = new long[(int) n][2];
        for (int i = 0; i < n; i++) {
            bNew[i][0] = b[i];
            bNew[i][1] = i ;
        }

        long[][] cNew = new long[(int) n][2];
        for (int i = 0; i < n; i++) {
            cNew[i][0] = c[i];
            cNew[i][1] = i ;
        }

        Arrays.sort(aNew, (x, y) -> Long.compare(y[0], x[0]));
        Arrays.sort(bNew, (x, y) -> Long.compare(y[0], x[0]));
        Arrays.sort(cNew, (x, y) -> Long.compare(y[0], x[0]));

        long maxSum=Long.MIN_VALUE;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    long aIdx=aNew[i][1];
                    long bIdx=bNew[j][1];
                    long cIdx=cNew[k][1];

                    if(aIdx==bIdx || bIdx==cIdx || cIdx==aIdx){
                        continue;
                    }
                    maxSum=Math.max(maxSum,a[(int) aIdx]+ b[(int) bIdx] + c[(int) cIdx]);
                }
            }
        }
        print(maxSum);

    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}