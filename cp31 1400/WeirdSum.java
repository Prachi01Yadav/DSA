import java.io.*;
import java.util.*;

public class WeirdSum {

    public static void main(String[] args) throws Exception {
      
      
            solve();
       
       
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        int m=fs.nextInt();

        ArrayList<Integer>[] colorRows=new ArrayList[100001];
        ArrayList<Integer>[] colorCols=new ArrayList[100001];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                int color=fs.nextInt();
                if(colorRows[color]==null){
                    colorRows[color]=new ArrayList<>();
                    colorCols[color]=new ArrayList<>();
                }
                colorRows[color].add(i);
                colorCols[color].add(j);
            }
        }

        long sum=0;

        for(int i=0;i<100001;i++){
            if(colorRows[i]!=null && colorRows[i].size()>1){
            sum+=calculateContribution(colorRows[i]);
            sum+=calculateContribution(colorCols[i]);
            }
        }
        System.out.println(sum);
    }
    private static long calculateContribution(ArrayList<Integer> list){
        Collections.sort(list);
        int s=list.size();
        long contri=0;
        for(int i=0;i<s;i++){
            contri+= (long)(list.get(i)*(2L*i + 1 -s));
        }
        return contri;
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