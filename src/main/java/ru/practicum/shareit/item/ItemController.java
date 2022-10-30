package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final UserService userService;

    @PostMapping
    public ItemDto addItem(
            @RequestHeader("X-Sharer-User-Id") int userId,
            @Valid @RequestBody ItemDto itemDto
    ) {
        User user = userService.getUser(userId);
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toItemDto(itemService.addItem(item, user));
    }

    @PatchMapping("/{itemId}")
    public ItemDto editItem(
            @RequestHeader("X-Sharer-User-Id") int userId,
            @PathVariable int itemId,
            @RequestBody ItemDto itemDto
    ) {
        User user = userService.getUser(userId);
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toItemDto(itemService.editItem(itemId, item, user));
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable int itemId) {
        return ItemMapper.toItemDto(itemService.getItem(itemId));
    }

    @GetMapping
    public List<ItemDto> getItems(@RequestHeader("X-Sharer-User-Id") int userId) {
        User user = userService.getUser(userId);
        return itemService.getUserItems(user)
                .stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam String text) {
        return itemService.search(text)
                .stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

}
