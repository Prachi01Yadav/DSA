import java.io.*;
import java.util.*;

public class NewsDistribution {

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

/*-------------Logic Here--------------------------*/

    static int[] par;
    static int[] size;
    static void solve() throws Exception {
        int n=fs.nextInt();
        int m=fs.nextInt();
        par=new int[n];
        size=new int[n];
        Arrays.fill(size,1);
        for(int i=0;i<n;i++){
            par[i]=i;
        }
       
        for(int i=0;i<m;i++){
            int k=fs.nextInt();
            if(k==0) continue;
            int firstUser=fs.nextInt()-1;
            for(int j=1;j<k;j++){
               int user=fs.nextInt()-1;
               union(firstUser,user);
            }
        }

        for(int i=0;i<n;i++){
            System.out.print(size[find(i)]+" ");
        }
        System.out.println();
    }
    private static int find(int x){
        if(x==par[x]) return x;
        return par[x]=find(par[x]);
    }
    private static void union(int x,int y){
        int xpar=find(x);
        int ypar=find(y);
        if(xpar != ypar){
            if(size[xpar]>size[ypar]){
                par[ypar]=xpar;
                size[xpar]+=size[ypar];
            }else if(size[xpar]<size[ypar]){
                par[xpar]=ypar;
                size[ypar]+=size[xpar];
            }else{
                par[xpar]=ypar;
                size[ypar]+=size[xpar];
            }
        }
    }

    public static void main(String[] args) throws Exception {
      
      
            solve();
        
        System.out.print(out);
    }
}