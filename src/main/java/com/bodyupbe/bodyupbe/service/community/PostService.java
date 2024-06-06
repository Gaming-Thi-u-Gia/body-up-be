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
        CategoryCommunity categoryCommunity = categoryCommunityRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Recipe not found"));
        Post post= postMapper.toPost(postDto);
        post.setUser(user);
        post.setBadge(badge);
        post.setCategoryCommunity(categoryCommunity);
        return postMapper.toPostDto(postRepository.save(post));
    }
}
