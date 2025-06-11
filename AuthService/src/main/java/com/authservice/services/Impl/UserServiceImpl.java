package com.authservice.services.Impl;

import com.authservice.models.User;
import com.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User User) {
        return userRepository.save(User);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    @Override
    public User update(User user) {
        User updateUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        updateUser.setRole(user.getRole());
        return userRepository.save(updateUser);
    }



    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public void deleteById(int id) {
        userRepository.deleteById((long) id);

    }
}
