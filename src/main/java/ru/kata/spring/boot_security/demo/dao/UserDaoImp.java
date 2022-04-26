package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM User user WHERE user.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        return entityManager.createQuery("select user from User user where user.email = :email", User.class)
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();

    }
}