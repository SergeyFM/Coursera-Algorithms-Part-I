import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

  private double[] percolationThreshold;
  private int nGridSize = 0;
  private int trials = 0;

  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {

    if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();

    this.nGridSize = n;
    this.trials = trials;

    percolationThreshold = new double[trials];

    for (int trial = 0; trial < trials; trial++) {

      Percolation percolation = new Percolation(nGridSize);
      while (!percolation.percolates()) {
        int row = StdRandom.uniformInt(1, nGridSize + 1);
        int col = StdRandom.uniformInt(1, nGridSize + 1);
        if (!percolation.isOpen(row, col)) percolation.open(row, col);
      }
      percolationThreshold[trial] = (double)percolation.numberOfOpenSites() / (nGridSize * nGridSize);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(this.percolationThreshold);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    if (trials == 1) return Double.NaN;
    return StdStats.stddev(this.percolationThreshold);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(trials));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(trials));
  }

  // test client
  public static void main(String[] args) {
    
    if (args.length < 2) return;
    int n = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    Stopwatch stopwatch = new Stopwatch();

    // performs T independent computational experiments on an n-by-n grid
    PercolationStats percolationStats = new PercolationStats(n, T);

    double elapsedTime = stopwatch.elapsedTime(); // to measure the total running time of PercolationStats for various values of n and T.
    //System.out.println(String.format("elapsed time = %f", elapsedTime));

    System.out.println(String.format("mean\t\t\t\t\t=\t%f", percolationStats.mean()));
    System.out.println(String.format("stddev\t\t\t\t\t=\t%f", percolationStats.stddev()));
    System.out.println(String.format("95%% confidence interval\t=\t[%f, %f]", percolationStats.confidenceLo(), percolationStats.confidenceHi()));
    
  }
}