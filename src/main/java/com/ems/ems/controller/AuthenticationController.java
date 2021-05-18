package com.ems.ems.controller;

import com.ems.ems.config.AuthenticationRequest;
import com.ems.ems.config.AuthenticationResponse;
import com.ems.ems.config.GenericResponse;
import com.ems.ems.dto.UserDto;
import com.ems.ems.service.CustomUserDetailService;
import com.ems.ems.util.AuthorizationUtil;
import com.ems.ems.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(Authentication authentication){
        if(AuthorizationUtil.getInstance().checkEmployeeRole(authentication)){
            return ResponseEntity.ok(new GenericResponse(200,"SUCCESS","Hello"));
        }else{
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password",e);
        }

        final UserDetails userDetails=userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        UserDto userDto=new UserDto(userDetails.getUsername(),userDetails.getAuthorities().toArray()[0].toString());
        final String jwt=jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt,userDto));
    }

}
