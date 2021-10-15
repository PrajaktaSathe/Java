import java.util.Random;
import java.util.Scanner;

public class Rockpaperscissor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Any One:\n1. Rock \n2. Paper \n3. Scissor");
        int user = sc.nextInt();
        if (user > 3 || user < 1) {
            System.out.println("Plz type valid input");
            return;
        }

        Random random = new Random();
        int computer = random.nextInt(1, 4);
        System.out.println("YOU CHOOSE: "+user);
        System.out.println("Computer Choose: "+computer);

        if (user == 1 && computer == 1 || user == 2 && computer == 2 || user == 3 && computer == 3) {
            System.out.println("Match Tie");
        } else if (user == 1 && computer == 3 || user == 2 && computer == 1 || user == 3 && computer == 2) {
            System.out.println("Hurray! YOU WIN");
        } else {
            System.out.println("Better luck next Time: Computer WIN");
        }


    }
}
