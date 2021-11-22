public abstract class Client {

  private double amount;
  public static final String SUCCESS = "Операция выполнена успешно.";
  public static final String FAILED = "Не удалось выполнить операцию.";

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getAmount() {
    return amount;
  }

  public void put(double amountToPut) {
    if (amountToPut > 0) {
      setAmount(getAmount() + amountToPut);
      System.out.println(SUCCESS + System.lineSeparator() +
          "Пополнение счета на  " + amountToPut + " y.e." + System.lineSeparator() +
          "На Вашем счете " + getAmount() + " y.e.");
    } else {
      System.out.println(FAILED);
    }
  }

  public void take(double amountToTake) {
    if (amountToTake > 0 && getAmount() >= amountToTake) {
      setAmount(getAmount() - amountToTake);
      System.out.println(SUCCESS + System.lineSeparator() +
          "Снятие со счета " + amountToTake + " y.e." + System.lineSeparator() +
          "Баланс Вашего счета " + getAmount() + " y.e.");
    } else {
      System.out.println(FAILED);
    }
  }

  public abstract String showInfo();
}
