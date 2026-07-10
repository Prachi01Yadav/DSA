import java.io.*;
import java.util.*;

public class VikaBridge {

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


    //----------Logic here---------
    static void solve() throws Exception {
        int n=fs.nextInt();
        int k=fs.nextInt();
        int[] c=new int[n];
        for(int i=0;i<n;i++){
            c[i]=fs.nextInt();
        }

        int s=0;
        int e=n;
        int ans=n;
        while(s<=e){
            int mid= s+ (e-s)/2;

            if(isPossible(mid,k,c)){
               ans=mid;
               e=mid-1;
            }else{
                s=mid+1;
            }
        }
        System.out.println(ans);
    }

    private static boolean isPossible(int maxStep, int k, int[] arr){
    int n = arr.length;
    Set<Integer> colors = new HashSet<>();
    for (int x : arr) colors.add(x); 

    for (int color : colors) {
        int last = -1;
        int overGap = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == color) {
                if (last != -1 && i - last - 1 > maxStep) overGap++;
                last = i;
            }
        }
        if (last != -1 && n - 1 - last > maxStep) overGap++;
        if (overGap <= 1) return true; 
    }
    return false;
}



    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}