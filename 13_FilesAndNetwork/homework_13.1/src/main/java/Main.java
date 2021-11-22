import java.io.File;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    takeDir();

  }

  private static final double TB = 1_099_511_627_776.0;
  private static final double GB = 1_073_741_824.0;
  private static final double MB = 1_048_576.0;
  private static final double KB = 1024;

  private static void takeDir() {
    Scanner scanner = new Scanner(System.in);
    for (; ; ) {
      System.out.println("Введите путь к директории");
      String line = scanner.nextLine().trim();
      if (new File(line).isDirectory()) {
        System.out.println("Размер папки " + line + " составляет " + sizeConverter(
            FileUtils.calculateFolderSize(line)) + "\n");
      } else {
        System.out.println("Папка не найдена");
      }
    }
  }

  public static String sizeConverter(long itemSize) {
    double totalSize = (double) itemSize;
    if (totalSize > TB) {
      return String.format("%.1f Tb", totalSize /= TB);
    }
    if (totalSize > GB) {
      return String.format("%.1f Gb", totalSize /= GB);
    }
    if (totalSize > MB) {
      return String.format("%.1f Mb", totalSize /= MB);
    }
    if (totalSize > KB) {
      return String.format("%.1f Kb", totalSize / KB);
    }

    return String.format("%.1f Byte", totalSize);
  }
}
