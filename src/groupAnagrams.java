

import java.util.*;

public class groupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<List<String>>();
        String[] strs_store = new String[strs.length];
        HashMap<String, List<Integer>> string_map = new HashMap<String, List<Integer>>();

        for (int i = 0; i < strs.length; i++) {
            strs_store[i] = strs[i];
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            strs[i] = String.valueOf(temp);
        }

        for (int i = 0; i < strs.length; i++) {
            if (!string_map.containsKey(strs[i])) {
                List<Integer> temp_index = new ArrayList<Integer>();
                temp_index.add(i);
                string_map.put(strs[i], temp_index);
            } else {
                List<Integer> temp_index = string_map.get(strs[i]);
                temp_index.add(i);
            }
        }


        Iterator iter = string_map.entrySet().iterator();
        while (iter.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iter.next();
            String key = (String)entry.getKey();
            List<Integer> temp = string_map.get(key);

            List<String> temp_string = new ArrayList<String>();
            for (int i = 0; i < temp.size(); i++) {
                temp_string.add(strs_store[temp.get(i)]);
            }
            res.add(temp_string);

        }

        return res;

    }

//
//    public static void main(String[] args) {
//        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
//        groupAnagrams(strs);
//
//    }


}
