/**
 * Find_LeavesOf_BT_Google_2022_
 */
import java.util.*;
public class Find_LeavesOf_BT_Google_2022_ {

    public static void main(String[] args) {
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5

        Node n4=new Node(null,null,4);
        Node n5=new Node(null,null,5);
        Node n3=new Node(null,null,3);
        Node n2=new Node(n4,n5,2);
        Node root=new Node(n2,n3,1);

       
        mp=new TreeMap<>();
        H(root);
    
        List<List<Integer>> ans=new ArrayList<>();

        for(Map.Entry<Integer,List<Integer>> e:mp.entrySet()){
            ans.add(e.getValue());
        }
        for(List<Integer> ls:ans){
            for(int x:ls){
                System.out.print(x+" ");
            }
            System.out.println();
        }
        

    }
    static Map<Integer,List<Integer>> mp;
    static int H(Node root){
        if(root==null){
            return 0;
        }
        int Lh=H(root.left);
        int rh=H(root.right);
        mp.computeIfAbsent(Math.max(Lh, rh), k -> new ArrayList<>()).add(root.val);
        return 1+Math.max(Lh,rh);
    }
    static class Node{
        Node left;
        Node right;
        int val;
        Node(){

        }
        Node(Node left,Node right,int val){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
}