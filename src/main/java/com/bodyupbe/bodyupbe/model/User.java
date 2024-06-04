package com.bodyupbe.bodyupbe.model;

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
@Data
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
    Set<BookmarkPost> bookmarkPosts;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Post> posts;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<UserDailyChallenge> userDailyChallenges;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<BookmarkRecipe> bookmarkRecipes;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<RatingRecipe> ratingRecipes;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<BookmarkVideo> bookmarkVideos;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Comment> comments;
}
