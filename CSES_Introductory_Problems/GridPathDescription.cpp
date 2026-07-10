// // import java.io.*;
// // import java.util.*;

// // public class GridPathDescription {

// // //-----Customize for each ques-----

// //  /*   static long[] segTree;
// //     static long[] arr;

// //     static void buildTree(int i,int l,int r){
// //         if(l==r){
// //             segTree[i]=arr[l];
// //             return;
// //         }
// //         int mid= l + (r-l)/2;
// //         buildTree(2*i +1,l,mid);
// //         buildTree(2*i +2, mid+1,r);
// //         segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
// //     }

// //     static long query(int s,int e,int i,int l,int r){
// //         if(e<l || s>r){
// //             return -1;
// //         }
// //         if(l>=s && r<=e){
// //             return segTree[i];
// //         }
// //         int mid=l+ (r-l)/2;
// //         long left=query(s, e, 2*i +1, l, mid);
// //         long right=query(s, e, 2*i +2, mid+1, r);
// //         if(left==-1) return right;
// //         if(right==-1) return left;
// //         return (left & right);
// //     }*/ 

// //     public static void main(String[] args) throws Exception {
// //         int t = 1;
// //         while (t-- > 0) {
// //             solve();
// //         }
// //         System.out.print(out);
// //     }


// // /*

// // PS : 
// //  Small Observations: 


// // Claims on Algo



// // */

// //     static void solve() throws Exception {
// //         s=fs.next();
// //         n=s.length();
// //         cnt=0;
// //         boolean[][] vis=new boolean[7][7];
// //         vis[0][0]=true;
// //         rec(0,0,0,vis);
       
// //         System.out.println(cnt);

// //     }
// //     static int n;
// //     static int[][] dir=new int[][]{
// //         {0,1},
// //         {0,-1},
// //         {1,0},
// //         {-1,0}
// //     };
// //     static String s;
// //     static boolean inBound(int x,int y){
// //         return (x>=0 && y>=0 && x<7 && y<7);
// //     }
// //     static int cnt;
// //     static void rec(int i,int j,int steps,boolean[][] vis){
// //         if(i==6 && j==0){
// //             if(steps==48){
// //                  cnt++;
// //                  return;
// //             }
// //             return;
// //         }
// //         if((!inBound(i-1,j) || vis[i-1][j]) && (!inBound(i+1,j) || vis[i+1][j])){
// //             if(inBound(i,j-1) && !vis[i][j-1] && inBound(i,j+1) && !vis[i][j+1]){
// //                 return;
// //             }
// //         }
// //         if((!inBound(i,j-1) || vis[i][j-1]) && (!inBound(i,j+1) || vis[i][j+1])){
// //             if(inBound(i-1,j) && !vis[i-1][j] && inBound(i+1,j) && !vis[i+1][j]){
// //                 return;
// //             }
// //         }


// //         for(int[] d:dir){
// //             char ch='a';
// //             if(d[0]==0 && d[1]==1){
// //                 ch='R';
// //             }
// //             if(d[0]==0 && d[1]==-1){
// //                 ch='L';
// //             }
// //             if(d[0]==1 && d[1]==0){
// //                 ch='D';
// //             }
// //             if(d[0]==-1 && d[1]==0){
// //                 ch='U';
// //             }
// //             int i_=i+d[0];
// //             int j_=j+d[1];
// //             if(i_>=0 && j_>=0 && i_<7 && j_<7 && !vis[i_][j_] && (s.charAt(steps)==ch || s.charAt(steps)=='?')){
// //                 vis[i_][j_]=true;
// //                 rec(i_,j_,steps+1,vis);
// //                 vis[i_][j_]=false;
// //             }
// //         }

// //     }






// //     // nCr using factorial + modular inverse -> Works when MOD is prime (ex: 1e9+7)

// //     static final int MAX = 1000005;
// //     static final long MOD = 1_000_000_007;

// //     static long[] fact = new long[MAX];
// //     static long[] invFact = new long[MAX];


// //     // Precompute factorials and inverse factorials
// //     static void precompute() {

// //         fact[0] = 1;

// //         for(int i = 1; i < MAX; i++)
// //             fact[i] = (fact[i-1] * i) % MOD;

// //         invFact[MAX-1] = modPow(fact[MAX-1], MOD-2);

// //         for(int i = MAX-2; i >= 0; i--)
// //             invFact[i] = (invFact[i+1] * (i+1)) % MOD;
// //     }


