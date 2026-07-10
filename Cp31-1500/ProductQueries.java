import java.io.*;
import java.util.*;

public class ProductQueries {

//-----Customize for each ques-----

    static long[] segTree;
    static long[] arr;

    static void buildTree(int i,int l,int r){
        if(l==r){
            segTree[i]=arr[l];
            return;
        }
        int mid= l + (r-l)/2;
        buildTree(2*i +1,l,mid);
        buildTree(2*i +2, mid+1,r);
        segTree[i]=(segTree[2*i +1] & segTree[2*i +2]);
    }

    static long query(int s,int e,int i,int l,int r){
        if(e<l || s>r){
            return -1;
        }
        if(l>=s && r<=e){
            return segTree[i];
        }
        int mid=l+ (r-l)/2;
        long left=query(s, e, 2*i +1, l, mid);
        long right=query(s, e, 2*i +2, mid+1, r);
        if(left==-1) return right;
        if(right==-1) return left;
        return (left & right);
    }



    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(out);
    }


    static void solve() throws Exception {
        int n=fs.nextInt();
        Map<Integer,Integer> mp=new HashMap<>();

        for(int i=0;i<n;i++){
            int el=fs.nextInt();
            mp.put(el,mp.getOrDefault(el,0)+1);
        }
        Map<Integer,List<Integer>> fact=new HashMap<>();
        fact.put(1,Arrays.asList(1));
        fact.put(2,Arrays.asList(2));
        fact.put(3,Arrays.asList(3));
        for(int i=4;i<=n;i++){
            fact.putIfAbsent(i, new ArrayList<>());
            List<Integer> temp=new ArrayList<>();
            temp.add(i);
            for(int j=2;j*j<=i;j++){
                if(i%j==0){
                    temp.add(j);
                }
            }
            fact.put(i,temp);
        }

        List<Integer> ans=new ArrayList<>();

        for(int i=1;i<=n;i++){
            List<Integer> temp=fact.get(i);
            boolean allPre=true;
            if(mp.containsKey(i)){
                ans.add(1);
                continue;
            }
            for(int x:temp){
                if(!mp.containsKey(x)){
                    allPre=false;
                    break;
                }
            }
            if(allPre){
                ans.add(temp.size());
            }else{
                ans.add(-1);
            }
        }

        System.out.println(ans);
    }



/*-------------For Fast I/O --------------------------*/

    private static final class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            boolean neg = false; if (c == '-') { neg = true; c = readByte(); }
            long val = 0;
            while (c > ' ') { val = val * 10 + (c - '0'); c = readByte(); }
            return neg ? -val : val;
        }

        int nextInt() throws IOException { return (int) nextLong(); }
        String next() throws IOException {
            int c; do { c = readByte(); } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') { sb.append((char)c); c = readByte(); }
            return sb.toString();
        }
    }

    static final long INF = (long) 1e18;
    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder out = new StringBuilder();

}