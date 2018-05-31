
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.*;


public class backtracking {
    /**
     * initialize your data structure here.
     */

    //295. 数据流的中位数
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;

    public backtracking() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        max.add(num);
        min.add(max.poll());
        if (max.size() < min.size()) {
            max.add(min.poll());
        }
    }

    public double findMedian() {
        if (max.size() == 0)
            return 0;

        if (max.size() == min.size()) {
            return (double) (max.peek() + min.peek()) / 2;
        }
        return (double) max.peek();
    }

    //17.电话号码的字母组合
//    List<String> res = new ArrayList<>();
//
//    public List<String> letterCombinations(String digits) {
//        String[] strs = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//
//        int deep = digits.length();
//        if (deep == 0)
//            return null;
//
//        dfs(strs, digits, 0, deep, new StringBuilder(""));
//        return res;
//    }
//
//    public void dfs(String[] strs, String digits, int floor, int deep, StringBuilder path) {
//        if (floor >= deep) {
//            res.add(path.toString());
//            return;
//        }
//
//        String tmp_str = strs[Character.getNumericValue(digits.charAt(floor)) - 2];
//        for (int i = 0; i < tmp_str.length(); i++) {
//            String sigle_str = tmp_str.substring(i, i + 1);
//            path.append(sigle_str);
//            dfs(strs, digits, ++floor, deep, path);
//            path.deleteCharAt(path.length() - 1);
//            --floor;
//        }
//
//    }

//    //22.生成括号
//    List<String> res = new ArrayList<>();
//
//    public List<String> generateParenthesis(int n) {
//        if (n == 0) {
//            res.add("");
//            return res;
//        }
//
//        dfs(n, n, new StringBuilder());
//        return res;
//    }
//
//    public void dfs(int left, int right, StringBuilder path) {
//        if (left > right)
//            return;
//
//        if (right == 0) {
//            res.add(path.toString());
//            return;
//        }
//
//        if (left > 0) {
//            path.append("(");
//            dfs(left - 1, right, path);
//            path.deleteCharAt(path.length() - 1);
//        }
//
//
//        path.append(")");
//        dfs(left, right - 1, path);
//        path.deleteCharAt(path.length() - 1);
//    }

//    //     46.全排列
//    List<List<Integer>> res = new ArrayList<>();
//    public List<List<Integer>> permute(int[] nums) {
//        if (nums.length == 0)
//            return res;
//
//        dfs(nums, 0);
//        return res;
//    }
//
//    public void dfs(int[] changed_nums, int begin) {
//        if (begin == changed_nums.length) {
//            List<Integer> tmp = new ArrayList<>();
//            for (int i = 0; i < changed_nums.length; i++) {
//                tmp.add(changed_nums[i]);
//            }
//            res.add(tmp);
//            return;
//        }
//
//        for (int i = begin; i < changed_nums.length; i++) {
//
//            if (i == begin) {
//                dfs(changed_nums, begin + 1);
//
//            } else {
//                swap(changed_nums, begin, i);
//                dfs(changed_nums, begin + 1);
//                swap(changed_nums, begin, i);
//            }
//
//        }
//    }
//
//    public void swap(int[] changed_nums, int a, int b) {
//        int tmp = changed_nums[a];
//        changed_nums[a] = changed_nums[b];
//        changed_nums[b] = tmp;
//    }


//    //     47.全排列2
//
//    List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        if (nums.length == 0)
//            return res;
//
//        dfs(nums, 0);
//        return res;
//    }
//
//    public void dfs(int[] changed_nums, int begin) {
//        if (begin == changed_nums.length) {
//            List<Integer> tmp = new ArrayList<>();
//            for (int i = 0; i < changed_nums.length; i++) {
//                tmp.add(changed_nums[i]);
//            }
//            res.add(tmp);
//            return;
//        }
//
//        Set<Integer> tmp = new HashSet<>();
//        for (int i = begin; i < changed_nums.length; i++) {
//
//            if (i == begin) {
//                tmp.add(changed_nums[begin]);
//                dfs(changed_nums, begin + 1);
//            } else {
//                if (!tmp.contains(changed_nums[i])) {
//                    tmp.add(changed_nums[i]);
//                    swap(changed_nums, begin, i);
//                    dfs(changed_nums, begin + 1);
//                    swap(changed_nums, begin, i);
//                }
//            }
//
//        }
//    }
//
//    public void swap(int[] changed_nums, int a, int b) {
//        int tmp = changed_nums[a];
//        changed_nums[a] = changed_nums[b];
//        changed_nums[b] = tmp;
//    }

//    // 78. 子集
//    List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        if (nums.length == 0)
//            return res;
//
//        Arrays.sort(nums);
//
//        List<Integer> tmp = new ArrayList<>();
//        dfs(nums,0,tmp);
//        return res;
//    }
//
//    public void dfs(int[] nums, int pos,List<Integer> tmp) {
//        if (pos == nums.length)
//        {
//            res.add(tmp);
//            return;
//        }
//
//        dfs(nums,pos+1,tmp);
//
//        List<Integer> tmp2 = new ArrayList<>(tmp);
//        tmp2.add(nums[pos]);
//        dfs(nums,pos+1,tmp2);
//
//    }

//    //    90. 子集 II
//    List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        if (nums.length == 0)
//            return res;
//
//        Arrays.sort(nums);
//
//        List<Integer> tmp = new ArrayList<>();
//        dfs(nums, 0, tmp);
//        return res;
//    }
//
//    public void dfs(int[] nums, int pos, List<Integer> tmp) {
//        if (pos == nums.length) {
//            res.add(tmp);
//            return;
//        }
//
//        int old_pos = pos;
//        while (pos < nums.length-1&&nums[pos]==nums[pos+1] )
//            pos++;
//
//        dfs(nums, pos + 1, tmp);
//
//        for (int i = old_pos; i < pos+1; i++) {
//            List<Integer> tmp2 = new ArrayList<>(tmp);
//            for (int j = old_pos; j <i+1 ; j++) {
//                tmp2.add(nums[j]);
//            }
//            dfs(nums,pos+1,tmp2);
//        }
//
//    }

//    //79. 单词搜索
//    public boolean exist(char[][] board, String word) {
//        if (word.length() == 0)
//            return false;
//        if (board.length == 0)
//            return false;
//
//
//        boolean res = false;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//
//                boolean tmp = dfs(board, word, 0, i, j);
//                if (tmp == true) {
//                    res = true;
//                    break;
//                }
//            }
//        }
//
//        return res;
//
//    }
//
//    public boolean dfs(char[][] board, String word, int pos, int x, int y) {
//        if (pos == word.length()) {
//            return true;
//        }
//
//        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
//
//        if (board[x][y] != word.charAt(pos))
//            return false;
//
//        char tmp = word.charAt(pos);
//        board[x][y] = '-';
//
//        boolean res = dfs(board, word, pos + 1, x - 1, y) || dfs(board, word, pos + 1, x + 1, y) ||
//                dfs(board, word, pos + 1, x, y - 1) || dfs(board, word, pos + 1, x, y + 1);
//
//        board[x][y] = tmp;
//        return res;
//
//    }


    public static void main(String[] args) {

        backtracking obj = new backtracking();
        int[] a = new int[]{5, 5, 5};


        char[][] board = new char[40][40];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = 'a';

            }
        }
        board[9][9] = 'b';


        StringBuilder tmp = new StringBuilder("b");
        for (int i = 0; i < 99; i++) {
            tmp.append('a');
        }


//        System.out.println(obj.exist(board, tmp.toString()));

    }


}
