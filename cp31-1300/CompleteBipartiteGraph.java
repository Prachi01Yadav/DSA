import java.io.*;
import java.util.*;

public class CompleteBipartiteGraph {

    public static void main(String[] args) throws Exception {
       
       
            solve();
      
      
    }

    static void solve() throws Exception {
        int n=fs.nextInt();
        Map<Integer,List<Integer>> mp=new HashMap<>();
        for(int i=0;i<n-1;i++){
            int u=fs.nextInt();
            int v=fs.nextInt();
            mp.putIfAbsent(u,new ArrayList<>());
            mp.putIfAbsent(v,new ArrayList<>());
            mp.get(u).add(v);
            mp.get(v).add(u);
        }
        int[] color=new int[n+1];
        Arrays.fill(color,-1);
        dfs(mp,1,1,color);

        int cnt0=0;
        int cnt1=0;
        for(int i=0;i<color.length;i++){
            if(color[i]==0){
                cnt0++;
            }else if(color[i]==1){
                cnt1++;
            }
        }
        System.out.println((1L*cnt0*cnt1)-(n-1));
    }

    private static void dfs(Map<Integer,List<Integer>> g,int node,int curr,int[] color){
        color[node]=curr;

        if(!g.containsKey(node)) return;

        for(int v:g.get(node)){
            if(color[v]==-1){
                dfs(g,v,1-curr,color);
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