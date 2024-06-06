package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.BookmarkRecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.BookmarkRecipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookmarkRecipeMapper {
    BookmarkRecipe toBookmarkRecipe(BookmarkRecipeDto bookmarkRecipeDto);
    BookmarkRecipeDto toBookmarkRecipeDto(BookmarkRecipe bookmarkRecipe);
}
