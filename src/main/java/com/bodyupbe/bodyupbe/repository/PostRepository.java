package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostByCategoryCommunity_IdOrderByCreatedAtDesc(int categoryId);
    List<Post> findPostByUser_Id(int userId);
    List<Post> findPostByBadge_Id(int badgeId);

    List<Post> findPostByBookmarkUsers_Id(int userId);
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Post p JOIN p.bookmarkUsers bu WHERE bu.id = :userId AND p.id = :postId")
    boolean findBookmarkedByUserIdAndPostId(int userId, int postId);
}
