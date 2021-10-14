// Java program to implement interpolation
public class InterpolationSearch {
    
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] arr = {12, 23, 10, 34, 55, 4, 68, 3, 73};
    Arrays.sort(arr);
    System.out.println("sorted array- " + Arrays.toString(arr));
    System.out.println("Enter value to search: ");
    int searchElement = sc.nextInt();
    int index = interpolationSearch(arr, searchElement);
    if(index != -1){
      System.out.println("Searched item " + arr[index] + " found at index "+index);
    }else{
      System.out.println("Searched item " + searchElement + " not found in the array");
    }
    sc.close();
  }

  private static int interpolationSearch(int[] arr, int searchElement){
    int start = 0;
    int end = arr.length - 1;
    int position;
    while ((arr[end] != arr[start]) && (searchElement >= arr[start]) && (searchElement <= arr[end])) {
      position = start + ((searchElement - arr[start]) * (end - start) / (arr[end] - arr[start]));

      if (arr[position] < searchElement)
        start = position + 1;
      else if (searchElement < arr[position])
        end = position - 1;
      else
        return position;
    }
    if (searchElement == arr[start])
      return start ;
    else
      return -1;
  }
}