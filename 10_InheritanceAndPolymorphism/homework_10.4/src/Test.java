import java.util.ArrayList;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    Company company = new Company();
    List<Employee> employees = new ArrayList<>();
    for (int i = 0; i < 180; i++) {
      employees.add(new Operator());
      if (i < 80) {
        employees.add(new Manager());
        if (i % 8 == 0) {
          employees.add(new TopManager(company));
        }
      }
    }

    company.hireAll(employees);

    System.out.println("Список самых высоких и самых низких зарплат до сокращения штата");
    outputList(company);

    int halfOfEmployees = company.getEmployees().size() / 2;
    for (int i = 0; i < halfOfEmployees; i++) {
      company.fire(company.getEmployees().get(i));
    }

    System.out.println("Список самых высоких и самых низких зарплат после сокращения штата");
    outputList(company);
  }

  private static void outputList(Company company) {
    List<Employee> top = company.getTopSalaryStaff(15);
    for (Employee e : top
    ) {
      System.out.println(e.getMonthSalary());
    }
    System.out.println("/*//*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");

    List<Employee> low = company.getLowestSalaryStaff(30);
    for (Employee e : low
    ) {
      System.out.println(e.getMonthSalary());
    }
  }

}
