import java.util.*;
public class Demodogs {
    private static final long mod = 1000000007L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            long n = sc.nextLong();

            long ans =
                (
                    (
                        (((n * (n + 1)) % mod * (2 * n + 1)) % mod * 166666668L) % mod
                      + (((n * (n - 1)) % mod * (n + 1)) % mod * 333333336L) % mod
                    ) % mod
                * 2022
                ) % mod;

            System.out.println(ans);
        }
    }
}
