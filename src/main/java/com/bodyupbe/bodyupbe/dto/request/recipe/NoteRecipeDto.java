package com.bodyupbe.bodyupbe.dto.request.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteRecipeDto {
    Integer id;
    String detail;
}
