package com.example.justdoit.repo;

import com.example.justdoit.model.TodoDTO;
import com.example.justdoit.model.TodoModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepo extends MongoRepository<TodoModel, ObjectId> {
     List<TodoDTO> findAllByUsername(String name);

     Optional<TodoModel> findById(ObjectId id);
}
