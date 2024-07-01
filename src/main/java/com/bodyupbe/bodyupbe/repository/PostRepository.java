package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.BageSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.PostCardResponseForAdminDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeCardResponseForAdminDto;
import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.community.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostByCategoryCommunity_Id(int categoryId);
    List<Post> findPostByUser_Id(int userId);

    @Query(value = "SELECT COUNT(*) FROM Post p")
    int countPost();
    @Query("SELECT new com.bodyupbe.bodyupbe.dto.response.admin.dashboard.PostCardResponseForAdminDto(p.id,p.title,p.description,null) FROM Post p ORDER BY p.createdAt DESC")
    Page<PostCardResponseForAdminDto> findAllSlim(Pageable pageable);
    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.admin.dashboard.BageSlimResponseDto(b.id,b.name) FROM Post p JOIN p.badge b WHERE p.id = :id")
    BageSlimResponseDto findBageByPostId(int id);
}
