/*
  Question 3
  Successor with delete.

  Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the
  following form:
  * Remove x from S
  * Find the successor of x: the smallest y in S such that y≥x.

  design a data type so that all operations (except construction)
  take logarithmic time or better in the worst case.
*/

import edu.princeton.cs.algs4.QuickFindUF;

public class SuccessorWithDelete {

    private QuickFindUF quickFindUF;

    public SuccessorWithDelete(int N) {
        quickFindUF = new QuickFindUF(N);
    }

    public void remove(int x) {
        quickFindUF.union(x, x+1);
    }

    public int successor(int x) {
        return quickFindUF.find(x);
    }

    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(100);
        System.out.println(swd.successor(77));
        swd.remove(77);
        System.out.println(swd.successor(77));
        swd.remove(79);
        swd.remove(78);
        System.out.println(swd.successor(77));
    }
}
