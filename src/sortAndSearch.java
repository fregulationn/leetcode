import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class sortAndSearch {


    //    75. 分类颜色
    public void sortColors(int[] nums) {

        if (nums.length == 0)
            return;
        int begin = 0, cur = 0, end = nums.length - 1;


        while (cur <= end) {
            if (nums[cur] == 1) {
                cur++;
            } else if (nums[cur] == 0) {
                while (nums[begin] == 0 && begin < cur)
                    begin++;
                swap(nums, begin, cur);
                cur++;
                begin++;
            } else if (nums[cur] == 2) {
                while (nums[end] == 2 && end > cur)
                    end--;
                swap(nums, end, cur);
                end--;
            }
        }
    }

    public void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }


    //    347. 前K个高频元素
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        if (nums.length == 0)
            return res;

        HashMap<Integer, Integer> store_value = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!store_value.containsKey(nums[i]))
                store_value.put(nums[i], 1);
            else
                store_value.put(nums[i], store_value.get(nums[i])+1 );
        }

        List<Integer>[] times = new List[nums.length + 1];
        for (int key : store_value.keySet())//按“键”遍历
        {
            int value  = store_value.get(key);
            if (times[value] == null) {
                List<Integer> tmp = new ArrayList<>();
                times[value] = tmp;
            }
            times[value].add(key);
        }


        for (int i = nums.length; i >0 ; i--) {
            if (res.size()>=k)
                break;
            if (times[i] !=null)
                res.addAll(times[i]);
        }

        return res;
    }

//215. 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];

    }


    public static void main(String[] args) {

        sortAndSearch obj = new sortAndSearch();
        int[] a = new int[]{3,4,5,6,1,2};


        System.out.println(obj.findKthLargest(a,2));
    }


}
