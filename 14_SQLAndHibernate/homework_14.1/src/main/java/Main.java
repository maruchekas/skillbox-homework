import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

  public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=Europe/Moscow";
    String user = "root";
    String password = "iqwynugr@3qew8Nbi19PM";

    try (Connection jdbcConnection = DriverManager.getConnection(url, user, password);
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement
            .executeQuery("SELECT c.name AS name, COUNT(sub.subscription_date) AS count, \n"
                + "\t\tTIMESTAMPDIFF(MONTH, min(sub.subscription_date), "
                + "max(sub.subscription_date)) + 1 AS period,\n"
                + "\t\t(COUNT(sub.subscription_date)) / (TIMESTAMPDIFF(MONTH, "
                + "min(sub.subscription_date), max(sub.subscription_date)) + 1) AS avg_purchases \n"
                + "FROM subscriptions sub\n"
                + "\tINNER JOIN courses c ON sub.course_id = c.id\n"
                + "WHERE sub.subscription_date BETWEEN '2018-01-01' AND '2019-01-01'\n"
                + "GROUP BY sub.course_id;")) {
      while (resultSet.next()) {
        String courseName = resultSet.getNString("name");
        Integer count = resultSet.getInt("count");
        Integer period = resultSet.getInt("period");
        Double avgPurchases = resultSet.getDouble("avg_purchases");
        System.out.println(courseName + " - Приобретено " + count + " курсов за "
            + period + " месяцв. Среднее количесво курсов в месяц - " + avgPurchases);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