// //     // Fast exponentiation
// //     static long modPow(long a, long b){
// //         long res = 1;
// //         while(b > 0){
// //             if((b & 1) == 1)
// //                 res = (res * a) % MOD;
// //             a = (a * a) % MOD;
// //             b >>= 1;
// //         }
// //         return res;
// //     }

// //     // nCr calculation
// //     static long nCr(int n, int r){

// //         if(r > n || r < 0)
// //             return 0;

// //         return (((fact[n] * invFact[r]) % MOD) * invFact[n-r]) % MOD;
// //     }



// // /*-------------For Fast I/O --------------------------*/

// //     private static final class FastScanner {
// //         private final byte[] buffer = new byte[1 << 16];
// //         private int ptr = 0, len = 0;
// //         private final InputStream in;

// //         FastScanner(InputStream in) { this.in = in; }

// //         private int readByte() throws IOException {
// //             if (ptr >= len) {
// //                 len = in.read(buffer);
// //                 ptr = 0;
// //                 if (len <= 0) return -1;
// //             }
// //             return buffer[ptr++];
// //         }

// //         long nextLong() throws IOException {
// //             int c; do { c = readByte(); } while (c <= ' ' && c != -1);
// //             boolean neg = false; if (c == '-') { neg = true; c = readByte(); }
// //             long val = 0;
// //             while (c > ' ') { val = val * 10 + (c - '0'); c = readByte(); }
// //             return neg ? -val : val;
// //         }

// //         int nextInt() throws IOException { return (int) nextLong(); }
// //         String next() throws IOException {
// //             int c; do { c = readByte(); } while (c <= ' ' && c != -1);
// //             StringBuilder sb = new StringBuilder();
// //             while (c > ' ') { sb.append((char)c); c = readByte(); }
// //             return sb.toString();
// //         }
// //     }

// //     static final long INF = (long) 1e18;
// //     static FastScanner fs = new FastScanner(System.in);
// //     static StringBuilder out = new StringBuilder();

// // }

// import java.io.*;
// import java.util.*;

// public class GridPathDescription {

//     public static void main(String[] args) throws Exception {
//         int t = 1;
//         while (t-- > 0) {
//             solve();
//         }
//         System.out.print(out);
//     }

//     static void solve() throws Exception {
//         s = fs.next();
//         n = s.length();
//         cnt = 0;
//         boolean[][] vis = new boolean[7][7];
//         vis[0][0] = true;
        
//         char[] path = s.toCharArray();
//         rec(0, 0, 0, vis, path);
        
//         System.out.println(cnt);
//     }

//     static int n;
//     static String s;
//     static int cnt;

//     static void rec(int i, int j, int steps, boolean[][] vis, char[] path) {
//         if (i == 6 && j == 0) {
//             if (steps == 48) {
//                  cnt++;
//             }
//             return;
//         }

//         if (steps == 48) return;

//         boolean up = (i == 0 || vis[i - 1][j]);
//         boolean down = (i == 6 || vis[i + 1][j]);
//         boolean left = (j == 0 || vis[i][j - 1]);
//         boolean right = (j == 6 || vis[i][j + 1]);

//         if (up && down && !left && !right) return;
//         if (left && right && !up && !down) return;

//         char expected = path[steps];

//         if ((expected == '?' || expected == 'D') && !down) {
//             vis[i + 1][j] = true;
//             rec(i + 1, j, steps + 1, vis, path);
//             vis[i + 1][j] = false;
//         }
//         if ((expected == '?' || expected == 'U') && !up) {
//             vis[i - 1][j] = true;
//             rec(i - 1, j, steps + 1, vis, path);
//             vis[i - 1][j] = false;
//         }
//         if ((expected == '?' || expected == 'R') && !right) {
//             vis[i][j + 1] = true;
//             rec(i, j + 1, steps + 1, vis, path);
//             vis[i][j + 1] = false;
//         }
//         if ((expected == '?' || expected == 'L') && !left) {
//             vis[i][j - 1] = true;
//             rec(i, j - 1, steps + 1, vis, path);
//             vis[i][j - 1] = false;
//         }
//     }

//     static final int MAX = 1000005;
//     static final long MOD = 1_000_000_007;

//     static long[] fact = new long[MAX];
//     static long[] invFact = new long[MAX];

//     static void precompute() {
//         fact[0] = 1;
//         for(int i = 1; i < MAX; i++)
//             fact[i] = (fact[i-1] * i) % MOD;
//         invFact[MAX-1] = modPow(fact[MAX-1], MOD-2);
//         for(int i = MAX-2; i >= 0; i--)
//             invFact[i] = (invFact[i+1] * (i+1)) % MOD;
//     }

