public class TopManager implements Employee{

  private final int salary = (int) (Math.round(Math.random() * DELTA) + BASE_SALARY * TOP_MANAGER_RANK);
  private final Company company;
  private final double bonus = 1.5;

  public TopManager(Company company) {
    this.company = company;
  }

  @Override
  public int getMonthSalary() {
    return company.getIncome() > 10_000_000 ? (int) (salary + salary * bonus) : salary;
  }
}
