package com.bodyupbe.bodyupbe.model.community;

import com.bodyupbe.bodyupbe.model.user.User;
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
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String detail;
    @Column(name = "up_vote")
    int upVote;
    @CreationTimestamp
    @Column(name = "created_at", updatable = true)
    Date createdAt;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "id")
    @JsonBackReference
    Post post;

    @ManyToOne
    @JoinColumn(name="reply_id",referencedColumnName = "id")
    @JsonBackReference
    Comment comment;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Comment> comments;
}
