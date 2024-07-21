package com.bodyupbe.bodyupbe.model.user;

import com.bodyupbe.bodyupbe.model.FeedbackWorkout;
import com.bodyupbe.bodyupbe.model.Notification;
import com.bodyupbe.bodyupbe.model.community.Comment;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 2000)
    String userName;
    @Column(length = 2000)
    String userName2;
    @Column(length = 2000)
    String firstName;
    @Column(length = 2000)
    String lastName;
    @Column(unique = true)
    String email;
    @Column(length = 2000)
    String password;
    @Column(length = 2000)
    String avatar;
    @Column(length = 2000)
    String bio;
    @Enumerated(EnumType.STRING)
    Role role;
    @Column(name = "create_at")
    @CurrentTimestamp
    Date createAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //Noi bang
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<UserChallenge> userChallenges;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<UserProgressPhoto> userProgressPhotos;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Post> posts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<UserDailyChallenge> userDailyChallenges;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<RatingRecipe> ratingRecipes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Comment> comments;

    @ManyToMany(mappedBy = "bookmarkUsers", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<Post> bookmarkPosts  = new HashSet<>();;

    @ManyToMany(mappedBy = "bookmarkUsers", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<Video> bookmarkVideos  = new HashSet<>();;

    @ManyToMany(mappedBy = "bookmarkUsers", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<Recipe> bookmarkRecipes  = new HashSet<>();;

    public User(String userName, String firstName, String lastName, String email, String password, String avatar, String bio, Role role, Date createAt) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.bio = bio;
        this.role = role;
        this.createAt = createAt;
    }
    @ManyToMany(mappedBy = "users",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<Notification> notifications;

    @OneToOne
    @JsonBackReference
    FeedbackWorkout feedbackWorkout;
}