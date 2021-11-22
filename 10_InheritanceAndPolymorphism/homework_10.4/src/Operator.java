public class Operator implements Employee{

  private final int salary = (int) (Math.round(Math.random() * DELTA) + BASE_SALARY * OPERATOR_RANK);

  @Override
  public int getMonthSalary() {
    return salary;
  }
}
