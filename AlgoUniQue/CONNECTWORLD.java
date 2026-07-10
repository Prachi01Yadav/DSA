import java.io.*;
import java.util.*;

public class CONNECTWORLD {

    public static void main(String[] args) throws Exception {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        System.out.println(out);
    }



    /*
    PS: 

    Small Observations: 
    
    
    
    Claims on Algo:
    */




    static void solve() throws Exception {
        int n=fs.nextInt();
        int m=fs.nextInt();
        g=new ArrayList<>();

        for(int i=0;i<=n;i++){
            g.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            int u=fs.nextInt();
            int v=fs.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
        boolean[] vis=new boolean[n+1];
        int comp=0;
        for(int i=1;i<=n;i++){
            if(!vis[i]){
                comp++;
                dfs(i,vis);
            }
        }
        out.append(comp-1);
    }
    static List<List<Integer>> g;

    static void dfs(int node,boolean[] vis){
        vis[node]=true;
        for(int v:g.get(node)){
            if(!vis[v]){
                dfs(v,vis);
            }
        }
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
        double nextDouble() throws IOException { return Double.parseDouble(next()); }

        String next() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == -1) return "";
            StringBuilder sb = new StringBuilder();
            while (c > ' ') { sb.append((char)c); c = readByte(); }
            return sb.toString();
        }
    }

    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder(1 << 21);
}