package com.bodyupbe.bodyupbe.dto.response.community;

import com.bodyupbe.bodyupbe.dto.request.community.BadgeDto;
import com.bodyupbe.bodyupbe.dto.request.community.CategoryCommunityDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostSlimResponse {
    Integer id;
    String title;
    String description;
    String imgBefore;
    String imgAfter;
    Date dayBefore;
    Date dayAfter;
    Date createdAt;
    CategoryCommunityDto categoryCommunity;
    BadgeDto badge;
}
