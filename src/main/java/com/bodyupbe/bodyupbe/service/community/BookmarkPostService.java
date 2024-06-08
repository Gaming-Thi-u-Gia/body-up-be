package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.dto.mapper.community.BookmarkPostMapper;
import com.bodyupbe.bodyupbe.dto.mapper.community.PostMapper;
import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.request.community.BookmarkPostDto;
import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import com.bodyupbe.bodyupbe.model.community.BookmarkPost;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.BookmarkPostRepository;
import com.bodyupbe.bodyupbe.repository.PostRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    BookmarkPostMapper bookmarkPostMapper;
    BookmarkPostRepository bookmarkPostRepository;
    UserMapper userMapper;
    PostMapper postMapper;

    public Optional<BookmarkPostDto> bookmarkPost(int userId, int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        BookmarkPost bookmarkPostFind = bookmarkPostRepository.findByUserAndPost(user, post);
        if (bookmarkPostFind != null) {
            bookmarkPostRepository.delete(bookmarkPostFind);
            log.info("Post with id {} has been unbookmarked by user {}", postId, userId);
            return Optional.empty();
        } else {
            BookmarkPost bookmarkPost = BookmarkPost.builder()
                    .post(post)
                    .user(user)
                    .build();
           ;

            BookmarkPostDto bookmarkPostDtoResults = bookmarkPostMapper.toBookmarkPostDto(bookmarkPostRepository.save(bookmarkPost));
            bookmarkPostDtoResults.setPost(postMapper.toPostDto(post));
            bookmarkPostDtoResults.setUser(userMapper.toUserDto(user));
            log.info(postMapper.toPostDto(post).toString());
            log.info(userMapper.toUserDto(user).toString());
            log.info("Post with id {} has been bookmarked by user {}", postId, userId);



            return Optional.of(bookmarkPostDtoResults);
        }
    }






}
