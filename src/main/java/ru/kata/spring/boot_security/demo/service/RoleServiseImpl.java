package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiseImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Lazy
    @Autowired
    public RoleServiseImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<Role> findAllById(List<Long> list) {
        return roleRepository.findAllById(list);
    }
}