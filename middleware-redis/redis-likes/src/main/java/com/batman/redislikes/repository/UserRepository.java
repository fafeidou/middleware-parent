package com.batman.redislikes.repository;

import com.batman.redislikes.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
}
