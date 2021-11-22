import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileUtils {

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
