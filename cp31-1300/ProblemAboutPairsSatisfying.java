import java.io.*;
import java.util.*;

public class ProblemAboutPairsSatisfying {

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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextInt();
        }

        ArrayList<Integer> validIndices = new ArrayList<>();
        long count = 0;

        for (int j = 0; j < n; j++) {
            if (a[j] < j+1) {
                int currentCount = binarySearch(validIndices, a[j]);
                count += currentCount;
                validIndices.add(j+1);
            }
        }
        System.out.println(count);

    }

    static int binarySearch(ArrayList<Integer> list, int key) {
    int low = 0;
    int high = list.size() - 1;
    int ans = 0;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (list.get(mid) < key) {
            ans = mid + 1; 
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return ans;
}

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static class Pair {
        long x;
        int y;

        Pair(long x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}