import java.util.ArrayList;

public class TodoList {

  ArrayList<String> todos = new ArrayList<>();

  public void add(String todo) {
    todos.add(todo);
  }

  public void add(int index, String todo) {
    if (!(index > todos.size())) {
      todos.add(index, todo);
    } else {
      todos.add(todo);
    }
  }

  public void edit(String todo, int index) {
    if (!(index > todos.size())) {
      todos.set(index, todo);
    }
  }

  public void delete(int index) {
    if (todos.size() > 0 && index >= 0 && index < todos.size()) {
      todos.remove(index);
    }
  }

  public String getTodo(int index) {
    return todos.get(index);
  }

  public ArrayList<String> getTodos() {
    return todos;
  }

}