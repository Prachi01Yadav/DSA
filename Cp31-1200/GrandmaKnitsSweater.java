import java.io.*;
import java.util.*;

public class GrandmaKnitsSweater {

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

    /*-------------Logic Here--------------------------*/
    static int maxFreq;
    static int cnt;

    static void solve() throws Exception {
        int n = fs.nextInt();
        String s = fs.next();
        int ans=Integer.MAX_VALUE;

        for(char c='a';c<='z';c++){
            int l=0;
            int h=n-1;
            int cnt=0;
            boolean possible=true;
            while(l<h){
                if(s.charAt(l)==s.charAt(h)){
                    l++;
                    h--;
                }else if(s.charAt(l)==c){
                    l++;
                    cnt++;
                }else if(s.charAt(h)==c){
                    h--;
                    cnt++;
                }else{
                    possible=false;
                    break;
                }
            }

            if(possible){
                ans=Math.min(ans,cnt);
            }
        }
        if(ans!=Integer.MAX_VALUE){
            System.out.println(ans);
        }else{
            System.out.println(-1);
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