public class uniqe_paths {

    public static int[][] value;
    public static int[][] obstacle;

//    public static int uniquePaths(int m, int n) {
//        value = new int[m][n];
//
//        dfs(m - 1, n - 1);
//
//        return value[m - 1][n - 1];
//    }

    public static int dfs(int m, int n) {
        if (obstacle[m][n] == 1)
            return 0;

        if (m == 0 && n == 0) {
            value[m][n] = 1;
            return value[m][n];
        }

        if (value[m][n] != 0)
            return value[m][n];
        else {
            if (m == 0) {
                value[m][n] = dfs(m, n - 1);
            } else if (n == 0) {
                value[m][n] = dfs(m - 1, n);
            } else {
                value[m][n] = dfs(m, n - 1) + dfs(m - 1, n);
            }

        }

        return value[m][n];

    }



    public static int uniquePathsII( int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        obstacle = obstacleGrid;

        value = new int[m][n];
        dfs(m - 1, n - 1);

        return value[m - 1][n - 1];
    }





//
//
//    public static void main(String[] args) {
//        int m = 1, n = 2;
//        int[][] temp = new int[][]{{0,0}};
//        System.out.println(uniquePathsII(temp));
//
//
//    }


}
