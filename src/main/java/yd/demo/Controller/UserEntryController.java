package yd.demo.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yd.demo.Entry.UserEntry;
import yd.demo.Services.UserEntryServices;

@RestController
@RequestMapping("user")
public class UserEntryController {

    @Autowired
    private UserEntryServices userService;

    @RequestMapping("/all")
    public ResponseEntity<List<UserEntry>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntry> createUser(@RequestBody UserEntry user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @RequestMapping("/get/{id}")
    public ResponseEntity<UserEntry> getUser(@PathVariable ObjectId id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntry> updateUser(@PathVariable ObjectId id, @RequestBody UserEntry user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

}
