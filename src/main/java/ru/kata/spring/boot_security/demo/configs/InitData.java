package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class InitData {

    @Autowired
    private final RoleService roleService;
    @Autowired
    private final UserService userService;

    public InitData(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @PostConstruct
    void postConstruct() {
        Role role_admin = new Role("ADMIN");
        Role role_user = new Role("USER");
        roleService.save(role_admin);
        roleService.save(role_user);

        userService.save(new User("Admin@mail.ru", "Admin",
                "AdminName", "AdminLastName", 30, Set.of(role_admin)));
        userService.save(new User("User1@mail.ru", "User1",
                "User1Name", "User1LastName", 20, Set.of(role_user)));
        userService.save(new User("User2@mail.ru", "User2",
                "User2Name", "User2LastName", 21, Set.of(role_user)));

    }
}
