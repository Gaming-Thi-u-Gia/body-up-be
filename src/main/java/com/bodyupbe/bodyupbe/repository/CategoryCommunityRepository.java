package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.community.Comment;
import com.bodyupbe.bodyupbe.model.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryCommunityRepository extends JpaRepository<CategoryCommunity,Integer>{

}
