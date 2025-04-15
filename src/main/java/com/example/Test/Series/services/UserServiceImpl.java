package com.example.Test.Series.services;

import com.example.Test.Series.entity.User;
import com.example.Test.Series.exceptions.UserException;
import com.example.Test.Series.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean phoneNumberExists(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    @Override
    public User registerNewUser(User user) throws UserException {
        if (emailExists(user.getEmail()) || phoneNumberExists(user.getPhoneNumber())) {
            throw new UserException("Email or phone number already exists");
        }
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUser() throws UserException {
        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()){
            throw new UserException("No any record founds");
        }
        return userList;
    }

    @Override
    public User loginUser(User user) throws UserException {
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());

        if (userFromDB.isPresent()) {
            String storedPassword = userFromDB.get().getPassword();

            if (storedPassword.equals(user.getPassword())) {
                return userFromDB.get(); // Return the logged-in user
            } else {
                throw new UserException("Incorrect password");
            }
        } else {
            throw new UserException("User not found");
        }
    }

    @Override
    public User getUserById(Integer id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with ID: " + id));
    }

    @Override
    public String changePassword(Integer userId, String oldPassword, String newPassword, String confirmPassword) throws UserException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found with ID: " + userId));

        // Validate old password
        if (!user.getPassword().equals(oldPassword)) {
            throw new UserException("Old password is incorrect");
        }

        // Validate new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            throw new UserException("New password and confirm password don't match");
        }

        // Validate new password is different from old password
        if (newPassword.equals(oldPassword)) {
            throw new UserException("New password must be different from old password");
        }

        // Update the password
        user.setPassword(newPassword);
        userRepository.save(user);

        return "Password changed successfully";
    }

    @Override
    public String forgotPassword(String email, String newPassword, String confirmPassword) throws UserException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found with email: " + email));

        // Validate new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            throw new UserException("New password and confirm password don't match");
        }

        // Update the password
        user.setPassword(newPassword);
        userRepository.save(user);

        return "Password reset successfully";
    }

}
