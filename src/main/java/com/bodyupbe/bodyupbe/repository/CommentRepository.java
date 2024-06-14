package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findCommentByPost_IdOrderByCreateAtDesc(int postId);

    List<Comment> findAllByUser_Id(int userId);

}
