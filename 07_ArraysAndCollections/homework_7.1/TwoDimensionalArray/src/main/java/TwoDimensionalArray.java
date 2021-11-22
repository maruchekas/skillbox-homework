public class TwoDimensionalArray {

  public static char symbol = 'X';

  public static char[][] getTwoDimensionalArray(int size) {
    char[][] diagonals = new char[size][size];

    for (int i = 0; i < diagonals.length; i++) {
      for (int j = 0; j < diagonals.length; j++) {
        diagonals[i][j] = ' ';
      }
    }

    for (int i = 0; i < size; i++) {
      diagonals[i][i] = symbol;
      diagonals[(size - 1) - i][i] = symbol;
    }
    return diagonals;
  }
}
