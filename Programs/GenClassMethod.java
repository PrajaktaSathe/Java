package Programs;

/**
 * BugFix
 * Descriptions:
 * 1. Added package name
 * 2. Optimized imports
 */
public class GenClassMethod {
    public static <T> void printItems(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        String arr1[] = {"Cat", "Mouse", "Dog"};
        Integer arr2[] = {1, 2, 3};
        Double arr3[] = {10.5, 3.33, 7.33};

        GenClassMethod.printItems(arr1);
        GenClassMethod.printItems(arr2);
        GenClassMethod.printItems(arr3);

    }
}

// Using generic program, implement a stack