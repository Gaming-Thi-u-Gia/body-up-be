package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findCommentByPost_IdOrderByCreateAtDesc(int postId);

    List<Comment> findAllByUser_Id(int userId);
    Set<Comment> findCommentByPost_IdAndUser_IdOrderByCreateAtDesc(int postId, int userId);

    // Query to get all children comment based on parentId of root comment, many tiers comment, using recursive CTE
    @Query(value = "WITH RECURSIVE sub_comments AS (" +
            "SELECT c.* FROM comments c WHERE c.parent_id = :parentId " +
            "UNION ALL " +
            "SELECT c.* FROM comments c INNER JOIN sub_comments sc ON c.parent_id = sc.id) " +
            "SELECT * FROM sub_comments", nativeQuery = true)
    List<Comment> findAllChildrenByParentId(@Param("parentId") Integer parentId);

    @Query(value = "WITH RECURSIVE comment_path AS (" +
            "SELECT c.*, 0 as level FROM comments c WHERE c.id = :commentId " +
            "UNION ALL " +
            "SELECT p.*, cp.level + 1 FROM comments p INNER JOIN comment_path cp ON p.id = cp.parent_id) " +
            "SELECT * FROM comment_path WHERE parent_id IS NULL ORDER BY level DESC LIMIT 1", nativeQuery = true)
    Optional<Comment> findRootCommentByChildId(@Param("commentId") Integer commentId);

}
