import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

  public static void main(String[] args) {

    int day = 2;
    int month = 11;
    int year = 2008;

    System.out.println(collectBirthdays(year, month, day));

  }

  public static String collectBirthdays(int year, int month, int day) {
    Calendar birthday = new GregorianCalendar(year, month - 1, day);
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - EE", Locale.ENGLISH);
    String birthdays = "";
    int counter = 0;
    birthdays = "";

    while (birthday.before(Calendar.getInstance())) {
      birthdays += counter + " - " + dateFormat.format(birthday.getTime()) + System.lineSeparator();
      birthday.add(Calendar.YEAR, 1);
      counter++;
    }
    return birthdays;
  }
}
