package com.bodyupbe.bodyupbe.repository;

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

    List<Post> findPostByBadge_NameAndCategoryCommunity_Id(String name, int categoryId);


    @Query("SELECT DISTINCT p FROM Post p JOIN p.comments c WHERE c.user.id = :userId")
    List<Post> findPostsCommentedByUserId(@Param("userId") Integer userId);

    Page<Post> findPostByBookmarkUsers_Id(int userId, Pageable pageable);
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Post p JOIN p.bookmarkUsers bu WHERE bu.id = :userId AND p.id = :postId")
    boolean findBookmarkedByUserIdAndPostId(int userId, int postId);
}
