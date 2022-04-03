package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleDAO {
    void create(Role role);
    Role readByRole(String role);
    List<Role> getAllRoles();
}
