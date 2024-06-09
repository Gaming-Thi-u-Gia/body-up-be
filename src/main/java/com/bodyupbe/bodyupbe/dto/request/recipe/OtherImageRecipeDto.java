package com.bodyupbe.bodyupbe.dto.request.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtherImageRecipeDto {
    Integer id;
    String img;
}
