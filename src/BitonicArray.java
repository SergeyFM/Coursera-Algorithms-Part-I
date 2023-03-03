/**
 Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers
 followed immediately by a decreasing sequence of integers. Write a program that,
 given a bitonic array of N distinct integer values, determines whether a given integer is in the array.

 Standard version: Use ~3lgN compares in the worst case.
 Signing bonus: Use ~2lgN compares in the worst case
 (and prove that no algorithm can guarantee to perform fewer than ~2lgN compares in the worst case).

 */

class BitonicArray {

  private static int binarySearchPeak(int[] array, int start, int end) {
    int mid = (start + end) / 2;
    if (start == end)
      return mid;
    else if (array[mid] < array[mid+1])
      return binarySearchPeak(array, mid+1, end);
    else
      return binarySearchPeak(array, start, mid);
  }

  private static boolean bitonicSearch(int[] array, int start, int end, int target, boolean desc) {
    int mid = (start + end) / 2;
    if (array[mid] == target) return true;
    if (start > end) return false;

    if ((desc && array[mid] < target) || (!desc && array[mid] > target))
         return bitonicSearch(array, start, mid-1, target, desc);
    else return bitonicSearch(array, mid+1, end, target, desc);
  }

  private static boolean find(int[] array, int peak, int target) {
    // binary search on the both halves
    return bitonicSearch(array, 0, peak, target, false) || bitonicSearch(array, peak, array.length-1, target, true);
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 10, 13, 15, 18, 17, 16, 12, 11};
    int peak = binarySearchPeak(arr, 0, arr.length-1);
    System.out.println("Elements nbr: " + arr.length + "\nPeak ptr: " + peak);
    System.out.println(0 + ":\t" + find(arr, peak, 0));
    System.out.println(-1 + ":\t" + find(arr, peak, -1));
    System.out.println(17 + ":\t" + find(arr, peak, 17));
    System.out.println(18 + ":\t" + find(arr, peak, 18));
  }
}
