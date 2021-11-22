import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      phoneNumberFormatter(input);
    }
  }

  public static void phoneNumberFormatter(String input){
    input = input.replaceAll("\\D+", "");

    int l = input.length();
    if (l == 11){
      input = input.replaceFirst("[7|8]", "7");
    }
    if (l == 10){
      input = "7" + input;
    }
    if (l > 11 || l < 10 || input.charAt(0) != '7'){
      input = "Неверный формат номера";
    }
    System.out.println(input);
  }

}
