import java.util.*;

public class BuildingAnAquarium {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            int n=sc.nextInt();
            long x=sc.nextLong();

            int[] arr=new int[n];
            long minEl=Long.MAX_VALUE;
            long maxEl=Long.MIN_VALUE;
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
                if(arr[i] <minEl){
                    minEl=arr[i];
                }
                if(arr[i] >maxEl){
                      maxEl=arr[i];
                }
            }
            long s=minEl;
            long e=(long) 1e12;
            long ans=-1;
            while(s<=e){
                long mid= s + (e-s)/2;
                if(isPossible(x,mid,arr)){
                    ans=mid;
                    s=mid+1;
                }else{
                    e=mid-1;
                }
            }
            System.out.println(ans);

            
        }
    }

    private static boolean isPossible(long x, long h, int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (h - arr[i] >= 0) {
                sum += (h - arr[i]);
            }
        }
        return sum <= x;
    }
}
