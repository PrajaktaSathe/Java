import java.util.List;
import java.util.stream.Collectors;

/*
     A demo for the Java Streams API
     Includes examples with lists of numbers and words
 */
public class JavaStreams {

    private static final List<Integer> numbers =
            List.of(10, 2, 21, 23, 22, 14, 16, 18, 19, 9, 4, 6, 7);

    private static final List<String> words =
            List.of("apple", "bread", "tree", "computer", "name", "brain", "arm");

    //Use stream to find all numbers that are greater than 10
    public static List<Integer> findAllNumbersGreaterThanTen(List<Integer> numbers){
        return numbers.stream()
                .filter(number -> number > 10)
                .sorted()
                .collect(Collectors.toList());
    }

    //Use stream to find all numbers that contain the letter a
    public static List<String> findAllWordsThatContainLetterA(List<String> words){
        return words.stream()
                .filter(word -> word.contains("a"))
                .collect(Collectors.toList());
    }

    //print out the origin lists, and then the changed lists
    public static void main(String[] args) {
        System.out.println("The unsorted array is: "
                + numbers);

        System.out.println("The sorted array is: "
                + numbers.stream().sorted().toList());

        System.out.println("All numbers in the list greater than 10: "
                + findAllNumbersGreaterThanTen(numbers));

        System.out.println();

        System.out.println("A list of words: " + words);
        System.out.println("All words in the list that contain the letter A: "
                + findAllWordsThatContainLetterA(words));
    }
}
