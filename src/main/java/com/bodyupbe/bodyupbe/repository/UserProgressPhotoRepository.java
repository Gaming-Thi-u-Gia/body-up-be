package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.user.UserProgressPhotoResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserProgressPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserProgressPhotoRepository extends JpaRepository<UserProgressPhoto,Integer> {
    Set<UserProgressPhoto> findAllByPhotoAngle(String photoAngle);
    Set<UserProgressPhoto> findByUserAndPhotoAngleOrderByDateDesc(User user, String photoAngle);
    Set<UserProgressPhoto> findAllByUserIdAndPhotoAngle(int userId, String photoAngle);
    Set<UserProgressPhoto> findAllByUserId(int userId);
}