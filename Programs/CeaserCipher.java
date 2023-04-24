package Programs;

import java.util.Scanner;

public class CeaserCipher {
    public static void main(String[] args) {
        System.out.println("""
                --------------------------------
                 CEASER CIPHER ENCRYPT/ DECRYPT
                --------------------------------
                """);
        Scanner sc = new Scanner(System.in);
        System.out.println("Encrypt or Decrypt:\n 1- Encryption\n 2- Decryption");
        int type = sc.nextInt();
        for (int j = 1; j > 0; j++) {
            if (type == 1) {
                ceaserCipherEncryption();
                break;
            } else if (type == 2) {
                ceaserCipherDecryption();
                break;
            } else {
                System.out.print("Please enter a valid number (1 or 2): ");
                type = sc.nextInt();
            }
        }
    }
    public static void ceaserCipherEncryption() {
        Scanner textInput = new Scanner(System.in);
        System.out.print("PlainText: ");
        String textToEncrypt = textInput.nextLine();
        System.out.print("shifting key: ");
        int keyToUse = textInput.nextInt();
        String cipher = "";
        for (int i = 0; true; i++) {
            if (keyToUse > 50 && keyToUse <= 0) {
                System.out.print("Please Enter key value <= 50: ");
                keyToUse = textInput.nextInt();
            } else {
                for (int j = 0; j < textToEncrypt.length(); j++) {
                    char temp = textToEncrypt.charAt(j);
                    if (temp >= 'A' && temp <= 'Z') {
                        temp = (char) (temp + keyToUse);
                        if (temp > 'Z') { // go back to A in Ascii
                            temp = (char) (temp + 'A' - 'Z' - 1);

                        }
                        cipher += temp;
                    } else if (temp >= 'a' && temp <= 'z') {
                        temp = (char) (temp + keyToUse);
                        if (temp > 'z') { // go back to a in Ascii
                            temp = (char) (temp + 'a' - 'z' - 1);

                        }
                        cipher += temp;
                    } else
                        cipher += temp;

                }
            }
            System.out.println("Enrypted text is: " + cipher);
            break;

        }
    }


    public static void ceaserCipherDecryption() {
        Scanner textInput = new Scanner(System.in);
        System.out.print("Encrypted Text: ");
        String textToEncrypt = textInput.nextLine();
        System.out.print("shifting key: ");
        int keyToUse = textInput.nextInt();
        String cipher = "";
        for (int i = 0; true; i++) {
            if (keyToUse > 50 && keyToUse <= 0) {
                System.out.print("Please Enter key value <= 50: ");
                keyToUse = textInput.nextInt();
            } else {
                for (int j = 0; j < textToEncrypt.length(); j++) {
                    char temp = textToEncrypt.charAt(j);
                    if (temp >= 'A' && temp <= 'Z') {
                        temp = (char) (temp - keyToUse);
                        if (temp < 'A') { // go back to A in Ascii
                            temp = (char) (temp - 'A' + 'Z' + 1);

                        }
                        cipher += temp;
                    } else if (temp >= 'a' && temp <= 'z') {
                        temp = (char) (temp - keyToUse);
                        if (temp < 'a') { // go back to a in Ascii
                            temp = (char) (temp - 'a' + 'z' + 1);
                        }
                        cipher += temp;
                    } else
                        cipher += temp;
                }
            }
            System.out.println("Decrypted text is: " + cipher);
            break;
        }
    }

}
