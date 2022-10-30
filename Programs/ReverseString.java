public class ReverseString {
    public static void main(String[] args) {
        String word = "Hello Jorge";
        String reverseWord = "";

        for (int i = word.length(); i != 0; i--) {
            reverseWord += word.substring(i-1,i);
        }
        
        System.out.println(reverseWord);
        
    }
}
