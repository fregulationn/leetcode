

public class set_matrix_zeros {

    public static  void setZeroes(int[][] matrix) {
        boolean first_column=false,first_row=false;

        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0)
                first_row = true;
        }


        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0)
                first_column = true;

            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j]==0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0)
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;

                }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (first_row)
        {
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        }
        if (first_column)
        {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }






        return;



    }

//    public static void main(String[] args) {
//        int[][] nums = new int[][]{{1,2,3,4},{5,0,5,2},{8,9,2,0},{5,7,2,1}};
//        setZeroes(nums);
//
//
//
//
//    }


}
