import java.io.*;
import java.util.*;

public class A_Circle_of_Light {

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

        solve();

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
    static int[] par;
    static int[] rank;

    static void solve() throws Exception {
        int n = fs.nextInt();
        par = new int[n];
        rank = new int[n];
        Arrays.fill(rank, 1);
        for (int i = 0; i < n; i++)
            par[i] = i;

        List<int[]> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int xi = fs.nextInt();
            int yi = fs.nextInt();
            int ri = fs.nextInt();
            ls.add(new int[] { xi, yi, ri });
        }
        for (int i = 0; i < n; i++) {
            int[] curr = ls.get(i);
            int xi = curr[0];
            int yi = curr[1];
            int ri = curr[2];
            for (int j = i + 1; j < n; j++) {
                int[] next = ls.get(j);
                int xi1 = next[0];
                int yi1 = next[1];
                int ri1 = next[2];

                long dx = (long) xi1 - xi;
                long dy = (long) yi1 - yi;
                long rSum = (long) ri1 + ri;
                if (dx * dx + dy * dy <= rSum * rSum) {
                    union(i, j);
                }

            }
        }

        int si = fs.nextInt();
        int sj = fs.nextInt();
        int di = fs.nextInt();
        int dj = fs.nextInt();

        int sid = -1;
        for (int i = 0; i < n; i++) {
            int[] c = ls.get(i);
            long dx = (long) c[0] - si;
            long dy = (long) c[1] - sj;
            long r = c[2];
            if (dx * dx + dy * dy <= r * r) {
                if (sid == -1)
                    sid = i;
                else
                    union(sid, i);
            }
        }

        int did = -1;
        for (int i = 0; i < n; i++) {
            int[] c = ls.get(i);
            long dx = (long) c[0] - di;
            long dy = (long) c[1] - dj;
            long r = c[2];
            if (dx * dx + dy * dy <= r * r) {
                if (did == -1)
                    did = i;
                else
                    union(did, i);
            }
        }

        if (sid == -1 || did == -1) {
            System.out.println("NO");
            return;
        }

        if (sid == did || find(sid) == find(did)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    private static int find(int x) {
        int root = x;
        while (par[root] != root)
            root = par[root];
        while (par[x] != root) {
            int next = par[x];
            par[x] = root;
            x = next;
        }
        return root;
    }

    private static void union(int x, int y) {
        int xpar = find(x);
        int ypar = find(y);
        if (xpar == ypar)
            return;

        if (rank[xpar] > rank[ypar]) {
            par[ypar] = xpar;
        } else if (rank[ypar] > rank[xpar]) {
            par[xpar] = ypar;
        } else {
            par[xpar] = ypar;
            rank[ypar]++;
        }
    }

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