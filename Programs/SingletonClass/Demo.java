package SingletonClass;

public class Demo {

    public static void main(String[] args) {
        // Obtain the singleton instance
        Singleton singleton1 = Singleton.getInstance();
        
        System.out.println("First time singleton class object: " + singleton1);
        // output: First time singleton class object: Singleton [message=got same singleton class object each time.]


        // accessing the singleton class methods through its object.
        System.out.println(singleton1.welcomeMethod());
        // output : Welcome to the Singleton class!
         
        
        
        
        // Attempting to create a new instance will give a compile-time error
        // Singleton singleton2 = new Singleton("hello"); // Compiler error: The constructor is private
        
        // Reusing the existing instance
        Singleton singleton2 = Singleton.getInstance();
        
        // repeating the same process to check, is getting same object each time or not?
        
        System.out.println("Second time singleton class object: " + singleton2);
        // output: Second time singleton class object: Singleton [message=got same singleton class object each time.]


        System.out.println(singleton2.welcomeMethod());
        // output : Welcome to the Singleton class!
    }
}
