package com.example.Test.Series.controllers;


import com.example.Test.Series.entity.User;
import com.example.Test.Series.entity.UserLoginRequest;
import com.example.Test.Series.exceptions.UserException;
import com.example.Test.Series.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUserHandler(@RequestBody User user) throws UserException {
        User user1 = userServices.registerNewUser(user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }


    @GetMapping("/all_users")
    public ResponseEntity<List<User>> getAllUserHandler() throws UserException {
        List<User> userList = userServices.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUserHandler(@RequestBody UserLoginRequest loginRequest) {
        try {
            // Create a User object with email and password from the login request
            User userForLogin = new User();
            userForLogin.setEmail(loginRequest.getEmail());
            userForLogin.setPassword(loginRequest.getPassword());

            User loggedInUser = userServices.loginUser(userForLogin);

            // You can customize the response based on your requirements,
            // such as returning user information or a success message
            String message = "Login successful for user: " + loggedInUser.getEmail();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByIdHandler(@PathVariable Integer id) throws UserException {
        User user = userServices.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePasswordHandler(
            @RequestParam Integer userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {
        try {
            String message = userServices.changePassword(userId, oldPassword, newPassword, confirmPassword);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPasswordHandler(
            @RequestParam String email,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {
        try {
            String message = userServices.forgotPassword(email, newPassword, confirmPassword);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
