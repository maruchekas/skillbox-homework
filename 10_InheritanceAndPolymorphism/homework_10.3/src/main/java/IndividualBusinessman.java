public class IndividualBusinessman extends Client {

  public void put(double amountToPut) {
    if (amountToPut > 0) {
      if (amountToPut < 1000) {
        setAmount(getAmount() + (amountToPut * 0.99));
        System.out.println("Пополнение счета на " + (amountToPut * 0.99) + " y.e.");
      } else {
        setAmount(getAmount() + (amountToPut * 0.995));
        System.out.println("Пополнение счета на " + (amountToPut * 0.995) + " y.e.");
      }
      System.out.println(SUCCESS + System.lineSeparator() +
          "На Вашем счете " + getAmount() + " y.e.");
    } else {
      System.out.println(FAILED);
    }
  }

  public String showInfo() {
    return String.format("\nИнформация о счете %s\n"
        + "Сумма на Вашем счете: %.2f y.e.\n"
        + "За пополнение счета взимается комиссия: \n"
        + "- при пополнении счета менее чем на 1000 у.е. - 1%%\n"
        + "- при пополнении счета на сумму 1000 у.е. и более - 0,5%%\n"
        + "Снятие средств доступно без комиссии.", this.getClass().getCanonicalName(), getAmount());
  }
}
