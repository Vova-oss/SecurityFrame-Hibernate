package com.example.securityframe.MainApp.Entity;

import com.example.securityframe.Security.SEntity.RefreshToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_user_entities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @OneToOne(mappedBy = "userEntity")
    private RefreshToken refreshToken;

}
