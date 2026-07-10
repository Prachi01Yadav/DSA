import java.io.*;
import java.util.*;

public class TreeDistancesI {

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
        int t=1;
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

    static List<List<Integer>> g;

    static void solve() throws Exception {
        int n = fs.nextInt();
        g = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }

        // dfs(1, -1);
        // int mx1 = 0;
        // int node = -1;
        // for (int i = 1; i <= n; i++) {
        // if (dist[i] > mx1) {
        // node = i;
        // mx1 = dist[i];
        // }
        // }
        // dist = new int[n + 1];
        // dfs(node, -1);
        // mx1 = 0;
        // for (int i = 1; i <= n; i++) {
        // if (dist[i] > mx1) {
        // mx1 = dist[i];
        // }
        // }
        // out.append(mx1).append('\n');

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        int level = 0;
        int lastNode = 1;
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int curr = q.poll();
                for (int v : g.get(curr)) {
                    if (!vis[v]) {
                        q.add(v);
                        vis[v] = true;
                    }
                }
                lastNode = curr;
            }
        }
        int firstNode = lastNode;
        vis = new boolean[n + 1];
        q = new ArrayDeque<>();
        q.add(firstNode);
        vis[firstNode] = true;
        lastNode = 1;
        while (!q.isEmpty()) {
            int s = q.size();

            for (int i = 0; i < s; i++) {
                int curr = q.poll();
                for (int v : g.get(curr)) {
                    if (!vis[v]) {
                        q.add(v);
                        vis[v] = true;
                        dist1[v]=1+dist1[curr];
                    }
                }
                lastNode = curr;
            }
            level++;
        }

        int secondNode = lastNode;

        // dfs(firstNode,-1,dist1);
        // dfs(secondNode,-1,dist2);
        vis=new boolean[n+1];
        bfs(secondNode,vis,dist2);
        // vis=new boolean[n+1];
        // bfs(secondNode, vis, dist2);

        int[] ans=new int[n+1];
        for(int i=1;i<=n;i++){
            ans[i]=Math.max(dist1[i],dist2[i]);
        }
        for(int i=1;i<=n;i++){
            out.append(ans[i]).append(" ");
        }
        out.append('\n');


       

    }

    static void bfs(int node,boolean[] vis,int[] dist){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(node);
        vis[node] = true;
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int curr = q.poll();
                for (int v : g.get(curr)) {
                    if (!vis[v]) {
                        q.add(v);
                        vis[v] = true;
                        dist[v]=1+dist[curr];
                    }
                }
            }
        }

    }

    // static void dfs(int node, int par,int[] dist) {
    //     for (int v : g.get(node)) {
    //         if (v != par) {
    //             dist[v] = 1 + dist[node];
    //             dfs(v, node,dist);
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