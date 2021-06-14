package com.ems.ems.controller;

import com.ems.ems.dto.*;
import com.ems.ems.model.User;
import com.ems.ems.service.UserService;
import io.swagger.annotations.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/signin")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public AuthResponseDTO login(//
                               @ApiParam("Username") @RequestParam String username, //
                               @ApiParam("Password") @RequestParam String password) {
    String token=userService.signin(username, password);
    String refresh_token=userService.refresh(username);
    User user=userService.search(username);
    return new AuthResponseDTO(token,refresh_token,"Bearer",user.getRoles().get(0));
  }

  @GetMapping("/test")
  public String test(){
    return "Hello";
  }

  @PostMapping("/signup")
  @ApiOperation(value = "${UserController.signup}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 422, message = "Username is already in use")})
  public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
    return userService.signup(modelMapper.map(user, User.class));
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.delete}", authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public String delete(@ApiParam("Username") @PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FINANCE') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_HR')")
  @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }


  @PostMapping("/change-password")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FINANCE') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_HR')")
  public ResponseEntity<?> changePassword(HttpServletRequest req, @RequestBody ChangePasswordDTO passwordDTO){
    String message=userService.changePassword(passwordDTO);
    if(message.equals("Password Changed"))
            return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList("Password Changed Successfully")));
    else if(message.equals("Password and confirm password don't match"))
      return ResponseEntity.ok(new GenericResponse(200,"FAILED", Collections.singletonList("Password and Confirm password don't match")));
    else if(message.equals("Old password you entered is incorrect"))
      return ResponseEntity.ok(new GenericResponse(200,"FAILED", Collections.singletonList("Old password you entered is incorrect")));
    else
      return ResponseEntity.ok(new GenericResponse(200,"FAILED", Collections.singletonList("User not present")));
  }


}
