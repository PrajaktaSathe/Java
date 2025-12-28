//code for xorpalindrome in Java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


class Xorpalindrome {
    public static void main(String[] args) throws java.lang.Exception {
        // your code goes here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int count1 = 0, count2 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1')
                    count1++;
                else
                    count2++;
            }
            boolean flag = false;
            if (n % 2 == 0) {
                if (count1 == count2 || count1 % 2 == 0 && count2 % 2 == 0)
                    flag = true;
            } else {
                flag = true;
            }
            if (flag)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}