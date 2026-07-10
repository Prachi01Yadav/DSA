import java.util.*;

public class TeleportersEasy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            long c = sc.nextLong();

            long[] cost = new long[n];
            for (int i = 0; i < n; i++) {
                long a = sc.nextLong();
                cost[i] = a + (i + 1); 
            }

            Arrays.sort(cost);

            long sum = 0;
            int teleporters = 0;

            for (long x : cost) {
                if (sum + x > c) break;
                sum += x;
                teleporters++;
            }

            System.out.println(teleporters);
        }
    }
}
