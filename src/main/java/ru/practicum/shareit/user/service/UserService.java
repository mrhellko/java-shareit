package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;

import java.util.List;

public interface UserService {
    User getUser(int userId);

    User addUser(User user);

    User editUser(int userId, User newUser);

    void delete(int userId);

    List<User> getAllUsers();
}
