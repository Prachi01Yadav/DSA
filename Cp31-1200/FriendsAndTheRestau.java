import java.io.*;
import java.util.*;

public class FriendsAndTheRestau {

    private static final class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            boolean neg = false; if (c == '-') { neg = true; c = readByte(); }
            long val = 0;
            while (c > ' ') { val = val * 10 + (c - '0'); c = readByte(); }
            return neg ? -val : val;
        }

        int nextInt() throws IOException { return (int) nextLong(); }
        String next() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') { sb.append((char)c); c = readByte(); }
            return sb.toString();
        }
    }

    static final long INF = (long) 1e18;
    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder();

/*-------------Logic Here--------------------------*/

    static void solve() throws Exception {
        int n=fs.nextInt();
        long[] a=new long[n];
        long[] b=new long[n];
        for(int i=0;i<n;i++){
            a[i]=fs.nextLong();
        }
        for(int i=0;i<n;i++){
            b[i]=fs.nextLong();
        }

        ArrayList<Long> diff=new ArrayList<>();
        for(int i=0;i<n;i++){
            diff.add(b[i]-a[i]);
        }

        Collections.sort(diff);

        int i=0;
        int j=n-1;
        int cnt=0;
        while(i<j){
            if(diff.get(i) + diff.get(j) >=0){
                cnt++;
                i++;
                j--;
            }else{
                i++;
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}