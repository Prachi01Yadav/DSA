import java.util.Scanner;

public class fibonacci_in_logN {

    
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       long n=sc.nextLong();
       if (n == 0) {
            System.out.println(0);
            return;
       }
       MAT basecase=new MAT(2, 1);
       basecase.mat[0][0]=1;
       basecase.mat[1][0]=0;
       MAT T=new MAT(2,2);
       T.mat[0][0]=1;
       T.mat[0][1]=1;
       T.mat[1][0]=1;
       T.mat[1][1]=0;
       long MOD=1000000007;
       MAT tpow= T.exp(T, n-1, MOD);
       MAT tmulp=T.mulp(tpow, basecase, MOD);
       
       System.out.println(tmulp.mat[0][0]);
    }
    
    static private class MAT{
        private int n;
        private int m;
        private long[][] mat;

        MAT(int n,int m){
            this.n=n;
            this.m=m;
            this.mat=new long[n][m];
        }
        MAT exp(MAT base,long exp,long MOD){
            if(exp==0){
                MAT identity =new MAT(base.n,base.n);
                for(int i=0;i<base.n;i++){
                    identity.mat[i][i]=1;
                }
                return identity;
            }
            MAT half=exp(base,exp/2,MOD);
            MAT res=mulp(half,half,MOD);

            if(exp%2==1){
                res=mulp(res,base,MOD);
            }
            return res;
        }
        MAT mulp(MAT a,MAT b,long MOD){
            MAT c=new MAT(a.n,b.m);

            for(int i=0;i<a.n;i++){
                for(int j=0;j<b.m;j++){
                    for(int k=0;k<a.m;k++){
                        c.mat[i][j]=(c.mat[i][j]+(a.mat[i][k]%MOD * b.mat[k][j]%MOD)%MOD);
                    }
                }
            }
            return c;
        }

    }
}
