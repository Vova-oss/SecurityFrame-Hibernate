package com.example.securityframe.MainApp.Service;

import com.example.securityframe.AuxiliaryClasses.StaticMethods;
import com.example.securityframe.MainApp.Entity.UserEntity;
import com.example.securityframe.MainApp.Repository.UserRepository;
import com.example.securityframe.Security.SService.JWTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.securityframe.Security.SecurityConstants.TOKEN_PREFIX;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JWTokenService jwTokenService;

    public UserEntity findByLogin(String login){
        return userRepository.findByLogin(login);
    }


    public UserEntity findByJWToken(String tokenWithPrefix, HttpServletRequest request, HttpServletResponse response){
        String token = tokenWithPrefix.replace(TOKEN_PREFIX, "");
        String login = jwTokenService.getLoginFromJWT(token);
        if(login == null){
            StaticMethods.createResponse(400, "Incorrect JWToken");
            return null;
        }

        return findByLogin(login);
    }
}
