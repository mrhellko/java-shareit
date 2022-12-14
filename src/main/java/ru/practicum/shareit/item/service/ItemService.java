package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.util.List;

public interface ItemService {
    Item addItem(Item item, User user);

    Item editItem(int itemId, Item item, User user);

    Item getItem(int itemId);

    List<Item> getUserItems(User user);

    List<Item> search(String text);
}
