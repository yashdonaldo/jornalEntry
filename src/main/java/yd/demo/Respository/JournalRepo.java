package yd.demo.Respository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import yd.demo.Entry.JournalEntry;

public interface JournalRepo extends MongoRepository <JournalEntry, ObjectId> {

    
} 
