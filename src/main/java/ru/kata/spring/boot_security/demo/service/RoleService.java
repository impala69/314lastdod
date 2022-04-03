package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    Iterable<Role> findAll();
    void save(Role role);
    void delete(Role role);
    Role findById(Long id);
    Iterable<Role> findAllById (List<Long> list);
}
