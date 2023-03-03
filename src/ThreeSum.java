/**
 Question 1
 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that
 takes time proportional to n^2 in the worst case.
 You may assume that you can sort the n integers in time
 proportional to n^2 or better.
 */

import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

class ThreeSum {

  public static void main(String[] args) {

    ArrayList<Integer> arr = new ArrayList<Integer>();
    Scanner scan = new Scanner(System.in);
    while (scan.hasNextInt()) arr.add(scan.nextInt());

    Collections.sort(arr);

    var size = arr.size();
    System.out.println("Elements: " + size);

    for (int a = 0; a < size-2; ++a) {
      int b = a + 1;
      int c = size - 1;

      while (b < c) {
        int sum = arr.get(a) + arr.get(b) + arr.get(c);
        if (sum == 0) System.out.println(arr.get(a) + " + " + arr.get(b) + " + " + arr.get(c));
        if (sum >= 0) --c; else ++b;
      }
    }

  }

}
