package com.example.securityframe.Repository;

import com.example.securityframe.Entity.UserEntity;
import com.example.securityframe.Security.SEntity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUserEntity(UserEntity userEntity);

}
