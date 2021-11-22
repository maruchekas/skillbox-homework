import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      nameValidator(input);
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }
  }

  public static void nameValidator(String input) {
    if (isInputValid(input)) {
      outputFullName(input);
    } else {
      System.out.println("Введенная строка не является ФИО");
    }
  }

  public static void outputFullName(String input) {

    String firstName = "Имя: ";
    String lastName = "Фамилия: ";
    String middleName = "Отчество: ";

    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ' ') {
        lastName += input.substring(0, i);
        input = input.substring(i + 1);
      }
    }
    System.out.println(lastName);

    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ' ') {
        firstName += input.substring(0, i);
        middleName += input.substring(i + 1);
      }
    }
    System.out.println(firstName);
    System.out.println(middleName);
  }

  public static boolean isInputValid(String input) {

    input = input
        .trim();  // removes spaces if the user enters an extra space at the end or beginning
    boolean isInputValid = true;
    int spaceCounter = 0;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ' ') {
        spaceCounter++;
      }
    }
    if (spaceCounter != 2) {
      isInputValid = false;
    }

    for (int i = 0; i < input.length(); i++) {
      if ((input.codePointAt(i) < 'А' || input.codePointAt(i) > 'я') &&
          (input.codePointAt(i) != ' ') && (input.codePointAt(i) != '-')) {
        isInputValid = false;
        break;
      }
    }
    return isInputValid;
  }

}