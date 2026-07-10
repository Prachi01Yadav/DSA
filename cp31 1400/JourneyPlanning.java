import java.io.*;
import java.util.*;

public class JourneyPlanning {

    public static void main(String[] args) throws Exception {
        solve();
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        long[] b = new long[n];
        ArrayList<Tuple> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            b[i] = fs.nextLong();
            list.add(new Tuple(i, b[i], (b[i] - (i + 1))));
        }

        if (n == 1) {
            System.out.println(b[0]);
            return;
        }
        // Set<Long> set = new HashSet<>();
        long ans=Integer.MIN_VALUE;
        Map<Long,Long> mp=new HashMap<>();
        for(int i=0;i<n;i++){
            long diff=b[i] - (i+1);
            mp.put(diff,mp.getOrDefault(diff,0L)+b[i]);
            ans=Math.max(ans,mp.get(diff));
        }
        // long ans = Long.MIN_VALUE;
        // for (int i = 0; i < n; i++) {
            // long diff = list.get(i).z;
            // long sum = 0;
            // if (!set.contains(diff)) {
                // for (int j = i; j < n; j++) {
                    // long d = list.get(j).z;
                    // if (d == diff) {
                        // sum += list.get(j).y;
                    // }
                // }
                // set.add(diff);
            // }
            // ans = Math.max(sum, ans);
        // }
        
        System.out.println(ans);
    }

    static class Tuple {
        int x;
        long y;
        long z;

        Tuple(int x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

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