// This program demonstrates a new and most efficient way to calculate number of digits by using logarithmic functions
/* the normal and most used algorithm to calculate number of digits is
while(num>0){
numberOfDigits=num%10;
numberOfDigits++;
num=num%10;
}
this algorithm has a time complexity of O(n)
we can simply calculate it by log(number)+1.
here is the code for it :- */

class NumberOfDigit {
    public static void main(String args[]) {
        int num = 356, digits = 0;
        digits = (int) Math.log10(num) + 1;
        System.out.println(digits);
    }
}
