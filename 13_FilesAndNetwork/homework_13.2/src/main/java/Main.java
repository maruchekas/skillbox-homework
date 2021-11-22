import java.io.File;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    for (; ; ) {
      String fromDir = scanner.nextLine();
      String toDir = scanner.nextLine();
      if (!new File(fromDir).isDirectory()){
        System.out.println("The folder was not found.");
      }
      if (FileUtils.calculateFolderSize(fromDir) > new File(toDir).getFreeSpace()) {
        System.err.println("Not enough free space!");
      }
      else {
        FileUtils.copyFolder(fromDir, toDir);
      }
    }
  }
}
