package com.bodyupbe.bodyupbe.model.user;

import com.bodyupbe.bodyupbe.model.community.Comment;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.recipe.BookmarkRecipe;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String userName;
    String firstName;
    String lastName;
    @Column(unique = true)
    String email;
    String password;
    String avatar;
    String bio;
    @Enumerated(EnumType.STRING)
    Role role;
    @Column(name = "create_at")
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
    Set<UserChallenge> userChallenges;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<UserProgressPhoto> userProgressPhotos;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Post> posts;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<UserDailyChallenge> userDailyChallenges;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<RatingRecipe> ratingRecipes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "bookmark_posts",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "post_id",referencedColumnName = "id")
    )
    Set<Post> bookmarkPosts;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "bookmark_videos",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "video_id",referencedColumnName = "id")
    )
    Set<Video> bookmarkVideos;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "bookmark_recipes",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id",referencedColumnName = "id")
    )
    Set<Recipe> bookmarkRecipes;

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
}
