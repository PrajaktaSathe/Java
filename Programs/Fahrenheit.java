/*
This code is a temperature converter that takes user input to determine whether to convert from Celsius to Fahrenheit or from Fahrenheit to Celsius, 
and then performs the calculation using two separate methods within the Converter class. It displays the result in a message dialog.
*/

// Importing the static JOptionPane class 
import static javax.swing.JOptionPane.*;

class Converter {
    // Converts from Celsius to Fahrenheit
    public static double fromCelsius(double celsiusDegrees) {
        double fahrenheit = celsiusDegrees * 9 / 5 + 32;
        return fahrenheit;
    }

    // Converts from Fahrenheit to Celsius
    public static double fromFahrenheit(double fahrenheitDegrees) {
        double celsius = (fahrenheitDegrees - 32) * 9 / 5;
        return celsius;
    }
}

public class Fahrenheit {
    public static void main(String[] args) {
        // Ask user which conversion they want
        String choice = showInputDialog("Convert from C or F?");

        if (choice.equals("C")) {
            // Get input as Celsius
            double celsius = Double.parseDouble(showInputDialog("Enter Celsius degrees"));

            // Convert and display result
            String result = celsius + " Celsius degrees is " + Converter.fromCelsius(celsius) + " Fahrenheit";
            showMessageDialog(null, result);
        }
        if (choice.equals("F")) {
            // Get input as Fahrenheit
            double fahrenheit = Double.parseDouble(showInputDialog("Enter Fahrenheit degrees"));

            // Convert and display result
            String result = fahrenheit + " Fahrenheit degrees is " + Converter.fromFahrenheit(fahrenheit) + " Celsius";
            showMessageDialog(null, result);
        }
    }
}

// sirin-koca
