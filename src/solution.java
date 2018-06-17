


public class solution {

//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix.length == 0 || matrix[0].length == 0)
//            return false;
//
//        int i = 0, j = matrix.length - 1;
//        int col_nums = matrix[0].length - 1;
//
//        int row = 0;
//        while (i <= j) {
//            if (matrix[i][0] <= target && target <= matrix[i][col_nums]) {
//                row = i;
//                break;
//            } else {
//                i++;
//            }
//
//            if (matrix[j][0] <= target && target <= matrix[j][col_nums]) {
//                row = j;
//                break;
//            } else {
//                j--;
//            }
//        }
//
//        i = 0;
//        j = col_nums;
//        while (i <= j) {
//            if (matrix[row][i] == target) {
//                return true;
//            } else {
//                i++;
//            }
//
//            if (matrix[row][j] == target) {
//                return true;
//            } else {
//                j--;
//            }
//        }
//        return false;
//
//
//    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;

        int i = 0;
        int j = matrix.length - 1;

        while (i < matrix[0].length && j >= 0) {
            if (target == matrix[j][i]) {
                return true;
            } else if (target > matrix[j][i]) {
                i++;
            } else {
                j--;
            }
        }

        return false;

    }

    public static void main(String[] args) {
        solution obj = new solution();
        int[] a = new int[]{3, 4, 5, 6, 1, 2};


        System.out.println(obj.searchMatrix(a, 2));

    }


}
