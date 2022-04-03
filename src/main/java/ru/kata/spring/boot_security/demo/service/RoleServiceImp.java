package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.models.Role;


import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDAO roleDao;

    @Autowired
    public RoleServiceImp(RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void create(Role role) {
        roleDao.create(role);
    }

    @Override
    public Role readByRole(String role) {
        return roleDao.readByRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

}
