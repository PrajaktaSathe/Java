package Programs;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        list(tasks);
    }

    public static void list(ArrayList<String> tasks) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Function you want to apply(1-4):\n"
                + "1- View\n"
                + "2- Add\n"
                + "3- Delete All Tasks\n"
                + "> ");
        int desiredFunction = sc.nextInt();
        choose(desiredFunction, tasks, sc);
    }

    public static void choose(int desiredFunction, ArrayList<String> tasks, Scanner sc) {
        if (desiredFunction == 1) {
            viewTask(tasks);
        } else if (desiredFunction == 2) {
            addTask(tasks);
        } else if (desiredFunction == 3) {
            deleteTasks(tasks);
        } else {
            System.out.println("Please choose in the range of 1 till 3 only!");
            list(tasks);
        }
    }

    static void addTask(ArrayList<String> tasks) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Task to save\n> ");
        tasks.add(sc.nextLine());
        System.out.print("Add more?\n 1- Yes\n 2- No\n> ");
        int yn = sc.nextInt();
        if (yn == 1) {
            addTask(tasks);
        } else if (yn == 2){
            list(tasks);
        } else {
            System.out.println("Invalid number!");
            addTask(tasks);
        }
    }

    static void viewTask(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("""
                    --------------------
                      No current tasks!
                    --------------------""");
        } else {
            System.out.println("""
                    --------------------
                           Tasks
                    --------------------""");
            int counter = 1;
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(counter + "- " + tasks.get(i));
                counter++;
            }
        }
        System.out.println("\nSelect from the following commands:");
        list(tasks);
    }

    static void deleteTasks(ArrayList<String> tasks) {
        tasks.clear();
    }
}
