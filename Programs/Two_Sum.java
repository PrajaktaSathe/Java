//In this code a array is taken along with an target element. The sum of two elements matching with the target element is displayed


import java.util.*;
public class Two_Sum {
    public static void main(String args[])
    {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            // length of the array is taken
            int a[] = new int[n];
            for(int i = 0; i < n; i++)
            {
                a[i] = sc.nextInt();
            }
            int target=sc.nextInt();
            // target value is initialzized
            int array[] = new int[2];
            a=twoSum(array,target);
            // method is called
            for(int i=0;i<2;i++)
            {
                System.out.print(a[i]+" ");
            }
            System.out.println();
        }
    }
    public static int[] twoSum(int[] n, int target) {
        Map<Integer,Integer> nm=new HashMap<>();
        // hashmap is created to store the array elements
        int array[]=new int[2];
        int k=n.length;
        for(int i=0;i<k;i++)
        {
            if(nm.containsKey(target-n[i]))
            {
                array[0]=nm.get(target-n[i]);
                array[1]=i;
                break;
                // The sum of the elements equals to the target is storee
            }
            nm.put(n[i],i);
        }
        return array;
        // the answer array is returned
    }
}
