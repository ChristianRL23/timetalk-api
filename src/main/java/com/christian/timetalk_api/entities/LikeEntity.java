package com.christian.timetalk_api.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "likes")
@Entity
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
