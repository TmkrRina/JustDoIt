package com.example.justdoit.repo;

import com.example.justdoit.model.UserModel;
import com.example.justdoit.model.UserDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserModel, ObjectId> {
    UserDTO findByUsername(String name);

}
