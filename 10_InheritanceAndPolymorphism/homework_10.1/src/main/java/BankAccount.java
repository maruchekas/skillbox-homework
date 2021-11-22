class BankAccount {

  private double amount;
  private boolean isTaken;
  private boolean isPut;

  double getAmount() {
    return amount;
  }

  void setAmount(double amount) {
    this.amount = amount;
  }

  boolean isTaken() {
    return isTaken;
  }

  void setTaken(boolean taken) {
    isTaken = taken;
  }

  boolean isPut() {
    return isPut;
  }

  void setPut(boolean put) {
    isPut = put;
  }

  boolean put(double amountToPut) {
    setPut(amountToPut > 0);
    if (isPut()) {
      amount += amountToPut;
    }
    return isPut();
  }

  boolean take(double amountToTake) {
    setTaken(amountToTake > 0 && amountToTake <= amount);
    if (isTaken()) {
      amount -= amountToTake;
    }
    return isTaken();
  }

  boolean send(BankAccount receiver, double amount) {
    return take(amount) && receiver.put(amount);
  }
}
