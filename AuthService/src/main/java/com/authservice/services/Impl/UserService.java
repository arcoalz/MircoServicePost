package com.authservice.services.Impl;

import com.authservice.models.User;

import java.util.List;

public interface UserService {

    User save(User student);

    User findById(int id);

    List<User> findAll();

    void deleteById(int id);

    User update(User student);
}
