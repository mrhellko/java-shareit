package ru.practicum.shareit.item.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.system.exception.ItemNotFoundException;
import ru.practicum.shareit.system.service.IdProvider;
import ru.practicum.shareit.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InMemoryItemDao implements ItemDao {

    private final IdProvider idProvider;
    private final Map<Integer, Item> data = new HashMap<>();

    @Override
    public Item save(Item item) {
        int id;
        if (item.getId() == null) {
            id = idProvider.getId();
            item.setId(id);
        } else {
            id = item.getId();
        }
        data.put(id, item);
        return item;
    }

    @Override
    public Item findById(int itemId) {
        Item item = data.get(itemId);
        if (item == null) {
            throw new ItemNotFoundException(String.format("Предмет с id = %d не найден", itemId));
        }
        return item;
    }

    @Override
    public List<Item> findByOwner(User user) {
        return data.values().stream()
                .filter(item -> item.getOwner().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> search(String text) {
        final String lowerText = text.toLowerCase();
        return data.values().stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getDescription().toLowerCase().contains(lowerText)
                        || item.getName().toLowerCase().contains(lowerText))
                .collect(Collectors.toList());
    }
}
