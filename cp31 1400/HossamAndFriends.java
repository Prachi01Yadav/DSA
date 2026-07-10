import java.io.*;
import java.util.*;

public class HossamAndFriends {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        int m = fs.nextInt();
        int[] bad=new int[n+2];
        Arrays.fill(bad,n+1);

        for (int i = 0; i < m; i++) {
            int x = fs.nextInt();
            int y = fs.nextInt();
            if(x>y){
                int t=x;
                x=y;
                y=t;
            }
            bad[x]=Math.min(bad[x],y);
        }

        for(int i=n-1;i>=1;i--){
            bad[i]=Math.min(bad[i],bad[i+1]);
        }
        long ans=0;
        for(int i=1;i<=n;i++){
            ans+=(bad[i]-i);
        }


        System.out.println(ans);

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