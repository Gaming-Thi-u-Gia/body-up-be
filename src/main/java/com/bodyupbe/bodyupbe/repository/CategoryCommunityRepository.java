package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCommunityRepository extends JpaRepository<CategoryCommunity,Integer>{
}
