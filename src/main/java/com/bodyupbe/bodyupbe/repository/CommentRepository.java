package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
