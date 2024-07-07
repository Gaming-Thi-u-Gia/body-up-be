package com.bodyupbe.bodyupbe.dto.request.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RecipeSelectForAdminRequestDto {
    int id;
    String name;
}
