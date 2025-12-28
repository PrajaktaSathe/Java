class Human {
    //Overridden method
    public void eat() {
        System.out.println("Human is eating");
    }
}

class Boy extends Human {
    public static void main(String args[]) {
        Boy obj = new Boy();
        //This will call the child class version of eat()
        obj.eat();
    }

    //Overriding method
    public void eat() {
        System.out.println("Boy is eating");
    }
}