import java.io.*;
import java.util.*;

public class SusieFairyTales {

    public static void main(String[] args) throws Exception {
        solve();
    }

    static class Pair{
        long i,j;
        Pair(long i,long j){
            this.i=i;
            this.j=j;
        }
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        if(n==1){
            System.out.println(1);
            return;
        }
        ArrayList<Pair> p=new ArrayList<>();
        for(int i=0;i<n;i++){
            long x=fs.nextLong();
            long h=fs.nextLong();
            p.add(new Pair(x,h));
        }

        Collections.sort(p,(a,b)->Long.compare(a.i, b.i));

        int cnt=2;
        long lastPos=p.get(0).i;

        for(int i=1;i<n-1;i++){
            //check left
            if((p.get(i).i - p.get(i).j) > lastPos){
                cnt++;
                lastPos=p.get(i).i;
            }
            //check right
            else if(p.get(i).i + p.get(i).j < p.get(i+1).i){
                cnt++;
                lastPos=(p.get(i).i + p.get(i).j);
            }else{
                lastPos=p.get(i).i;
            }
        }
        System.out.println(cnt);

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