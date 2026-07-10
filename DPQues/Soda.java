import java.util.*;

public class Soda {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int i = 0; i < n; i++) b[i] = sc.nextInt();

        int total = 0;
        for (int x : a) total += x;

        int maxCap = 0;
        for (int x : b) maxCap += x;

        // dp[i][j][c] = max soda already in kept bottles
        // considering first i bottles, keeping j of them, with total capacity exactly c
        // -1 means state not reachable
        int[][][] dp = new int[n + 1][n + 1][maxCap + 1];
        for (int[][] x : dp) for (int[] y : x) Arrays.fill(y, -1);
        dp[0][0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                for (int c = 0; c <= maxCap; c++) {
                    // skip bottle i
                    if (dp[i-1][j][c] != -1) {
                        dp[i][j][c] = Math.max(dp[i][j][c], dp[i-1][j][c]);
                    }
                    // keep bottle i
                    if (j > 0 && c >= b[i-1] && dp[i-1][j-1][c - b[i-1]] != -1) {
                        dp[i][j][c] = Math.max(dp[i][j][c], dp[i-1][j-1][c - b[i-1]] + a[i-1]);
                    }
                }
            }
        }

        for (int k = 1; k <= n; k++) {
            int bestSoda = -1;
            for (int c = total; c <= maxCap; c++) {
                if (dp[n][k][c] != -1) {
                    bestSoda = Math.max(bestSoda, dp[n][k][c]);
                }
            }
            if (bestSoda != -1) {
                int t = total - bestSoda;
                System.out.println(k + " " + t);
                return;
            }
        }
    }
}
