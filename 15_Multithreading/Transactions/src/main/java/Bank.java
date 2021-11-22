import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

  private Map<String, Account> accounts = new ConcurrentHashMap<>();
  private Map<String, Account> blockedAccounts = new ConcurrentHashMap<>();
  private long sumAllAccounts;
  private final Random random = new Random();

  public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException {
    Thread.sleep(1000);
    return random.nextBoolean();
  }

  /**
   * Метод переводит деньги между счетами. Если сумма транзакции > 50000, то после совершения
   * транзакции, она отправляется на проверку Службе Безопасности – вызывается метод isFraud. Если
   * возвращается true, то делается блокировка счетов *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* *
   * Блокировка подозрительных счетов производится методом добавления счета отправителя и счета
   * получателя в множество blockedAccounts Перед операцией транзакции производится проверка каждого
   * счета, не является ли он заблокированным (отсутствует в списке заблокированных) и только затем
   * производится транзакция метод isValid проверяет валидность операции: счет отправителя и счет
   * получателя существуют сумма перевода положительная и не превышает остаток счета отправителя
   */
  public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
    if (isValid(fromAccountNum, toAccountNum, amount)) {
      if (amount > 50000) {
        try {
          if (isFraud(fromAccountNum, toAccountNum, amount)) {
            blockAccount(fromAccountNum);
            blockAccount(toAccountNum);
            System.out.println("Счет заблокирован, операция не выполнена");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
        accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
      }

    }
  }

  public synchronized long getBalance(String accountNum) {
    return accounts.get(accountNum).getMoney();
  }

  public void setBalance(String accountNum, long money) {
    accounts.get(accountNum).setMoney(accounts.get(accountNum).getMoney() + money);
  }

  public long getSumAllAccounts() {
    return sumAllAccounts;
  }

  public void addAccount(String accNum, int money) {
    synchronized (this) {
      accounts.put(accNum, new Account(money, accNum));
    }
    synchronized (this) {
      sumAllAccounts += money;
    }
  }

  public Account getAccount(String accountNum) {
    return accounts.get(accountNum);
  }

  public int getAccountsSize() {
    return accounts.size();
  }

  private void blockAccount(String accountNum) {
    blockedAccounts.put(accountNum, accounts.get(accountNum));
  }

  public Map<String, Account> getBlockedAccounts() {
    return blockedAccounts;
  }

  private boolean isValid(String fromAccountNum, String toAccountNum, long amount) {
    return (accounts.containsKey(fromAccountNum) && accounts.containsKey(toAccountNum))
        && (amount > 0 && getBalance(fromAccountNum) >= amount)
        && !(getBlockedAccounts().containsKey(fromAccountNum)
        || getBlockedAccounts().containsKey(toAccountNum));
  }

}
