public class Main {

  private static final String URL = "https://www.moscowmap.ru/metro.html#lines";
  private static String path = "src/main/data/map.json";

  public static void main(String[] args) {

    MetroBuilder metro = new MetroBuilder(URL);
    JSONWriter jsonWriter = new JSONWriter(metro);
    JSONReader jsonReader = new JSONReader(path);

    jsonWriter.writeJSON(path);
    jsonReader.printNumOfStationsOnEachLine();


  }

}
