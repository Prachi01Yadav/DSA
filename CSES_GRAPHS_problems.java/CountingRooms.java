// import java.io.*;
// import java.util.*;
 
// public class CountingRooms {
 
//     // -----Customize for each ques-----
 
//     // static long[] segTree;
//     // static long[] arr;
 
//     // static void buildTree(int i,int l,int r){
//     // if(l==r){
//     // segTree[i]=arr[l];
//     // return;
//     // }
//     // int mid= l + (r-l)/2;
//     // buildTree(2*i +1,l,mid);
//     // buildTree(2*i +2, mid+1,r);
//     // segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
//     // }
 
//     // static long query(int s,int e,int i,int l,int r){
//     // if(e<l || s>r){
//     // return -1;
//     // }
//     // if(l>=s && r<=e){
//     // return segTree[i];
//     // }
//     // int mid=l+ (r-l)/2;
//     // long left=query(s, e, 2*i +1, l, mid);
//     // long right=query(s, e, 2*i +2, mid+1, r);
//     // if(left==-1) return right;
//     // if(right==-1) return left;
//     // return (left & right);
//     // }
 
//     public static void main(String[] args) throws Exception {
//         solve();
//     }
 
//     static int[][] dir = new int[][] {
//             { 0, 1 },
//             { 0, -1 },
//             { 1, 0 },
//             { -1, 0 }
//     };
//     static int n, m;
 
//     static void solve() throws Exception {
//         n = fs.nextInt();
//         m = fs.nextInt();
 
