import java.util.*;

public class D_Kefa_and_Dishes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextLong();
        
        // bonus[x][y] = satisfaction if x eaten immediately before y
        long[][] bonus = new long[n][n];
        for (int i = 0; i < k; i++) {
            int x = sc.nextInt() - 1; // 0-indexed
            int y = sc.nextInt() - 1;
            long c = sc.nextLong();
            bonus[x][y] = c;
        }
        
        // dp[mask][last] = max satisfaction
        // mask = which dishes eaten, last = last dish eaten
        long[][] dp = new long[1 << n][n];
        for (long[] row : dp) Arrays.fill(row, Long.MIN_VALUE);
        
        // Base case: start with each dish as first dish
        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = a[i];
        }
        
        long ans = Long.MIN_VALUE;
        
        // Try every state
        for (int mask = 1; mask < (1 << n); mask++) {
            
            // Only care if exactly m dishes chosen .. check answer
            int dishCount = Integer.bitCount(mask);
            
            for (int last = 0; last < n; last++) {
                
                // Skip invalid states
                if (dp[mask][last] == Long.MIN_VALUE) continue;
                
                // Is 'last' actually in mask?
                if ((mask & (1 << last)) == 0) continue;
                
                // If we've eaten m dishes, update answer
                if (dishCount == m) {
                    ans = Math.max(ans, dp[mask][last]);
                    continue;
                }
                
                // Try adding a new dish
                for (int next = 0; next < n; next++) {
                    
                    // Skip if already eaten
                    if ((mask & (1 << next)) != 0) continue;
                    
                    int newMask = mask | (1 << next);
                    long newSat = dp[mask][last] + a[next] + bonus[last][next];
                    
                    dp[newMask][next] = Math.max(dp[newMask][next], newSat);
                }
            }
        }
        
        System.out.println(ans);
    }
}
// ```
// 
// ---
// 
// ## Quick walkthrough of flow
// ```
// Start → pick any 1 dish as first dish (base case)
    //   → keep adding dishes one by one
    //   → track which dishes used (mask) + last dish
    //   → when mask has m dishes → check if it's max