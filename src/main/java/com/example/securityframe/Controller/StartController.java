package com.example.securityframe.Controller;

import com.example.securityframe.Entity.Role;
import com.example.securityframe.Entity.UserEntity;
import com.example.securityframe.Repository.RoleRepository;
import com.example.securityframe.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
public class StartController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void createRole(){
        Role user = new Role();
        user.setRole("USER");
        roleRepository.save(user);

        Role admin = new Role();
        admin.setRole("ADMIN");
        roleRepository.save(admin);

        UserEntity adminEntity= new UserEntity();
        adminEntity.setLogin("admin@admin");
        adminEntity.setPassword(bCryptPasswordEncoder.encode("admin"));
        adminEntity.setRoles(List.of(admin));
        userRepository.save(adminEntity);

        UserEntity userEntity= new UserEntity();
        userEntity.setLogin("user@user");
        userEntity.setPassword(bCryptPasswordEncoder.encode("user"));
        userEntity.setRoles(List.of(user));
        userRepository.save(userEntity);

    }

    @GetMapping("/home")
    public String homePage(){
        return "Hello";
    }

    @GetMapping("/work")
    public String workPage(){
        return "Hello on work";
    }

}
