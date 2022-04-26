package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    void delete(Long id);

    void update(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();
}