package com.bodyupbe.bodyupbe.dto.request;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeCategoryDto {
    Integer id;
    String name;
    String type;
    Set<RecipeFilterDto> recipeFilterDtos;
}
