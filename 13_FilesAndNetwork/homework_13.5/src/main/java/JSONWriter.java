import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriter {

  private MetroBuilder metro;
  private JSONObject mainObject;

  public JSONWriter(MetroBuilder metro) {
    this.metro = metro;
    this.mainObject = new JSONObject();
  }

  private JSONArray createJSONLines() {
    List<Line> lines = metro.buildLines(metro.buildMainElement());
    JSONArray jsonLines = new JSONArray();
    for (Line line: lines
    ) {
      JSONObject object = new JSONObject();
      object.put("number", line.getNumber());
      object.put("name", line.getName());
      jsonLines.add(object);
    }
    return jsonLines;
  }

  private JSONArray createJSONStations() {
    List<LineContent> allLineContents = metro.buildLinesContent(metro.buildMainElement());
    JSONArray lineContents = new JSONArray();
    for (LineContent content : allLineContents
    ) {
      JSONObject object = new JSONObject();
      object.put(content.getLineNumber(), content.getStations());
      lineContents.add(object);
    }
    return lineContents;
  }

  public void writeJSON(String path) {
    Path file = Path.of(path);
    mainObject.put("Stations", createJSONStations());
    mainObject.put("Lines", createJSONLines());

    try (FileWriter writer = new FileWriter(path)) {
      if (!Files.isRegularFile(file)) {
        Files.createFile(file);
      }
      writer.write(mainObject.toJSONString());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
