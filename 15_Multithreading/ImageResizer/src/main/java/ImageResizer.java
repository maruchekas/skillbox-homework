import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class ImageResizer implements Runnable {

  private File[] files;
  private int NEW_WIDTH;
  private String dstFolder;
  private long start;

  public ImageResizer(File[] files, int NEW_WIDTH, String dstFolder, long start) {
    this.files = files;
    this.NEW_WIDTH = NEW_WIDTH;
    this.dstFolder = dstFolder;
    this.start = start;
  }

  @Override
  public void run() {
    try {
      for (File file : files) {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
          continue;
        }

        int newHeight = (int) Math.round(
            image.getHeight() / (image.getWidth() / (double) NEW_WIDTH));

        BufferedImage newImage = Scalr.resize(image, NEW_WIDTH, newHeight, Scalr.OP_ANTIALIAS);

        File newFile = new File(dstFolder + "/" + file.getName());
        ImageIO.write(newImage, "jpg", newFile);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("Finished after start " + (System.currentTimeMillis() - start) + " ms");
  }
}