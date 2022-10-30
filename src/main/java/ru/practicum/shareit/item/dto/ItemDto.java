package ru.practicum.shareit.item.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemDto {
    private final Integer id;
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String description;
    @NotNull
    private final Boolean available;
}
