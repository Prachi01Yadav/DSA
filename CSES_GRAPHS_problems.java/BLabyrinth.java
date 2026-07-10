import java.io.*;
import java.util.*;

public class BLabyrinth {

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
        int r=fs.nextInt();
        int c=fs.nextInt();
        long x=fs.nextLong();
        long y=fs.nextLong();

        char[][] grid=new char[n][m];
        for(int i=0;i<n;i++){
            String s=fs.next();
            for(int j=0;j<m;j++){
                grid[i][j]=s.charAt(j);
            }
        }
        r--;
        c--;
        if(grid[r][c]=='*'){
            System.out.println(0);
            return;
        }

        long[][] dist=new long[n][m];
        for(long[] d:dist){
            Arrays.fill(d,Long.MAX_VALUE);
        }
        dist[r][c]=0;

        Deque<int[]> dq=new ArrayDeque<>();
        dq.add(new int[]{r,c});

        while(!dq.isEmpty()){
            int[] curr=dq.pollFirst();
            int i=curr[0];
            int j=curr[1];
            long L=dist[i][j];
            for(int d=0;d<4;d++){
                int i_=i+dir[d][0];
                int j_=j+dir[d][1];

                if(i_>=0 && j_>=0 && i_<n && j_<m && grid[i_][j_]!='*'){
                    long cost=(d==0)?1:0;
                    if(L+cost<dist[i_][j_]){
                       dist[i_][j_]=L+cost;
                       if(cost==0){
                        dq.addFirst(new int[]{i_,j_});
                       }else{
                        dq.addLast(new int[]{i_,j_});
                       }
                    }
                }
            }
        }

        long cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
               if(dist[i][j]!=Long.MAX_VALUE){
                 long R= dist[i][j]+j-c;

                 if(dist[i][j]<=x && R<=y){
                    cnt++;
                 }
               }
            }
        }
        System.out.println(cnt);

    }
    static int[][] dir=new int[][]{
        {0,-1},
        {0,1},
        {1,0},
        {-1,0}
    };




    // // nCr using factorial + modular inverse
    // static final int MAX = 1000005;
    // static final long MOD = 1_000_000_007;
    // static final long inf = (long) 2e18;
    // 
    // static long[] fact = new long[MAX];
    // static long[] invFact = new long[MAX];
    // 
    // // factorials and inverse factorials
    // static void precompute() {
    //     fact[0] = 1;
    //     for (int i = 1; i < MAX; i++)
    //         fact[i] = (fact[i-1] * i) % MOD;
    // 
    //     invFact[MAX-1] = modPow(fact[MAX-1], MOD-2);
    //     for (int i = MAX-2; i >= 0; i--)
    //         invFact[i] = (invFact[i+1] * (i+1)) % MOD;
    // }
    // 
    // // Fast exponentiation
    // static long modPow(long a, long b) {
    //     long res = 1;
    //     while (b > 0) {
    //         if ((b & 1) == 1)
    //             res = (res * a) % MOD;
    //         a = (a * a) % MOD;
    //         b >>= 1;
    //     }
    //     return res;
    // }
    // 
    // // nCr calculation
    // static long nCr(int n, int r) {
    //     if (r > n || r < 0)
    //         return 0;
    //     return (((fact[n] * invFact[r]) % MOD) * invFact[n-r]) % MOD;
    // }

    static class MultiSet<T extends Comparable<T>> {
        private TreeMap<T, Integer> map = new TreeMap<>();
        private int totalSize = 0;

        void add(T v)         { map.merge(v, 1, Integer::sum); totalSize++; }          // O(log n)
        boolean contains(T v) { return map.containsKey(v); }                           // O(log n)
        int count(T v)        { return map.getOrDefault(v, 0); }                       // O(log n)
        int size()            { return totalSize; }                                    // O(1)
        int distinctSize()    { return map.size(); }                                   // O(1)
        boolean isEmpty()     { return totalSize == 0; }                               // O(1)
        T first()             { return map.firstKey(); }                               // O(log n) — min element
        T last()              { return map.lastKey(); }                                // O(log n) — max element
        T lower(T v)          { return map.lowerKey(v); }                              // O(log n) — largest element STRICTLY < v
        T floor(T v)          { return map.floorKey(v); }                              // O(log n) — largest element <= v
        T ceiling(T v)        { return map.ceilingKey(v); }                            // O(log n) — smallest element >= v  [= lower_bound]
        T higher(T v)         { return map.higherKey(v); }                             // O(log n) — smallest element STRICTLY > v [= upper_bound]
        TreeMap<T,Integer> getMap() { return map; }                                    // O(1)

        // Remove ONE occurrence of v- O(log n)
        boolean remove(T v) {
            Integer c = map.get(v);
            if (c == null) return false;
            if (c == 1) map.remove(v); else map.put(v, c - 1);
            totalSize--;
            return true;
        }

        // Remove ALL occurrences of v - O(log n)
        boolean removeAll(T v) {
            Integer c = map.get(v);
            if (c == null) return false;
            map.remove(v);
            totalSize -= c;
            return true;
        }

        // Iterate over all elements in sorted order - O(n)
        // Usage: for (Map.Entry<T,Integer> e : ms.getMap().entrySet()) { T val=e.getKey(); int freq=e.getValue(); }

        void clear() { map.clear(); totalSize = 0; }  // O(n)

        @Override public String toString() { return map.toString(); }
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