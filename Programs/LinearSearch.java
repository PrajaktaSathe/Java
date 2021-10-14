class LinearSearch{
    
    public static int search(int arr[], int x)
    {
        int n = arr.length;
        for (int i = 0; i < n; i++)
        {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }
 
    public static void main(String args[])
    {
        int arr[] = { 45, 56, 37, 79, 46, 18, 90, 81, 51  };
        int x = 79;
 
        int result = search(arr, x);
        if (result == -1)
            System.out.print("\n\nElement is not present in array\n");
        else
            System.out.print("\n\nElement is present at index " + result + "\n");
    }
}
 
