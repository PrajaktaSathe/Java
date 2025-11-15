//student data model
package Programs.studentManagement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
        //private accessed via getter setter
        private String id;
        private String name;
        private int age;
        private String faculty;
        private String email;

        /*Email verification regex:
         * Basic format: 
         * ^: Asserts the start of the string.
         * [a-zA-Z0-9_+&*-]+: Matches one or more alphanumeric characters, plus _, +, &, *, and - for the local part (username) of the email.
        (?:\\.[a-zA-Z0-9_+&*-]+)*: Optionally matches additional parts of the local part, separated by a dot (.). The (?: ... ) creates a non-capturing group.
        @: Matches the literal @ symbol.
        (?:[a-zA-Z0-9-]+\\.)+: Matches one or more domain components, each consisting of alphanumeric characters and hyphens, followed by a dot (.).
        [a-zA-Z]{2,7}: Matches the top-level domain (TLD), which must be 2 to 7 alphabetic characters long. This range is a common practice, but specific TLDs can be longer or shorter. 
        $: Asserts the end of the string.
         */
        private static final String EMAIL_FORMAT = "(?=^.{4,40}$)[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$";; //compiles te given regex string into pattern that can be used to reated matcher obj.
        private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_FORMAT, Pattern.CASE_INSENSITIVE);

        //No-arg constructor for creating obj tht will be populated later
        //E.g: Student s = new Student();
        public Student() {}

        //E.g: Student s = new Student(id, name, age, email, faculty);
        public Student(String id, String name, int age, String email, String faculty) {
            this.id = id;
            this.name = name;
            //apply validation for age and email for range and format check
            setAge(age);
            setEmail(email); 
            this.faculty = faculty;
        }

        //getter setup for each field to read values
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getEmail() {
            return email;
        }

        public String getFaculty() {
            return faculty;
        }

        //setter setup for each field to update values
        public void setId(String id ) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            if (age<=15 || age>23) {
                throw new IllegalArgumentException("Age must be between 15 and 23 for college students.");
            } else {
                this.age = age;
            }
        }

        public void setEmail(String email) {
            if (email == null) {
                throw new IllegalArgumentException("Email must not be null.");
            }
            String trimmed = email.trim(); //remove leading/trailing whitespace
            Matcher m = EMAIL_PATTERN.matcher(trimmed);
            if (!m.matches()) {
                throw new IllegalArgumentException("Email format is invalid.");
            }
            // Enforce gmail.com domain to catch typos like "@gmail.xom"
            String lower = trimmed.toLowerCase();
            if (!lower.endsWith("@gmail.com")) {
                throw new IllegalArgumentException("Only gmail.com address are accepted.");
            }
            this.email = trimmed;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }
    }

