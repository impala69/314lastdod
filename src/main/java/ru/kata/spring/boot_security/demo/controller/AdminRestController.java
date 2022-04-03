package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleServise;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers () {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping( "/users/{id}")
    public ResponseEntity<User> getUser (@PathVariable Long id){


        final User user = userService.findById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping ("/users")
    public ResponseEntity<?> addUser (@RequestBody User user) {
        userService.save(user);
 //       public ResponseEntity<?> newUser (@RequestBody User user){
        return ResponseEntity.ok().body(user);
//    }
    }

    @PutMapping("/users")
//    public void updateUser(@RequestBody User user) {
//        userService.save(user);
        public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping( "/users/{id}")
    public void deleteUser (@PathVariable Long id){
        User user = userService.findById(id);
        userService.delete(user);
    }

    @GetMapping("/name")
    public ResponseEntity<User> getAdminByName(Principal principal) {
        return ResponseEntity.ok(userService.getUserByEmail(principal.getName()));
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