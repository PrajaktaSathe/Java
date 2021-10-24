/*
Name - Jayneel Shah
Date - 23/10/21
Topic - Application of classes in Java
Program to implement growable self sorting array(GSSArray)
*/

import java.util.*;

class GSSArray
{
    private int [] arr;
    private int size;
    private int lastIndex = -1;

    //default constructor
    GSSArray(int n)
    {
        arr = new int[n];
        size = n;
    }

    int i = 0;

    //insert function
    public void insert(int value)
    {
        //if size is less than defined size regular addition
        if (size > i)
        {
            lastIndex++;
            arr[lastIndex] = value;
            i++;
            display();
        }

        //if the size is more then it first increases the size and then performs addition in the array
        else
        {
            increaseSize();
            lastIndex++;
            arr[lastIndex] = value;
            i++;
            display();
        }
    }

    //incrase size function - it doubles the size and copie the elements of old array in the new array
    private void increaseSize()
    {
        int[] arr1 = new int[arr.length*2];
    		for(int i=0;i<arr.length;i++)
    		{
      			arr1[i] = arr[i];
    		}

            arr=arr1;
    }

    //to check whether the element to be delted is present in the array is present or not
    public boolean delete(int value) 
    {
        int pos = -1;
        for (int index = 0; index < lastIndex; index++) 
        {
            if (arr[index] == value) 
            {
                pos = index;
                break;
            }
        }
        if (pos == -1) 
        {
            return false;
        }
        else
        {
            System.out.println(pos);
            for (int i = pos ; i < size - 1; i++) 
            {
                arr[i] = arr[i + 1];
            }
            i--;
            lastIndex = lastIndex - 1;
            return true;
        }
    }

    //display function
    void display()
    {
    sort();
    System.out.print("Elements in array:- \n");
    for(int i=0;i<=lastIndex;i++)
    {
        System.out.print(arr[i] + " ");
    }
    System.out.println();
    }

    //function to sort the incoming elements
    void sort()
    {
      int temp;
      for(int i=0;i<=lastIndex;i++)
      {
          for(int j=i+1;j<=lastIndex;j++)
          {
              if(arr[i]>arr[j])
              {
                 temp = arr[i];
                 arr[i] = arr[j];
                 arr[j] = temp;
              }
          }
      }
    }
}

class Gssarray
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GSSArray gs = new GSSArray(5);

        int choice, val;
        boolean ans;

        while (true) {
            System.out.println("Enter 1 to insert");
            System.out.println("Enter 2 to delete");
            System.out.println("Enter 3 to exit");
            choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("Enter the value to enter in array: ");
                val = sc.nextInt();
                gs.insert(val);
            } else if (choice == 2) {
                System.out.print("Enter the value to delete: ");
                val = sc.nextInt();
                ans = gs.delete(val);
                System.out.println(ans);
                gs.display();
            } else if (choice == 3) {
                System.out.println("Thank You!");
                break;
            }
        }
        sc.close();
    }
}
