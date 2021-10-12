import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NumberToRoman {

    /**
     * A function to convert a number to roman numerals Examples: num = 3 -> `III`,
     * num = 58 --> `LVIII`
     * 
     * @param num
     * @return String
     */

    public static String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");

        List<Integer> mapKeys = Arrays.asList(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
        int remainder = num;
        StringBuilder result = new StringBuilder("");
        int mapKeysIndex = 0;

        while (remainder != 0) {
            int currentComparison = mapKeys.get(mapKeysIndex);
            if (remainder < currentComparison) {
                mapKeysIndex++;
                continue;
            }

            result.append(map.get(currentComparison));
            remainder = remainder - currentComparison;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please enter an Integer value");
                int num = Integer.parseInt(sc.next());
                System.out.println(intToRoman(num));
            } catch (Exception e) {
                System.out.println("Something went wrong, please make sure to enter integer only");
            }
        }
    }
}
