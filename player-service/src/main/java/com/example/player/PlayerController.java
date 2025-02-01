package com.example.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @GetMapping("/test")
    public ResponseEntity<?> getFollowers() {
        return ResponseEntity.ok("ok");
    }
}


