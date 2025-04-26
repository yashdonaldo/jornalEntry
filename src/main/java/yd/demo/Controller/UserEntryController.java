package yd.demo.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

    // Get all User
    @RequestMapping("/all")
    public ResponseEntity<List<UserEntry>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Create User
    @PostMapping("/create")
    public ResponseEntity<UserEntry> createUser(@RequestBody UserEntry user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    

    // Get a User
    @RequestMapping("/get/{id}")
    public ResponseEntity<UserEntry> getUser(@PathVariable ObjectId id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Delete a User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Update a User
    @PutMapping("/update")
    public ResponseEntity<UserEntry> updateUser(@RequestBody UserEntry user) {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if (authentication == null || !authentication.isAuthenticated()) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
        String username = authentication.getName();

        System.out.println(username);
        

        return ResponseEntity.ok(userService.updateUser(username, user));
    }

}
