package com.socialmedia.repository;

import com.socialmedia.models.SocialGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<SocialGroup, Long> {
}
