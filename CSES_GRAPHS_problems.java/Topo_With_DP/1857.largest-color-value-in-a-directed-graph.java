/*
 * @lc app=leetcode id=1857 lang=java
 *
 * [1857] Largest Color Value in a Directed Graph
 */
// @lc code=start

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        List<List<Integer>> g=new ArrayList<>();
        int n=colors.length();
        for(int i=0;i<n;i++){
            g.add(new ArrayList<>());
        }

        for(int[] e:edges){
            int u=e[0];
            int v=e[1];
            g.get(u).add(v);
        }
        int[] indeg=new int[n];
        Queue<Integer> q=new ArrayDeque<>();

        for(int i=0;i<n;i++){
            for(int v:g.get(i)){
                indeg[v]++;
            }
        }
        
        int[][] dp=new int[n][26];
        
        for(int i=0;i<n;i++){
            if(indeg[i]==0){
                q.add(i);
                dp[i][colors.charAt(i)-'a']++;
            }
        }
        int c=q.size();

        while(!q.isEmpty()){
            int curr=q.poll();

            for(int v:g.get(curr)){
                char ch=colors.charAt(v);
                for(int i=0;i<26;i++){
                    dp[v][i]=Math.max(dp[v][i],dp[curr][i]);
                }
                indeg[v]--;
                if(indeg[v]==0){
                    q.add(v);
                    c++;
                    dp[v][ch-'a']++;
                }
            }
        }
        if(c<n){
            return -1;
        }
        int mx=0;
        for(int[] y:dp){
            for(int x:y) mx=Math.max(x,mx);
        }
        return mx;
    }
}

// @lc code=end

