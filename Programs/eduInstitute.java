package realWorldScenario;
import java.util.Scanner;

class Person {
	Scanner sc = new Scanner(System.in);
	public String name;
	public int id;
}

class Student extends Person {
	public int year;
	public String course;
	void getDetails() {
		System.out.println("Enter name: ");
		name = sc.nextLine();
		System.out.println("Enter id: ");
		id = sc.nextInt();
		System.out.println("Enter year: ");
		year = sc.nextInt();
		System.out.println("Enter course: ");
		course = sc.next();
		System.out.println();
	}
	void display() {
		System.out.println("Displaying student details: ");
		System.out.println("Name: " + name);
		System.out.println("ID: " + id);
		System.out.println("Year: " + year);
		System.out.println("Course: " + course);
	}
}

class Teacher extends Person {
	public int expYears;
	public String subject;
	void getDetails() {
		System.out.println("Enter name: ");
		name = sc.nextLine();
		System.out.println("Enter id: ");
		id = sc.nextInt();
		System.out.println("Enter years of experience: ");
		expYears = sc.nextInt();
		System.out.println("Enter subject: ");
		subject = sc.next();
	}
	void display() {
		System.out.println("Displaying teacher details: ");
		System.out.println("Name: " + name);
		System.out.println("ID: " + id);
		System.out.println("Years of experience: " + expYears);
		System.out.println("Subject taught: " + subject);
	}
}

public class EduInstitute {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student();
		s1.getDetails();
		s1.display();
		System.out.println();
		Teacher t1 = new Teacher();
		t1.getDetails();
		t1.display();
	}
}
