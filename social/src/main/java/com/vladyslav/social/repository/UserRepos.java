package com.vladyslav.social.repository;

import com.vladyslav.social.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
