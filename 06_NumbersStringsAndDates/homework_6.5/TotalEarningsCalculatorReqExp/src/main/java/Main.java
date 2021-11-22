import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

  }

  public static int calculateSalarySum(String text) {
    int salarySum = 0;
    Matcher matcher = Pattern.compile("\\d+").matcher(text);
    while (matcher.find()) {
      salarySum += Integer.parseInt(text.substring(matcher.start(), matcher.end()));
    }

//    text = text.trim();
//    String[] amounts = text.split("\\D+");
//    for (int i = 0; i < amounts.length; i++) {
//      if (amounts[i].isEmpty()){
//        amounts[i] = "0";
//      }
//      salarySum += Integer.parseInt(amounts[i]);
//    }
    return salarySum;
  }

}