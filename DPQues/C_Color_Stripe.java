// import java.io.*;
// import java.util.*;

// public class C_Color_Stripe {

//     // -----Customize for each ques-----
//     //
//     // static long[] segTree;
//     // static long[] arr;
//     //
//     // static void buildTree(int i,int l,int r){
//     // if(l==r){
//     // segTree[i]=arr[l];
//     // return;
//     // }
//     // int mid= l + (r-l)/2;
//     // buildTree(2*i +1,l,mid);
//     // buildTree(2*i +2, mid+1,r);
//     // segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
//     // }
//     //
//     // static long query(int s,int e,int i,int l,int r){
//     // if(e<l || s>r){
//     // return -1;
//     // }
//     // if(l>=s && r<=e){
//     // return segTree[i];
//     // }
//     // int mid=l+ (r-l)/2;
//     // long left=query(s, e, 2*i +1, l, mid);
//     // long right=query(s, e, 2*i +2, mid+1, r);
//     // if(left==-1) return right;
//     // if(right==-1) return left;
//     // return (left & right);
//     // }
//     //
//     /*
//      * Small Obsetvations:
//      * 
//      * DP(level)-> minm moves required to make level....n-1 valid, assuming
//      * 0...level is a valid sequence
//      * 
//      * Transitions:
//      * DP(level,prev)-->DP(i,0)-->if(color[prev]==color[level])-->Dp(level,prev)=1+
//      * dp(level,ch);
//      * DP(level,prev)-->dp(level+1,color[level]);
//      * 
//      */

//     public static void main(String[] args) throws Exception {
//         solve();
//     }

//     static void solve() throws Exception {
//         int n = fs.nextInt();
//         int k = fs.nextInt();

//         String s=fs.next();
//         int[][] dp=new int[n+1][26];

//         for(int c=0;c<k;c++){
//             dp[n][c]=0;
//         }

//         int[][] choice=new int[n][k];
//         for(int i=n-1;i>=0;i--){
//             int curr=s.charAt(i)-'A';
//             for(int prev=0;prev<k;prev++){
//                 int ans=Integer.MAX_VALUE;
//                 int bestClr=0;
//                 for(int nc=0;nc<k;nc++){
//                     if(nc==prev) continue;
//                     int cost=(nc==curr? 0:1) + dp[i+1][nc];
//                     if(cost<ans){
//                         ans=cost;
//                         bestClr=nc;
//                     }
//                 }
//                 dp[i][prev]=ans;
//                 choice[i][prev]=bestClr;
//             }
//         }

//         int res=Integer.MAX_VALUE;
//         int minIdx=0;
//         for(int i=0;i<k;i++){
//             if(dp[0][i]<res){
//                 res=dp[0][i];
//                 minIdx=i;
//             }
//         }
//         System.out.println(res);
//         StringBuilder sb=new StringBuilder();
//         for(int i=0;i<n;i++){
//             sb.append((char)(choice[i][minIdx]+'A'));
//             minIdx=choice[i][minIdx];
//         }
//         System.out.println(sb.toString());
//     }

//     /*-------------For Fast I/O --------------------------*/

//     private static final class FastScanner {
//         private final byte[] buffer = new byte[1 << 16];
//         private int ptr = 0, len = 0;
//         private final InputStream in;

//         FastScanner(InputStream in) {
//             this.in = in;
//         }

//         private int readByte() throws IOException {
//             if (ptr >= len) {
//                 len = in.read(buffer);
//                 ptr = 0;
//                 if (len <= 0)
//                     return -1;
//             }
//             return buffer[ptr++];
//         }

//         long nextLong() throws IOException {
//             int c;
//             do {
//                 c = readByte();
//             } while (c <= ' ' && c != -1);
//             boolean neg = false;
//             if (c == '-') {
//                 neg = true;
//                 c = readByte();
//             }
//             long val = 0;
//             while (c > ' ') {
//                 val = val * 10 + (c - '0');
//                 c = readByte();
//             }
//             return neg ? -val : val;
//         }

//         int nextInt() throws IOException {
//             return (int) nextLong();
//         }

//         String next() throws IOException {
//             int c;
//             do {
//                 c = readByte();
//             } while (c <= ' ' && c != -1);
//             StringBuilder sb = new StringBuilder();
//             while (c > ' ') {
//                 sb.append((char) c);
//                 c = readByte();
//             }
//             return sb.toString();
//         }
//     }

//     static final long INF = (long) 1e18;
//     static FastScanner fs = new FastScanner(System.in);
//     static StringBuilder out = new StringBuilder();

// }


import java.io.*;
import java.util.*;

public class C_Color_Stripe {

    public static void main(String[] args) throws Exception {
        solve();
    }

    static void solve() throws Exception {
        int n = fs.nextInt();
        int k = fs.nextInt();

        String s = fs.next();

        // rolling DP: only 2 rows
        int[] next = new int[k];   // dp[i+1][*]
        int[] currDP = new int[k]; // dp[i][*]

        // to reconstruct
        int[][] choice = new int[n][k];

        // base case: dp[n][c] = 0 (already 0 in next[])

        for (int i = n - 1; i >= 0; i--) {
            int curr = s.charAt(i) - 'A';

            for (int prev = 0; prev < k; prev++) {
                int ans = Integer.MAX_VALUE;
                int bestClr = 0;

                for (int nc = 0; nc < k; nc++) {
                    if (nc == prev) continue;

                    int cost = (nc == curr ? 0 : 1) + next[nc];
                    if (cost < ans) {
                        ans = cost;
                        bestClr = nc;
                    }
                }

                currDP[prev] = ans;
                choice[i][prev] = bestClr;
            }

            // move currDP -> next
            int[] temp = next;
            next = currDP;
            currDP = temp;
        }

        int res = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 0; i < k; i++) {
            if (next[i] < res) { // dp[0][i] is now in next[]
                res = next[i];
                minIdx = i;
            }
        }

        System.out.println(res);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char) (choice[i][minIdx] + 'A'));
            minIdx = choice[i][minIdx];
        }
        System.out.println(sb.toString());
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

    static FastScanner fs = new FastScanner(System.in);
}