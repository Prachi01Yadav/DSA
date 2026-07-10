import java.io.*;
import java.util.*;

public class DeepDownBelow {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
    static class Pair{
        long x,y;
        Pair(long x,long y){
            this.x=x;
            this.y=y;
        }
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        List<List<Long>> list = new ArrayList<>();
        List<Pair> minPowToEnter=new ArrayList<>();
        long maxEl = 1;
        for (int i = 0; i < n; i++) {
            int k = fs.nextInt();
            List<Long> temp = new ArrayList<>();
            long minP=Long.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                long el = fs.nextLong();
                temp.add(el);
                maxEl = Math.max(el, maxEl);
                minP=Math.max(minP,el-j+1);
            }
            minPowToEnter.add(new Pair(minP,temp.size()));
            list.add(temp);
        }
        Collections.sort(minPowToEnter,(a,b)->Long.compare(a.x, b.x));
        
        long s = 1;
        long e = 1_000_000_000L + 100_000L;
        long ans = 1;
        while (s <= e) {
            long mid = s + (e - s) / 2;
            if (isPossible(mid, minPowToEnter)) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.println(ans);
    }
    private static boolean isPossible(long pow,List<Pair> minPowToEnter){
        for(int i=0;i<minPowToEnter.size();i++){
            if(pow<minPowToEnter.get(i).x){
                return false;
            }else{
                pow+=minPowToEnter.get(i).y;
            }
        }
        return true;
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