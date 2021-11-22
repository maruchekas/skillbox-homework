import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    PhoneBook phoneBook = new PhoneBook();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Введите номер, имя или команду:");
      String input = scanner.nextLine();
      String name;
      String phone;
      if (input.equals("0")) {
        break;
      }
      if (input.equals("LIST")) {
        System.out.println(phoneBook.getAllContacts());
      } else if (phoneBook.checkName(input)) {
        name = input;
        if (phoneBook.getPhonesByName(name).isEmpty()) {
          System.out.println("Такого имени в телефонной книге нет."
              + System.lineSeparator() +
              "Введите номер телефона для абонента " + name + ":");
          phone = scanner.nextLine();
          if (phoneBook.checkPhone(phone)) {
            phoneBook.addContact(phone, name);
          }
        } else {
          System.out.println(phoneBook.getPhonesByName(name));
        }
      } else if (phoneBook.checkPhone(input)) {
        phone = input;
        if (phoneBook.getNameByPhone(phone).isEmpty()) {
          System.out.println("Такого номера нет в телефонной книге. "
              + System.lineSeparator() +
              "Введите имя абонента для номера " + phone + ":");
          name = scanner.nextLine();
          if (phoneBook.checkName(name)) {
            phoneBook.addContact(phone, name);
          }
        } else {
          System.out.println("Абонент с таким номером уже есть, хотите изменить его? Да/Нет.");
          input = scanner.nextLine();
          if (input.equals("Да")) {
            System.out.println("Введите имя абонента для номера " + phone + ":");
            name = scanner.nextLine();
            if (phoneBook.checkName(name)) {
              phoneBook.addContact(phone, name);
            }
          }
        }
      }
    }
  }
}
