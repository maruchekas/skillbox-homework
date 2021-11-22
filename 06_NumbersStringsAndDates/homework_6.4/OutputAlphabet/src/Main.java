public class Main {

  public static void main(String[] args) {

    for (char i = 'A'; i <= 'Z'; i++) {
      System.out.print("Буква латинского алфавита - " + i +
          " Код буквы " + (int) i + "\n");
    }

    for (char i = 'a'; i <= 'z'; i++) {
      System.out.print("Буква латинского алфавита - " + i +
          " Код буквы " + (int) i + "\n");
    }

    for (char i = 'А'; i <= 'я'; i++) {
      System.out.println("Буква кириллического алфавита - " + i +
          " Код буквы " + (int) i);
    }
  }

}
