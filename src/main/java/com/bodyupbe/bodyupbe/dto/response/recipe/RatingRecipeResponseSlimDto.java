package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingRecipeResponseSlimDto {
    int id;
    int star;
    double avgStar;
    int totalRating;
}
