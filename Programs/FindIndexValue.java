import java.util.function.BiFunction;
public class MyClass {
    public static final BiFunction<int[],Integer, Integer> fun = (i, val) -> {
        if(val >= i.length){
            return -999;
        }
        return i[val];
    };
    public static void main(String args[]) {
        int a1[] = {1,2,4,51,3,4,5,6,7,2,3,4};
        int a2[] = {3,2,52,12,4,43,54,5,6,56,56,56,5,11};
        System.out.println(fun.apply(a1,2));
        System.out.println(fun.apply(a1,12));
        System.out.println(fun.apply(a1,7));
        System.out.println(fun.apply(a1,21));
        System.out.println(fun.apply(a2,1));
        System.out.println(fun.apply(a2,11));
        System.out.println(fun.apply(a2,9));
    }
}