//         char[][] grid = new char[n][m];
//         for (int i = 0; i < n; i++) {
//             String s = fs.next();
//             for (int j = 0; j < m; j++) {
//                 grid[i][j] = s.charAt(j);
//             }
//         }
//         boolean[][] vis = new boolean[n][m];
//         int cnt = 0;
 
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (grid[i][j] == '.' && !vis[i][j]) {
//                     bfs(i, j, vis, grid);
//                     cnt++;
//                 }
//             }
//         }
//         System.out.println(cnt);
//     }
 
//     static void bfs(int i, int j, boolean[][] vis, char[][] grid) {
//         Queue<int[]> q = new LinkedList<>();
//         q.add(new int[] { i, j });
//         vis[i][j] = true;
 
//         while (!q.isEmpty()) {
//             int[] cur = q.poll();
//             int r = cur[0], c = cur[1];
 
//             for (int[] d : dir) {
//                 int i_ = r + d[0];
//                 int j_ = c + d[1];
 
//                 if (i_ >= 0 && i_ < n && j_ >= 0 && j_ < m
//                         && !vis[i_][j_] && grid[i_][j_] == '.') {
 
//                     vis[i_][j_] = true;
//                     q.add(new int[] { i_, j_ });
//                 }
//             }
//         }
//     }
 
//     // static void dfs(int i, int j, boolean[][] vis, char[][] grid) {
//     // vis[i][j] = true;
//     // grid[i][j] = '#';
 
//     // for (int[] d : dir) {
//     // int i_ = i + d[0];
//     // int j_ = j + d[1];
//     // if (i_ >= 0 && i_ < n && j_ >= 0 && j_ < m && !vis[i_][j_] && grid[i_][j_] ==
//     // '.') {
//     // dfs(i_, j_, vis, grid);
//     // }
//     // }
//     // }
 
//     /*-------------For Fast I/O --------------------------*/
 
//     private static final class FastScanner {
//         private final byte[] buffer = new byte[1 << 16];
//         private int ptr = 0, len = 0;
//         private final InputStream in;
 
//         FastScanner(InputStream in) {
//             this.in = in;
//         }
 
//         private int readByte() throws IOException {
//             if (ptr >= len) {
//                 len = in.read(buffer);
//                 ptr = 0;
//                 if (len <= 0)
//                     return -1;
//             }
//             return buffer[ptr++];
//         }
 
//         long nextLong() throws IOException {
//             int c;
//             do {
//                 c = readByte();
//             } while (c <= ' ' && c != -1);
//             boolean neg = false;
//             if (c == '-') {
//                 neg = true;
//                 c = readByte();
//             }
//             long val = 0;
//             while (c > ' ') {
//                 val = val * 10 + (c - '0');
//                 c = readByte();
//             }
//             return neg ? -val : val;
//         }
 
//         int nextInt() throws IOException {
//             return (int) nextLong();
//         }
 
//         String next() throws IOException {
//             int c;
//             do {
//                 c = readByte();
//             } while (c <= ' ' && c != -1);
//             StringBuilder sb = new StringBuilder();
//             while (c > ' ') {
//                 sb.append((char) c);
//                 c = readByte();
//             }
//             return sb.toString();
//         }
//     }
 
//     static final long INF = (long) 1e18;
//     static FastScanner fs = new FastScanner(System.in);
//     static StringBuilder out = new StringBuilder();
 
// }
import java.io.*;
import java.util.*;
 
public class CountingRooms {
 
    // -----Customize for each ques-----
 
    /*
     * static long[] segTree;
     * static long[] arr;
     * 
     * static void buildTree(int i,int l,int r){
     * if(l==r){
     * segTree[i]=arr[l];
     * return;
     * }
     * int mid= l + (r-l)/2;
     * buildTree(2*i +1,l,mid);
     * buildTree(2*i +2, mid+1,r);
     * segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
     * }
     * 
     * static long query(int s,int e,int i,int l,int r){
     * if(e<l || s>r){
     * return -1;
     * }
     * if(l>=s && r<=e){
     * return segTree[i];
     * }
     * int mid=l+ (r-l)/2;
     * long left=query(s, e, 2*i +1, l, mid);
     * long right=query(s, e, 2*i +2, mid+1, r);
     * if(left==-1) return right;
     * if(right==-1) return left;
     * return (left & right);
     * }
     */
 
    public static void main(String[] args) throws Exception {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
 
    /*
     * 
     * PS :
     * Small Observations:
     * 
     * 
     * Claims on Algo
     * 
     * 
     * 
     */
    static char[][] g;
 
    static void solve() throws Exception {
         n = fs.nextInt();
         m = fs.nextInt();
        g = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = fs.next();
            for (int j = 0; j < m; j++) {
                g[i][j] = s.charAt(j);
            }
        }
 
 
 
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == '.') {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
       out.append(cnt).append('\n');
 
    }
    static void bfs(int i,int j){
        Queue<int[]> q=new ArrayDeque<>();
        q.add(new int[]{i,j});
        g[i][j]='#';
 
        while(!q.isEmpty()){
            int[] curr=q.poll();
            int i_=curr[0];
            int j_=curr[1];
            for (int[] d : dir) {
                int x = i_ + d[0];
                int y = j_ + d[1];
                if (x>=0 && y>=0 && x<n && y<m && g[x][y] == '.') {
                    q.add(new int[]{x,y});
                    g[x][y]='#';
                }
            }
        }
    }
 
    static int[][] dir = new int[][] {
            { 0, 1 },
            { 0, -1 },
            { 1, 0 },
            { -1, 0 }
    };
    static int n,m;
    // static void dfs(int i, int j) {
    //     g[i][j] = '#';
 
    //     for (int[] d : dir) {
    //         int i_ = i + d[0];
    //         int j_ = j + d[1];
    //         if (i_>=0 && j_>=0 && i_<n && j_<m && g[i_][j_] == '.') {
    //             dfs(i_, j_);
    //         }
    //     }
    // }
 
    // nCr using factorial + modular inverse -> Works when MOD is prime (ex: 1e9+7)
 
    static final int MAX = 1000005;
    static final long MOD = 1_000_000_007;
 
    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];
 
    // Precompute factorials and inverse factorials
    static void precompute() {
 
        fact[0] = 1;
 
        for (int i = 1; i < MAX; i++)
            fact[i] = (fact[i - 1] * i) % MOD;
 
        invFact[MAX - 1] = modPow(fact[MAX - 1], MOD - 2);
 
        for (int i = MAX - 2; i >= 0; i--)
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
    }
 
    // Fast exponentiation
    static long modPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
 
    // nCr calculation
    static long nCr(int n, int r) {
 
        if (r > n || r < 0)
            return 0;
 
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
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