/* Minimum Heap Example:

	K Closest Points to Origin 
		- Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, 
              return the k closest points to the origin (0, 0).
		  The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
	Example 
		Input: points = [[1,3],[-2,2]], k = 1
		Output: [[-2,2]]

		Explanation:
			The distance between (1, 3) and the origin is sqrt(10).
			The distance between (-2, 2) and the origin is sqrt(8).
			Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
			We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

 */



class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Closest> minHeap = new PriorityQueue<>((a,b)->
         {
             return a.getSum()-b.getSum();}
          );
        
        for(int[] point : points){
            int x= point[0];
            int y = point[1];
            Closest closestObj = new Closest(x,y);
            minHeap.add(closestObj);
        }
        
        int[][] result = new int[k][2];
        int index = 0;
        while(index < k){
            Closest closestObj = minHeap.poll(); 
            result[index][0]=closestObj.x;
            result[index][1]=closestObj.y;
            index++;
        }
        return result ;
    }
}
public class Closest{
    public int x;
    public int y;
        Closest(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getSum(){
        return (this.x*this.x)+(this.y*this.y);
    }
}