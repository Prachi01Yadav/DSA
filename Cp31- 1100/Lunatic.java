import java.util.*;

public class Lunatic {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            int n=sc.nextInt();
            if(n==1){
                System.out.println(0);
                continue;
            }
            long[] arr=new long[(int)n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextLong();
            }
            long g=0;
            for(int i=0;i<n/2;i++){
               long diff=Math.abs(arr[i]-arr[n-i-1]);
               g=gcd(diff,g);
            }
            System.out.println(g);
            
        }
    }
    private static long gcd(long a,long b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
}
