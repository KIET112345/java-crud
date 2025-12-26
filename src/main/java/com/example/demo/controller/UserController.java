package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService service;

  public UserController(UserService service){ this.service=service; }

  @GetMapping public List<User> all(){ return service.findAll(); }
  @GetMapping("/{id}") public User one(@PathVariable Long id){ return service.findById(id); }
  @PostMapping public User create(@RequestBody User u){ return service.create(u); }
  @PutMapping("/{id}") public User update(@PathVariable Long id, @RequestBody User u){ return service.update(id,u); }
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
