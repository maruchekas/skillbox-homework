import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.redisson.api.RScoredSortedSet;

public class ShowUsers {

  // Количество пользователей в очереди
  private static final int CAPACITY = 20;

  // ждем, пока все настмотрятся на этого ричи
  private static final int WAITING = 1000;

  private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

  private static void log(int userOnline) {
    String log = String
        .format("[%s] Пользователь %d отображен на главной странице", DF.format(new Date()),
            userOnline);
    System.out.println(log);
  }

  private static void payment(int usersOnline) {
    String log = String
        .format("[%s] > Пользователь %d оплатил продвижение на странице", DF.format(new Date()),
            usersOnline);
    System.out.println(log);
  }

  public static void main(String[] args) throws InterruptedException {

    RedisStorage redis = new RedisStorage();
    redis.init();

    for (int user_id = 1; user_id <= CAPACITY; user_id++) {
      redis.addUser(user_id);
    }

    RScoredSortedSet<String> users = redis.getAllUsers();

    for (; ; ) {
      for (String user : users
      ) {
        // Рандомное число, больше 0 и в 10 раз больше величины очереди.
        int wildCard = new Random().nextInt(200) + 1;
        if (wildCard <= CAPACITY && users.contains(String.valueOf(wildCard))) {
          users.add(users.firstScore() - 1, String.valueOf(wildCard));
          payment(wildCard);
          log(wildCard);
          Thread.sleep(WAITING);
        }
        log(Integer.parseInt(user));
      }
    }
  }
}
