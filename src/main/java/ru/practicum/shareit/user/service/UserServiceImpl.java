package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.system.exception.DuplicateEmailException;
import ru.practicum.shareit.system.exception.NoEmailException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User getUser(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public User addUser(User user) {
        checkNoEmail(user);
        checkDuplicateEmail(user);
        return userDao.save(user);
    }

    @Override
    public User editUser(int userId, User newUser) {
        checkDuplicateEmail(newUser);
        User user = userDao.findById(userId);
        if (newUser.getName() != null) {
            user.setName(newUser.getName());
        }
        if (newUser.getEmail() != null) {
            user.setEmail(newUser.getEmail());
        }
        return user;
    }

    @Override
    public void delete(int userId) {
        userDao.delete(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    private void checkNoEmail(User user) {
        if (user.getEmail() == null) {
            throw new NoEmailException("Не задан email");
        }
    }

    private void checkDuplicateEmail(User user) {
        if (userDao.findByEmail(user.getEmail()) != null) {
            throw new DuplicateEmailException(String.format(
                    "Такой email = %s уже существует",
                    user.getEmail()
            ));
        }
    }
}
