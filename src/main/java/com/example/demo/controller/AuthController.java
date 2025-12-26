package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserService service;
  private final JwtUtil jwt;

  public AuthController(UserService service, JwtUtil jwt){
    this.service=service;
    this.jwt=jwt;
  }

  @PostMapping("/register")
  public User register(@RequestBody User u){ return service.create(u); }

  @PostMapping("/login")
  public Map<String,String> login(@RequestBody Map<String,String> body){
    User u=service.validate(body.get("username"), body.get("password"));
    if(u==null) return Map.of("error","Invalid credentials");
    return Map.of("token", jwt.generate(u.getUsername()));
  }
}
