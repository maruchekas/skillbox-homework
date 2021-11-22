public class Manager implements Employee{

  private final int minSales = 115000;
  private final int maxSales = 140000;
  private final double percentage = 0.05;
  private final int SALARY = (int) (Math.round(Math.random() * DELTA) + BASE_SALARY * MANAGER_RANK);
  private int sales = (int) (Math.round((Math.random() * (maxSales - minSales)) + minSales) * percentage);

  @Override
  public int getMonthSalary() {
    return SALARY + sales;
  }
}
