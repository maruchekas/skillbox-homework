import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

  private static final String URL = "https://lenta.ru/";

  public static void main(String[] args) {

    downloadImagesAndPrintNames(parseImages(URL), "data/images");

  }

  private static Elements parseImages(String url) {
    Document document = null;

    try {
      document = Jsoup.connect(url)
          .userAgent("Chrome/4.0.249.0 Safari/532.5")
          .referrer("http://www.google.com").get();
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    return document.select("img[src$=.jpg]");
  }

  private static void downloadImagesAndPrintNames(Elements elements, String destinationDirectory) {
    for (Element element : elements
    ) {
      try {
        if (element.normalName().equals("img")) {
          String absPath = element.attr("abs:src");
          URL url = new URL(absPath);
          String fileName = new File(absPath).getName();
          FileUtils.copyURLToFile(url, new File(destinationDirectory, fileName));
          System.out.println(fileName);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
