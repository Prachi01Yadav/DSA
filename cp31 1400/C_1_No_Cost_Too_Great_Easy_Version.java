import java.io.*;
import java.util.*;

public class C_1_No_Cost_Too_Great_Easy_Version {

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
        int t = fs.nextInt();
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

    static boolean shareFactor(int x, HashSet<Integer> set) {
        for (int p = 2; p * p <= x; p++) {
            if (x % p == 0) {
                if (set.contains(p))
                    return true;
                while (x % p == 0)
                    x /= p;
            }
        }
        if (x > 1 && set.contains(x))
            return true;
        return false;
    }

    static void addFactors(int x, HashSet<Integer> set) {
        for (int p = 2; p * p <= x; p++) {
            if (x % p == 0) {
                set.add(p);
                while (x % p == 0)
                    x /= p;
            }
        }
        if (x > 1)
            set.add(x);
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++)
            a[i] = fs.nextInt();
        for (int i = 0; i < n; i++)
            fs.nextInt();

        HashSet<Integer> primes = new HashSet<>();

        for (int x : a) {
            if (shareFactor(x, primes)) {
                System.out.println(0);
                return;
            }
            addFactors(x, primes);
        }

        HashSet<Integer> plusOne = new HashSet<>();

        for (int x : a) {
            int y = x + 1;
            for (int p = 2; p * p <= y; p++) {
                if (y % p == 0) {
                    if (primes.contains(p)) {
                        System.out.println(1);
                        return;
                    }
                    while (y % p == 0)
                        y /= p;
                }
            }
            if (y > 1 && primes.contains(y)) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(2);
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