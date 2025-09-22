package Programs;

import java.util.List;

/**
 * Program to demo the usage of enums
 *
 */
enum Role{
    STUDENT,
    TEACHER,
    ADMIN
}

class Person{
     String name;
     Role role;

    public Person(String name, Role role){
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString(){
        return name + " is a"  + role;
    }

}

public class EnumDemo {

    public static void main(String[] args){
        List<Person> personList = List.of(
            new Person("Alice", Role.STUDENT),
            new Person("Alice", Role.TEACHER),
            new Person("Carol", Role.STUDENT),
            new Person("Dave", Role.ADMIN)
        );

        List<Person> filteredList = personList.stream()
                .filter( person -> person.role == Role.STUDENT)
                .toList();

        filteredList.forEach(System.out::println);

    }

}
