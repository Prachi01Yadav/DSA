package DPQUESPRACTICE;
import java.util.*;

public class DivisibleBy25Rec {

    static final int INF = Integer.MAX_VALUE / 2;
    static int[][] memo;
    static String num;
    static String target;

    static int rec(int i, int state) {
        if (state == 2) return 0;
        if (i < 0) return INF;
        if (memo[i][state] != -1) return memo[i][state];

        int deleteCost = rec(i - 1, state);
        if (deleteCost != INF) deleteCost += 1;

        int keepCost = INF;
        if (state == 0 && num.charAt(i) == target.charAt(1)) {
            keepCost = rec(i - 1, 1);
        } else if (state == 1 && num.charAt(i) == target.charAt(0)) {
            keepCost = rec(i - 1, 2);
        } else {
            keepCost = rec(i - 1, state);
        }

        return memo[i][state] = Math.min(deleteCost, keepCost);
    }

    static int solve(String n) {
        num = n;
        int ans = INF;
        for (String t : new String[]{"00", "25", "50", "75"}) {
            target = t;
            memo = new int[n.length()][3];
            for (int[] row : memo) Arrays.fill(row, -1);
            ans = Math.min(ans, rec(n.length() - 1, 0));
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) System.out.println(solve(sc.next()));
    }
}