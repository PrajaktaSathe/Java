package Programs;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
      public static void main(String[] args){
            int[][] mat= {{1, 2, 3,4},{5, 6, 7, 8},{9, 10, 11, 12},{13, 14, 15, 16}};
            List<Integer> ans = spiral(mat);
            System.out.println(ans);
      }
      
      public static List<Integer> spiral(int[][] matrix){
            
            List<Integer> ans = new ArrayList<>();
            
            int n = matrix.length, m = matrix[0].length;
            int left = 0, top = 0;
            int right = m-1, bottom = n-1;
            
            while(top<=bottom && left <= right){
                  
                  for(int i = left; i <= right;i++){
                        ans.add(matrix[top][i]);
                  }
                  top++;
                  
                  for(int i = top;i <= bottom; i++){
                        ans.add(matrix[i][right]);
                  }
                  right--;
                  
                  for(int i = right; i >= left; i--){
                        ans.add(matrix[bottom][i]);
                  }
                  
                  bottom --;
                  
                  for(int i = bottom; i>= top; i--){
                        ans.add(matrix[i][left]);
                  }
                  
                  left++;
                  
            }
            return ans;
      }
}
