import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class EmailList {

  TreeSet<String> emailList = new TreeSet<>();

  public void add(String email) {
    // TODO: валидный формат email добавляется
    if (email.matches("[^ @]*@[\\w]+[.][a-zA-Z]+")){
      emailList.add(email.toLowerCase());
    }
  }

  public int size(){
    return emailList.size();
  }

  public void outputListEmails(){
    for (String str : emailList) {
      System.out.print(str + System.lineSeparator());
    }
  }


  public List<String> getSortedEmails() {
    // TODO: возвращается список электронных адресов в алфавитном порядке
    return new ArrayList<>(emailList);
  }

}
