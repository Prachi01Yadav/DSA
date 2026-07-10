import java.io.*;
import java.util.*;

public class Queen {


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
            solve();
    }

    static void solve() throws Exception {
        int n=fs.nextInt();

        int[][] edges=new int[n][2];
        for(int i=0;i<n;i++){
            edges[i][0]=fs.nextInt();
            edges[i][1]=fs.nextInt();
        }

        List<List<Integer>> g=new ArrayList<>();
        for(int i=0;i<n;i++){
            g.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            int par=edges[i][0];
            if(par==-1) {
                continue;
            }
            par--;
            g.get(par).add(i);
        }

        boolean[] vis=new boolean[n];

        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            if(!vis[i]){
                dfs(i,edges,g,vis,pq);
            }
        }
        if(pq.isEmpty()){
            System.out.println(-1);
            return;
        }
        while(!pq.isEmpty()){
            System.out.print((pq.poll()+1)+ " ");
        }
        System.out.println();

    }
    static void dfs(int node,int[][] edges,List<List<Integer>> g,boolean[] vis,PriorityQueue<Integer> pq){
        vis[node]=true; 

        for(int v:g.get(node)){
            if(!vis[v]){
                dfs(v,edges,g,vis,pq);
            }
        }

        if(edges[node][0]!=-1 && edges[node][1]==1){
            boolean haveZero=false;
            for(int v:g.get(node)){
                if(edges[v][1]==0){
                    haveZero=true;
                    break;
                }
            }
            if(!haveZero){
                pq.add(node);
            }
        }
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