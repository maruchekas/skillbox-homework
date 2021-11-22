import java.util.List;

public class LineContent {

  private String lineNumber;
  private List<String> stations;

  public LineContent(String lineNumber, List<String> stations) {
    this.lineNumber = lineNumber;
    this.stations = stations;
  }

  public String getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(String lineNumber) {
    this.lineNumber = lineNumber;
  }

  public List<String> getStations() {
    return stations;
  }

  public void setStations(List<String> stations) {
    this.stations = stations;
  }
}
