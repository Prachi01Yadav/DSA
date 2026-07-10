import java.io.*;
import java.util.*;

public class AddModuloTen {

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        long[] arr = new long[n];
        long el = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
            if(arr[i]%2==1){
                arr[i]=arr[i]+arr[i]%10;
            }
        }

        boolean allEqual=true;
        for(int i=1;i<n;i++){
            if(arr[i]!=arr[0]){
                allEqual=false;
                break;
            }
        }
        if(allEqual){
            System.out.println("YES");
            return;
        }
        boolean oneZero=false;
        for(long x:arr){
            if(x%10==0){
                oneZero=true;
                break;
            }
        }
        if(oneZero){
            System.out.println("NO");
            return;
        }

        
        for(int i=0;i<n;i++){
            while(arr[i]%10!=2){
                arr[i]+=arr[i]%10;
            }
            arr[i]%=20;
        }

        allEqual=true;
        for(int i=1;i<n;i++){
            if(arr[i]!=arr[0]){
                allEqual=false;
                break;
            }
        }
        
        if(allEqual){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

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