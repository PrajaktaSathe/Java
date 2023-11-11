package Programs;

/**
 * Program to print a pattern using nested for loops
 * eg:- *
 *      * *
 *      * * *
 *      * * * *
 *      * * * * *
 * @author arshcoder13 - https://github.com/arshcoder13/
 **/

/**
 * BugFix
 * Descriptions:
 * 1. Added package name
 * 2. Optimized imports
 */
public class PrintPattern {

    public static void main(String args[]){

        for(int i=1;i<=5;i++){
            for(int j=1;j<=i;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        
    }
    
}
