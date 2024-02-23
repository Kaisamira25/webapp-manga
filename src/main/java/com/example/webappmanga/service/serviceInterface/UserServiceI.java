package com.example.webappmanga.service.serviceInterface;

import com.example.webappmanga.model.User;

public interface UserServiceI {
    User findByEmail(String email);
    User findById(Integer id);
}
