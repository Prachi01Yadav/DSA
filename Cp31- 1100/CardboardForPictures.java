import java.util.*;

public class CardboardForPictures {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);  
        int t=sc.nextInt();
        while(t-- > 0){
            long n=sc.nextLong();
            long c=sc.nextLong();

            long[] s=new long[(int) n];
            for(int i=0;i<n;i++){
                s[i]=sc.nextLong();
            }

            long l=1;
            long h=(long)1e9;
            long ans=-1;
            while(l<=h){
                long mid= l+ (h-l)/2;

                if(isPossible(mid,c,s)){
                    ans=mid;
                    h=mid-1;
                }else{
                    l=mid+1;
                }
            }
          System.out.println(ans);
        }
    }
    private static boolean isPossible(long w,long c,long[] s){
        long total=0;
        for(int i=0;i<s.length;i++){
            long side=(s[i]+2*w);
            long area=side*side;

            if(total>= c - area){
                return true;
            }
            total+=area;
            
        }
        return total>=c;
    }
}
