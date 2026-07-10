import java.io.*;
import java.util.*;

public class StrongVertices {

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

        long[] diff = new long[(int) n];

        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
        }

        for (int i = 0; i < n; i++) {
            b[i] = fs.nextLong();
        }
        long maxDiff=Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            diff[i]=a[i]-b[i];
            maxDiff=Math.max(maxDiff,diff[i]);
        }

        ArrayList<Integer> ans=new ArrayList<>();
        // long cnt=0;
        for(int i=0;i<n;i++){
            if(diff[i]==maxDiff){
                 ans.add(i+1);
                // cnt++;
            }
        }
        System.out.println(ans.size());
        for(int i=0;i<ans.size();i++){
            System.out.print(ans.get(i)+ " ");
        }
      System.out.println();
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}