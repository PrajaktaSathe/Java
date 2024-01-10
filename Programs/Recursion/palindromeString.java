package Recursion;

public class palindromeString {

    public static boolean fun(String s, int l, int r){
        if(l>=r){
            return true;
        }
        if(s.charAt(l) != s.charAt(r)){
            return false;
        }
        return fun(s, l+1, r-1);
    }

    public static void main(String[] args){
        String str = "MADAM";

        int l = 0;
        int r = str.length() - 1;
        System.out.println(fun(str, l, r));


    }
}


// This article is Contributed by Himanshu Jha.
// Connect with me on linkedin https://www.linkedin.com/in/himanshujhaa/