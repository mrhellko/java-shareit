package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.system.exception.AccessDeniedException;
import ru.practicum.shareit.user.User;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Override
    public Item addItem(final Item item, final User user) {
        item.setOwner(user);
        return itemDao.save(item);
    }

    @Override
    public Item editItem(final int itemId, final Item newItem, final User user) {
        Item item = itemDao.findById(itemId);
        checkAccess(user, item.getOwner());
        if (newItem.getName() != null) {
            item.setName(newItem.getName());
        }
        if (newItem.getDescription() != null) {
            item.setDescription(newItem.getDescription());
        }
        if (newItem.getAvailable() != null) {
            item.setAvailable(newItem.getAvailable());
        }
        return item;
    }

    @Override
    public Item getItem(int itemId) {
        return itemDao.findById(itemId);
    }

    @Override
    public List<Item> getUserItems(User user) {
        return itemDao.findByOwner(user);
    }

    @Override
    public List<Item> search(String text) {
        if (text == null || text.isBlank()) {
            return Collections.emptyList();
        }
        return itemDao.search(text);
    }

    private void checkAccess(User currentUser, User owner) {
        if (!owner.equals(currentUser)) {
            throw new AccessDeniedException(
                    String.format(
                            "Попытка изменить предмет пользователя %d из под пользователя %d",
                            owner.getId(),
                            currentUser.getId()
                    )
            );
        }
    }

}
