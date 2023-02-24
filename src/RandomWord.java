import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
Write a program RandomWord.java that reads a sequence of words from standard input and prints one of those words uniformly at random.
Do not store the words in an array or list. Instead, use Knuth’s method: when reading the ith word, select it with probability 1/i
to be the champion, replacing the previous champion. After reading all of the words, print the surviving champion.

StdIn.readString(): reads and returns the next string from standard input.
StdIn.isEmpty(): returns true if there are no more strings available on standard input, and false otherwise.
StdOut.println(): prints a string and terminating newline to standard output. It’s also fine to use System.out.println() instead.
StdRandom.bernoulli(p): returns true with probability p and false with probability 1−p
 */

public class RandomWord {
    public static void main(String[] args) {


        String candidate = "";
        boolean yesReplace = true;
        int i = 0;
        String arg = "";
        do {
            arg = StdIn.readString();
            i++;
            yesReplace = StdRandom.bernoulli(1.0/i);
            candidate = yesReplace ? arg : candidate;
        } while (!StdIn.isEmpty());
        StdOut.println(candidate);

    }
}