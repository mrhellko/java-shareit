package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.util.List;

public interface ItemDao {
    Item save(Item item);

    Item findById(int itemId);

    List<Item> findByOwner(User user);

    List<Item> search(String text);
}
