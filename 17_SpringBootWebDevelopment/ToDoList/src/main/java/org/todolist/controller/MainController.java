package org.todolist.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.todolist.models.Task;
import org.todolist.repositories.TaskRepository;

@Controller
@RequestMapping("todolist")
public class MainController {

  private final TaskRepository taskRepository;

  public MainController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @GetMapping
  public String index(Model model) {
    Iterable<Task> allBooks = taskRepository.findAll();
    List<Task> tasks = new ArrayList<>((Collection<? extends Task>) allBooks);
    model.addAttribute("tasks", tasks);
    model.addAttribute("taskCount", tasks.size());
    return "todolist";
  }

}