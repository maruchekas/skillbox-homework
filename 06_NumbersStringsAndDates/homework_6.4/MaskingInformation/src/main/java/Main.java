public class Main {

  public static void main(String[] args) {

  }

  public static String searchAndReplaceDiamonds(String text, String placeholder) {
    while (true){
      int n1 = text.indexOf('<');
      int n2 = text.indexOf('>');
      if (n1 > n2){
        n2 = text.indexOf('>', n1);
      }
      if (n1 == -1 || n2 == -1){
        break;
      }
      if (n1 < n2){
        text = text.substring(0, n1) + placeholder + text.substring(n2 + 1);
      }
    }
    return text;
  }
}

