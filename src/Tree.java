import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


public class Tree {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> tmp = new Stack<TreeNode>();
        if (root == null) return null;
        tmp.push(root);


        TreeNode out = root;
        while (out != null && !tmp.empty()) {
            while (out != null) {
                tmp.push(out);
                System.out.println("fuck");
                System.out.println(out.val);
                if (out.left != null)
                    out = out.left;
            }

            if (!tmp.empty()) {
                out = tmp.pop();
                System.out.println("fuck");
                res.add(out.val);
                if (out.right != null) {

                    tmp.push(out.right);
                    out = out.right;
                }


            }
        }
        return res;


    }

//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//
//    }

    public static TreeNode createTree(int[] value) {


        Queue<TreeNode> store_node = new ConcurrentLinkedQueue<TreeNode>();
        Queue<Integer> store_order = new ConcurrentLinkedQueue <Integer>();

        TreeNode root = new TreeNode(value[0]);

        store_node.add(root);
        store_order.add(0);

        while (!store_node.isEmpty()) {
            TreeNode tmp = store_node.poll();
            int order = store_order.poll();

            int left_value = 0;
            int right_value = 0;

            if (2 * order + 2 < value.length) //if应该只有一个判断标准比较好,右子节点是否过界来赋值
            {
                left_value = value[2 * order + 1];
                right_value = value[2 * order + 2];

            } else if (2 * order + 2 == value.length) {
                left_value = value[2 * order + 1];
            }


            if (left_value != 0) {
                TreeNode left = new TreeNode(left_value);
                int new_order = 2 * order + 1;
                tmp.left = left;

                store_node.add(left);
                store_order.add(new_order);
            }

            if (right_value != 0) {
                TreeNode right = new TreeNode(right_value);
                int new_order = 2 * order + 2;
                tmp.right = right;

                store_node.add(right);
                store_order.add(new_order);
            }
        }

        return root;
    }


    public static void main(String[] args) {
        int[] value = new int[]{3,4,5,0,2,4,0,0,0,3,2};


        TreeNode tmp = createTree(value);
        System.out.println("OK");


    }

}