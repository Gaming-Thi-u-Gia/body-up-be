package com.bodyupbe.bodyupbe.model.community;

import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_program.FinishProgramTag;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    String description;
    String imgBefore;
    String imgAfter;
    Date dayBefore;
    Date dayAfter;
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    User user;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    CategoryCommunity categoryCommunity;
    @ManyToOne
    @JoinColumn(name = "badge_id", referencedColumnName = "id")
    @JsonBackReference
    Badge badge;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    Set<Comment> comments;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<BookmarkPost> bookmarkPosts;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<OtherImagePost> otherImagePosts;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<FinishProgramTag> finishProgramTags;
}
