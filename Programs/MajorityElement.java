package Programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MajorityElement {
      private static List<Integer> majorityElement(int[] v){
            
            int n = v.length;
            List<Integer> ans = new ArrayList<>();
            int min = n/3 +1;
            HashMap<Integer,Integer> mpp = new HashMap<>();
            
            for (int j : v) {
                  int val = mpp.getOrDefault(j, 0);
                  mpp.put(j, val + 1);
                  
                  if (mpp.get(j) == min) {
                        ans.add(j);
                  }
                  
                  if(ans.size() == 2)
                        break;
            }
            
            return ans;
      }
      
      public static  void main(String[] args) {
            int[] arr = {1,2,2,2,1,1};
            List<Integer> ans = majorityElement(arr);
            System.out.print("The majority elements are: ");
            for (Integer an : ans) {
                  System.out.print(an + " ");
            }
            System.out.println();
      }
}