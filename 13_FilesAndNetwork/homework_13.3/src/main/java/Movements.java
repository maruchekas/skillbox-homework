import java.io.IOException;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Movements {

  public static final String CSV_DOC = "src/test/resources/movementList.csv";
  private static String pathMovementsCsv;
  private double incomeSum;
  private double expenseSum;
  private static List<Operation> operations = new ArrayList<>();
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");

  public Movements(String pathMovementsCsv) {
    this.pathMovementsCsv = pathMovementsCsv;
    parseCSV();
  }

  private static List<String> getAllLines() {
    List<String> allLines = new ArrayList<>();
    try {
      allLines = Files.readAllLines(Path.of(pathMovementsCsv));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    allLines.remove(0);

    return allLines;
  }

  private void parseCSV() {
    for (String line : getAllLines()
    ) {
      String[] fragments = quotesCleaner(line).split(",");
      try {
        operations.add(new Operation(organisationParser(fragments[5]),
            dateFormat.parse(fragments[3]),
            Double.parseDouble(fragments[6]),
            Double.parseDouble(fragments[7])));
      } catch (ParseException e) {
        e.printStackTrace();
      }

      incomeSum += Double.parseDouble(fragments[6]);
      expenseSum += Double.parseDouble(fragments[7]);

    }
  }

  private static String quotesCleaner(String line) {
    Pattern pattern = Pattern.compile("\".*\"");
    if (line.contains("\"")) {
      Matcher matcher = pattern.matcher(line);
      if (matcher.find()) {
        String cleaner = matcher.group().replaceAll("\"", "")
            .replaceAll(",", ".");
        line = line.replaceAll(matcher.group(), cleaner);
      }
    }
    return line;
  }

  private static String organisationParser(String line) {
    Pattern pattern = Pattern.compile("[\\\\|/].*\\s{10,}"); // \d{6,}\++.*\w{3}\d{4,}
    if (line.contains("\\") || line.contains("/")) {
      Matcher matcher = pattern.matcher(line);
      if (matcher.find()) {
        line = matcher.group().replaceAll("\\\\", " ").trim();
      }
    }
    return line;
  }

  public void printReport() {
    Map<String, Double> expensesByOrganization = operations.stream().collect(
        Collectors.groupingBy(Operation::getOrganization,
            Collectors.summingDouble(Operation::getExpense)));

    System.out.println(
        "Сводный отчет по расходам и доходам за период с " +
            getMinDate() + " по " + getMaxDate() + "\n" +
            "\tОбщая сумма доходов: " + getIncomeSum() + "\n" +
            "\tОбщая сумма расходов: " + getExpenseSum() + "\n" +
            "\nСумма расходов по организациям: ");
    expensesByOrganization.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(e -> System.out.printf("\t%s %15.2f \n", e.getKey(), e.getValue()));

  }

  private String getMinDate(){
    return dateFormat
        .format(operations.stream().min(Comparator.comparing(Operation::getDate)).get().getDate());
  }

  private String getMaxDate(){
    return dateFormat
        .format(operations.stream().max(Comparator.comparing(Operation::getDate)).get().getDate());
  }

  public double getExpenseSum() {
    return expenseSum;
  }

  public double getIncomeSum() {
    return incomeSum;
  }
}
