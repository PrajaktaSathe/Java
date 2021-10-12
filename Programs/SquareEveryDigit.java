public class SquareDigit {

  public int squareDigits(int n) {
  
    String strDigits = String.valueOf(n);    
    String result = "";
    
    for (char c : strDigits.toCharArray()) {
      int digit = Character.digit(c, 10);
      result += digit * digit;
    }
    
    return Integer.parseInt(result);
  }

}
