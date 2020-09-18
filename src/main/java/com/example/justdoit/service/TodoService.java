package com.example.justdoit.service;

import com.example.justdoit.model.TodoDTO;
import com.example.justdoit.model.TodoModel;
import com.example.justdoit.repo.TodoRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TodoService {
    @Autowired
    TodoRepo todoRepo;


    public void save(TodoDTO todo) throws Exception{
        TodoModel newTodo =  new TodoModel();
        LocalDateTime presentTime = LocalDateTime.now();
        todo.setCreatedAt(presentTime);
        System.out.println(presentTime);
        newTodo.setTitle(todo.getTitle());
        newTodo.setDescription((todo.getDescription()));
        newTodo.setCompleted(todo.getCompleted());
        newTodo.setUsername(todo.getUsername());
        todoRepo.save(newTodo);

    }

    public List<TodoDTO> loadTodoByUsername(String username) throws UsernameNotFoundException {
        List<TodoDTO> todoList = todoRepo.findAllByUsername(username);
        if (todoList == null) {
            throw new UsernameNotFoundException("No todos yet");
        }

        return todoList;
    }
    public TodoModel getOne(ObjectId id){
        return todoRepo.findById(id).orElseThrow();

    }





}
