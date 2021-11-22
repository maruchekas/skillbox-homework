import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      String[] fullName = input.split("\\s");
      if (isInputValid(input, fullName)) {
        System.out.println("Фамилия: " + fullName[0]);
        System.out.println("Имя: " + fullName[1]);
        System.out.println("Отчество: " + fullName[2]);
      } else {
        System.out.println("Введенная строка не является ФИО");
      }
    }
  }

  public static boolean isInputValid(String input, String[] fullName) {
    input = input.trim();
    return input.equals(input.replaceAll("[^а-яА-Я- ]", "")) && (fullName.length == 3);
  }

}