public class Main {

  public static void main(String[] args) {

    int sum = sumDigits(1234567);

    System.out.println(sum);
  }

  /* Реализуйте метод sumDigits который возвращает сумму цифр числа, пример:
  передано 12345, метод должен вернуть 15
  если передано null, то должен вернуть -1

  Запустите тесты TestSumDigits для проверки корректности работы метода

  не меняйте название метода, его возвращаемое значение и модификаторы доступа (public).
  В противном случае тестовый метод не сможет проверить ваш код
   */

  public static int sumDigits(Integer number) {
    if (number == null) {
      return -1;
    }

    int sumDigits = 0;
    String string = number.toString();

    for (int i = 0; i < string.length(); i++) {
      sumDigits += Character.getNumericValue(string.charAt(i));
    }
    return sumDigits;
  }
}
