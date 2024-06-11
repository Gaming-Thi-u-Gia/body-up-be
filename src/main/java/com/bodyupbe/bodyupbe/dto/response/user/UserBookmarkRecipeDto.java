package com.bodyupbe.bodyupbe.dto.response.user;

import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.user.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserBookmarkRecipeDto {
    Integer id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String avatar;
    String bio;
    Role role;
    Date createAt;
    Set<RecipeSlimResponseDto> bookmarkRecipes;
}
