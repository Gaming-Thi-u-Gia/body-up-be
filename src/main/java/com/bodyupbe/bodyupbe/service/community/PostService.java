package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.dto.mapper.community.PostMapper;
import com.bodyupbe.bodyupbe.dto.request.community.PostRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.BadgeRepository;
import com.bodyupbe.bodyupbe.repository.CategoryCommunityRepository;
import com.bodyupbe.bodyupbe.repository.PostRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostMapper postMapper;
    PostRepository postRepository;
    UserRepository userRepository;
    BadgeRepository badgeRepository;
    CategoryCommunityRepository categoryCommunityRepository;

    public PostResponseDto createPost(PostRequestDto postDto, User user, int badgeId, int categoryId) {
        Badge badge = badgeRepository.findById(badgeId).orElseThrow(() -> new RuntimeException("Badge not found"));
        CategoryCommunity categoryCommunity = categoryCommunityRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        Post post = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .imgBefore(postDto.getImgBefore())
                .imgAfter(postDto.getImgAfter())
                .dayBefore(postDto.getDayBefore())
                .dayAfter(postDto.getDayAfter())
                .user(user)
                .badge(badge)
                .categoryCommunity(categoryCommunity)
                .build();
        return postMapper.toPostResponseDto(postRepository.save(post));
    }

    public List<PostResponseDto> getPostAllByCategoryId(Optional<Integer> userId, int categoryId) {
        List<Post> post = postRepository.findPostByCategoryCommunity_IdOrderByCreatedAtDesc(categoryId);
        List<PostResponseDto> postResponseDto = postMapper.toListPostResponseDto(post);
        if (userId.isPresent()) {
            for (PostResponseDto p : postResponseDto) {
                p.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), p.getId()));
            }
        }
        return postResponseDto;
    }

    public List<PostResponseDto> getPostByUserId(Optional<Integer> userId) {
        List<Post> posts = postRepository.findPostByUser_Id(userId.get());
        List<PostResponseDto> postResponseDto = postMapper.toListPostResponseDto(posts);
        for (PostResponseDto p : postResponseDto) {
                p.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), p.getId()));
        }
        return postResponseDto;
    }
    public List<PostResponseDto> getAllPostBookmarkByUserId(Optional<Integer> userId) {
        List<Post> posts = postRepository.findPostByBookmarkUsers_Id(userId.get());
        List<PostResponseDto> postResponseDto = postMapper.toListPostResponseDto(posts);
        for (PostResponseDto p : postResponseDto) {
            p.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), p.getId()));
        }
        return postResponseDto;
    }


    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }

    public PostResponseDto getPostById(Optional<Integer> userId, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        PostResponseDto postResponseDto = postMapper.toPostResponseDto(post);
        if (userId.isPresent()) {
            postResponseDto.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), postId));
        }
        return postResponseDto;
    }



    public List<PostResponseDto> getAllPostByBadgeId(int badgeId) {
        List<Post> posts = postRepository.findPostByBadge_Id(badgeId);
        return postMapper.toListPostResponseDto(posts);
    }


}
