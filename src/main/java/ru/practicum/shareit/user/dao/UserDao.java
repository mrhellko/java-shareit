package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.User;

import java.util.List;

public interface UserDao {
    User findById(int userId);

    User save(User user);

    void delete(int userId);

    List<User> findAll();

    User findByEmail(String email);
}
