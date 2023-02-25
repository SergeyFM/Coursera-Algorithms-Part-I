/*
Question 1
Social network connectivity.
Given a social network containing n members and a log file
containing m timestamps at which times pairs of members formed friendships,
design an algorithm to determine the earliest time at which all members are
connected (i.e., every member is a friend of a friend of a friend ... of a friend).
Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
The running time of your algorithm should be m log n or better and use extra space proportional to n.
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SocialNetwork {

    private static int[] id;
    private static int[] size;
    private static int N;

    public static void main(String[] args) {
        // set n = number of members in the social network
        N = 3;
        id = new int[N];
        size = new int[N];
        // initialize the arrays
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
        // read data from the log file
        boolean allConnected = false;
        while (!StdIn.isEmpty() && !allConnected) {
            // An example of the Log line: 0 1 01.01.2001-01:01:01
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            String date = StdIn.readString();

            allConnected = union(p,q);
            if (allConnected) StdOut.println("All members are connected at " + date);
        }
        if (!allConnected) StdOut.println("Not all members are connected");
    }

    private static int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    private static boolean union(int p, int q) {
        // connects p and q
        // returns true when there are no more members to connect
        int i = root(p);
        int j = root(q);
        if (i == j) return size[i] == N ? true : false;

        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
        return size[i] == N || size[j] == N ? true : false;
    }

}
