import java.io.*;
import java.util.*;

public class B_Lucky_Numbers_easy {

//-----Customize for each ques-----

    static List<Long> superLucky = new ArrayList<>();

    static void generatePermutations(int n) {
        int[] digits = new int[2 * n];
        for (int i = 0; i < n; i++)     digits[i] = 4;
        for (int i = n; i < 2 * n; i++) digits[i] = 7;

        do {
            StringBuilder sb = new StringBuilder();
            for (int d : digits) sb.append(d);
            superLucky.add(Long.parseLong(sb.toString()));
        } while (nextPermutation(digits));
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) i--;
        if (i < 0) return false;

        int j = arr.length - 1;
        while (arr[j] <= arr[i]) j--;

        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;

        int left = i + 1, right = arr.length - 1;
        while (left < right) {
            temp = arr[left]; arr[left] = arr[right]; arr[right] = temp;
            left++; right--;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        // Pregenerate all 223 super lucky numbers
        for (int n = 1; n <= 5; n++) generatePermutations(n);
        Collections.sort(superLucky);

        solve();
        System.out.print(out);
    }

/*

PS :
 Small Observations:
 - Super lucky numbers only have digits 4 and 7, with equal count
 - n <= 1e9 so max length needed is 10 (5 fours + 5 sevens)
 - Total super lucky numbers = C(2,1)+C(4,2)+C(6,3)+C(8,4)+C(10,5) = 1+6+20+70+126 = 223
 - Pregenerate all 223, sort, binary search for first >= n

Claims on Algo:
 - Always an answer exists since 4444477777 > 1e9

*/

    static void solve() throws Exception {
        long n = fs.nextLong();

        // Binary search: first element >= n 
        int lo = 0, hi = superLucky.size() - 1, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (superLucky.get(mid) >= n) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        out.append(superLucky.get(ans)).append("\n");
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