package yd.demo.Services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import yd.demo.Entry.UserEntry;
import yd.demo.Respository.UserRepo;

@Component
public class UserEntryServices {
    @Autowired
    private UserRepo userRepo;

    public UserEntry createUser(UserEntry user) {
        userRepo.save(user);
        System.out.println("User created successfully.");
        // Additional logic can be added here if needed, such as sending a welcome
        // email.
        // Optional: Log the user creation event for auditing purposes.
        System.out.println("User creation event logged.");
        // Notify other services about the new user creation event.
        // Optionally, return the created user object for further processing.
        return user;
    }

    public UserEntry getUserById(ObjectId id) {
        return userRepo.findById(id).orElse(null);
        // Optional: Handle the case where the user is not found.
    }

    public void deleteUser(ObjectId id) {
        userRepo.deleteById(id);
        System.out.println("User deleted successfully.");
        // Optional: Log the user deletion event for auditing purposes.
        System.out.println("User deletion event logged.");
        // Notify other services about the user deletion event.
        System.out.println("User deletion event notified to other services.");
        // Optionally, return a status message or the deleted user object for
        // confirmation.
        // return "User with ID " + id + " has been successfully deleted.";
        // Optional: Handle any exceptions that may occur during deletion.
        try {
            userRepo.deleteById(id);
            System.out.println("Error occurred while deleting user: ");
            throw new RuntimeException("User deletion failed.");
        } catch (Exception e) {
            System.out.println("User deletion event notified to other services.");
            // return "User deletion failed due to an exception: " + e.getMessage();
        }
        // Optional: Handle the case where the user ID is invalid or does not exist.
        if (userRepo.findById(id).isEmpty()) {
            System.out.println("User ID " + id + " does not exist.");
            // return "User ID " + id + " is invalid or does not exist.";
        }
        // Optional: Return a confirmation message after the deletion process is
        // complete.
        System.out.println("User deletion process completed.");
    }

    public List<UserEntry> getAllUsers() {
        return userRepo.findAll();
        // Optional: Log the retrieval of all users for auditing purposes.
        // Optionally, handle any exceptions that may occur during retrieval.
    }

    public UserEntry updateUser(ObjectId id, UserEntry updatedUser) {
        userRepo.findById(id).ifPresent(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            userRepo.save(existingUser);
            System.out.println("User updated successfully.");
            System.out.println("User update event logged.");
            // Notify other services about the user update event.
            System.out.println("User update event notified to other services.");
        });
        System.out.println("User ID " + id + " not found for update.");
        // Optionally, return a message indicating the update failure.
        System.out.println("Update failed: User with ID " + id + " does not exist.");
        // return "Update failed: User with ID " + id + " does not exist.";
        return updatedUser;
    };

}
