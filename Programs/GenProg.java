package Programs;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from genProg to GenProg
 * 2. Added package name
 * 3. Optimized imports
 */
public class GenProg<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return this.item;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GenProg<String> gc1 = new GenProg<>();
        gc1.setItem("Hello");
        String item1 = gc1.getItem();
        System.out.println(item1);

        GenProg<Integer> gc2 = new GenProg<>();
        gc2.setItem(new Integer(1));
        Integer item2 = gc2.getItem();
        System.out.println(item2);

        GenProg<Double> gc3 = new GenProg<>();
        gc3.setItem(new Double(3.33));
        Double item3 = gc3.getItem();
        System.out.println(item3);

        GenProg<Boolean> gc4 = new GenProg<>();
        gc4.setItem(new Boolean(false));
        Boolean item4 = gc4.getItem();
        System.out.println(item4);
    }
}
