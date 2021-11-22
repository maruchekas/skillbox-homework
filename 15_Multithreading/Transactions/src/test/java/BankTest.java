import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {

  private static final int CORES = Runtime.getRuntime().availableProcessors();
  private final Bank skillBoxBank = new Bank();
  private final Random randomNum = new Random();
  private final Random randomSum = new Random();
  private final List<String> selected = new CopyOnWriteArrayList<>();
  private final AtomicInteger countAccounts = new AtomicInteger(1000000);
  private final AtomicLong sumAllAccounts = new AtomicLong();


  /**
   * создание списка счетов в несколько потоков (количество потоков равно количеству ядер
   * процессора)
   */
  @Before
  public void fillAccounts() {

    ExecutorService executorService = Executors.newFixedThreadPool(CORES);

    for (int n = 0; n < countAccounts.intValue(); n++) {
      int finalN = n;
      executorService.submit(new Thread(() -> {
        long num = Math.abs(randomNum.nextLong()) + finalN;
        int money = randomSum.nextInt(52500);
        skillBoxBank.addAccount("skillBox - " + num, money);
        sumAllAccounts.addAndGet(money);
        if (finalN % 100 == 0) {
          selected.add("skillBox - " + num);
        }
      }));
    }
    executorService.shutdown();
    try {
      executorService.awaitTermination(5, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * счета создаются и добавляются в список в несколько потоков. тест проверяет, что количество
   * счетов в списке равно сумме произведенных операций во всех потоках
   */

  @Test
  public void addAccountTest() {

    int expected = countAccounts.intValue();

    Assert.assertEquals(expected, skillBoxBank.getAccountsSize());
  }

  /**
   * счета создаются и добавляются в список в несколько потоков. тест проверяет, что сумма балансов
   * всех счетов равна сумме всех внесенных средств во всех потоках
   */
  @Test
  public void getSumAllAccountsTest() {
    long expected = sumAllAccounts.longValue();

    Assert.assertEquals(expected, skillBoxBank.getSumAllAccounts());
    Assert.assertNotEquals(sumAllAccounts.longValue() + 1, skillBoxBank.getSumAllAccounts());
  }

  /**
   * тест проверяет, что сумма балансов всех счетов не изменяется после блокировки счетов во всех
   * потоках
   */

  @Test
  public void getSumAllAccountsAfterTransactionsTest() {

    for (int i = 0; i < 100; i++) {
      skillBoxBank.transfer(selected.get(i), selected.get(i + 100), skillBoxBank.getBalance(
          selected.get(i)));
    }
    long finishBalance = skillBoxBank.getSumAllAccounts();

    Assert.assertEquals(sumAllAccounts.longValue(), finishBalance);
  }

  /**
   * попытка провести трансфер со счета с нулевым балансом
   */

  @Test
  public void transferFromEmptyAccountTest() {
    String sender = "empty_sender";
    String recipient = "empty_recipient";
    skillBoxBank.addAccount(sender, 0);
    skillBoxBank.addAccount(recipient, 0);
    long senderBalance = skillBoxBank.getBalance(sender);
    long recipientBalance = skillBoxBank.getBalance(recipient);
    skillBoxBank.transfer(sender, recipient, 1);

    Assert.assertEquals(0, senderBalance);
    Assert.assertEquals(0, recipientBalance);
  }

  /**
   * попытка перевода отрицательной суммы
   */

  @Test
  public void transferNegativeAmountTest() {
    String sender = selected.get(1);
    String recipient = selected.get(2);
    long senderBalance = skillBoxBank.getBalance(sender);
    long recipientBalance = skillBoxBank.getBalance(recipient);
    skillBoxBank.transfer(sender, recipient, -1);

    Assert.assertEquals(senderBalance, skillBoxBank.getBalance(sender));
    Assert.assertEquals(recipientBalance, skillBoxBank.getBalance(recipient));
  }

  /**
   * проводит транзакции между 100 псевдослучайными счетами. Счета не прошедшие проверку на фрод
   * добавляются в список заблокированных. Пытается провести транзакцию по заблокированному счету
   */

  @Test
  public void blockAccountTest() {
    long startBalance = skillBoxBank.getBalance(selected.get(300));
    for (int i = 0; i < 100; i++) {
      skillBoxBank.transfer(selected.get(i), selected.get(i + 100), skillBoxBank.getBalance(
          selected.get(i)));
    }
    skillBoxBank.getBlockedAccounts().forEach((a, b) ->
        skillBoxBank.transfer(a, selected.get(300), 1)
    );
    long finishBalance = skillBoxBank.getBalance(selected.get(300));

    Assert.assertEquals(startBalance, finishBalance);
  }

}