//     static long modPow(long a, long b){
//         long res = 1;
//         while(b > 0){
//             if((b & 1) == 1)
//                 res = (res * a) % MOD;
//             a = (a * a) % MOD;
//             b >>= 1;
//         }
//         return res;
//     }

//     static long nCr(int n, int r){
//         if(r > n || r < 0)
//             return 0;
//         return (((fact[n] * invFact[r]) % MOD) * invFact[n-r]) % MOD;
//     }

//     private static final class FastScanner {
//         private final byte[] buffer = new byte[1 << 16];
//         private int ptr = 0, len = 0;
//         private final InputStream in;

//         FastScanner(InputStream in) { this.in = in; }

//         private int readByte() throws IOException {
//             if (ptr >= len) {
//                 len = in.read(buffer);
//                 ptr = 0;
//                 if (len <= 0) return -1;
//             }
//             return buffer[ptr++];
//         }

//         long nextLong() throws IOException {
//             int c; do { c = readByte(); } while (c <= ' ' && c != -1);
//             boolean neg = false; if (c == '-') { neg = true; c = readByte(); }
//             long val = 0;
//             while (c > ' ') { val = val * 10 + (c - '0'); c = readByte(); }
//             return neg ? -val : val;
//         }

//         int nextInt() throws IOException { return (int) nextLong(); }
//         String next() throws IOException {
//             int c; do { c = readByte(); } while (c <= ' ' && c != -1);
//             StringBuilder sb = new StringBuilder();
//             while (c > ' ') { sb.append((char)c); c = readByte(); }
//             return sb.toString();
//         }
//     }

//     static final long INF = (long) 1e18;
//     static FastScanner fs = new FastScanner(System.in);
//     static StringBuilder out = new StringBuilder();
// }

#include <iostream>
#include <vector>
#include <string>

using namespace std;

int cnt_paths;
string s;
int n;

void rec(int i, int j, int steps, bool vis[7][7]) {
    if (i == 6 && j == 0) {
        if (steps == 48) {
            cnt_paths++;
        }
        return;
    }

    if (steps == 48) return;

    bool up = (i == 0 || vis[i - 1][j]);
    bool down = (i == 6 || vis[i + 1][j]);
    bool left = (j == 0 || vis[i][j - 1]);
    bool right = (j == 6 || vis[i][j + 1]);

    if (up && down && !left && !right) return;
    if (left && right && !up && !down) return;

    char expected = s[steps];

    if ((expected == '?' || expected == 'D') && !down) {
        vis[i + 1][j] = true;
        rec(i + 1, j, steps + 1, vis);
        vis[i + 1][j] = false;
    }
    if ((expected == '?' || expected == 'U') && !up) {
        vis[i - 1][j] = true;
        rec(i - 1, j, steps + 1, vis);
        vis[i - 1][j] = false;
    }
    if ((expected == '?' || expected == 'R') && !right) {
        vis[i][j + 1] = true;
        rec(i, j + 1, steps + 1, vis);
        vis[i][j + 1] = false;
    }
    if ((expected == '?' || expected == 'L') && !left) {
        vis[i][j - 1] = true;
        rec(i, j - 1, steps + 1, vis);
        vis[i][j - 1] = false;
    }
}

void solve() {
    cin >> s;
    n = s.length();
    cnt_paths = 0;
    
    bool vis[7][7] = {false};
    vis[0][0] = true;
    
    rec(0, 0, 0, vis);
    
    cout << cnt_paths << "\n";
}

const int MAX = 1000005;
const long long MOD = 1000000007;

long long fact[MAX];
long long invFact[MAX];

long long modPow(long long a, long long b) {
    long long res = 1;
    while (b > 0) {
        if ((b & 1) == 1)
            res = (res * a) % MOD;
        a = (a * a) % MOD;
        b >>= 1;
    }
    return res;
}

void precompute() {
    fact[0] = 1;
    for (int i = 1; i < MAX; i++)
        fact[i] = (fact[i - 1] * i) % MOD;
        
    invFact[MAX - 1] = modPow(fact[MAX - 1], MOD - 2);
    
    for (int i = MAX - 2; i >= 0; i--)
        invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
}

long long nCr(int n_val, int r_val) {
    if (r_val > n_val || r_val < 0)
        return 0;
    return (((fact[n_val] * invFact[r_val]) % MOD) * invFact[n_val - r_val]) % MOD;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t = 1;
    while (t-- > 0) {
        solve();
    }
    
    return 0;
}