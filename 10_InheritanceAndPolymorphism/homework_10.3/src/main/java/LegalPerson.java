public class LegalPerson extends Client {

  public void take(double amountToTake) {
    if (amountToTake > 0 && getAmount() >= (amountToTake * 1.01)) {
      setAmount(getAmount() - (amountToTake * 1.01));
      System.out.println(SUCCESS + System.lineSeparator() +
          "Снятие со счета " + amountToTake + " y.e." + System.lineSeparator() +
          "Баланс Вашего счета " + getAmount() + " y.e.");
    } else {
      System.out.println(FAILED);
    }
  }

  public String showInfo() {
    return String
        .format("\nИнформация о счете %s \nСумма на Вашем счете: %.2f y.e."
                + "\nЗа пополнение счета комиссия не взимается."
                + "\nЗа снятие средств взимается комиссия - 1%%",
            this.getClass().getCanonicalName(),
            getAmount());
  }
}
