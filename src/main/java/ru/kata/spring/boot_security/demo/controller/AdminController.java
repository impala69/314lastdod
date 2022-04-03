package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String printUser(Model model) {
        model.addAttribute("users", userService.read());

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", roleService.getAllRoles());

        return "admin";
    }

    @PostMapping()
    public String create(@ModelAttribute(value = "user") User user,
                         @RequestParam(name = "roles", required = false) String... roles) {
        List<Role> listRoles = readRoles(roles);
        user.setRoles(listRoles);
        userService.create(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user
            , @RequestParam(name = "roles", required = false) String... roles) {
        List<Role> listRoles = readRoles(roles);
        user.setRoles(listRoles);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    private List<Role> readRoles(String... role) {
        List<Role> roles = new ArrayList<>();
        if (role != null) {
            for (String s : role) {
                roles.add(roleService.readByRole(s));
            }
        }
        return roles;
    }


}
//RestController
//@RequestMapping("/admin")
//public class AdminRestController {
//    private final RoleService roleService;
//    private final UserService userService;
//
//    @Autowired
//    public AdminRestController(RoleService roleService, UserService userService) {
//        this.roleService = roleService;
//        this.userService = userService;
//    }
//
//    @GetMapping("/roles")
//    public ResponseEntity<List<Role>> getAllRoles() {
//        return ResponseEntity.ok().body(roleService.getAllRole());
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>>getAllUsers(){
//        return ResponseEntity.ok().body(userService.getAllUsers());
//    }
//
//
//    @GetMapping("/user")
//    public ResponseEntity<User> getUserPages(@AuthenticationPrincipal User user) {
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(user);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> newUser (@RequestBody User user){
//        userService.save(getUsersRole(user));
//        System.out.println(user);
//        return ResponseEntity.ok().body(user);
//    }
//
//    @GetMapping ("/user/{id}")
//    public ResponseEntity<User> getUserById (@PathVariable Long id){
//        final User user = userService.getUserById(id);
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    } //получение юзера по ID
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable(name = "id") int id, @RequestBody User user) {
//        userService.editUser(getUsersRole(user), id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    } //редачим юзера
//
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable(name = "id") int id) {
//        userService.deleteUser(id);
//    } //удаляем юзера (оставил только Лонг)
//
//    private User getUsersRole(User user) {
//        List<Role> roles = new ArrayList<>();
//        for (Role role : user.getRoles()) {
//            roles.add(roleService.getRoleByName(role.getRole()));
//        }
//        user.setRoles(roles);
//        return user;
//    }
//}