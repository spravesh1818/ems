package com.ems.ems.service;

import com.ems.ems.model.User;
import com.ems.ems.repository.UserRepository;
import com.ems.ems.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUserName(username);

        user.orElseThrow(()->new UsernameNotFoundException("Not found:"+username));
        return user.map(CustomUserDetails::new).get();
    }
}
