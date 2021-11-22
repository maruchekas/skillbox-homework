import java.util.Scanner;

public class Main {

  private static TodoList todoList = new TodoList();

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (true) {
      String command = scanner.nextLine();
      int index = 0;
      if (command.replaceAll("\\D+", "").matches("\\d+")) {
        index = Integer.parseInt(command.replaceAll("\\D+", ""));
      }
      if (command.length() > 5 && command.substring(0, 5).matches("[A].*?[\\D]")) {
        System.out.println("Добавлено дело: " + command.replace("ADD ", ""));
        todoList.add(command.replace("ADD ", ""));
      }
      if (command.length() > 5 && command.substring(0, 5).matches("[A].*?[\\d]")) {
        System.out.println("Добавлено дело: " + command.replaceFirst("[A].*?[\\d]+[\\s]", ""));
        todoList.add(index, command.replaceFirst("[A].*?[\\d]+[\\s]", ""));
      }

      if (command.contains("EDIT ")) {
        System.out.println("Дело "
            + todoList.getTodo(index)
            + " заменено на " + command.replaceFirst("[E].*?[\\d]+[\\s]", ""));
        todoList.edit(command.replaceFirst("[E].*?[\\d]+[\\s]", ""),
            index);
      }

      if (command.contains("DELETE ")) {
        System.out.println("Дело " +
            todoList.getTodo(index) + " удалено");
        todoList.delete(index);
      }

      if (command.matches("LIST")) {
        System.out.println(todoList.getTodos());
      }
      if (command.equals("EXIT")) {
        break;
      }
    }
  }
}
