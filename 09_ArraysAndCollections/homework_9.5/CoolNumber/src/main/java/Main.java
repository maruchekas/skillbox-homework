import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

  static Map<Long, String> map = new TreeMap<>();

  public static void main(String[] args) {

    System.out.println("Выберите тип поиска: " + System.lineSeparator() +
        "HASH - поиск в HashSet" + System.lineSeparator() +
        "TREE - поиск в TreeSet" + System.lineSeparator() +
        "BINARY - бинарный поиск в сортированном List" + System.lineSeparator() +
        "BRUTE - поиск перебором в List" + System.lineSeparator() +
        "LINKED - поиск в LinkedList" + System.lineSeparator() +
        "SORTLINKED - бинарный поиск в сортированном LinkedList" + System.lineSeparator() +
        "SHOW - вывести результат измерений");
    TreeSet<String> treeSet = new TreeSet<>(CoolNumbers.generateCoolNumbers());
    HashSet<String> hashSet = new HashSet<>(CoolNumbers.generateCoolNumbers());
    List<String> list = new ArrayList<>(CoolNumbers.generateCoolNumbers());
    List<String> linkedList = new LinkedList<>(CoolNumbers.generateCoolNumbers());
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      if (input.equals("HASH")) {
        System.out.println("Введите номер автомобиля");
        input = new Scanner(System.in).nextLine();
        durationInHashSet(hashSet, input);
      }

      if (input.equals("TREE")) {
        System.out.println("Введите номер автомобиля");
        input = new Scanner(System.in).nextLine();
        durationInTreeSet(treeSet, input);
      }

      if (input.equals("BINARY")) {
        System.out.println("Введите номер автомобиля");
        input = new Scanner(System.in).nextLine();
        durationInBinarySearch(list, input);
      }

      if (input.equals("BRUTE")) {
        System.out.println("Введите номер автомобиля");
        input = new Scanner(System.in).nextLine();
        durationInBruteSearch(list, input);
      }

      if (input.equals("LINKED")) {
        System.out.println("Введите номер автомобиля");
        input = new Scanner(System.in).nextLine();
        durationInLinkedList(linkedList, input);
      }
      if (input.equals("SORTLINKED")) {
        System.out.println("Введите номер автомобиля");
        input = new Scanner(System.in).nextLine();
        durationInSortedLinkedList(linkedList, input);
      }

      if (input.equals("SHOW")) {
        for (Map.Entry<Long, String> pair : map.entrySet()
        ) {
          System.out.println(pair.getKey() + " - " + pair.getValue());
        }
      }
    }

  }

  public static void durationInHashSet(HashSet<String> numbers, String item) {
    long start = System.nanoTime();
    System.out.println("Результат поиска: " + CoolNumbers.searchInHashSet(numbers, item));
    long finish = System.nanoTime();
    System.out.println("Длительность поиска: " + (finish - start) + " нс.");
    map.put(finish - start, "HashSet");
  }

  public static void durationInTreeSet(TreeSet<String> numbers, String item) {
    long start = System.nanoTime();
    System.out.println("Результат поиска: " + CoolNumbers.searchInTreeSet(numbers, item));
    long finish = System.nanoTime();
    System.out.println("Длительность поиска: " + (finish - start) + " нс.");
    map.put(finish - start, "TreeSet");
  }

  public static void durationInBinarySearch(List<String> numbers, String item) {
    Collections.sort(numbers);
    long start = System.nanoTime();
    System.out.println("Результат поиска: " + CoolNumbers.binarySearchInList(numbers, item));
    long finish = System.nanoTime();
    System.out.println("Длительность поиска: " + (finish - start) + " нс.");
    map.put(finish - start, "Binary search");
  }

  public static void durationInBruteSearch(List<String> numbers, String item) {
    long start = System.nanoTime();
    System.out.println("Результат поиска: " + CoolNumbers.bruteForceSearchInList(numbers, item));
    long finish = System.nanoTime();
    System.out.println("Длительность поиска: " + (finish - start) + " нс.");
    map.put(finish - start, "Brute force");
  }

  public static void durationInLinkedList(List<String> numbers, String item) {
    long start = System.nanoTime();
    System.out.println("Результат поиска: " + CoolNumbers.bruteForceSearchInList(numbers, item));
    long finish = System.nanoTime();
    System.out.println("Длительность поиска: " + (finish - start) + " нс.");
    map.put(finish - start, "LinkedList");

  }

  public static void durationInSortedLinkedList(List<String> numbers, String item) {
    Collections.sort(numbers);
    long start = System.nanoTime();
    System.out.println("Результат поиска: " + CoolNumbers.binarySearchInList(numbers, item));
    long finish = System.nanoTime();
    System.out.println("Длительность поиска: " + (finish - start) + " нс.");
    map.put(finish - start, "SortedLinkedList");

  }
}
