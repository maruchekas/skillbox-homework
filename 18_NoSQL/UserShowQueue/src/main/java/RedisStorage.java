import static java.lang.System.out;

import java.util.Date;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class RedisStorage {

  // Объект для работы с Redis
  private RedissonClient redisson;

  // Объект для работы с ключами
  private RKeys rKeys;

  // Объект для работы с Sorted Set'ом
  private RScoredSortedSet<String> onlineUsers;

  private final static String KEY = "ONLINE_USERS";

  private double getTs() {
    return new Date().getTime() / 1000;
  }

  void init() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    try {
      redisson = Redisson.create(config);
    } catch (RedisConnectionException ex) {
      out.println("Не удалось подключиться к Redis");
      out.println(ex.getMessage());
    }
    rKeys = redisson.getKeys();
    onlineUsers = redisson.getScoredSortedSet(KEY);
    rKeys.delete(KEY);
  }

  void addUser(int user_id)
  {
    onlineUsers.add(getTs(), String.valueOf(user_id));
  }

  void clearQue(){
    onlineUsers.removeRangeByRank(0, -1);
  }

  RScoredSortedSet<String> getAllUsers(){
    return onlineUsers;
  }

}
