package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.dto.mapper.community.BadgeMapper;
import com.bodyupbe.bodyupbe.dto.mapper.community.CategoryMapper;
import com.bodyupbe.bodyupbe.dto.mapper.community.PostMapper;
import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    UserMapper userMapper;
    BadgeMapper badgeMapper;
    CategoryMapper categoryMapper;
    public PostDto createPost(PostDto postDto, int userId, int badgeId, int categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
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
        PostDto postDtoResults = postMapper.toPostDto(postRepository.save(post));
        postDtoResults.setBadge(badgeMapper.toBadgeDto(badge));
        postDtoResults.setUser(userMapper.toUserDto(user));
        postDtoResults.setCategoryCommunity(categoryMapper.toCategoryCommunityDto(categoryCommunity));
        return postDtoResults;
    }

    public List<PostDto> getPostAllByCategoryId(int categoryId) {
        List<Post> posts = postRepository.findPostByCategoryCommunity_Id(categoryId);
        return posts.stream()
                .map(post -> {
                    PostDto postDto = postMapper.toPostDto(post);
                    postDto.setBadge(badgeMapper.toBadgeDto(post.getBadge()));
                    postDto.setUser(userMapper.toUserDto(post.getUser()));
                    postDto.setCategoryCommunity(categoryMapper.toCategoryCommunityDto(post.getCategoryCommunity()));
                    return postDto;
                })
                .collect(Collectors.toList());
    }
    public List<PostDto> getPostByUserId(int userId){
        List<Post> posts = postRepository.findPostByUser_Id(userId);
        return posts.stream()
                .map(post -> {
                    PostDto postDto = postMapper.toPostDto(post);
                    postDto.setBadge(badgeMapper.toBadgeDto(post.getBadge()));
                    postDto.setUser(userMapper.toUserDto(post.getUser()));
                    postDto.setCategoryCommunity(categoryMapper.toCategoryCommunityDto(post.getCategoryCommunity()));
                    return postDto;
                })
                .collect(Collectors.toList());
    }
    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }
    public PostDto getPostById(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        PostDto postDto = postMapper.toPostDto(post);
        postDto.setBadge(badgeMapper.toBadgeDto(post.getBadge()));
        postDto.setUser(userMapper.toUserDto(post.getUser()));
        postDto.setCategoryCommunity(categoryMapper.toCategoryCommunityDto(post.getCategoryCommunity()));
        return postDto;
    }



}
