public interface Employee extends Comparable<Employee>{

  int BASE_SALARY = 30000;
  double OPERATOR_RANK = 1;
  double MANAGER_RANK = 1.5;
  double TOP_MANAGER_RANK = 2.5;
  int DELTA = 2000;


  @Override
  default int compareTo(Employee o) {
    return getMonthSalary() - o.getMonthSalary();
  }

  int getMonthSalary();

}
