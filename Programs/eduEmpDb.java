// An educational institution wishes to maintain a database of employees. Database is divided into number of classes.
// This hierarchical relationships are shown in the figure, it also shows minimum information required for each class and define methods to create a database
// and retrieve individual info as and when required.
import java.util.Scanner;
class Staff {
	// code
	// name
	Scanner sc = new Scanner(System.in);
	public int emp_id;
	public String name;
}

class Teacher extends Staff {
	// subject 
	// publication
	public String subject, publication;
	public void getData() {
		System.out.println("Enter employee details: ");
		System.out.println("Enter name: ");
		name = sc.nextLine();
		System.out.println("Enter employee id: ");
		emp_id = sc.nextInt();
		System.out.println("Enter subject: ");
		subject = sc.nextLine();
		System.out.println("Enter publication: ");
		publication = sc.nextLine();
	} 
	public void displayData() {
		System.out.println("Displaying employee details: ");
		System.out.println("Name: " + name);
		System.out.println("Employee id: " + emp_id);
		System.out.println("Subject: " + subject);
		System.out.println("Publication: " + publication);
	}
}

class Typist extends Staff {
	// speed
	public double speed;
	public void getData() {
		System.out.println("Enter employee details: ");
		System.out.println("Enter name: ");
		name = sc.nextLine();
		System.out.println("Enter employee id: ");
		emp_id = sc.nextInt();
		System.out.println("Enter speed: ");
		speed = sc.nextDouble();
	} 
	public void displayData() {
		System.out.println("Displaying employee details: ");
		System.out.println("Name: " + name);
		System.out.println("Employee id: " + emp_id);
		System.out.println("Speed: " + speed);
	}
}

class Officer extends Staff {
	// grade
	public String grade;
	public void getData() {
		System.out.println("Enter employee details: ");
		System.out.println("Enter name: ");
		name = sc.nextLine();
		System.out.println("Enter employee id: ");
		emp_id = sc.nextInt();
		System.out.println("Enter grade: ");
		grade = sc.next();
	} 
	public void displayData() {
		System.out.println("Displaying employee details: ");
		System.out.println("Name: " + name);
		System.out.println("Employee id: " + emp_id);
		System.out.println("Grade: " + grade);
	}
}

class Regular extends Typist {
	
}

class Casual extends Typist {
	// daily wages
	public double dwages;
	public void getData() {
		System.out.println("Enter employee details: ");
		System.out.println("Enter name: ");
		name = sc.nextLine();
		System.out.println("Enter employee id: ");
		emp_id = sc.nextInt();
		System.out.println("Enter daily wages: ");
		dwages = sc.nextDouble();
	}
	public void displayData() {
		System.out.println("Displaying employee details: ");
		System.out.println("Name: " + name);
		System.out.println("Employee id: " + emp_id);
		System.out.println("Daily wages: " + dwages);
	}
}
public class EduEmpDB {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("Enter 1 for teacher, 2 for regular typist, 3 for casual typist, 4 for officer, 5 to exit: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				Teacher t = new Teacher();
				t.getData();
				t.displayData();
				break;
			case 2:
				Regular r = new Regular();
				r.getData();
				r.displayData();
				break;
			case 3:
				Casual c = new Casual();
				c.getData();
				c.displayData();
				break;
			case 4:
				Officer o = new Officer();
				o.getData();
				o.displayData();
				break;
			default:
				if (choice != 5) {
					System.out.println("Invalid input!");
				}
				else if (choice == 5) {
					System.out.println("End of program");
				}
			}
		} while(choice != 5);		
	}
}
