import java.io.*;
import java.util.*;

public class GoodArray {

    public static void main(String[] args) throws Exception {
            solve();
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        ArrayList<Long> list=new ArrayList<>();
        long totalSum=0;
        Map<Long,Long> mp=new HashMap<>();
        for(int i=0;i<n;i++){
            long el=fs.nextInt();
            list.add(el);
            totalSum+=el;
            mp.put(el,mp.getOrDefault(el,0L)+1);
        }
        List<Integer> ans=new ArrayList<>();

        for(int i=0;i<n;i++){
            long el=list.get(i);
            long sum=totalSum-el;
            if(sum%2==1){
                continue;
            }
            mp.put(el,mp.getOrDefault(el,0L)-1);
            long x=sum/2;
            if(mp.containsKey(x) && mp.get(x)>=1){
                ans.add(i+1);
            }
            mp.put(el,mp.getOrDefault(el,0L)+1);
        }

        System.out.println(ans.size());
        for(int i=0;i<ans.size();i++){
            System.out.print(ans.get(i)+" ");
        }

        System.out.println();
   
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