package Programs;
// for more info on time and space complexity you can view here -https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/solutions/3848999/binary-search-time-complexity-o-log-n-space-complexity-o-1/
/* Intitution :The code handles duplicates correctly by checking if arr[mid], arr[l], and arr[h] are equal and proceeds accordingly. */

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] arr) {
        int l = 0, h = arr.length - 1, res = arr[0];

        while (l <= h) {
            int mid = (l + h) / 2;
            if (arr[mid] == arr[l] && arr[h] == arr[mid]) {
                res = Math.min(res, arr[l]);
                l++;
                h--;
            } else if (arr[l] < arr[h]) {
                res = Math.min(res, arr[l]);
                break;
            } else if (arr[mid] <= arr[h]) {
                res = Math.min(res, arr[mid]);
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }
}
