import java.io.*;
import java.util.*;

public class ArrayGame {

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

    // ---------- SHORT PRINT FUNCTION ----------
    static void print(Object x) {
        out.append(x).append('\n');
    }

    // -----------SOlve function----------

    static void solve() throws Exception {
        long n = fs.nextLong();
        long k = fs.nextLong();

        long[] arr = new long[(int) n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }
        Arrays.sort(arr);

        if (k >= 3) {
            System.out.println(0);
            return;
        }

        long ans=arr[0];

        if(k==1){
            for(int i=1;i<n;i++){
                ans=Math.min(ans,Math.abs(arr[i]-arr[i-1]));
            }
            System.out.println(ans);
        }else{
            long res=Long.MAX_VALUE;

            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    long d=Math.abs(arr[i]-arr[j]);
                      res = Math.min(res, d);
                    res=Math.min(res,BS(d,arr));

                    if(res==0) break;
                }
                if(res==0) break;
            }

            System.out.println(res);

        }

    }

    private static long BS(long target,long[] arr){
        long s=0,e=arr.length-1;
        long res=Long.MAX_VALUE;

        while(s<=e){
            long mid= s+ (e-s)/2;

            res=Math.min(res,Math.abs(arr[(int)mid]-target));

            if(arr[(int)mid]==target){
                return 0;
            }else if(arr[(int)mid] < target){
                s=mid+1;
            }else{
                e=mid-1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
}