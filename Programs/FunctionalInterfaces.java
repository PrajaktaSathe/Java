// Program to demonstrate how to use the Function and BiFunction functional interfaces

import java.util.function.Function;
import java.util.function.BiFunction;

public class FunctionalInterfaces {

    /**
     * A functional interface such as Function class takes two generic types, the
     * type of the input and the type of the output. They can be written as
     * anonymous classes that implement the Function interface, but they are more
     * commonly written as lambda functions.
     * 
     */
    static Function<String, String> sayHello = name -> "Hello, " + name;

    /**
     * A BiFunction works the same way as a Function,except for taking two input
     * types
     */
    static BiFunction<Integer, Integer, Integer> sumValues = (x, y) -> x + y;

    public static void main(String[] args) {
        // Function types are invoked using the .apply() method on the interface
        System.out.println(sayHello.apply("Dan"));
        System.out.println(sumValues.apply(3, 2));
    }
}