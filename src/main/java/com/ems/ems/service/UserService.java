package com.ems.ems.service;


import com.ems.ems.dto.ChangePasswordDTO;
import com.ems.ems.exception.CustomException;
import com.ems.ems.model.User;
import com.ems.ems.repository.UserRepository;
import com.ems.ems.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(User user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public void edit(User user){
    userRepository.save(user);
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }

  public String changePassword(ChangePasswordDTO passwordDTO) {
    if (userRepository.findById(passwordDTO.getId()).isPresent()) {
      User user = userRepository.findById(passwordDTO.getId()).get();
      System.out.println(user.getPassword());
      System.out.println(passwordEncoder.encode(passwordDTO.getOldPassword()));
      System.out.println(passwordEncoder.matches(passwordDTO.getOldPassword(),user.getPassword()));
      if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
        if (passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())) {
          user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
          userRepository.save(user);
          return "Password Changed";
        } else {
          return "Password and confirm password don't match";
        }
      } else {
        return "Old password you entered is incorrect";
      }
    }
    return "User not present";
  }

}
