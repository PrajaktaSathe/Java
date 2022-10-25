public class startpatternfunc {
    static void star(int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print("*");
               
            }
            System.out.print("\n");

        }
    }

    static void starrev(int n){
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                System.out.print("*");
               
            }
            System.out.print("\n");

        }
    }


   public static void main(String[] args) {
    star(3);
    starrev(3);
   } 
}
