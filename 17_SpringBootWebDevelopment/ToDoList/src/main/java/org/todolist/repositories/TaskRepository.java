package org.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.todolist.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

}
