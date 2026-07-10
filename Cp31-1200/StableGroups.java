import java.io.*;
import java.util.*;

public class StableGroups {

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
        long k = fs.nextLong();
        long x = fs.nextLong();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }
        Arrays.sort(arr);

        long[] sub = new long[n];
        sub[0] = 0;
        ArrayList<Long> studNeeds = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            sub[i] = arr[i] - arr[i - 1];
            if (sub[i] > x) {
                studNeeds.add((sub[i] - 1) / x);
            }
        }
        Collections.sort(studNeeds);
        int i=0;
        while(i<studNeeds.size()) {
            if(k>=studNeeds.get(i)){
                 k -= studNeeds.get(i);
            }else{
                break;
            }
            i++;
        }
        System.out.println((studNeeds.size() - i) + 1);
    }

    public static void main(String[] args) throws Exception {
        solve();
    }
}