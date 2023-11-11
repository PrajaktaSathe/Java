package Programs;

class Human {
    //Overridden method
    public void eat() {
        System.out.println("Human is eating");
    }
}

class Boy extends Human {
    //Overriding method
    public void eat() {
        System.out.println("Boy is eating");
    }
}

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from Overriding to OverridingDemo
 * 2. Added package name
 */
public class OverridingDemo {
    public static void main(String[] args) {
        Human human = new Boy();
        human.eat(); // Runtime polymorphism. print -> Boy is eating

        Human human1 = new Human();
        human1.eat(); // print -> Human is eating
    }
}