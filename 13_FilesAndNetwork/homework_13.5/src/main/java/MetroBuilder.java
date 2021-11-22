import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetroBuilder {

  private final String URL;

  public MetroBuilder(String URL) {
    this.URL = URL;
  }

  // получаем элемент, содержащий всю струтуру метро
  public Element buildMainElement() {
    File metro = new File("src/main/data/metro.html");
    Element metroData = null;
    try {
      Document document = Jsoup.parse(metro, "UTF-8", "https://mosmetro.ru/");
      Document mainPage = Jsoup.connect(URL).maxBodySize(0)
          .userAgent(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:49.0) Gecko/20100101 Firefox/49.0")
          .ignoreHttpErrors(true).followRedirects(true).timeout(10000).ignoreContentType(true)
          .get();
      metroData = document.select("div[id=metrodata]").first();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return metroData;
  }

  //создаем список станций по формату: "номер" : номер, "имя" : имя
  public List<Line> buildLines(Element mainElement) {
    Elements lines = mainElement.select("span[data-line]");
    List<Line> linesList = new ArrayList<>();
    JSONArray jsonLinesArray = new JSONArray();
    for (Element line : lines
    ) {
      linesList.add(new Line(line.attr("data-line"), line.text()));
      jsonLinesArray.add(new Line(line.attr("data-line"), line.text()));
    }
    return linesList;
  }

  // создаем список станций. Формат: Номер линии - Список станций на этой линии
  public List<LineContent> buildLinesContent(Element mainElement) {
    Elements lines = mainElement.select("div[data-line]");
    List<LineContent> lineContentList = new ArrayList<>();
    for (Element line : lines
    ) {
      lineContentList
          .add(new LineContent(line.attr("data-line"),
              line.select("span.name").eachText()));
    }
    return lineContentList;
  }

  // создаем полный список станций. Формат: имя станции - номер линии, на которой находится станция.
  public List<Station> buildStationsList(List<LineContent> linesContentList) {
    Map<String, String> map = new LinkedHashMap<>();
    List<Station> stations = new ArrayList<>();
    for (LineContent st : linesContentList
    ) {
      for (String name : st.getStations()
      ) {
        map.put(name, st.getLineNumber());
        stations.add(new Station(name, st.getLineNumber()));
      }
    }
    return stations;
  }

}
