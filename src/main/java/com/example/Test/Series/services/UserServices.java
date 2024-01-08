package com.example.Test.Series.services;

import com.example.Test.Series.entity.User;
import com.example.Test.Series.exceptions.UserException;

import java.util.List;

public interface UserServices {
    public User registerNewUser(User user) throws UserException;
    public List<User> getAllUser() throws UserException;
    public User loginUser(User user) throws UserException;

}
