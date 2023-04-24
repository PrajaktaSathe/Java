package Programs;

public class Check_If_Sorted {
    public static void main(String[] args) {
        int[] toCheck = {1, 2, 3, 5, 16, 8};
        int pointer = 0;
        System.out.println(checkIfSorted(toCheck, pointer));
    }

    private static boolean checkIfSorted(int[] toCheck, int pointer) {
        if (pointer == toCheck.length - 1) {
            return true;
        }
        return toCheck[pointer] < toCheck[pointer + 1] && checkIfSorted(toCheck, pointer + 1);
    }
}
