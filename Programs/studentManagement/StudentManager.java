//store and manage Student objects 
package Programs.studentManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class StudentManager {
    //HashMap to store students with ID as key for quick lookup
    private final Map<String, Student> students = new HashMap<>();

    //check existence by ID
    public boolean studentExists(String id) {
        if (id == null) return false;
        return students.containsKey(id);
    }

    public boolean addStudent(Student s) {
        if ( s == null || s.getId() == null || s.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Student or Student ID cannot be null/empty.");
        }
        String id = s.getId().trim();
        if (students.containsKey(id) || findByEmail(s.getEmail()) != null){
            return false; //student with same ID exists
        }
        String email = s.getEmail();
        if ( email == null)  {
            return false;
        } else {
            students.put(id, s);
            return true; //successfully added
        }
    }

    public Student findById(String id) {
        if ( id == null) return null;
        return students.get(id);
    } 

    //Linear search for email.Normalizes by trimming and lower-casting
    public Student findByEmail(String email) {
        if ( email == null) return null;
        String norm = normalizedEmail(email);
        for (Student s : students.values()) {
            String sEmail = normalizedEmail(s.getEmail());
            if (sEmail != null && norm.equals(normalizedEmail(sEmail))) {
                return s;
            }
        }
        return null;
    } 

    public boolean updateStudent(Student updated) {
        if ( updated == null || updated.getId() == null ) {
            throw new IllegalArgumentException("Student and id must not be empty.");
        }
        String id = updated.getId();
        if (!students.containsKey(id)) {
            return false;
        }
        Student updatedEmail = findByEmail(updated.getEmail());
        if (updatedEmail != null && !updatedEmail.getId().equals(id)) {
            return false; //another student has this email
        }
        students.put(id, updated);
        return true;
    }

    public boolean deleteStudents(String id) {
        if ( id == null ) return false;
        return students.remove(id) != null;
    }

    public List<Student> listAll() {
        return new ArrayList<>(students.values()); 
    }

    //helpers method
    private String normalizedEmail(String email) {
        if ( email == null) return null;
        return email.trim().toLowerCase();
    }
}
