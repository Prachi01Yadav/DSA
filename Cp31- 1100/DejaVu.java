import java.util.*;

public class DejaVu {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            int n=sc.nextInt();
            int q=sc.nextInt();
            int[] x=new int[q];
            int[] a=new int[n];

            for(int i=0;i<n;i++){
                a[i]=sc.nextInt();
            }

            for(int j=0;j<q;j++){
                x[j]=sc.nextInt();
            }
            int prev=31;
            for(int i=0;i<q;i++){
                if(x[i] > prev) continue;
                 int div = 1 << x[i];        
                int add = 1 << (x[i] - 1);
                for(int j=0;j<n;j++){
                    if(a[j] % div ==0){
                        a[j]+=add;
                    }
                }
                prev=x[i];
            }

           for(int k :a){
            System.out.print(k + " ");
           }
           System.out.println();
        }
    }
}
