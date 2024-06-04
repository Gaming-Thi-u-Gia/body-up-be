package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
