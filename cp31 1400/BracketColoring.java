import java.io.*;
import java.util.*;

public class BracketColoring {

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

        char[] s = fs.next().toCharArray();

        int bal = 0;
        for (char c : s) {
            if (c == '(')
                bal++;
            else
                bal--;
        }
        if (bal != 0) {
            System.out.println(-1);
            return;
        }

        int cnt = 0;

        int i = 0;
        int j = 0;

        int[] ans = new int[n];

        while (i < n && j < n) {
            if (s[j] == '(') {
                cnt += 1;
            } else {
                cnt -= 1;
            }

            if (cnt == 0) {

                int color = (s[i] == '(') ? 1 : 2;
                for (int idx = i; idx <= j; idx++) {
                    ans[idx] = color;
                }
                i = j + 1;
            }
            j++;
        }

        Set<Integer> set = new HashSet<>();
        for (int a : ans) {
            set.add(a);
        }
        
        if (set.size() == 1) {
        System.out.println(1);
        for (int k = 0; k < n; k++) {
            System.out.print(1 + " ");
        }
        } else {
            System.out.println(2);
            for(int k=0;k<ans.length;k++){
                System.out.print(ans[k]+ " ");
            }
            
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