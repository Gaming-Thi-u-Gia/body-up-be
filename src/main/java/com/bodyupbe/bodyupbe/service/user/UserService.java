package com.bodyupbe.bodyupbe.service.user;

import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.request.user.UserProgressPhotoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserProgressPhotoResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserProgressPhoto;
import com.bodyupbe.bodyupbe.repository.UserProgressPhotoRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    UserProgressPhotoRepository userProgressPhotoRepository;

    public Set<UserProgressPhotoResponseDto> addProgressPhoto(User user, UserProgressPhotoRequestDto userProgressPhotoRequestDto) {
        UserProgressPhoto progressPhoto = userMapper.toUserProgressPhoto(userProgressPhotoRequestDto);
        progressPhoto.setUser(user);
        user.getUserProgressPhotos().add(progressPhoto);
        userProgressPhotoRepository.save(progressPhoto);
        return userMapper.toListUserProgressPhotoResponseDto(user.getUserProgressPhotos());
    }

    public Set<UserProgressPhotoResponseDto> getProgressPhoto(User user) {
        return userMapper.toListUserProgressPhotoResponseDto(user.getUserProgressPhotos());
    }

    public Set<UserProgressPhotoResponseDto> getProgressPhotoByPhotoAngle(User user,String photoAngle) {
        Set<UserProgressPhoto> userProgressPhotos = userProgressPhotoRepository.findByUserAndPhotoAngleOrderByDateDesc(user, photoAngle);
        return userMapper.toListUserProgressPhotoResponseDto(userProgressPhotos);
    }
    public UserProgressPhotoResponseDto getUserProgressPhotoById(int progressPhotoId) {
        UserProgressPhoto userProgressPhoto = userProgressPhotoRepository.findById(progressPhotoId).orElseThrow(() -> new RuntimeException("Progress photo not found"));
        return userMapper.toUserProgressPhotoResponseDto(userProgressPhoto);
    }

    public UserResponseDto getUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponseDto(user);
    }

    public void deleteUserProgressPhotoById (User user, int progressPhotoId) {
        UserProgressPhoto userProgressPhoto = userProgressPhotoRepository.findById(progressPhotoId).orElseThrow(() -> new RuntimeException("Progress photo not found"));
        user.getUserProgressPhotos().remove(userProgressPhoto);
        userProgressPhotoRepository.deleteById(progressPhotoId);
    }
    public UserProgressPhotoResponseDto updateUserProgressPhoto(User user, int progressPhotoId, UserProgressPhotoRequestDto userProgressPhotoRequestDto) {
        UserProgressPhoto userProgressPhoto = userProgressPhotoRepository.findById(progressPhotoId).orElseThrow(() -> new RuntimeException("Progress photo not found"));
        userProgressPhoto.setCaption(userProgressPhotoRequestDto.getCaption());
        userProgressPhoto.setImgUrl(userProgressPhotoRequestDto.getImgUrl());
        userProgressPhoto.setPhotoAngle(userProgressPhotoRequestDto.getPhotoAngle());
        userProgressPhoto.setVisibility(userProgressPhotoRequestDto.isVisibility());
        userProgressPhoto.setDate(userProgressPhotoRequestDto.getDate());
        return userMapper.toUserProgressPhotoResponseDto(userProgressPhotoRepository.save(userProgressPhoto));
    }

    public UserResponseDto getUserByUsername2(String username2) {
        User user = userRepository.findByUserName2(username2);
        return userMapper.toUserResponseDto(user);
    }

    public Set<UserProgressPhotoResponseDto> getUserProgressPhotoByUserId(int progressPhotoId, String photoAngle) {
        Set<UserProgressPhoto> userProgressPhoto = userProgressPhotoRepository.findAllByUserIdAndPhotoAngle(progressPhotoId, photoAngle);
        return userMapper.toListUserProgressPhotoResponseDto(userProgressPhoto);
    }

    public Set<UserProgressPhotoResponseDto> getUserProgressPhotoByUserId(int userId) {
        Set<UserProgressPhoto> userProgressPhoto = userProgressPhotoRepository.findAllByUserIdOrderByDate(userId);
        return userMapper.toListUserProgressPhotoResponseDto(userProgressPhoto);
    }
}
