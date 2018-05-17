
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;


import java.util.Iterator;
import java.util.Map.Entry;

public class K_sum {


    public static int[] twoSum(int[] nums, int target) {

        int[] temp = new int[2];
        int[] nums_old = new int[nums.length];
        System.arraycopy(nums, 0, nums_old, 0, nums.length);


        Arrays.sort(nums);

        int j = 0, k = nums.length - 1;
        while (j != k) {
            if (nums[j] + nums[k] == target) {
                temp[0] = nums[j];
                temp[1] = nums[k];
                break;
            } else if (nums[j] + nums[k] < target) {
                j++;

            } else {
                k--;
            }
        }

        int i;

        for (i = 0; i < nums.length; i++) {
            if (nums_old[i] == temp[0]) {
                temp[0] = i;
                break;
            }


        }
        if (nums[j] == nums[k]) {
            i++;
        } else
            i = 0;

        for (; i < nums.length; i++) {

            if (nums_old[i] == temp[1]) {
                temp[1] = i;
                break;
            }
        }
        return temp;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;
            int target = 0 - nums[i];

            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    List<Integer> temp_res = new ArrayList<Integer>();
                    temp_res.add(nums[i]);
                    temp_res.add(nums[j]);
                    temp_res.add(nums[k]);
                    res.add(temp_res);
                    while (j < k && nums[j + 1] == nums[j])
                        j++;
                    while (j < k && nums[k - 1] == nums[k])
                        k--;
                    j++;
                    k--;
                } else if (nums[j] + nums[k] < target) {
                    j++;

                } else {
                    k--;
                }
            }

        }

        return res;
    }

    public static int threeSumClosest(int[] nums, int target) {

        int closet = 10000000;
        int res = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;
            int two_target = target - nums[i];

            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[j] + nums[k] + nums[i];
                if (Math.abs(sum - target) < closet) {
                    closet = Math.abs(sum - target);
                    res = sum;
                }


                if (nums[j] + nums[k] == two_target) {
                    return target;
                } else if (nums[j] + nums[k] < two_target) {
                    while (j < k && nums[j + 1] == nums[j])
                        j++;
                    j++;

                } else {
                    while (j < k && nums[k - 1] == nums[k])
                        k--;
                    k--;
                }
            }

        }

        return res;


    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();

        Arrays.sort(nums);
        HashMap<Integer, List<List<Integer>>> sum_map = new HashMap<Integer, List<List<Integer>>>();


        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;

            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                List<List<Integer>> temp = new ArrayList<List<Integer>>();
                int a = nums[i] + nums[j];
                if (sum_map.containsKey(a)) {
                    temp = sum_map.get(a);
                }

                List<Integer> temp_two_num = Arrays.asList(i, j);
                temp.add(temp_two_num);
                sum_map.put(a, temp);

            }
        }


//        int[] key = new int[sum_map.size()];
//        int w = 0;
//        Iterator iter = sum_map.entrySet().iterator();
//        while (iter.hasNext()) {
//            HashMap.Entry entry = (HashMap.Entry) iter.next();
//            key[w++] =entry.getKey().hashCode();
//
//        }
//        Arrays.sort(key);


        for (int i = 2; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int temp_tar = target - nums[i] - nums[j];

                if (!sum_map.containsKey(temp_tar))
                    continue;

                List<List<Integer>> temptemp = sum_map.get(temp_tar);

                for (int b = 0; b < temptemp.size(); b++) {
                    if (temptemp.get(b).get(1) >= i)
                        continue;

                    List<Integer> temp_res = new ArrayList<Integer>();
                    int[] temparr = new int[]{nums[temptemp.get(b).get(0)], nums[temptemp.get(b).get(1)],
                            nums[i], nums[j]};
                    Arrays.sort(temparr);
                    for (int n = 0; n < temparr.length; n++) {
                        temp_res.add(temparr[n]);
                    }
                    if (!set.contains(temp_res)) {
                        set.add(temp_res);
                        res.add(temp_res);
                    }
                }


            }
        }

//        int j = 0, k = sum_map.size() - 1;
//        while (j != k) {
//            if (key[j] + key[k] == target) {
//
//                List<List<Integer>> tempj = sum_map.get(key[j]);
//                List<List<Integer>> tempk = sum_map.get(key[k]);
//
//                for (int a = 0;a <tempj.size();a++)
//                {
//                    for (int b = 0;b <tempk.size();b++)
//                    {
//
//                        List<Integer> temp_res = new ArrayList<Integer>();
//                        int [] temptemp = new int[]{tempj.get(a).get(0),tempj.get(a).get(1),
//                                tempk.get(b).get(0),tempk.get(b).get(1)};
//                        Arrays.sort(temptemp);
//                        for (int i = 0; i < temptemp.length; i++) {
//                            temp_res.add(temptemp[i]);
//                        }
//
//                        if (!set.contains(temp_res))
//                        {
//                            set.add(temp_res);
//                            res.add(temp_res);
//
//                        }
//
//                    }
//
//                }
//
//                j++;
//                k--;
//
//            } else if (key[j] + key[k] < target) {
//                j++;
//
//            } else {
//                k--;
//            }
//        }
        return res;

    }


//    public static void main(String[] args) {
//        int[] nums = new int[]{3, 3};
//        int target = 6;
//
////        int [] three_nums = new int[]{-1,0,1,2,-1,-4};
////        threeSum(three_nums);
//
////        int [] three_nums_cloest = new int[]{-1,2,1,1};
////        threeSumClosest(three_nums_cloest,1);
//
//        int[] four_nums = new int[]{0, 0, 0, 0};
//        fourSum(four_nums, 0);
//
//
//        System.out.println(twoSum(nums, target));
//
//
//    }
}
