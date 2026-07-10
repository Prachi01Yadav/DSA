import java.util.*;
public class EraseFirstOrSecondLetter {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0){
            int n=sc.nextInt();
            String s=sc.next();

            int[] arr=new int[n];
            Arrays.fill(arr,0);
             
            Map<Character,Integer> mp=new HashMap<>();
            int count=0;
            for(int i=0;i<n;i++){
                mp.put(s.charAt(i),mp.getOrDefault(s.charAt(i),0)+1);

                if(mp.get(s.charAt(i))==1){
                    count++;
                }
                arr[i]=count;
            }
            long ans=0;

            for(int i=0;i<n;i++){
                ans+=arr[i];
            }

            System.out.println(ans);
        }
    }
}

// Looks like recursion but we cant do that bcz of higher TC, so for each valid string or length i we have total distinct strings 
//i=0 se i=i tak k distinct characters

//TC= O(nlogn)
//Sc= O(n)