class CardAccount extends BankAccount {

  boolean take(double amountToTake) {
    setTaken(amountToTake > 0 && getAmount() >= (amountToTake * 1.01));
    if (isTaken()) {
      setAmount(getAmount() - (amountToTake * 1.01));
    }
    return isTaken();
  }

}
