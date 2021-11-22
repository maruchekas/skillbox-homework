import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

  private Voter voter;
  private HashMap<Voter, Integer> voters;

  public XMLHandler(){
    voters = new HashMap<>();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (qName.equals(Constants.VOTER_TAG) && voter == null) {
      voter = new Voter(attributes.getValue(Constants.NAME_TAG), attributes.getValue(Constants.BIRTHDAY_TAG));
    } else if (qName.equals(Constants.VISIT_TAG) && voter != null){
      int count = voters.getOrDefault(voter, 0);
      voters.put(voter, count + 1);
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName){
    if (qName.equals(Constants.VOTER_TAG)){
      voter = null;
    }
  }

  public HashMap<Voter, Integer> getAllVoters(){
    return new HashMap<>(voters);
  }

}
