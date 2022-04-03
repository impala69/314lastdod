package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        manager.persist(user);
    }

    @Override
    public List<User> read() {
        return manager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User readById(long id) {
        return manager.find(User.class, id);
    }

    @Override
    public User readByFirstName(String name) {
        return manager.createQuery("from User user join fetch user.roles where user.firstName =:firstName", User.class)
                .setParameter("firstName", name)
                .getSingleResult();
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        manager.merge(user);
    }

    @Override
    public void delete(long id) {
        manager.remove(readById(id));
    }

}