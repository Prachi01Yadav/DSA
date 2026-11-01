package ConstructiveAlgosContests;
import java.io.*;
import java.util.*;


public class DSayNoToPalindromes {

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

        String s=fs.next();
        if(n==1){
            for(int i=0;i<m;i++){
                out.append(0).append('\n');
            }
            return;
        }
        
        String[] ar=new String[6];
        ar[0]="abc";
        ar[1]="acb";
        ar[2]="bac";
        ar[3]="bca";
        ar[4]="cba";
        ar[5]="cab";

        int[][] psum=new int[6][n];

        for(int i=0;i<6;i++){
            char ch0=ar[i].charAt(0);
            char ch1=ar[i].charAt(1);
            char ch2=ar[i].charAt(2);

            psum[i][0]=(s.charAt(0)!=ch0)?1:0;
            for(int j=1;j<n;j++){
                if(j%3==0){
                    psum[i][j]=psum[i][j-1] + ((s.charAt(j)!=ch0)?1:0);
                }else if(j%3==1){
                    psum[i][j]=psum[i][j-1] + ((s.charAt(j)!=ch1)?1:0);
                }else if(j%3==2){
                    psum[i][j]=psum[i][j-1] + ((s.charAt(j)!=ch2)?1:0);
                }
            }
        }

        for(int i=0;i<m;i++){
            int l=fs.nextInt();
            int r=fs.nextInt();
            l--;
            r--;
            if(l==r){
                out.append(0).append('\n');
                continue;
            }
            if((r-l)==1){
                if(s.charAt(r)!=s.charAt(l)){
                    out.append(0).append('\n');
                }else{
                    out.append(1).append('\n');
                }
                continue;
            }
            int res=Integer.MAX_VALUE;
            for(int j=0;j<6;j++){
                int ans=psum[j][r]-((l==0)?0:psum[j][l-1]);
                res=Math.min(ans,res);
            }
            out.append(res).append('\n');
        }
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