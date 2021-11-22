import java.io.FileReader;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReader {

  private final String path;

  public JSONReader(String path) {
    this.path = path;
  }

  public void printNumOfStationsOnEachLine() {
    JSONParser parser = new JSONParser();
    try (FileReader reader = new FileReader(path)) {
      JSONObject mapJson = (JSONObject) parser.parse(reader);
      JSONArray stationArray = (JSONArray) mapJson.get("Stations");

      for (Object item : stationArray
      ) {
        JSONObject stationObject = (JSONObject) item;
        Set<String> keys = stationObject.keySet();
        keys.forEach(k -> {
          JSONArray stationsOnTheLine = (JSONArray) stationObject.get(k);
          System.out.println("Line № " + k + " contains " + stationsOnTheLine.size() + " stations");
        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
