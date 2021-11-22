class Main {

  public static void main(String[] args) {
    BankAccount bankAccount = new BankAccount();
    BankAccount cardAccount = new CardAccount();
    BankAccount depositAccount = new DepositAccount();

    bankAccount.put(100);
    cardAccount.put(100);
    depositAccount.put(100);

    System.out.println(bankAccount.getAmount());
    System.out.println(cardAccount.getAmount());
    System.out.println(depositAccount.getAmount());

    System.out.println(bankAccount.send(cardAccount, 30));
    System.out.println(cardAccount.send(depositAccount, 40));
    System.out.println(depositAccount.send(bankAccount, 50));

    System.out.println(bankAccount.getAmount());
    System.out.println(cardAccount.getAmount());
    System.out.println(depositAccount.getAmount());

    System.out.println(cardAccount.send(bankAccount, 2.22));
    System.out.println(bankAccount.getAmount());
    System.out.println(cardAccount.getAmount());

    System.out.println(cardAccount.send(depositAccount, 86.5));
    System.out.println(cardAccount.getAmount());
    System.out.println(depositAccount.getAmount());
  }

}
