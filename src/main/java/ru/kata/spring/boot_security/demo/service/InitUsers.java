package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class InitUsers {
    private final UserService userService;
    private final RoleService roleService;

    public InitUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role userRole = roleService.create(new Role("User"));
        Role adminRole = roleService.create(new Role("Admin"));

        userService.create(new User("Tom", "Holland", "tom@holland", "111", Set.of()));
        userService.create(new User("John", "Smith", "john@smith", "222", Set.of(userRole)));
        userService.create(new User("Mike", "Scott", "mike@scott", "333", Set.of(adminRole)));
        userService.create(new User("Shwartc", "Negr", "shwartc@negr", "444",Set.of(userRole, adminRole)));
    }
}
