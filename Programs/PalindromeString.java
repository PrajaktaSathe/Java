import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class PalindromeString{
    private static boolean isPalindrome(String str){
        //return true if the given string is a palindromic string or not
        return new StringBuilder(str).reverse().toString().equals(str);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(isPalindrome(br.readLine())? "Palindrome" : "NOT Palindrome");
    }
}
