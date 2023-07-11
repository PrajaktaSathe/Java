package SingletonClass;

public class Singleton {

    private String message;
    
    private static Singleton instance;
    
    // Private constructor to prevent direct instantiation
    private Singleton(String message) {
        this.message = message;
    }
    
    // Method to access the Singleton instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton("got same singleton class object each time.");
        }
        return instance;
    }
    
    // Method to demonstrate accessing Singleton methods
    public String welcomeMethod() {
        return "Welcome to the Singleton class!";
    }

    // To obtain the string representation of an object, we need to override the `toString` method in Java; otherwise, it prints the default representation of the object. 
    
    @Override
    public String toString() {
        return "Singleton [message=" + message + "]";
    }
}
