package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.community.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge,Integer> {
}
//