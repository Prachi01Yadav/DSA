import java.util.*;

public class SubsequenceAdditionHard {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while (t-- >0) {
            int n=sc.nextInt();
            long[] c=new long[n];
            for(int i=0;i<n;i++){
                c[i]=sc.nextLong();
            }

            
            Arrays.sort(c);
            if(c[0] != 1){
                System.out.println("No");
                continue;
            }
            long sum=c[0];
            boolean canMake=true;
            for(int i=1;i<n;i++){
                if(c[i] > sum){
                    canMake=false;
                    break;
                }

                sum+=c[i];
            }
            if(canMake){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
          
        }
    }
}
