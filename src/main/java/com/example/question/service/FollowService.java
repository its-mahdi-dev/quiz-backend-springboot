package com.example.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.question.model.Follow;
import com.example.question.model.User;
import com.example.question.repository.FollowRepository;
import com.example.question.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public List<User> getFollowers(Long userId) {
        List<Long> followerIds = followRepository.findByFollowerId(userId)
                .stream()
                .map(Follow::getFollowingId)
                .collect(Collectors.toList());
                

        return userRepository.findByIdIn(followerIds)
                .stream()
                .map(user -> user.maskData(new String[]{"firstName" , "lastName" , "score" , "id" , "questions"}))
                .collect(Collectors.toList());
       
    }

    public List<User> getFollowings(Long userId) {
        List<Long> followerIds = followRepository.findByFollowingId(userId)
                .stream()
                .map(Follow::getFollowerId)
                .collect(Collectors.toList());
                
        return userRepository.findByIdIn(followerIds)
                .stream()
                .map(user -> user.maskData(new String[]{"firstName" , "lastName" , "score" , "id"}))
                .collect(Collectors.toList());
    }

    public void followUser(Long followingId, Long followerId) {
        Follow follow = new Follow();
        follow.setFollowingId(followingId);
        follow.setFollowerId(followerId);
        followRepository.save(follow);
    }

    @Transactional
    public void unFollow(Long userId, Long followerId) {
        Follow follow = followRepository.findByFollowingIdAndFollowerId(userId, followerId)
                .orElseThrow(() -> new RuntimeException("Follow relationship not found"));
        followRepository.delete(follow);
    }
}
