package com.example.helpsite.repo;

import com.example.helpsite.models.UserEntity;
import com.example.helpsite.models.UserInRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInRoleRepository extends JpaRepository<UserInRole, Long> {
    List<UserInRole> findByUser(UserEntity user);
//    Optional<UserInRole> findByUser(Long userId);
    Optional<UserInRole> findById(Long userId);
    Optional<UserInRole> findByUserId(Long userId);
}
