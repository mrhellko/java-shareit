package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userService.addUser(user));
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable int userId) {
        return UserMapper.toUserDto(userService.getUser(userId));
    }

    @PatchMapping("/{userId}")
    public UserDto editUser(@PathVariable int userId, @RequestBody UserDto userDto) {
        User newUser = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userService.editUser(userId, newUser));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.delete(userId);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUsers().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

}
