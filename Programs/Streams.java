import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SquareNumbersList {
    public static void main(String...doYourBest){
        List<Integer> numbers = Arrays.asList(2,4,5,8,9);

        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

    }
}
