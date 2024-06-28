package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeSlimResponseDto {
    Integer id;
    String name;
    String detail;
    double avgStar;
    int prepTime;
    int cookTime;
    String img;
    String cookingInstruction;
    Date createAt;
}
