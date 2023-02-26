import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int openCellCounter = 0;
  private int[][] cardianlDirections = new int[][] {
      { 1, 0 }, // BOTTOM
      { -1, 0 }, // TOP
      { 0, -1 }, // LEFT
      { 0, 1 } // RIGHT
  };

  private int size;
  private int[] grid;

  // a UF to check if the matrix percolates
  private WeightedQuickUnionUF ufFirst;

  // a UF used to discover if a site is full or not
  private WeightedQuickUnionUF ufSecond;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {

    if (n <= 0) throw new java.lang.IllegalArgumentException();

    this.size = n;

    // add two virtual sites, one connected to all the elements of the top row,
    // and one connected to all the ones at the bottom row.
    ufFirst = new WeightedQuickUnionUF((n * n) + 2);

    // add one virtual site connected to all the elements of the top row.
    ufSecond = new WeightedQuickUnionUF((n * n) + 1);

    // connect top row sites to virtual top site
    for (int i = 1; i < n + 1; i++) {
      ufFirst.union(i, 0);
      ufSecond.union(i, 0);
    }

    // connect bottom row sites to virtual bottom site
    for (int i = n * n; i > n * n - n; i--)
      ufFirst.union(i, (n * n) + 1);

    // create n-by-n grid
    grid = new int[n * n];

    // Initialize
    for (int i = 0; i < n * n; i++) grid[i] = 0;

  }

  // opens the site (row, col) if it is not open already
  public void open(int i, int j) {

    if (!isCoordinateOK(i, j)) throw new java.lang.IllegalArgumentException();

    int tmp_i = i;
    int tmp_j = j;

    if (isBlocked(i, j)) { // if blocked, open it
      grid[getPtrFromCoordinates(i, j)] = 1;
      openCellCounter++;

      for (int d = 0; d < cardianlDirections.length; d++) {
        i = tmp_i + cardianlDirections[d][0];
        j = tmp_j + cardianlDirections[d][1];

        if (isCoordinateOK(i, j) && isOpen(i, j)) {
          // shift by one because of the virtual top site
          int siteA = getPtrFromCoordinates(i, j) + 1;
          int siteB = getPtrFromCoordinates(tmp_i, tmp_j) + 1;
          ufFirst.union(siteA, siteB);
          ufSecond.union(siteA, siteB);
        }
      }

    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int i, int j) {
    if (isCoordinateOK(i, j)) return grid[getPtrFromCoordinates(i, j)] == 1;
    throw new java.lang.IllegalArgumentException();
  }

  // is the site (row, col) full?
  public boolean isFull(int i, int j) {
    if (isCoordinateOK(i, j)) {
      if (isOpen(i, j)) {

        // index is shifted by one due to the virtual top site
        int index = getPtrFromCoordinates(i, j) + 1;
        // it's full anyway
        if (index < size) return true;

        return ufSecond.find(index) == ufSecond.find(0);
      }
    } else {
      throw new java.lang.IllegalArgumentException();
    }

    return false;
  }
  
  // returns the number of open sites
  public int numberOfOpenSites() {
    return openCellCounter;
  }

  // does the system percolate?
  public boolean percolates() {

    int virtualTop = 0;
    int virtualBottom = size * size + 1;

    // corner case: 1 site
    if (virtualBottom == 2) return isOpen(1, 1);

    // corner case: no sites
    if (virtualBottom == 0) return false;

    return ufFirst.find(virtualTop) == ufFirst.find(virtualBottom);
  }

  // ---------------------------------------------------------------------------------------------------
  private boolean isCoordinateOK(int x, int y) {
    if (x > 0 && x <= size && y > 0 && y <= size) return true;
    return false;
  }

  private boolean isBlocked(int i, int j) {
    if (isCoordinateOK(i, j)) return grid[getPtrFromCoordinates(i, j)] == 0;
    return false;
  }

  private int getPtrFromCoordinates(int x, int y) {
    return (x - 1) * size + (y - 1);
  }



}