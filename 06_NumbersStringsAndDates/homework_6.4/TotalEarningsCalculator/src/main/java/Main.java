public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    text += ' ';
    String str = "";
    int totalEarnings = 0;

    for (int i = 0; i < text.length(); i++) {
      boolean b = text.charAt(i) >= '0' && text.charAt(i) <= '9';
      if (b && text.charAt(i + 1) != ' ') {
        str += text.charAt(i);
      }
      if (b && text.charAt(i + 1) == ' '){
        str = str + text.charAt(i) + ' ';
      }
    }

    while (!str.isBlank()) {
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ' ') {
          totalEarnings += Integer.parseInt(str.substring(0, i));
          str = str.substring(i + 1);
          break;
        }
      }
    }
    System.out.println(totalEarnings);
  }

}