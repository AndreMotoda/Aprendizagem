package com.example.todolistapplication.ToDoListApplication.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.todolistapplication.ToDoListApplication.model.Task;
import com.example.todolistapplication.ToDoListApplication.model.Status;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Task task) {
        return jdbcTemplate.update("INSERT INTO tasks (name, description, status) VALUES (?, ?, ?)",
                task.getName(), task.getDescription(), task.getStatus().name());
    }

    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM tasks",
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        Status.valueOf(rs.getString("status"))
                ));
    }

    public int update(Long id, Status status) {
        return jdbcTemplate.update("UPDATE tasks SET status = ? WHERE id = ?",
                status.name(), id);
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM tasks WHERE id = ?", id);
    }
}
