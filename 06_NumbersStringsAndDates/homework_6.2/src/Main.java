public class Main {

  public static void main(String[] args) {
    minAndMaxOfNumTypes();
  }

  public static void minAndMaxOfNumTypes(){
    System.out.println("Минимальное значение для типа byte: " + Byte.MIN_VALUE);
    System.out.println("Максимальное значение для типа byte: " + Byte.MAX_VALUE);
    System.out.println("Минимальное значение для типа short: " + Short.MIN_VALUE);
    System.out.println("Максимальное значение для типа short: " + Short.MAX_VALUE);
    System.out.println("Минимальное значение для типа integer: " + Integer.MIN_VALUE);
    System.out.println("Максимальное значение для типа integer: " + Integer.MAX_VALUE);
    System.out.println("Минимальное значение для типа long: " + Long.MIN_VALUE);
    System.out.println("Максимальное значение для типа long: " + Long.MAX_VALUE);
    System.out.println("Минимальное значение для типа float: " + (- Float.MAX_VALUE));
    System.out.println("Максимальное значение для типа float: " + Float.MAX_VALUE);
    System.out.println("Минимальное значение для типа double: " + (- Double.MAX_VALUE));
    System.out.println("Максимальное значение для типа double: " + Double.MAX_VALUE);
  }

}
