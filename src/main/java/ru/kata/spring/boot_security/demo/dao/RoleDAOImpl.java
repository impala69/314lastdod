package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(Role role) {
        manager.persist(role);
    }

    @Override
    public Role readByRole(String role) {
        return manager.createQuery("from Role role where role.role =:role", Role.class)
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return manager.createQuery("from Role", Role.class).getResultList();
    }

}