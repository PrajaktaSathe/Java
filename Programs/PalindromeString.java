//JAVA PROGRAM TO CHECK IF A STRING IS PALINDROMIC OR NOT (INPUT TAKEN FROM USER)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromeString{
    private static boolean isPalindromic(String args){
        return new StringBuilder(args).reverse().toString().equals(args);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(isPalindromic(br.readLine())? "Palindrome" : "NOT Palindrome");
    }
}