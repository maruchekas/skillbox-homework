public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextIntoWords(String text) {
    return text.replaceAll("[^a-zA-Z’]+", System.lineSeparator()).trim();
  }

}