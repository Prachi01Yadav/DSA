import java.util.*;

public class TonsofTNT {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
        sc.close();
    }
    private static void solve(Scanner sc){
        int n=sc.nextInt();
        long[] arr=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextLong();
        }

        long[] pre=new long[n];
        pre[0]=arr[0];
        for(int i=1;i<n;i++){
            pre[i]=arr[i]+pre[i-1];
        }

        long ans=0;
        for(int k=1;k<=n;k++){
            if(n%k != 0) continue;
            int start=k-1;
            long max=pre[start];
            long min=pre[start];
            for(int i=start+k;i<n;i+=k){
                max=Math.max(max, pre[i]-pre[i-k]);
                min=Math.min(min, pre[i]-pre[i-k]);
            }
            ans=Math.max(ans, max-min);
        }
        System.out.println(ans);
    }
}
