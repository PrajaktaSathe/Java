package Programs.matrix;


import java.util.Arrays;

public class RotateMatrix {
    static boolean rotate(int[][] matrix){
        if(matrix.length == 0 || matrix.length != matrix[0].length) return false;
        int n = matrix.length;

        for(int layer =0; layer < n/2;layer++){
            int first = layer;
            int last = n-1-layer;
            for(int i = first; i<last;i++){
                int offset = i - first;
                int top = matrix[first][i];

                matrix[first][i] = matrix[last -offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last -offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4,5,6},
                {7,8,9,10,11,12},
                {13,14,15,16,17,18},
                {19,20,21,22,23,24},
                {25,26,27,28,29,30},
                {31,32,33,34,35,36}};
        System.out.println(rotate(matrix));
    }
}
