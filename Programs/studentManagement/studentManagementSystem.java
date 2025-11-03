package Programs.studentManagement;
import java.util.List;
import java.util.Scanner;

public class studentManagementSystem {
    private final StudentManager manager = new StudentManager();//CRUD
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*run method contain loop that shows the menu and processes user choices*/
        new studentManagementSystem().run(); 
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": 
                    handleAdd();
                    break;
                case "2": 
                    handleView();
                    break;
                case "3": 
                    handleUpdate();
                    break;
                case "4": 
                    handleDelete();
                    break;
                case "5": 
                    handleList();
                    break;
                case "6": 
                    running = false;
                    break;
            
                default: System.out.println("Unknown option, try again");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. View Student by ID");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. List All Students");
        System.out.println("6. Exit");
    }

    //helper input method for non-empty strings and integers 
    //promptNonEmpty ensure non-empty input (id, name)
    private String promptNonEmpty(String label, boolean required, String defaultValue) {
        while (true) {
            if (required) {
                System.out.print(label + " ");
            } else {
                System.out.print(label + " [" + (defaultValue == null ? "" : defaultValue) + "]: ");
            }
            
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty())  {
                return defaultValue;
            }
            return input.trim();            
        }
    }

    //loops for integer validation
    private int promptInt(String label, boolean required , Integer defaultValue) {
        while (true) {
            if (required) {
                System.out.print(label + ": ");
            } else {
                System.out.print(label + " [" + defaultValue + "]: ");
            }

            String input = scanner.nextLine();
            if (input == null) input = "";
            input = input.trim();

            if (input.isEmpty() && !required) {
                return defaultValue;
            } else if(required && input.isEmpty()) {
                System.out.println(label + " must not be empty.");
                continue;
            }
            
            try {
                return Integer.parseInt(input);
            //convert a string to numeric type but dont have appropriate format(subclass of IllegalArgumentException)
            } catch (NumberFormatException e) {
                System.out.println("Error: Cannot parse ' " + label + " ' to an integer.");
                System.out.println(e.getMessage());
            }
        }
    }

    private Student collectStudentInput(String suggestedId, boolean requiredId) {
        String id;
        String name = promptNonEmpty("Name: ", true, null);
        int age = promptInt("Age ", true, null);
        String email = promptNonEmpty("Email: ", true, null);
        String faculty = promptNonEmpty("Faculty: ", true, null);

        if (suggestedId != null && !suggestedId.trim().isEmpty()) {
            id = promptNonEmpty("Sugessted ID: ", false, suggestedId);
        } else if (requiredId){
            id = promptNonEmpty("ID: ", true, null);
        } else {
            id = promptNonEmpty("ID", false, null);
        }

        if ( id == null || id.trim().isEmpty()) return null;

        if (manager.findById(id) != null) {
            System.out.println("Student with this id already exists.");
            return null;
        } else if (manager.findByEmail(email) != null) {
            System.out.println("Student with this email already exists.");
            return null;
        }

        try {
            return new Student(id, name, age, email, faculty);
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid Input: " + ex.getMessage());
            return null;
        }
    }

    private void handleAdd() {
        /*System.out.println("--- Add Student ---");
        Student s = collectStudentInput(null, true);
        if (s== null) return;
        boolean added = manager.addStudent(s);
        System.out.println(added ? "Student added." : "Add failed.");*/
        handleAdd((String) null);
    }

    private void handleAdd(String suggestedId) {
        System.out.println("--- Add Student ---");
        Student s = collectStudentInput(suggestedId, true);
        if (s == null) return;
        boolean added = manager.addStudent(s);
        System.out.println(added ? "Student added." : "Add failed.");
    }
        
    private void handleView() {
        System.out.println("--View Student--");
        String id = promptNonEmpty("ID ", true, null);
        Student s = manager.findById(id);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            printStudent(s);
        }
    }

    private void handleUpdate() {
        System.out.println("--- Update Student ---");
        String id = promptNonEmpty("ID of student to update", true, null);
        Student existing = manager.findById(id);
        if (existing == null) {
            System.out.println("Student not found.");
            String answer = promptNonEmpty("Do you want to add a new student with this ID? (y/n)", true, null);
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("Y")){
                // Optionally pre-fill ID into the add flow:
            // You can either set a field or modify handleAdd to accept a prefilled ID.
            // For simplicity, call handleAdd() and let the user enter the ID again,
            // or modify handleAdd to accept an optional suggestedId parameter.
            handleAdd(id);
            } else {
                System.out.println("Update cancelled.");
            }

            return;
        }

            String name = promptNonEmpty("Name", false, existing.getName());
            int age = promptInt("Age", false, existing.getAge());
            String email = promptNonEmpty("Email", false, existing.getEmail());
            String faculty = promptNonEmpty("Faculty", false, existing.getFaculty());

            if (!normalized(email).equals(normalized(existing.getEmail()))) {
                Student emailOwner = manager.findByEmail(email);
                if (emailOwner != null && !emailOwner.getId().equals(id)) {
                    System.out.println("Another student with this email already exists. Update cancelled.");
                    return;
                }
            }

            try {
                Student updated = new Student(id, name, age, email, faculty);
                boolean updateCompleted = manager.updateStudent(updated);
                System.out.println(updateCompleted ? "Student updated." : "Updated failed");
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid Input: " + ex.getMessage());
            }
    }

    private void handleDelete() {
        System.out.println("---Delete Student---");
        String id = promptNonEmpty("ID", false, null);
        boolean deleted = manager.deleteStudents(id);
        System.out.println(deleted ? "Student deleted." : "Student not found.");
    }

    private void handleList() {
        System.out.println("---All Students---");
        List<Student> allStudents = manager.listAll();
        if (allStudents.isEmpty()) {
            System.out.println("No students available");
            return;
        }
        for (Student s : allStudents) {
            printStudent(s);
            System.out.println("-------------------");
        }
    }

    private void printStudent(Student s) {
        System.out.println("ID" + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Age: " + s.getAge());
        System.out.println("Email: " + s.getEmail());
        System.out.println("Faculty: " + s.getFaculty());
    }

    //helper sames as manager
    private String normalized(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }

}
