package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.BageSlimResponseDto;
import com.bodyupbe.bodyupbe.model.community.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BadgeRepository extends JpaRepository<Badge,Integer> {

}
