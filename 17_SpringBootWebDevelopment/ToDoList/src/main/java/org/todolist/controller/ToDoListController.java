package org.todolist.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.todolist.models.Task;
import org.todolist.repositories.TaskRepository;

@Controller
@RequestMapping("tasks")
public class ToDoListController {

  private final TaskRepository taskRepository;

  public ToDoListController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @GetMapping
  public ResponseEntity<Iterable<Task>> list() {
    return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Task> get(@PathVariable int id) {
    return taskRepository.findById(id).map(task -> ResponseEntity.status(HttpStatus.OK).body(task))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
  }

  @PostMapping
  public ResponseEntity<Long> add(@RequestBody Task task) {
    if (taskRepository.existsById(task.getId())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    task.setCreateDate(format.format(new Date()));
    taskRepository.save(task);
    return ResponseEntity.status(HttpStatus.OK).body(taskRepository.count());
  }

  @PutMapping("{id}")
  public ResponseEntity<Task> update(@PathVariable int id, @RequestBody Task task) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (optionalTask.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    task.setId(id);
    return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(task));
  }

  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable int id) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (optionalTask.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    taskRepository.delete(optionalTask.get());
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @DeleteMapping()
  public ResponseEntity<Long> deleteAll() {
    taskRepository.deleteAll();
    if (taskRepository.count() != 0) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskRepository.count());
    }
    return ResponseEntity.status(HttpStatus.OK).body(taskRepository.count());
  }

}
