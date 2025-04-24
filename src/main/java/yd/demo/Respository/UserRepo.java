package yd.demo.Respository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import yd.demo.Entry.UserEntry;

public interface UserRepo extends MongoRepository <UserEntry, ObjectId>  {
    
}
