import java.util.*;

public class jojoAdventure {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            String s=sc.next();
            int n=s.length();

            if(n==1){
                System.out.println(s.charAt(0)=='1' ? 1:0);
                continue;
            }
            String snew=s + s;
            long cnt=0;
            long size=0;
            for(int i=0;i<2*n;i++){
                if(snew.charAt(i)=='1'){
                    cnt++;
                }else{
                    cnt=0;
                }
                size=Math.max(cnt,size);
            }

            size=Math.min((long)n,size);
            long side1= (size+1L)/2;
            long side2= (size+2L)/2;

            if(size==n){
                long ans=n*n;
                System.out.println(ans);
            }else{
                System.out.println(side1 * side2);
            }
        }
    }
}
