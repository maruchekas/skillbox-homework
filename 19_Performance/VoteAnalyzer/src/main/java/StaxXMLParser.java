import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StaxXMLParser {
  private static Voter voter;
  private static HashMap<Voter, Integer> voters;

  public static void parseVoteXmlFile(String fileName) throws SQLException {
    XMLInputFactory factory = XMLInputFactory.newFactory();
    voters = new HashMap<>();

    try {
      XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(fileName));
      int event = reader.getEventType();

      while (true) {
        switch (event) {
          case XMLStreamConstants.START_ELEMENT:
            if (reader.getLocalName().equals(Constants.VOTER_TAG)) {
              voter = new Voter(reader.getAttributeValue(0), reader.getAttributeValue(1));
            } else if (reader.getLocalName().equals(Constants.VISIT_TAG) && voter != null) {
              int count = voters.getOrDefault(voter, 0);
              voters.put(voter, count + 1);
            }
            break;
          case XMLStreamConstants.END_ELEMENT:
            if (reader.getLocalName().equals(Constants.VOTER_TAG)) {
              voter = null;
            }
            break;
        }
        if (!reader.hasNext()) {
          break;
        }
        event = reader.next();
      }
    } catch (FileNotFoundException | XMLStreamException e) {
      e.printStackTrace();
    }
  }

  public static HashMap<Voter, Integer> getAllVoters(){
    return new HashMap<>(voters);
  }

}
