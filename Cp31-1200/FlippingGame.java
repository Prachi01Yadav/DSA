import java.util.*;

public class FlippingGame {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        int cnt1=0;
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
            if(arr[i]==1){
                cnt1++;
                arr[i]=-1;
            }else{
                arr[i]=1;
            }
        }

        int j=1;
        int sum=arr[0];
        int maxSum=arr[0];
        while(j<n){
            sum+=arr[j];

            if(sum<arr[j]){
                sum=arr[j];
            }
            maxSum=Math.max(sum,maxSum);
            j++;
        }
        System.out.println(cnt1+maxSum);
    }
}