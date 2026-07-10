import java.io.*;
import java.util.*;

public class AnnaAndShasha {

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

    // ---------- SHORT PRINT FUNCTION ----------
    static void print(Object x) { out.append(x).append('\n'); }

    //----------- Logic here-----------

    static void solve() throws Exception {
        int n=fs.nextInt();
        int m=fs.nextInt();
        long[] cntZero=new long[n];
        long[] arr=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=fs.nextLong();
            cntZero[i]=cntZeroes(arr[i]);
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(arr[i]);
        }
        long totalDigits=sb.length();
        Arrays.sort(cntZero);

        int i=n-1;
        long len=0;
        while(i>=0){
            len+=cntZero[i];
            i-=2;
        }
        long remain=totalDigits-len;
        if(remain -1 >=m ){
            System.out.println("Sasha");
        }else{
            System.out.println("Anna");
        }

    }
    private static int cntZeroes(long num){
        int cnt=0;
        while(num>0){
            long lastdigit=num%10;
            if(lastdigit==0){
                cnt++;
            }else{
                break;
            }
            num=num/10;
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}