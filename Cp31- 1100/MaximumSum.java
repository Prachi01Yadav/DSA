import java.util.*;

public class MaximumSum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            int n=sc.nextInt();
            int k=sc.nextInt();

            long[] arr=new long[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextLong();
            }

            Arrays.sort(arr);

            long[] preSum=new long[n];
            preSum[0]=arr[0];
           
            for(int i=1;i<n;i++){
                preSum[i]=arr[i]+preSum[i-1];
            }
            long ans=0;
            for(int x=0;x<=k;x++){
                int y=k-x;
                int left=2*x;
                int right=n-y-1;
                if(left>right) continue;
                long sum=preSum[right]-(left==0? 0:preSum[left-1]);
                ans=Math.max(ans,sum);

            }
            System.out.println(ans);
        }
    }
}
