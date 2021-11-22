import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {

  private int income = (int) (Math.round(Math.random() * 2_000_000) + 4_500_000);
  private final List<Employee> employees = new ArrayList<>();

  public int getIncome(){
    return income;
  }

  public void setIncome(int income) {
    this.income = income;
  }

  public List<Employee> getEmployees() {
    return new ArrayList<>(employees);
  }

  private boolean isEmployeeManager(Employee employee){
    return employee.getClass().getName().equals("Manager");
  }

  public void hire(Employee employee){
    if (isEmployeeManager(employee)){
      setIncome((int) (getIncome() + employee.getMonthSalary() * 0.95));
    }
    employees.add(employee);
  }

  public void hireAll(List<Employee> employees){
    for (Employee emp : employees
    ) {
      hire(emp);
    }
  }

  public void fire(Employee employee){
    employees.remove(employee);
    if(isEmployeeManager(employee)){
      setIncome((int) (getIncome() - employee.getMonthSalary() * 0.95));
    }
  }


  public List<Employee> getTopSalaryStaff(int count){
    return getList(count, Comparator.reverseOrder());
  }

  List<Employee> getLowestSalaryStaff(int count){
    return getList(count, Comparator.naturalOrder());
  }

  private ArrayList<Employee> getList(int count, Comparator comparator) {
    if (count < 0){
      return new ArrayList<>();
    }
    if (count > employees.size()){
      return new ArrayList<>(employees);
    }
    employees.sort(comparator);
    return new ArrayList<>(employees.subList(0, count));
  }

}
