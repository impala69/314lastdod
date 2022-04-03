package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDAO {
    void create(User user);

    List<User> read();

    User readById(long id);

    User readByFirstName(String name);

    void update(User user);

    void delete(long id);
}
