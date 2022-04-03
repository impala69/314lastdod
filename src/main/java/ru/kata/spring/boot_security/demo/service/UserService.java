package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(User user);
    void delete(User user);
    User findById(Long id);
    User getUserByEmail (String email);
}
