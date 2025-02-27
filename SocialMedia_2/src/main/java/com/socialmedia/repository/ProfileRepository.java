package com.socialmedia.repository;

import com.socialmedia.models.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<SocialProfile, Long> {
}
