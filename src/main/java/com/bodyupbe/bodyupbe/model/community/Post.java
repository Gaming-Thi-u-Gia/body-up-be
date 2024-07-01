package com.bodyupbe.bodyupbe.model.community;

import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 2000)
    String title;
    @Column(length = 2000)
    String description;
    @Column(length = 2000)
    String imgBefore;
    @Column(length = 2000)
    String imgAfter;
    Date dayBefore;
    Date dayAfter;

    @CreationTimestamp
    @Column(name = "created_at", updatable = true)
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

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    Set<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    Set<OtherImagePost> otherImagePosts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "bookmark_posts",
            inverseJoinColumns  = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            joinColumns   = @JoinColumn(name = "post_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    Set<User> bookmarkUsers;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "finish_program_tag",
            joinColumns = @JoinColumn   (name = "post_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_challenge_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    Set<UserChallenge> userChallenges;
}
