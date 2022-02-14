// In this code a linkedlist is taken as input and the reversed form is displayed
import java.util.*;
public class ReverseList {
    public static void main(String args[])
    {
        try (Scanner sc = new Scanner(System.in)) {
            ListNode fresh,start=null,prev=null,ptr=null;
            int c;
            do
            {
                fresh=new ListNode();
                System.out.println("Enter the data");
                fresh.data=sc.nextInt();
                if(start==null)
                {
                    start=fresh;
                    // stores the starting address
                }
                else
                {
                    prev.next=fresh;
                }
                prev=fresh;
                System.out.println("Press 1 to for next node initialization else press any number other than 1 to exit");
                c=sc.nextInt();
                // for next node initialization option is asked from the user
            }while(c==1);
            Solution nm=new Solution();
            System.out.println("The original LinkedList is ");
            for(ptr=start;ptr!=null;ptr=ptr.next)
            {
                System.out.print(ptr.data+" ");
            }
            System.out.println();
            // displayes the original linkedlist
            System.out.println("The reversed LinkedList is ");
            nm.reverseList(start);
            for(ptr=start;ptr!=null;ptr=ptr.next)
            {
                System.out.print(ptr.data+" ");
            }
            System.out.println();
            // displays the reserved linkedlist
        }
    }
}
class Solution 
{
    public ListNode reverseList(ListNode start) 
    {
        if(start==null)
            return null;
            // if there is no elements in the linkedlist then it returns null
        else
        {
            ListNode ptr,ptr1,ptr2;
				for(ptr=null,ptr1=start,ptr2=start.next,ptr1.next=null;ptr2!=null;ptr2=ptr2.next,ptr1.next=ptr)
				{
					ptr=ptr1;
					ptr1=ptr2;
				}
				start=ptr1;
				return start;
                // returns the start position of the linkedlist after reversing the list
        }
    }
}
// Solution class contains reverseList method which takes the start address as input and reverses the whole linkedlist
class ListNode 
{
    int data;
    ListNode next;
}
// ListNode class is created for linklist creation