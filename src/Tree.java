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

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
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


    public static TreeNode createTree(int[] value) {


        Queue<TreeNode> store_node = new ConcurrentLinkedQueue<TreeNode>();
        Queue<Integer> store_order = new ConcurrentLinkedQueue<Integer>();

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


    public static void levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        Queue<TreeNode> store_node = new LinkedList<TreeNode>();
        Queue<Integer> store_layer = new LinkedList<Integer>();

        store_node.add(root);
        store_layer.add(0);

        if (root == null) {
            List<Integer> add_list = new ArrayList<Integer>();
            res.add(add_list);
        }


        while (!store_node.isEmpty()) {
            TreeNode tmp = store_node.poll();
            int floor = store_layer.poll();


            if (res.size() <= floor) {
                List<Integer> add_list = new ArrayList<Integer>();
                res.add(add_list);
            }
            res.get(floor).add(tmp.val);


            if (tmp.left != null) {
                store_node.add(tmp.left);
                store_layer.add(floor + 1);
            }

            if (tmp.right != null) {
                store_node.add(tmp.right);
                store_layer.add(floor + 1);
            }
        }

        System.out.println(res);
    }

    static List<List<Integer>> res = new ArrayList<List<Integer>>();

    public static void recursion(TreeNode root, int floor) {
        if (root == null) return;
        if (res.size() <= floor) {
            List<Integer> add_list = new ArrayList<Integer>();
            res.add(add_list);
        }
        res.get(floor).add(root.val);
        recursion(root.left, floor + 1);
        recursion(root.right, floor + 1);
    }

    public static void levelOrder2(TreeNode root) {
        int floor = 0;
        recursion(root, floor);

        List<List<Integer>> trans_res = new ArrayList<List<Integer>>();
        for (int i = res.size() - 1; i >= 0; i--) {
            trans_res.add(res.get(i));
        }

        System.out.println(res);
        System.out.println(trans_res);
    }

//    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//        helper(root, result, 0);
//        return result;
//    }
//
//    private static void helper(TreeNode root, List<List<Integer>> result, int currDepth) {
//        if (root == null) {
//            return;
//        } else if (result.size() <= currDepth) {
//            result.add(0, new ArrayList<Integer>());
//        }
//        result.get(result.size() - ++currDepth).add(root.val);
//        helper(root.left, result, currDepth);
//        helper(root.right, result, currDepth);
//    }


    public static void zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> trans_res = new ArrayList<List<Integer>>();

        if (root == null) {
            List<Integer> add_list = new ArrayList<Integer>();
            trans_res.add(add_list);
        }


        Queue<TreeNode> store_node = new LinkedList<TreeNode>();
        store_node.add(root);

        int level = 0;

        while (!store_node.isEmpty()) {
            List<Integer> tmp = new ArrayList<Integer>();
            int num = store_node.size();
            for (int i = 0; i < num; i++) {
                TreeNode tmp_node = store_node.poll();

                if (tmp_node.left != null) {
                    store_node.add(tmp_node.left);
                }

                if (tmp_node.right != null) {
                    store_node.add(tmp_node.right);
                }


                if (level % 2 == 0) {
                    tmp.add(tmp_node.val);
                } else
                    tmp.add(0, tmp_node.val);

            }
            trans_res.add(tmp);
            ++level;
        }

        System.out.println(trans_res);

    }


