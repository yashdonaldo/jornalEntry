package yd.demo.Controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yd.demo.Entry.UserEntry;
import yd.demo.Services.UserEntryServices;

@RestController
@RequestMapping("public")
public class PublicController {

      @Autowired
    private UserEntryServices userService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Create User
    @PostMapping("/create")
    public ResponseEntity<UserEntry> createUser2(@RequestBody UserEntry user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Arrays.asList("user"));
        return ResponseEntity.ok(userService.createUser(user));
    }
}
