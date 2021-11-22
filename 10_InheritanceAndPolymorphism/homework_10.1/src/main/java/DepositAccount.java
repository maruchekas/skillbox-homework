import java.time.LocalDate;

class DepositAccount extends BankAccount {

  private static LocalDate lastIncome;

  boolean put(double amountToPut) {
    setPut(amountToPut > 0);
    if (isPut()) {
      lastIncome = LocalDate.now();
      setAmount(getAmount() + amountToPut);
    }
    return isPut();
  }

  boolean take(double amountToTake) {
    setTaken(amountToTake > 0 && getAmount() >= amountToTake && LocalDate.now()
        .isAfter(lastIncome.plusMonths(1)));
    if (isTaken()) {
      setAmount(getAmount() - amountToTake);
    }
    return isTaken();
  }

}
