import java.io.*;
import java.util.*;

public class BerlandRegional {

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

        int[] u = new int[n + 1];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            u[i] = fs.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            int s = fs.nextInt();
            list.get(u[i]).add(s);
        }
       

        for (int i = 1; i <= n; i++) {
            Collections.sort(list.get(i), Collections.reverseOrder());
        }

        ArrayList<long[]> prefList=new ArrayList<>();
        for(int i=1;i<=n;i++){
            int m=list.get(i).size();

            if(m==0) continue;
            long[] pref=new long[m+1];

            for(int j=0;j<m;j++){
                pref[j+1]=pref[j]+list.get(i).get(j);
            }
            prefList.add(pref);
        }

        long[] ans=new long[n+1];

        for(long[] p:prefList){
            int stud=p.length-1;

            for(int k=1;k<=stud;k++){
                int participating= stud - (stud%k);
                ans[k]+=p[participating];
            }
        }

        for(int i=1;i<=n;i++){
            System.out.print(ans[i]+" ");
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