package CSES_Dynamic_Programming;
import java.io.*;
import java.util.*;

public class BTheLeastRoundWay {

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
        long[][] g=new long[n][n];
        int r0=-1;
        int c0=-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                g[i][j]=fs.nextLong();
                if(g[i][j]==0 && r0==-1){
                    r0=i;
                    c0=j;
                }
            }
        }

        long[][] dp=new long[n+1][n+1];
        char[][] path=new char[n][n];
        dp[0][0]=div5(g[0][0]);
        
        for(int i=1;i<n;i++){
            dp[0][i]=div5(g[0][i])+dp[0][i-1];
        }
        for(int i=1;i<n;i++){
            dp[i][0]=div5(g[i][0])+dp[i-1][0];
        }
        for(int i=1;i<n;i++){
            path[0][i]='R';
        }
        for(int i=1;i<n;i++){
            path[i][0]='D';
        }
        

        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                long curr=div5(g[i][j]);
                if(dp[i-1][j]<dp[i][j-1]){
                    dp[i][j]=curr+dp[i-1][j];
                    path[i][j]='D';
                }else{
                    dp[i][j]=curr+dp[i][j-1];
                    path[i][j]='R';
                }
            }
        }
        long[][] dp2=new long[n+1][n+1];
        char[][] path2=new char[n][n];
        dp2[0][0]=div2(g[0][0]);
        
        for(int i=1;i<n;i++){
            dp2[0][i]=div2(g[0][i])+dp2[0][i-1];
        }
        for(int i=1;i<n;i++){
            dp2[i][0]=div2(g[i][0])+dp2[i-1][0];
        }
        for(int i=1;i<n;i++){
            path2[0][i]='R';
        }
        for(int i=1;i<n;i++){
            path2[i][0]='D';
        }
        

        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                long curr=div2(g[i][j]);
                if(dp2[i-1][j]<dp2[i][j-1]){
                    dp2[i][j]=curr+dp2[i-1][j];
                    path2[i][j]='D';
                }else{
                    dp2[i][j]=curr+dp2[i][j-1];
                    path2[i][j]='R';
                }
            }
        }
        long best2=dp2[n-1][n-1];
        long best5=dp[n-1][n-1];

        if (r0 != -1 && Math.min(best2, best5) > 1) {
            System.out.println(1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r0; i++) sb.append('D');
            for (int j = 0; j < c0; j++) sb.append('R');
            for (int i = r0; i < n-1; i++) sb.append('D');
            for (int j = c0; j < n-1; j++) sb.append('R');
            System.out.println(sb);
            return;
        }

        if (best2 < best5) {
            System.out.println(best2);
            System.out.println(path(path2));
        } else {
            System.out.println(best5);
            System.out.println(path(path));
        }


        

    }
    static StringBuilder path(char[][] path){
        StringBuilder sb=new StringBuilder();
        int i=path.length-1;
        int j=path[0].length-1;
        while(i!=0 || j!=0){
            sb.append(path[i][j]);
            if(path[i][j]=='R'){
                j--;
            }else{
                i--;
            }
        }
        sb.reverse();
        return sb;
    }
    static long div5(long x){
        int ret=0;
        while(x>0 && x%5==0){
            ret++;
            x=x/5;
        }
        return ret;
    }
    static long div2(long x){
        int ret=0;
        while(x>0 && x%2==0){
            ret++;
            x=x/2;
        }
        return ret;
    }




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