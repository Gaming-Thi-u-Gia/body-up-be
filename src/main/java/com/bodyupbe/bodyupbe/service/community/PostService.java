package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.dto.mapper.community.CommentMapper;
import com.bodyupbe.bodyupbe.dto.mapper.community.PostMapper;
import com.bodyupbe.bodyupbe.dto.request.community.PostRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.CommentResponseDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostCommentSlimDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.community.Comment;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    CommentRepository commentRepository;
    CommentMapper commentMapper;

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
    public PostResponseDto editPost(PostRequestDto postDto, User user, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Badge badge = badgeRepository.findById(post.getBadge().getId()).orElseThrow(() -> new RuntimeException("Badge not found"));
        post.setBadge(badge);
        if(!user.getId().equals(post.getUser().getId())) {
            throw new RuntimeException("You are not authorized to edit this post");
        }
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setImgBefore(postDto.getImgBefore());
        post.setImgAfter(postDto.getImgAfter());
        post.setDayBefore(postDto.getDayBefore());
        post.setDayAfter(postDto.getDayAfter());
        return postMapper.toPostResponseDto(postRepository.save(post));
    }

    public List<PostResponseDto> getPostAllByCategoryId(Optional<Integer> userId, int categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postsPage = postRepository.findPostByCategoryCommunity_IdOrderByCreatedAtDesc(categoryId, pageable);
        List<Post> post = postsPage.getContent();
        List<PostResponseDto> postResponseDto = postMapper.toListPostResponseDto(post);
        if (userId.isPresent()) {
            for (PostResponseDto p : postResponseDto) {
                p.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), p.getId()));
            }
        }
        return postResponseDto;
    }

    public List<PostResponseDto> getPostByUserId(Optional<Integer> userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postsPage = postRepository.findPostByUser_Id(userId.get(), pageable);
        List<Post> posts = postsPage.getContent();
        List<PostResponseDto> postResponseDto = postMapper.toListPostResponseDto(posts);
        for (PostResponseDto p : postResponseDto) {
                p.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), p.getId()));
        }
        return postResponseDto;
    }
    public List<PostResponseDto> getAllPostBookmarkByUserId(Optional<Integer> userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postsPage = postRepository.findPostByBookmarkUsers_Id(userId.get(), pageable);
        List<Post> posts = postsPage.getContent();

        List<PostResponseDto> postResponseDto = postMapper.toListPostResponseDto(posts);
        for (PostResponseDto p : postResponseDto) {
            p.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), p.getId()));
        }
        return postResponseDto;
    }


    public void deletePost(int postId,User user) {
        user = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        if(!user.getId().equals(post.getUser().getId())) {
            throw new RuntimeException("You are not authorized to delete this post");
        }
        if(user.getBookmarkPosts().contains(post)){
            user.getBookmarkPosts().remove(post);
            userRepository.save(user);
        }
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


    public List<PostCommentSlimDto>  getPostsCommentedAndCommentByUser(User user ) {
        List<Post> posts = postRepository.findPostsCommentedByUserId(user.getId());
        List<PostCommentSlimDto> postResults = new ArrayList<>();
        for (Post post : posts) {
           Set<Comment> comments = commentRepository.findCommentByPost_IdAndUser_IdOrderByCreateAtDesc(post.getId(), user.getId());
           Set<CommentResponseDto> commentResults= commentMapper.toSetCommentResponseDto(comments);
           PostCommentSlimDto postResponseDto = postMapper.toPostCommentSlimDto(post);
           postResponseDto.setComments(commentResults);
            postResults.add(postResponseDto);
        }
        return postResults;
    }

    public List<PostResponseDto> getPostByBadgeNameAndCategoryId(String badgeName, int categoryId, Optional<Integer> userId) {
        List<Post> posts = postRepository.findPostByBadge_NameAndCategoryCommunity_Id(badgeName, categoryId);
        List<PostResponseDto> postResponseDto = postMapper.toListPostResponseDto(posts);
        if (userId.isPresent()) {
            for (PostResponseDto p : postResponseDto) {
                p.setBookmarked(postRepository.findBookmarkedByUserIdAndPostId(userId.get(), p.getId()));
            }
        }
        return postResponseDto;
    }

}








