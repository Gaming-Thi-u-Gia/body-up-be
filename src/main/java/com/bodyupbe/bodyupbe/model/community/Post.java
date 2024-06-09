package com.bodyupbe.bodyupbe.model.community;

import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(name = "created_at", updatable = true)
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value = "post-user")
    User user;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference(value = "post-category")
    CategoryCommunity categoryCommunity;

    @ManyToOne
    @JoinColumn(name = "badge_id", referencedColumnName = "id")
    @JsonBackReference(value = "post-badge")
    Badge badge;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "post-comments")
    Set<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "post-otherImagePosts")
    Set<OtherImagePost> otherImagePosts;

    @ManyToMany(mappedBy = "bookmarkPosts")
    @JsonBackReference(value = "post-bookmarkUsers")
    Set<User> bookmarkUsers;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "finish_program_tag",
            joinColumns = @JoinColumn(name = "post_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_challenge_id",referencedColumnName = "id")
    )
    @JsonBackReference(value = "post-userChallenges")
    Set<UserChallenge> userChallenges;
}
