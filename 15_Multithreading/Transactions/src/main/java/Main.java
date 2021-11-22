import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  public static final int CORES = Runtime.getRuntime().availableProcessors();

  public static void main(String[] args) {
    Bank skillBoxBank = new Bank();
    Random randomNum = new Random();
    Random randomSum = new Random();
    int accountsCount;
    List<String> selected = new CopyOnWriteArrayList<>();
    long start = System.currentTimeMillis();

    ExecutorService executorService = Executors.newFixedThreadPool(CORES);

    for (accountsCount = 0; accountsCount < 1000000; accountsCount++) {
      int finalAccountsCount = accountsCount;
      executorService.submit(new Thread(() -> {
            long num = Math.abs(randomNum.nextLong()) + finalAccountsCount;
            int money = randomSum.nextInt(52500);
            skillBoxBank.addAccount("skillBox - " + num, money);
            if (finalAccountsCount % 1000 == 0) {
              selected.add("skillBox - " + num);
            }
          })
      );
    }

    executorService.shutdown();
    try {
      executorService.awaitTermination(1, TimeUnit.HOURS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(skillBoxBank.getAccountsSize());
    System.out.println(skillBoxBank.getSumAllAccounts());
    for (int i = 0; i < 100; i++) {
      skillBoxBank.transfer(selected.get(i), selected.get((selected.size() - 1) - i),
          skillBoxBank.getBalance(selected.get(i)));
    }

    System.out.println(skillBoxBank.getSumAllAccounts());
    System.out.println(selected.size());
    System.out.println(skillBoxBank.getBlockedAccounts().size());
    System.out.println(skillBoxBank.getAccountsSize());
    System.out.println((System.currentTimeMillis() - start));


  }


}
