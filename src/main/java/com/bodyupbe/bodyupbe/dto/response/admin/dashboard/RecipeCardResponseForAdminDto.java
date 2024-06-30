package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import com.bodyupbe.bodyupbe.dto.response.recipe.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RecipeCardResponseForAdminDto {
    Integer id;
    String name;
    String detail;
    double avgStar;
    String img;
}
