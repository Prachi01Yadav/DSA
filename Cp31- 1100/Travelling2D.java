import java.util.*;

public class Travelling2D {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            long n=sc.nextLong();
            long k=sc.nextLong();
            int a=sc.nextInt();
            int b=sc.nextInt();
            a--;
            b--;

            long[][] x=new long[(int)n][2];
            for(int i=0;i<n;i++){
                x[i][0]=sc.nextLong();
                x[i][1]=sc.nextLong();
            }
            long ans=Math.abs(x[(int)a][0]-x[(int)b][0])+Math.abs(x[a][1]-x[b][1]);
            long minSrc=Long.MAX_VALUE;
            long minDest=Long.MAX_VALUE;
            for(int i=0;i<k;i++){
                minSrc=Math.min(minSrc , Math.abs(x[a][0]-x[i][0])+Math.abs(x[a][1] - x[i][1]));
                minDest=Math.min(minDest , Math.abs(x[b][0]-x[i][0])+Math.abs(x[b][1] - x[i][1]));
            }
            if(k>0){
            ans=Math.min(ans , minSrc + minDest);
            }
            System.out.println(ans);

        }
    }
}
