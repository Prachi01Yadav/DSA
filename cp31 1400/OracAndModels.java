import java.io.*;
import java.util.*;

public class OracAndModels {

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
    // static int n;
    // static int[][] t;

    static void solve() throws Exception {
        int n=fs.nextInt();
        long[] s=new long[n+1];
        for(int i=1;i<=n;i++){
            s[i]=fs.nextLong();
        }

        int[] dp=new int[n+1];
        Arrays.fill(dp,1);

        int maxLis=0;
        for(int i=1;i<=n;i++){
            maxLis=Math.max(maxLis,dp[i]);

            for(int j=2*i;j<=n;j+=i){
                if(s[j]>s[i]){
                    dp[j]=Math.max(dp[j],1+dp[i]);
                }
            }
        }
        System.out.println(n==0 ? 0:maxLis);
    }
    //    int  n = fs.nextInt();

    //    long[] s=new long[n];
    //    for(int i=1;i<=n;i++){
        // s[i]=fs.nextLong();
    //    }

    //    ArrayList<Pair> list=new ArrayList<>();
    //    
    //    for(int i=1;i<=n;i++){
        //  if(list.size()==0 || (((list.get(list.size() -1 ).y) % i)==0 && list.get(list.size()-1).x <s[i])){
            // list.add(new Pair(s[i],i));
        //  }else{
            // int idx=BS(s[i],list);
            // list.set(idx,new Pair(s[i],i));
        //  }
    //    }
    //    System.out.println(list.size());     
    // }
    // private static int BS(long target,ArrayList<Pair> list ){
        // int s=0;
        // int e=list.size()-1;
// 
        // while(s<=e){
            // int mid=s + (e-s)/2;
// 
            // if(list.get(mid).x == target){
                // return mid;
            // }else if(list.get(mid).x<target){
                // s=mid+1;
            // }else{
                // e=mid-1;
            // }
        // }
        // return s;
// 
    // }
// 

        // long[] s=new long[n+1];
        // 
        // for(int i=1;i<=n;i++){
            // s[i]=fs.nextLong();
        // }

        // int[] dp=new int[n+1];
    //    
        // int maxLis=0;
        // for(int i=1;i<=n;i++){
            // dp[i]=1;
            // for(int j=1;j<i;j++){
                // if(i%j==0 && s[i]>s[j]){
                    // dp[i]=Math.max(dp[i] , 1+ dp[j]);
                // }
            // }
            // maxLis=Math.max(maxLis,dp[i]);
        // }
        // System.out.println(maxLis);
    // }

        // t=new int[n+1][n+1];
        // for(int[] d:t){
            // Arrays.fill(d,-1);
        // }
        // long[] s = new long[n + 1];
        // s[0] = 0;
        // for (int i = 1; i <= n; i++) {
            // s[i] = fs.nextLong();
        // }
        // if (n == 1) {
            // System.out.println(1);
            // return;
        // }

        // long ans = help(1, s, 0);
        // System.out.println(ans);
    // }

    // private static int help(int index, long[] s, int prev) {
        // if (index > n)
            // return 0;

        // if(t[index][prev] != -1){
            // return t[index][prev];
        // }
        // int take = 0;
        // if ((prev == 0) || (s[prev] < s[index] && index % prev == 0)) {
            // take = 1 + help(index + 1, s, index);
        // }
        // int skip = help(index + 1, s, prev);

        // return  t[index][prev]= Math.max(take, skip);
    // }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }
    static class Pair{
        long x;
        int y;
        Pair(long x,int y){
            this.x=x;
            this.y=y;
        }
    }
}