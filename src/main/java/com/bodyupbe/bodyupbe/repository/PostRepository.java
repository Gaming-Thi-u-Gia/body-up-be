package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.community.Post;
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
}
