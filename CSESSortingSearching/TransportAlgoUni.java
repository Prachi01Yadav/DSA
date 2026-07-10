import java.io.*;
import java.util.*;

public class TransportAlgoUni {

//-----Customize for each ques-----

 /*   static long[] segTree;
    static long[] arr;

    static void buildTree(int i,int l,int r){
        if(l==r){
            segTree[i]=arr[l];
            return;
        }
        int mid= l + (r-l)/2;
        buildTree(2*i +1,l,mid);
        buildTree(2*i +2, mid+1,r);
        segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
    }

    static long query(int s,int e,int i,int l,int r){
        if(e<l || s>r){
            return -1;
        }
        if(l>=s && r<=e){
            return segTree[i];
        }
        int mid=l+ (r-l)/2;
        long left=query(s, e, 2*i +1, l, mid);
        long right=query(s, e, 2*i +2, mid+1, r);
        if(left==-1) return right;
        if(right==-1) return left;
        return (left & right);
    }*/ 

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }


/*

PS : 
 Small Observations: 


Claims on Algo



*/

    static void solve() throws Exception {
        int n=fs.nextInt();
        int m=fs.nextInt();
        int c=fs.nextInt();
        long mx=Long.MIN_VALUE;
        long[] a=new long[n];
        for(int i=0;i<n;i++){
            a[i]=fs.nextLong();
            mx=Math.max(mx,a[i]);
        }

        
        Arrays.sort(a);

        long s=-1;
        long e=mx;
        while(s+1<e){
            long mid=s+(e-s)/2;
            if(isPoss(mid,m,c,a,n)){
                e=mid;
            }else{
                s=mid;
            }
        }
        System.out.println(e);
    }
    static boolean isPoss(long dff,long bus,long cap,long[] a,int n){
        long buses=1;
        long currCap=1;
        int i=0;
        long firstTime=a[0];
        while(i<n){
            if(currCap>cap || (a[i]-firstTime)>dff){
                buses++;
                currCap=1;
                firstTime=a[i];
            }
            currCap++;
            i++;
        }
        if(buses<=bus) return true;
        else return false;
    }






    // nCr using factorial + modular inverse -> Works when MOD is prime (ex: 1e9+7)

    static final int MAX = 1000005;
    static final long MOD = 1_000_000_007;

    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];


    // Precompute factorials and inverse factorials
    static void precompute() {

        fact[0] = 1;

        for(int i = 1; i < MAX; i++)
            fact[i] = (fact[i-1] * i) % MOD;

        invFact[MAX-1] = modPow(fact[MAX-1], MOD-2);

        for(int i = MAX-2; i >= 0; i--)
            invFact[i] = (invFact[i+1] * (i+1)) % MOD;
    }


    // Fast exponentiation
    static long modPow(long a, long b){
        long res = 1;
        while(b > 0){
            if((b & 1) == 1)
                res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    // nCr calculation
    static long nCr(int n, int r){

        if(r > n || r < 0)
            return 0;

        return (((fact[n] * invFact[r]) % MOD) * invFact[n-r]) % MOD;
    }



/*-------------For Fast I/O --------------------------*/

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

}