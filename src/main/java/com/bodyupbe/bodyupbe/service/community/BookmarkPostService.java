package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.dto.mapper.community.PostMapper;
import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.response.community.BookmarkResponseDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.PostRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookmarkPostService {
    PostRepository postRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    PostMapper postMapper;


    public BookmarkResponseDto bookmarkPost(User user, int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        UserSlimResponseDto userSlimResponseDto = userMapper.toUserSlimResponseDto(user);
        boolean isBookmarked;
        if(user.getBookmarkPosts().contains(post)) {
            user.getBookmarkPosts().remove(post);
            post.getBookmarkUsers().remove(user);
            isBookmarked = false;
        } else {
            user.getBookmarkPosts().add(post);
            post.getBookmarkUsers().add(user);
            isBookmarked = true;

        }
        userRepository.save(user);
        return new BookmarkResponseDto(userSlimResponseDto, postId, isBookmarked);
    }

    public BookmarkResponseDto checkBookmarkPost(User user, int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        UserSlimResponseDto userSlimResponseDto = userMapper.toUserSlimResponseDto(user);
        boolean isBookmarked;
        if(user.getBookmarkPosts().contains(post)) {
            isBookmarked = true;
        } else {
            isBookmarked = false;
        }
        return new BookmarkResponseDto(userSlimResponseDto, postId, isBookmarked);
    }


    public  Set<PostResponseDto> getBookmarkPost(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return postMapper.toSetPostResponseDto(user.getBookmarkPosts());
    }



}
