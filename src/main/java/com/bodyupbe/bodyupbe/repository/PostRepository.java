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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findPostByCategoryCommunity_IdOrderByCreatedAtDesc(int categoryId, Pageable pageable);
    Page<Post> findPostByUser_Id(int userId, Pageable pageable);
    List<Post> findPostByBadge_Id(int badgeId);
    @Query(value = "SELECT COUNT(*) FROM Post p")
    int countPost();
    Page<Post> findPostByBadge_NameAndCategoryCommunity_Id(String name, int categoryId, Pageable pageable);

    Page<Post> findPostByTitleContainingIgnoreCaseAndCategoryCommunity_Id(String title,int categoryId ,Pageable pageable);
    //write find postBookmarkedByUserAndBadgeName
    Page<Post> findPostByBookmarkUsers_IdAndBadge_Name(int userId, String name, Pageable pageable);
    Page<Post> findPostByBookmarkUsers_IdAndTitleContainingIgnoreCase(int userId, String title, Pageable pageable);
    Page<Post> findPostByBadgeNameAndUser_Id(String name, int userId, Pageable pageable);
    Page<Post> findPostByTitleContainingIgnoreCaseAndUser_Id(String title, int userId, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Post p JOIN p.comments c WHERE c.user.id = :userId")
    List<Post> findPostsCommentedByUserId(@Param("userId") Integer userId);

    Page<Post> findPostByBookmarkUsers_Id(int userId, Pageable pageable);
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Post p JOIN p.bookmarkUsers bu WHERE bu.id = :userId AND p.id = :postId")
    boolean findBookmarkedByUserIdAndPostId(int userId, int postId);
    @Query("SELECT new com.bodyupbe.bodyupbe.dto.response.admin.dashboard.PostCardResponseForAdminDto(p.id, p.title, p.description, null) " +
            "FROM Post p " +
            "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "ORDER BY p.createdAt DESC")
    Page<PostCardResponseForAdminDto> findAllSlim(Pageable pageable, @Param("name") String name);
    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.admin.dashboard.BageSlimResponseDto(b.id,b.name) FROM Post p JOIN p.badge b WHERE p.id = :id")
    BageSlimResponseDto findBageByPostId(int id);
}