package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.BookmarkPost;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkPostRepository extends JpaRepository<BookmarkPost,Integer> {
    BookmarkPost findByUserAndPost(User user, Post post);

}
