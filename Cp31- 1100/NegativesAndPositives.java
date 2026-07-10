import java.util.*;

public class NegativesAndPositives {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            int n=sc.nextInt();
            long[] arr=new long[n];
            long cntneg=0;
            long sum=0;
            long minEl=Long.MAX_VALUE;
            long zero=0;
            for(int i=0;i<n;i++){
                arr[i]=sc.nextLong();
                sum+=Math.abs(arr[i]);
                minEl=Math.min(minEl,Math.abs(arr[i]));
                if(arr[i]==0){
                    zero++;
                }
                if(arr[i] < 0){
                    cntneg++;
                }
            }

             if(cntneg % 2==0 || zero>0){
                System.out.println(sum);
             }else{
                System.out.println(sum - 2 * minEl);
             }
        }
    }
}
