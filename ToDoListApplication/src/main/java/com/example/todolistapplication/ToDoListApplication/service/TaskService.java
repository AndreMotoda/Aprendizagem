package com.example.todolistapplication.ToDoListApplication.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.todolistapplication.ToDoListApplication.controller.RequiredArgsConstructor;
import com.example.todolistapplication.ToDoListApplication.model.*;

import scala.collection.immutable.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private JdbcTemplate jdbcTemplate;

    public Task createTask(Task task) {
        String sql = "INSERT INTO tasks (name, description, status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, task.getName(), task.getDescription(), task.getStatus());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        task.setId(id);
        return task;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM tasks";
        return (List<Task>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(Task.class));
    }

    public Task getTaskById(Long id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Task.class), id);
    }

    public Task updateTask(Long id, Task task) {
        String sql = "UPDATE tasks SET name = ?, description = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, task.getName(), task.getDescription(), task.getStatus(), id);
        return getTaskById(id);
    }

    public void deleteTask(Long id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}