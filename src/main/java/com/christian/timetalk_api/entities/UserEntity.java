package com.christian.timetalk_api.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PostEntity> posts;
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "account_no_expired")
    private boolean accountNoExpired;
    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;
    @Column(name = "account_no_locked")
    private boolean accountNoLocked;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

}
