import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CoolNumbers {

  static Set<String> numbers = new HashSet<>();
  static String letters = "АВЕКМНОРСТУХ";
  static String[] coolDigits = {"111", "222", "333", "444", "555", "666", "777", "888", "999"};

  public static List<String> generateCoolNumbers() {
    for (int i = 0; i < 2222222; i++) {
      numbers.add(String.format("%s%03d%s%s%d",
          letters.charAt((int) Math.round(Math.random() * 11)),
          (Math.round(Math.random() * 1000) + 1),
          letters.charAt((int) Math.round(Math.random() * 11)),
          letters.charAt((int) Math.round(Math.random() * 11)),
          (Math.round(Math.random() * 190) + 10)
      ));
    }
    return new LinkedList<>(numbers);
  }

  public static boolean bruteForceSearchInList(List<String> list, String number) {
    return list.contains(number);
  }

  public static boolean binarySearchInList(List<String> sortedList, String number) {
    return Collections.binarySearch(sortedList, number) >= 0;
  }


  public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
    return hashSet.contains(number);
  }

  public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
    return treeSet.contains(number);
  }

}
