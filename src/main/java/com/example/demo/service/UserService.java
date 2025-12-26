package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
  private final UserRepository repo;
  private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

  public UserService(UserRepository repo){ this.repo=repo; }

  public List<User> findAll(){ return repo.findAll(); }
  public User findById(Long id){ return repo.findById(id).orElse(null); }

  public User create(User u){
    u.setPassword(encoder.encode(u.getPassword()));
    return repo.save(u);
  }

  public User update(Long id, User u){
    User x=repo.findById(id).orElse(null);
    if(x==null) return null;
    x.setEmail(u.getEmail());
    if(u.getPassword()!=null) x.setPassword(encoder.encode(u.getPassword()));
    return repo.save(x);
  }

  public void delete(Long id){ repo.deleteById(id); }

  public User validate(String username,String rawPassword){
    return repo.findByUsername(username)
      .filter(u->encoder.matches(rawPassword,u.getPassword()))
      .orElse(null);
  }
}
