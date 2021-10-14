package games;
import java.util.HashSet;
import java.util.Scanner;

public class TicTacToe {
    static HashSet<Integer>ur_set = new HashSet<Integer>();
    static HashSet<Integer>comp_set = new HashSet<Integer>();

    static int generate_rd(){
        int min=1;
        int max=9;
        int range= max-min+1;
        int res=(int) (Math.random()*range)+min;
        return res;
    }

    static void print_board(char[][] arr){
        for(int r =0;r<arr.length;r++){
            for(int c=0;c<arr[0].length;c++){
                System.out.print(" "+arr[r][c]);
            }
            System.out.println();
        }

    }

    static void p_holder(char[][]arr,int pos,String user){
        char sym='X';
        if(user.equals("Me")){
            sym='X';
            ur_set.add(pos);
        }else if (user.equals("Comp")){
            sym='O';
            comp_set.add(pos);
        }else{
            System.out.println("Invalid user");
        }

        switch (pos){

            case 1:
                arr[0][0]=sym;
                break;
            case 2:
                arr[0][2]=sym;
                break;
            case 3:
                arr[0][4]=sym;
                break;
            case 4:
                arr[2][0]=sym;
                break;
            case 5:
                arr[2][2]=sym;
                break;
            case 6:
                arr[2][4]=sym;
                break;
            case 7:
                arr[4][0]=sym;
                break;
            case 8:
                arr[4][2]=sym;
                break;
            case 9:
                arr[4][4]=sym;
                break;
            default:
                System.out.println("Invalid input");
        }
        System.out.println();
        print_board(arr);
    }

    static String check_winner(){
        //we have 8 winning condition
        HashSet<Integer>r1=new HashSet<Integer>();
        r1.add(1);r1.add(2);r1.add(3);
        HashSet<Integer>r2=new HashSet<Integer>();
        r2.add(4);r2.add(5);r2.add(6);
        HashSet<Integer>r3=new HashSet<Integer>();
        r3.add(7);r3.add(8);r3.add(9);
        HashSet<Integer>c1=new HashSet<Integer>();
        c1.add(1);c1.add(4);c1.add(7);
        HashSet<Integer>c2=new HashSet<Integer>();
        c2.add(2);c2.add(5);c2.add(8);
        HashSet<Integer>c3=new HashSet<Integer>();
        c3.add(3);c3.add(6);c3.add(9);
        HashSet<Integer>d1=new HashSet<Integer>();
        d1.add(1);d1.add(5);d1.add(9);
        HashSet<Integer>d2=new HashSet<Integer>();
        d2.add(3);d2.add(5);d2.add(7);

        HashSet<HashSet<Integer>> set=new HashSet<>();
        set.add(r1);set.add(r2);set.add(r3);
        set.add(c1);set.add(c2);set.add(c3);
        set.add(d1);set.add(d2);
        for (HashSet<Integer> c: set) {
            if (ur_set.containsAll(c)){
              return "You Won";
            }else if(comp_set.containsAll(c)){
                return "You Lose , Computer Won";

            }
        }
        if(ur_set.size()+ comp_set.size()==9){
            return "Draw";
        }
        return "";

    }
    public static void main(String[] args) {
        // creating a tic tac toe game.r=5 ,c=5
        char [][] gboard={
                {' ','|',' ','|',' '},
                {'-','|','-','|','-'},
                {' ','|',' ','|',' '},
                {'-','|','-','|','-'},
                {' ','|',' ','|',' '},
        };
        print_board(gboard);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Enter values from 1 to 9 :");
            int user_pos=sc.nextInt();
            while(ur_set.contains(user_pos)|| comp_set.contains(user_pos)){
                System.out.println();
                System.out.print("Retry , Enter values from 1 to 9 :");
                user_pos=sc.nextInt();
            }
            p_holder(gboard,user_pos,"Me");

            String res=check_winner();
            if(res.length()>0)
            {
                System.out.println(res);
                break;
            }

            int comp_pos=generate_rd();
            while(ur_set.contains(comp_pos)|| comp_set.contains(comp_pos)){

                comp_pos=generate_rd();
            }
            p_holder(gboard,comp_pos,"Comp");

            res=check_winner();
            if(res.length()>0)
            {
                System.out.println(res);
                break;
            }
        }
    }
}