//  从前序与中序遍历序列构造二叉树

    public static TreeNode buildTreeFromPre(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        return buildTreeFromPreAndIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildTreeFromPreAndIn(int[] preorder, int[] inorder, int preBegin, int preEnd, int inBegin, int inEnd) {
        if (preBegin > preEnd || inBegin > inEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preBegin]);
        int i;
        for (i = inBegin; i < inEnd; i++) {
            if (inorder[i] == preorder[preBegin])
                break;
        }
        int leftNum = i - inBegin;
        root.left = buildTreeFromPreAndIn(preorder, inorder, preBegin + 1, preBegin + leftNum, inBegin, inBegin + leftNum - 1);
        root.right = buildTreeFromPreAndIn(preorder, inorder, preBegin + leftNum + 1, preEnd, i + 1, inEnd);
        return root;
    }


    //  从中序与后序遍历序列构造二叉树
    public static TreeNode buildTreeFromPost(int[] inorder, int[] postorder) {
        if (inorder.length == 0)
            return null;
        return buildTreeFromInandPost(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildTreeFromInandPost(int[] postorder, int[] inorder, int postBegin, int postEnd, int inBegin, int inEnd) {
        if (postBegin > postEnd || inBegin > inEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int i;
        for (i = inBegin; i < inEnd; i++) {
            if (inorder[i] == postorder[postEnd])
                break;
        }
        int leftNum = i - inBegin;

        root.left = buildTreeFromInandPost(postorder, inorder, postBegin, postBegin + leftNum - 1, inBegin, inBegin + leftNum - 1);
        root.right = buildTreeFromInandPost(postorder, inorder, postBegin + leftNum, postEnd - 1, i + 1, inEnd);
        return root;
    }


    public static int kthSmallest(TreeNode root, int k) {
        if (root==null)
            return 0;

        inOrder(root, k);
        return numbers.get(k - 1);
    }


    static List<Integer> numbers = new ArrayList<>();

    public static void inOrder(TreeNode root, int k) {
        if (root == null)
            return;

        inOrder(root.left, k);

        if (numbers.size() >= k)
            return;

        numbers.add(root.val);

        inOrder(root.right, k);
    }


    public static void main(String[] args) {
//        int[] value = new int[]{3, 4, 5, 0, 2, 4, 0, 0, 0, 3, 2};
//
//        TreeNode tmp = createTree(value);
//
//        levelOrder(tmp);
//        zigzagLevelOrder(tmp);
//        System.out.println("OK");

//        int[] left = new int[]{3, 9, 20, 15, 7};
//        int[] right = new int[]{9, 3, 15, 20, 7};
//        int[] postorder = new int[]{9, 15, 7, 20, 3};
//
//        TreeNode root = buildTreeFromPost(right, postorder);


//        connect(createTree2(value));


        int[] value = new int[]{5, 3, 6, 2, 4, 0, 0, 1};

        TreeNode tmp = createTree(value);

        System.out.println(kthSmallest(tmp, 3));


    }


//    public static void connect(TreeLinkNode root) {
//        TreeLinkNode level_start = root;
//        while (level_start != null) {
//
//            TreeLinkNode cur = level_start;
//
//            while (cur != null) {
//                if (cur.left != null) {
//                    if (cur.right != null)
//                        cur.left.next = cur.right;
//                    else {
//                        cur.left.next = next_recursion(cur.next);
//                    }
//                }
//
//                if (cur.right != null && cur.next != null) cur.right.next = next_recursion(cur.next);
//
//                cur = cur.next;
//            }
//            level_start = level_start.left;
//        }
//        return;
//    }
//
//
//    public static TreeLinkNode next_recursion(TreeLinkNode root) {
//        if (root == null)
//            return null;
//
//        if (root.left != null)
//            return root.left;
//
//        if (root.right != null)
//            return root.right;
//        return next_recursion(root.next);
//    }
//
//    public static TreeLinkNode createTree2(int[] value) {
//        Queue<TreeLinkNode> store_node = new ConcurrentLinkedQueue<TreeLinkNode>();
//        Queue<Integer> store_order = new ConcurrentLinkedQueue<Integer>();
//
//        TreeLinkNode root = new TreeLinkNode(value[0]);
//
//        store_node.add(root);
//        store_order.add(0);
//
//        while (!store_node.isEmpty()) {
//            TreeLinkNode tmp = store_node.poll();
//            int order = store_order.poll();
//
//            int left_value = 0;
//            int right_value = 0;
//
//            if (2 * order + 2 < value.length) //if应该只有一个判断标准比较好,右子节点是否过界来赋值
//            {
//                left_value = value[2 * order + 1];
//                right_value = value[2 * order + 2];
//
//            } else if (2 * order + 2 == value.length) {
//                left_value = value[2 * order + 1];
//            }
//
//
//            if (left_value != 0) {
//                TreeLinkNode left = new TreeLinkNode(left_value);
//                int new_order = 2 * order + 1;
//                tmp.left = left;
//
//                store_node.add(left);
//                store_order.add(new_order);
//            }
//
//            if (right_value != 0) {
//                TreeLinkNode right = new TreeLinkNode(right_value);
//                int new_order = 2 * order + 2;
//                tmp.right = right;
//
//                store_node.add(right);
//                store_order.add(new_order);
//            }
//        }
//
//        return root;
//    }


}