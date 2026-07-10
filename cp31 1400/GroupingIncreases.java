import java.io.*;
import java.util.*;

public class GroupingIncreases {

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

    // ---------- SHORT PRINT FUNCTION ----------
    static void print(Object x) { out.append(x).append('\n'); }

    //---------Logic here--------------

    static void solve() throws Exception {
        int n=fs.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=fs.nextInt();
        }

        ArrayList<Integer> a =new ArrayList<>();
        ArrayList<Integer> b=new ArrayList<>();
        int prevA=Integer.MAX_VALUE;
        int prevB=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int el=arr[i];
            if(el <= prevA && el<=prevB){
                if(prevA<prevB){
                    a.add(el);
                    prevA=el;
                }else{
                    b.add(el);
                    prevB=el;
                }
            }else if(el<=prevB){
                b.add(el);
                prevB=el;
            }else if(el<=prevA){
                a.add(el);
                prevA=el;
            }
            else if(el>prevA && el> prevB){
                if(prevA<prevB){
                   a.add(el);
                   prevA=el;
                }else{
                    b.add(el);
                    prevB=el;
                }
            }
        }
        int indices1=0;
        int indices2=0;

        for(int i=0;i<a.size()-1;i++){
            if(a.get(i)<a.get(i+1)){
                indices1++;
            }
        }
        for(int i=0;i<b.size()-1;i++){
            if(b.get(i)<b.get(i+1)){
                indices2++;
            }
        }
        if(n==1){
            System.out.println(0);
        }else{
            System.out.println(indices1+indices2);
        }
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}