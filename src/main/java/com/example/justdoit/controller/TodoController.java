package com.example.justdoit.controller;

import com.example.justdoit.model.TodoDTO;
import com.example.justdoit.model.TodoModel;
import com.example.justdoit.repo.TodoRepo;
import com.example.justdoit.service.TodoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos?username={}")
    public List<TodoModel> getAllTodos() {

     return todoRepo.findAll();
    }

    @GetMapping("/todos/{username}")
    public ResponseEntity < List<TodoDTO> > getEmployeeById(@PathVariable(value = "username") String username)
            throws Exception {
        List<TodoDTO> todoList = todoService.loadTodoByUsername(username);
        return ResponseEntity.ok().body(todoList);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@Valid @RequestBody TodoDTO todo) throws Exception {
        todo.setCompleted(false);
        todoService.save(todo);
        return ResponseEntity.ok("Todo Created");
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<?> setCompleted(@PathVariable ObjectId id) {
        TodoModel t = todoService.getOne(id);
        t.setCompleted(true);
        return ResponseEntity.ok("Todo Updated");
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<?> editTodo(@PathVariable ObjectId id, @RequestBody TodoDTO todoDTO) throws Exception {
        TodoModel t = todoService.getOne(id);
        todoService.save(todoDTO);
        return ResponseEntity.ok("Todo Updated");
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable ObjectId id) throws Exception {
        TodoModel todo = todoService.getOne(id);
        todoRepo.delete(todo);
        return ResponseEntity.ok("Todo Updated");
    }

















}
