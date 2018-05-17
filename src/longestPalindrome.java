public class longestPalindrome {


    public static String longestPalindrome(String s) {
        char[] ss = new char[2 * s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            ss[2 * i] = '~';
            ss[2 * i + 1] = s.charAt(i);
        }
        ss[2 * s.length()] = '~';

        int max_right = 0;
        int pos = 0;
        int[] rl = new int[ss.length];

        int max_length = 0;
        int max_pos = 0;

        for (int i = 0; i < ss.length; i++) {
            if (i < max_right) {
                rl[i] = Math.min(rl[2 * pos - i], max_right - i);
            } else {
                rl[i] = 1;
            }

            while (i - rl[i] >= 0 && i + rl[i] < ss.length && ss[i - rl[i]] == ss[i + rl[i]])
                ++rl[i];

            if (i + rl[i] > max_right) {
                max_right = i + rl[i];
                pos = i;
            }

            if (rl[i] > max_length) {
                max_length = rl[i];
                max_pos = i;
            }
        }

        char[] res = new char[max_length - 1];
        int tmp = 0;
        for (int i = max_pos - max_length + 1; i <= max_pos; i++) {
            if (ss[i] != '~') {
                res[tmp] = ss[i];
                res[max_length - tmp - 1 - 1] = ss[i];
                tmp += 1;
            }
        }
        return new String(res);
    }


    public static void main(String[] args) {
        String strs = "babad";
        System.out.println(longestPalindrome(strs));


    }


}
