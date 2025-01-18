package com.example.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.question.model.Follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollowerId(Long followerId);

    List<Follow> findByFollowingId(Long followingId);

    Optional<Follow> findByFollowingIdAndFollowerId(Long followingId, Long followerId);
}
