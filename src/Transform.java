import java.util.*;

public class Transform {
    public static boolean  chkTransform(String str1, int a, String str2, int b) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chas1.length; i++) {
            map[chas1[i]]++;
        }
        for (int i = 0; i < chas2.length; i++) {
            if (map[chas2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        String strs = "abba";
//        if(chkTransform(strs,4,"ba",4))
//            System.out.println("OK");
//
//    }


}
