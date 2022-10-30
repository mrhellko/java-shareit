package ru.practicum.shareit.user.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.system.exception.UserNotFoundException;
import ru.practicum.shareit.system.service.IdProvider;
import ru.practicum.shareit.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InMemoryUserDao implements UserDao {

    private final Map<Integer, User> data = new HashMap<>();
    private final IdProvider idProvider;

    @Override
    public User findById(int userId) {
        User user = data.get(userId);
        if (user == null) {
            throw new UserNotFoundException(String.format(
                    "Пользователь с id = %d не найден",
                    userId
            ));
        }
        return user;
    }

    @Override
    public User save(User user) {
        int id;
        if (user.getId() == null) {
            id = idProvider.getId();
            user.setId(id);
        } else {
            id = user.getId();
        }
        data.put(id, user);
        return user;
    }

    @Override
    public void delete(int userId) {
        data.remove(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public User findByEmail(String email) {
        return data.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

}
