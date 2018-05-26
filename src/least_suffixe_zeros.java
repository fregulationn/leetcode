//


import java.util.*;

public class least_suffixe_zeros {

    public static void add(int[][] grid) {
        int[][] tmp_zip = zip(grid);

        if (grid.length == 0)
            return;

        int rows = grid.length;
        int cols = grid[0].length;

//        data = new int[rows][cols];
//        path = new String[rows][cols];


//        //处理data第一列
//        for (int i = 1; i < rows; ++i) {
//            data[i][0] = data[i - 1][0] + grid[i][0];
//            path[i][0] = path[i - 1][0] + "V";
//
//        }
//        //处理data第一行
//        for (int j = 1; j < cols; ++j) {
//            data[0][j] = data[0][j - 1] + grid[0][j];
//            path[0][j] = path[0][j - 1] + ">";
//        }
//
//        //处理data非第一行和第一列的元素
//        for (int i = 1; i < rows; ++i) {
//            for (int j = 1; j < cols; ++j) {
//
//                if (data[i][j - 1] < data[i - 1][j]) {
//                    data[i][j] = data[i][j - 1] + grid[i][j];
//                    path[i][j] = path[i][j - 1] + ">";
//                } else {
//                    data[i][j] = data[i - 1][j] + grid[i][j];
//                    path[i][j] = path[i - 1][j] + "V";
//                }
//
//            }
//        }
        dfs(grid, 0, 0);


        System.out.println(res_data);
        System.out.println(res_path);
    }


    static int data = 0;
    static int res_data = 100000;
    static String res_path = "";
    static String path = "";

    public static void dfs(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length)
            return;

        if (i == grid.length - 1 && j == grid[0].length - 1) {
            if (data / 4 < res_data) {
                res_data = data / 4;
                res_path = path;
            }
        }

        data += grid[i][j];

        path += ">";
        dfs(grid, i, j + 1);
        path = path.substring(0, path.length() - 1);

        path += "V";
        dfs(grid, i + 1, j);
        path = path.substring(0, path.length() - 1);

        data -= grid[i][j];
    }


    public static int[][] zip(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = zip_num(grid[i][j]);
            }
        }
        return grid;
    }

    public static int zip_num(int num) {
        int count = 0;
        while (num % 2 == 0) {
            count++;
            num = num / 2;
        }
        return count;
    }


    public static void main(String[] args) {
        int[][] value = new int[][]
                {{0x3, 0x2, 0x8},
                        {0xc, 0x8, 0x8},
                        {0x2, 0xa, 0xf}};
        add(value);
    }


}
