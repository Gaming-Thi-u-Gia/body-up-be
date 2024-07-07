package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.repository.CategoryCommunityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryPostService {
    CategoryCommunityRepository categoryCommunityRepository;

    public List<CategoryCommunity> getAllCategoryCommunity() {
        return categoryCommunityRepository.findAll();
    }





}
