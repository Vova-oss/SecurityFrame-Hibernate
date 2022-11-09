package com.example.securityframe.MainApp.Repository;

import com.example.securityframe.MainApp.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
