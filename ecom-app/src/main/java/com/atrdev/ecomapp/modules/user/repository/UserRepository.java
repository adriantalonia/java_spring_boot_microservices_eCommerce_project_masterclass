package com.atrdev.ecomapp.modules.user.repository;

import com.atrdev.ecomapp.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
