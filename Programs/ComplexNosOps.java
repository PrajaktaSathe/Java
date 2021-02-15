// Program to demonstrate operations on complex numbers in Java -
package ComplexNumsOps;
import java.util.Scanner; // For user-input
class Complex {
	double real1, real2, imag1, imag2;
	// Function to input data from user
	public void get_data() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter real part of first complex number: ");
		real1 = sc.nextDouble();
		System.out.println("Enter imaginary part first of complex number: ");
		imag1 = sc.nextDouble();
		System.out.println("Enter real part of second complex number: ");
		real2 = sc.nextDouble();
		System.out.println("Enter imaginary part second of complex number: ");
		imag2 = sc.nextDouble();
	}
	// Function to display data that has been taken as input from user
	public void display() {
		System.out.println("Entered first complex number is: " + real1 + " + " + imag1 + "i");
		System.out.println("Entered second complex number is: " + real2 + " + " + imag2 + "i");
	}
	// Function to add two complex numbers
	public void add() {
		double addr = real1 + real2;
		double addi = imag1 + imag2;
		System.out.println("Addition is: " + addr + " + " + addi + "i");
	}
	// Function to subtract two complex numbers
	public void sub() {
		double subr = real1 - real2;
		double subi = imag1 - imag2;
		System.out.println("Subtraction is: " + subr + " + " + subi + "i");
	}
	// Function to multiply two complex numbers
	public void mult() {
		double s1, s2, s3, rs2;
		s1 = real1 * real2;
		s2 = (real1 * imag2) + (real2 * imag1);
		rs2 = Math.round((s2 * 100)/100);
		if (imag1 == 0 || imag2 == 0) {
			s3 = 0;
		}
		else {
			s3 = (-1) * (imag1 * imag2);
		}
		s1 = s1 + s3;
		System.out.println("Multiplication is: " + s1 + " + " + rs2 + "i");
		
	}
	// Function to divide two complex numbers
	public void div() {
		double d1, d2, d3, d4, d5, d6;
		if (real2 == 0 && imag2 == 0) {
			System.out.println("Division cannot be performed.");
		}
		else { 
			d1 = real1 * real2; // For numerator
			d2 = (real1 * imag2) + (real2 * imag1); // For numerator
			d4 = (real2 * real2); // For denominator
			d5 = (real2 * (-1) * imag2) + (real2 * (-1) * imag2); // For denominator
			d6 = d4 + d5; // For denominator
			if (imag1 == 0 || imag2 == 0) {
				d3 = 0;
			}
			else {
				d3 = (-1) * (imag1 * imag2);
			}
			d1 = d1 + d3;
			System.out.println("Division is: " + d1/d6 + " + " + d2/d6 + "i");
		}
	}
	
};

public class CmplxNumOps {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		Complex c1 = new Complex(); // Creation of object of class Complex
		c1.get_data();
		c1.display();
		System.out.println("Enter 1 for addition, 2 for subtraction, 3 for multiplication, 4 for division: ");
		choice = sc.nextInt();
		switch (choice) {
		case 1:
			c1.add();
			break;
		case 2:
			c1.sub();
			break;
		case 3:
			c1.mult();
			break;
		case 4:
			c1.div();
			break;
		default:
			System.out.println("Wrong input. Please enter correct choice.");
		}
	}
}
