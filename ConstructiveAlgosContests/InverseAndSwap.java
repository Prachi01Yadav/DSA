package ConstructiveAlgosContests;
import java.io.*;
import java.util.*;

public class InverseAndSwap {

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
        int q=fs.nextInt();

        int[][] p=new int[n+1][2];
        for(int i=1;i<=n;i++){
            p[i][0]=fs.nextInt();
            p[i][1]=i;
        }

        int[] p_ = new int[n+1];
        for(int i=1;i<=n;i++){
            int el=p[i][0];
            p_[el]=p[i][1];
        }
        int[] pp=new int[n+1];
        for(int i=1;i<=n;i++){
            pp[i]=p[i][0];
        }
        
        for(int i=0;i<q;i++){
            int t=fs.nextInt();
            if(t==1){
                int x=fs.nextInt();
                int y=fs.nextInt();
                p_[pp[y]]=x;
                p_[pp[x]]=y;
                int tempVal = pp[x];
                pp[x] = pp[y];
                pp[y] = tempVal;
            }else{
                int[] tempRef = pp;
                pp = p_;
                p_ = tempRef;
            }
        }
        for(int i=1;i<=n;i++){
            out.append(pp[i]).append(' ');
        }
        out.append('\n');
    }










    static class Combinatorics {
        int max;
        long mod;
        long[] fact;
        long[] invFact;

        public Combinatorics(int max, long mod) {
            this.max = max;
            this.mod = mod;
            this.fact = new long[max];
            this.invFact = new long[max];
            precompute();
        }

        private void precompute() {
            fact[0] = 1;
            for (int i = 1; i < max; i++) {
                fact[i] = (fact[i - 1] * i) % mod;
            }
            invFact[max - 1] = modPow(fact[max - 1], mod - 2);
            for (int i = max - 2; i >= 0; i--) {
                invFact[i] = (invFact[i + 1] * (i + 1)) % mod;
            }
        }

        public long modPow(long a, long b) {
            long res = 1;
            a %= mod;
            while (b > 0) {
                if ((b & 1) == 1) res = (res * a) % mod;
                a = (a * a) % mod;
                b >>= 1;
            }
            return res;
        }

        public long nCr(int n, int r) {
            if (r > n || r < 0) return 0;
            return (((fact[n] * invFact[r]) % mod) * invFact[n - r]) % mod;
        }
    }

    static class SegTree {
        long[] seg;
        long[] nums;
        int n;

        public SegTree(long[] nums) {
            this.nums = nums;
            this.n = nums.length;
            this.seg = new long[4 * n];
            if (n > 0) {
                build(0, 0, n - 1);
            }
        }

        void build(int i, int l, int r) {
            if (l == r) {
                seg[i] = nums[r];
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * i + 1, l, mid);
            build(2 * i + 2, mid + 1, r);
            seg[i] = seg[2 * i + 1] + seg[2 * i + 2];
        }

        void update(int i, int l, int r, int idx, long val) {
            if (l == r) {
                seg[i] = val;
                nums[idx] = val;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * i + 1, l, mid, idx, val);
            } else {
                update(2 * i + 2, mid + 1, r, idx, val);
            }
            seg[i] = seg[2 * i + 1] + seg[2 * i + 2];
        }

        long query(int i, int l, int r, int start, int end) {
            if (l > end || r < start) {
                return 0;
            }
            if (l >= start && r <= end) {
                return seg[i];
            }
            int mid = l + (r - l) / 2;
            return query(2 * i + 1, l, mid, start, end) + query(2 * i + 2, mid + 1, r, start, end);
        }

        public void update(int idx, long val) {
            update(0, 0, n - 1, idx, val);
        }

        public long query(int start, int end) {
            return query(0, 0, n - 1, start, end);
        }
    }

    static class MultiSet<T extends Comparable<T>> {
        private TreeMap<T, Integer> map = new TreeMap<>();
        private int totalSize = 0;

        void add(T v)         { map.merge(v, 1, Integer::sum); totalSize++; }
        boolean contains(T v) { return map.containsKey(v); }
        int count(T v)        { return map.getOrDefault(v, 0); }
        int size()            { return totalSize; }
        int distinctSize()    { return map.size(); }
        boolean isEmpty()     { return totalSize == 0; }
        T first()             { return map.firstKey(); }
        T last()              { return map.lastKey(); }
        T lower(T v)          { return map.lowerKey(v); }
        T floor(T v)          { return map.floorKey(v); }
        T ceiling(T v)        { return map.ceilingKey(v); }
        T higher(T v)         { return map.higherKey(v); }
        TreeMap<T,Integer> getMap() { return map; }

        boolean remove(T v) {
            Integer c = map.get(v);
            if (c == null) return false;
            if (c == 1) map.remove(v); else map.put(v, c - 1);
            totalSize--;
            return true;
        }

        boolean removeAll(T v) {
            Integer c = map.get(v);
            if (c == null) return false;
            map.remove(v);
            totalSize -= c;
            return true;
        }

        void clear() { map.clear(); totalSize = 0; }

        @Override public String toString() { return map.toString(); }
    }

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