package com.example.securityframe.Security.SEntity;

import com.example.securityframe.Entity.UserEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false, name = "expiry_date")
    private Long expiryDate;

}
