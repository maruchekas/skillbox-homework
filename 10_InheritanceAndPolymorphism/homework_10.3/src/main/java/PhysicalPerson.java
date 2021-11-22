public class PhysicalPerson extends Client {

  public String showInfo() {
    return String
        .format("\nИнформация о счете %s \nСумма на Вашем счете: %.2f y.e."
                + "\nЗа пополнение счета комиссия не взимается."
                + "\nСнятие средств доступно без комиссии.",
            this.getClass().getCanonicalName(),
            getAmount());
  }
}
