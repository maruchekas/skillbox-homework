import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileUtils {

  public static void copyFolder(String sourceDirectory, String destinationDirectory) {

    if (new File(sourceDirectory).isDirectory()) {
      copyDir(sourceDirectory, destinationDirectory);
    } else {
      copyFile(sourceDirectory, destinationDirectory);
    }
  }

  public static void copyDir(String sourceDirectory, String destinationDirectory) {
    File fromDir = new File(sourceDirectory);
    File toDir = new File(destinationDirectory);

    if (!toDir.exists()) {
      toDir.mkdir();
    }
    for (String item : fromDir.list()) {
      copyFolder(new File(fromDir, item).toString(), new File(toDir, item).toString());
    }
  }

  public static void copyFile(String source, String dest) {
    try {
      Files.copy(Path.of(source), Path.of(dest));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public static long calculateFolderSize(String path) {

    long totalSize = 0L;

    try {
      totalSize = Files.walk(Paths.get(path))
          .map(Path::toFile)
          .filter(File::isFile)
          .mapToLong(File::length).sum();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return totalSize;
  }

}
