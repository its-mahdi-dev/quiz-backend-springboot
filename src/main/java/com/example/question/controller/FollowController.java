package com.example.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.question.dto.Response;
import com.example.question.model.Follow;
import com.example.question.model.User;
import com.example.question.service.FollowService;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/followers")
    public ResponseEntity<List<User>> getFollowers(@RequestParam(required = false) Long userId, @RequestAttribute("userId") Long currentUserId) {
        Long idToFetch = (userId != null) ? userId : currentUserId;
        return ResponseEntity.ok(followService.getFollowers(idToFetch));
    }

    @GetMapping("/followings")
    public ResponseEntity<List<User>> getFollowings(@RequestParam(required = false) Long userId, @RequestAttribute("userId") Long currentUserId) {
        Long idToFetch = (userId != null) ? userId : currentUserId;
        return ResponseEntity.ok(followService.getFollowings(idToFetch));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Response> follow(@PathVariable("id") Long id, @RequestAttribute("userId") Long userId) {
        followService.followUser(userId, id);
        return ResponseEntity.ok(new Response("انحام شد", 201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> unfollow(@PathVariable("id") Long id, @RequestAttribute("userId") Long userId) {
        followService.unFollow(userId, id);
        return ResponseEntity.ok(new Response("انجام شد", 201));
    }
}
