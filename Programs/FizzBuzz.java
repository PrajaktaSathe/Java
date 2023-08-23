// Program to demonstrate fizzbuzz algorithm using Java joptionpane-
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputMessage = JOptionPane.showInputDialog("Enter a number to determine if it's fizz or buzz");
        int number = Integer.parseInt(inputMessage);

        if (number % 3 == 0 && number % 5 == 0) {
            JOptionPane.showMessageDialog(null, "The number is FizzBuzz");
        } else if (number % 3 == 0) {
            JOptionPane.showMessageDialog(null, "The number is Fizz");
        } else if (number % 5 == 0) {
            JOptionPane.showMessageDialog(null, "The number is Buzz");
        } else {
            JOptionPane.showMessageDialog(null, "The number is neither Fizz nor Buzz: " + number);
        }
    }
}