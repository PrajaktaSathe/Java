import java.util.ArrayList;
import java.util.Arrays;

public class ZeroMatrix {

      
      static void markRow(ArrayList<ArrayList<Integer>> mat , int n ,int i){
            for(int j = 0;j < n ;j++){
                  if(mat.get(i).get(j)!=0){
                        mat.get(j).set(j,-1);
                  }
            }
      }
      
      static void markCol(ArrayList<ArrayList<Integer>> mat , int m ,int j){
            for(int i = 0;i < m;i++){
                 if(mat.get(i).get(j)!=0){
                       mat.get(1).set(j,-1);
                 }
            }
      }
      static ArrayList<ArrayList<Integer>> zeroMatrix(ArrayList<ArrayList<Integer>> mat , int m ,int n ){
      
            for(int i = 0; i < m; i++){
                  for(int j = 0 ; i < n ; i++){
                        if(mat.get(i).get(j)==0){
                              markRow(mat,n,i);
                              markCol(mat,m,j);
                        }
                  }
            }
            for(int i = 0; i < m; i++){
                  for(int j = 0 ; i < n ; i++){
                        if(mat.get(i).get(j)==-1){
                              mat.get(i).set(j,0);
                        }
                  }
            }
            
            return mat;
      }
      
      static public void main(String[] args){
            ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
            mat.add(new ArrayList<>(Arrays.asList(1, 1, 1)));
            mat.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
            mat.add(new ArrayList<>(Arrays.asList(1, 1, 1)));
            
            
            zeroMatrix(mat,mat.size(),mat.get(0).size());
            
            
            System.out.println(mat);
      }
}
