package com.cricclub.repository;

import com.cricclub.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    UserProfile findUserProfileByEmail(String email);
}
