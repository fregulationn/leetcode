


import java.util.*;


public class lengthOfLongestSubstring {
    //    public  static  int lengthOfLongestSubstring(String s) {
//        int longest = 0;
//        int tmp_longest = 0;
//        Map<String,Integer> sign = new HashMap<String, Integer>();
//
//        for (int i = 0; i < s.length(); i++) {
//            String tmp =Character.toString(s.charAt(i));
//            if(sign.containsKey(tmp))
//            {
//                if (tmp_longest>longest)
//                    longest = tmp_longest;
//                int tmp_index = sign.get(tmp);
//                tmp_longest = i-tmp_index-1;
//                sign.clear();
//
//                for (int j = i-1; j >tmp_index; j--) {
//                    sign.put(Character.toString(s.charAt(j)),j);
//                }
//            }
//            sign.put(tmp,i);
//            tmp_longest +=1;
//        }
//        if (tmp_longest>longest)
//            longest = tmp_longest;
//
//
//        return longest;
//    }
    public static int lengthOfLongestSubstring(String s) {
        int longest = 0;
        int left = 0;
        Map<String, Integer> sign = new HashMap<String, Integer>();

        for (int i = 0; i < s.length(); i++) {
            String tmp = Character.toString(s.charAt(i));
            if (sign.containsKey(tmp)) {
                if (sign.get(tmp) < left) {
                    longest = Math.max(longest, i - left);
                }else
                    left = i;
            }
            sign.put(tmp, i);
        }


        return longest;
    }


//    public static void main(String[] args) {
//        String strs = "abba";
//        lengthOfLongestSubstring(strs);
//
//    }


}
