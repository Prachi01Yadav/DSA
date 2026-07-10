import java.util.*;
public class Quests {
    public static void main(String[] args) {  
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            int n=sc.nextInt();
            int k=sc.nextInt();

            int[] a=new int[n];
            for(int i=0;i<n;i++){
                a[i]=sc.nextInt();
            }
            int[] b=new int[n];
            for(int i=0;i<n;i++){
                b[i]=sc.nextInt();
            }

            int sumA=0;
            int maxB=0;
            long ans=0;

            for(int i=0;i<n && i< k ;i++){
                sumA+=a[i];

                maxB=Math.max(maxB,b[i]);
                long current= sumA + (long)(k-i-1)*maxB;   //prefixSUm and prefixMaxB

                ans=Math.max(ans,current);
            }
            
            System.out.println(ans);
        }
    }
}



// import java.util.*;
// 
// public class Quests {
    // public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int t = sc.nextInt();
// 
        // while (t-- > 0) {
            // int n = sc.nextInt();
            // int k = sc.nextInt();
// 
            // int[] a = new int[n + 1];
            // int[] b = new int[n + 1];
// 
            // for (int i = 1; i <= n; i++) {
                // a[i] = sc.nextInt();
            // }
            // for (int i = 1; i <= n; i++) {
                // b[i] = sc.nextInt();
            // }
// 
            // long[] prefixA = new long[n + 1];
            // int[] prefixMaxB = new int[n + 1];
// 
            // for (int i = 1; i <= n; i++) {
                // prefixA[i] = prefixA[i - 1] + a[i];
                // prefixMaxB[i] = Math.max(prefixMaxB[i - 1], b[i]);
            // }
// 
            // long ans = 0;
            // int limit = Math.min(n, k);
// 
            // for (int x = 1; x <= limit; x++) {
                // long curr =
                        // prefixA[x] + (long)(k - x) * prefixMaxB[x];
                // ans = Math.max(ans, curr);
            // }
// 
            // System.out.println(ans);
        // }
    // }
// }
// 