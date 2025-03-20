// Program to solve the Tower of Hanoi problem using recursion in Java

class TowerOfHanoi{
    
    public static void towerOfHanoi(int n,String src,String helper,String dest){
        if(n==1){
            System.out.println(src+"->"+dest);
            return;
        }
        //transfer top n-1 from src to helper usingdest as 'helper'
        towerOfHanoi(n-1,src,dest,helper);
        // Move nth disk from src to dest
        towerOfHanoi(1,src,helper,dest);
        // Transfer n-1 disks from helper to dest using src as helper
        towerOfHanoi(n-1,helper,src,dest);
    }

    
    public static void main(String args[]){
        int n =3;
        towerOfHanoi(n,"A","B","C");

}
}