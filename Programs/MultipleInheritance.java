// Program to demonstrate multiple inheritance
// Don't use two base classes, instead use an interface.
// HW - 
// WAP which implements hybrid inheritance in which create a class student with data, roll no, name. 
// Create another derived class as test, which uses the base class student
// and will accept the 3 subject marks from the user which will contain the function as reading, displaying the student details
// Create interface sports, which will initialize sports marks
// Another class test which extends the test class and implements the sports interface for calculating the total marks for the student
// Base class - student
// Two derived classes - test, result (extends test and implements sports class)
// Check for hierarchical inheritance (create another class for competitive exams)
package multipleInheritance;
class Test {
	int sub1, sub2, sub3;
	void readData(int x, int y, int z) {
		sub1 = x;
		sub2 = y;
		sub3 = z;
	}
	void display() {
		System.out.println("Sub1 marks = " + sub1);
		System.out.println("Sub2 marks = " + sub2);
		System.out.println("Sub3 marks = " + sub3);
	}
}
interface sports {
	int smarks = 60;
}
class Result extends Test implements sports {
	int total;
	public void printData() {
//		display();
		total = sub1 + sub2 + sub3 + smarks;
		System.out.println("Total marks = " + total);
	}
}
public class MultipleInheritance {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Result obj = new Result();
		obj.readData(50, 60, 70);
		obj.display();
		obj.printData();
	}
}
