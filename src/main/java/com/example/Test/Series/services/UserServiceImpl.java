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

}
