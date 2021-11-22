import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParser {

  private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

  private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
  private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

  public static void parseFile(String fileName) throws Exception {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(new File(fileName));

    findEqualVoters(doc);
    fixWorkTimes(doc);
  }

  private static void findEqualVoters(Document doc) throws Exception {
    NodeList voters = doc.getElementsByTagName(Constants.VOTER_TAG);
    int votersCount = voters.getLength();
    for (int i = 0; i < votersCount; i++) {
      Node node = voters.item(i);
      NamedNodeMap attributes = node.getAttributes();


      Voter voter = new Voter(attributes.getNamedItem(Constants.NAME_TAG).getNodeValue(),
          attributes.getNamedItem(Constants.BIRTHDAY_TAG).getNodeValue());
      Integer count = voterCounts.get(voter);
      voterCounts.put(voter, count == null ? 1 : count + 1);
    }
  }

  private static void fixWorkTimes(Document doc) throws Exception {
    NodeList visits = doc.getElementsByTagName(Constants.VISIT_TAG);
    byte visitCount = (byte) visits.getLength();
    for (int i = 0; i < visitCount; i++) {
      Node node = visits.item(i);
      NamedNodeMap attributes = node.getAttributes();

      int station = Integer.parseInt(attributes.getNamedItem(Constants.STATION_TAG).getNodeValue());
      Date time = visitDateFormat.parse(attributes.getNamedItem(Constants.TIME_TAG).getNodeValue());
      WorkTime workTime = voteStationWorkTimes.get(station);
      if (workTime == null) {
        workTime = new WorkTime();
        voteStationWorkTimes.put(station, workTime);
      }
      workTime.addVisitTime(time.getTime());
    }
  }

  public static void printDuplicatedVoters() {
    System.out.println("Duplicated voters: ");
    for (Voter voter : voterCounts.keySet()) {
      Integer count = voterCounts.get(voter);
      if (count > 1) {
        System.out.println("\t" + voter + " - " + count);
      }
    }
  }

  public static void printResults() {
    System.out.println("Voting station work times: ");
    for (Integer votingStation : voteStationWorkTimes.keySet()) {
      WorkTime workTime = voteStationWorkTimes.get(votingStation);
      System.out.println("\t" + votingStation + " - " + workTime);
    }
  }

}
