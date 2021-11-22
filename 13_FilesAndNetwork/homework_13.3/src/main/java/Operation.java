import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Operation {

  private String organization;
  private Date date;
  private double income;
  private double expense;

  public Operation(String organization, Date date, double income, double expense) {
    this.organization = organization;
    this.date = date;
    this.income = income;
    this.expense = expense;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getIncome() {
    return income;
  }

  public void setIncome(double income) {
    this.income = income;
  }

  public Double getExpense() {
    return expense;
  }

  public void setExpense(Double expense) {
    this.expense = expense;
  }

  @Override
  public String toString() {
    return "Operation: " + "organization: " + organization + "\t" +
        " date: " + (new SimpleDateFormat("dd.MM.yyyy")).format(date) + "\t" +
        " expense: " + expense;
  }
}
