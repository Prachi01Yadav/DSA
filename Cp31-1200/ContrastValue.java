import java.io.*;
import java.util.*;

public class ContrastValue {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        long[] arr=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=fs.nextLong();
        }
        if(n==1){
            System.out.println(1);
            return;
        }
        ArrayList<Long> a=new ArrayList<>();
        a.add(arr[0]);
        for(int i=1;i<n;i++){
            if(arr[i]!=arr[i-1]){
                a.add(arr[i]);
            }
        }
        if(a.size()==1){
            System.out.println(1);
            return;
        }
        int count=2;
        for(int i=1;i<a.size()-1;i++){
            if(a.get(i)==0){
                count++;
                continue;
            }
            if(((1L*(a.get(i)-a.get(i-1)))*(1L*(a.get(i+1)-a.get(i)))) < 0){
               count++;
            }
        }
        
        System.out.println(count);

    }



/*-------------For Fast I/O --------------------------*/

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

}